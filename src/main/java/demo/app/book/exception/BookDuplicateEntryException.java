package demo.app.book.exception;

public class BookDuplicateEntryException extends Exception {
  public BookDuplicateEntryException(String isbn) {
    super("Unable to proceed the new book entry due to the duplicate entry violation[ISBN: %s]".formatted(isbn));
  }
}
