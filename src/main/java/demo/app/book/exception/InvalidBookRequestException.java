package demo.app.book.exception;

public class InvalidBookRequestException extends Exception {
  public InvalidBookRequestException() {
    super("Unable to perform this operation for the given book reference since it is not exist");
  }
}
