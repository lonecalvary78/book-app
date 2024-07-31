package demo.app.book.domain.borrower.exception;

public class DuplicateBorrowerEntryException extends Exception {
  public DuplicateBorrowerEntryException() {
    super("Unable to the new borrower profile due to the duplicate entry violation.");
  }
}
