package demo.app.book.domain.rent.visitor;

import jakarta.enterprise.context.ApplicationScoped;
import demo.app.book.domain.book.service.BookService;
import jakarta.inject.Inject;


@ApplicationScoped
public class BookVisitor {
  @Inject
  private BookService bookService;

  public boolean isExist(Long bookId) {
    return bookService.findBookById(bookId).isPresent();
  }
}
