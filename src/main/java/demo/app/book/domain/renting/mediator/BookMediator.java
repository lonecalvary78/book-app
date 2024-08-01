package demo.app.book.domain.renting.mediator;

import jakarta.enterprise.context.ApplicationScoped;
import demo.app.book.domain.book.service.BookService;
import jakarta.inject.Inject;


@ApplicationScoped
public class BookMediator {
  @Inject
  private BookService bookService;

  public boolean IsExist(Long bookId) {
    return bookService.findBookById(bookId).isPresent();
  }
}
