package demo.app.book.service;

import demo.app.book.domain.entity.Book;
import demo.app.book.domain.entity.BookRenting;
import demo.app.book.domain.repository.BookRentingRepository;
import demo.app.book.domain.repository.BookRepository;
import demo.app.book.domain.repository.ReadOnlyBorrowerRepository;
import demo.app.book.exception.BookDuplicateEntryException;
import demo.app.book.exception.BookIsRentedException;
import demo.app.book.exception.InvalidBookRequestException;
import demo.app.book.exception.UnknownBorrowerException;
import demo.app.book.mapper.BookMapper;
import demo.app.book.mapper.BookMapperImpl;
import demo.app.book.model.BookDTO;
import demo.app.book.model.BookRentRequestDTO;
import demo.app.book.model.BookReturnRequestDTO;
import demo.app.book.model.pagination.PaginatedInquiryResult;
import demo.app.book.util.pagination.PaginationUtil;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class BookService {
  @Inject
  private BookRepository bookRepository;

  @Inject
  private BookRentingRepository bookRentingRepository;

  @Inject
  private ReadOnlyBorrowerRepository readOnlyBorrowerRepository;

  @Inject
  private PaginationUtil paginationUtil;

  private final BookMapper bookMapper = new BookMapperImpl();

  @CacheResult(cacheName = "allBooks")
  public PaginatedInquiryResult retrieveAllBooks(int currentPage) {
    var inquiryResults = bookRepository.retrieveAllBooks().stream().map(book->bookMapper.fromEntity(book)).toList();
    return paginationUtil.newPaginatedInquiryResult(currentPage, inquiryResults);
  }

  public BookDTO newEntry(BookDTO bookDTO) throws BookDuplicateEntryException {
    var book = bookMapper.fromDTO(bookDTO);
    return bookMapper.fromEntity(bookRepository.newEntry(book));
  }

  public void rentBook(BookRentRequestDTO bookRentRequestDTO) throws UnknownBorrowerException, BookIsRentedException {
    var bookRenting = toEntity(bookRentRequestDTO);
    if(!readOnlyBorrowerRepository.anyRegisteredBorrowerFor(bookRentRequestDTO.borrowerId()))
      throw new UnknownBorrowerException();
    else
      bookRentingRepository.saveForNewRenting(bookRenting);
  }

  public void returnBook(BookReturnRequestDTO bookReturnRequestDTO) throws InvalidBookRequestException {
    bookRentingRepository.saveForReturningBook(bookReturnRequestDTO.bookId(), bookReturnRequestDTO.borrowerId());
  }

  private BookRenting toEntity(BookRentRequestDTO bookRentRequestDTO) {
    var bookRenting = new BookRenting();
    bookRenting.setBookId(bookRentRequestDTO.bookId());
    bookRenting.setBorrowerId(bookRentRequestDTO.borrowerId());
    bookRenting.setFromDate(bookRentRequestDTO.fromDate());
    bookRenting.setToDate(bookRentRequestDTO.toDate());
    return bookRenting;
  }
}
