package demo.app.book.domain.book.service;

import demo.app.book.domain.renting.entity.Renting;
import demo.app.book.domain.renting.repository.RentingRepository;
import demo.app.book.domain.book.repository.BookRepository;
import demo.app.book.domain.borrower.repository.ReadOnlyBorrowerRepository;
import demo.app.book.domain.book.exception.DuplicateBookEntryException;
import demo.app.book.domain.renting.exception.BookIsRentedException;
import demo.app.book.domain.renting.exception.InvalidBookRentingRequestException;
import demo.app.book.domain.renting.exception.UnknownBorrowerException;
import demo.app.book.util.mapper.BookMapper;
import demo.app.book.util.mapper.BookMapperImpl;
import demo.app.book.domain.book.model.BookDTO;
import demo.app.book.domain.renting.model.RentingRequestDTO;
import demo.app.book.domain.renting.model.ReturningRequestDTO;
import demo.app.book.util.pagination.model.PaginatedInquiryResult;
import demo.app.book.util.pagination.PaginationUtil;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class BookService {
  @Inject
  private BookRepository bookRepository;

  @Inject
  private RentingRepository rentingRepository;

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

  public BookDTO newEntry(BookDTO bookDTO) throws DuplicateBookEntryException {
    var book = bookMapper.fromDTO(bookDTO);
    return bookMapper.fromEntity(bookRepository.newEntry(book));
  }

  public void rentBook(RentingRequestDTO rentingRequestDTO) throws UnknownBorrowerException, BookIsRentedException {
    var bookRenting = toEntity(rentingRequestDTO);
    if(!readOnlyBorrowerRepository.anyRegisteredBorrowerFor(rentingRequestDTO.getBorrowerId()))
      throw new UnknownBorrowerException();
    else
      rentingRepository.saveForNewRenting(bookRenting);
  }

  public void returnBook(ReturningRequestDTO returningRequestDTO) throws InvalidBookRentingRequestException {
    rentingRepository.saveForReturningBook(returningRequestDTO.getBookId(), returningRequestDTO.getBorrowerId());
  }

  private Renting toEntity(RentingRequestDTO rentingRequestDTO) {
    var bookRenting = new Renting();
    bookRenting.setBookId(rentingRequestDTO.getBookId());
    bookRenting.setBorrowerId(rentingRequestDTO.getBorrowerId());
    bookRenting.setFromDate(rentingRequestDTO.getFromDate());
    bookRenting.setToDate(rentingRequestDTO.getFromDate());
    return bookRenting;
  }
}
