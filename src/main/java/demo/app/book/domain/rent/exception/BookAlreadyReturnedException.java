package demo.app.book.domain.rent.exception;

public class BookAlreadyReturnedException extends Exception {
  public BookAlreadyReturnedException() {
    super("Unable to proceed this request since the book already returned");
  }
}
