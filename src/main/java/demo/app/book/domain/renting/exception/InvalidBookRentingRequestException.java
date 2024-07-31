package demo.app.book.domain.renting.exception;

public class InvalidBookRentingRequestException extends Exception {
  public InvalidBookRentingRequestException() {
    super("Unable to perform this operation for the given book reference since it is not exist");
  }
}
