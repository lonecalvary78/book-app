package demo.app.book.domain.rent.exception;

public class NonExistBookRentingException extends Exception {
  public NonExistBookRentingException() {
    super("Unable to proceed this book return since there is no book renting recorded before");
  }
}
