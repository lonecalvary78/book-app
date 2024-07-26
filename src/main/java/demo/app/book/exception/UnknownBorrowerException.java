package demo.app.book.exception;

public class UnknownBorrowerException extends Exception {
  public UnknownBorrowerException() {
    super("Unable to find the borrer profile for the given reference since it is not exist");
  }
}
