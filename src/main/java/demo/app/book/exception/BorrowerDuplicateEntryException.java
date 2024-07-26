package demo.app.book.exception;

public class BorrowerDuplicateEntryException extends Exception {
  public BorrowerDuplicateEntryException() {
    super("Unable to the new borrower profile due to the duplicate entry violation.");
  }
}
