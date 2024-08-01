package demo.app.book.domain.book.exception;

public class NonExistingBookException extends Exception {
  public NonExistingBookException() {
    super("There is no book with the given unique ID since the book is not recorded yet");
  }
}
