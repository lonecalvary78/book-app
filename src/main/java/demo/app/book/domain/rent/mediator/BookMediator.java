package demo.app.book.domain.rent.mediator;

import jakarta.enterprise.context.ApplicationScoped;
import demo.app.book.domain.book.service.BookService;
import jakarta.inject.Inject;


@ApplicationScoped
public class BookMediator {
  @Inject
  private BookService bookService;

  public boolean isExist(Long bookId) {
    return bookService.findBookById(bookId).isPresent();
  }
}
