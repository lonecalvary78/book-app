package demo.app.book.domain.renting.exception;

public class BookIsOccupiedException extends Exception {
  private static final String ERR_MESSAGE_WITH_SAME_BORROWER="The book renting that you requested is rejected since you are still rent the book that you requested for";
  private static final String ERR_MESSAGE_WITH_OTHER_BORROWER="The book is occupied by other borrower at the moment";

  public BookIsOccupiedException(boolean withSameBorrower) {
    super(constructErrorMessage(withSameBorrower));
  }
  private static String constructErrorMessage(boolean withSameBorrower){
    return (withSameBorrower)?ERR_MESSAGE_WITH_SAME_BORROWER:ERR_MESSAGE_WITH_OTHER_BORROWER;
  }
}
