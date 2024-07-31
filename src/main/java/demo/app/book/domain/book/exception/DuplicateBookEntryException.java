package demo.app.book.domain.book.exception;

public class DuplicateBookEntryException extends Exception {
  public DuplicateBookEntryException(String isbn) {
    super("Unable to proceed the new book entry due to the duplicate entry violation[ISBN: %s]".formatted(isbn));
  }
}
