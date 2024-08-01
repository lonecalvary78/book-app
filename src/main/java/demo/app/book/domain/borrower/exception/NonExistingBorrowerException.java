package demo.app.book.domain.borrower.exception;

import demo.app.book.domain.book.exception.NonExistingBookException;

public class NonExistingBorrowerException extends Exception {
  public NonExistingBorrowerException() {
    super("There is no borrower profile that match with the given unique ID since the borrower is not registered yet");
  }
}
