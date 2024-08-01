package demo.app.book.domain.book.service;

import demo.app.book.domain.book.entity.Book;
import demo.app.book.domain.book.repository.BookRepository;
import demo.app.book.domain.book.exception.DuplicateBookEntryException;
import demo.app.book.util.mapper.BookMapper;
import demo.app.book.util.mapper.BookMapperImpl;
import demo.app.book.domain.book.model.BookDTO;
import demo.app.book.util.pagination.model.PaginatedInquiryResult;
import demo.app.book.util.pagination.PaginationUtil;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;


@ApplicationScoped
public class BookService {
  @Inject
  private BookRepository bookRepository;

  @Inject
  private PaginationUtil paginationUtil;

  private final BookMapper bookMapper = new BookMapperImpl();

  @CacheResult(cacheName = "allBooks")
  public PaginatedInquiryResult retrieveAllBooks(int currentPage) {
    var inquiryResults = bookRepository.retrieveAllBooks().stream().map(book->bookMapper.fromEntity(book)).toList();
    return paginationUtil.newPaginatedInquiryResult(currentPage, inquiryResults);
  }

  public Optional<BookDTO> findBookById(Long bookId) {
    return Optional.ofNullable(bookRepository.findBookById(bookId)).map(book->bookMapper.fromEntity(book));
  }

  public BookDTO newEntry(BookDTO bookDTO) throws DuplicateBookEntryException {
    var book = bookMapper.fromDTO(bookDTO);
    if(isAnyRegisteredBookUseThisRegistrationNumber(book))
      throw new DuplicateBookEntryException(book.getIsbn());
    else
      return bookMapper.fromEntity(bookRepository.save(book));
  }

  private boolean isAnyRegisteredBookUseThisRegistrationNumber(Book book) {
    return bookRepository.countRegisteredBooksForThisReference(book)>0;
  }
}
