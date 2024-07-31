package demo.app.book.domain.renting.exception;

public class BookIsRentedException extends Exception {
  private static final String ERR_MESSAGE_WITH_SAME_BORROWER="The book is rented by you at the moment";
  private static final String ERR_MESSAGE_WITH_OTHER_BORROWER="The book is rented by other borrower at the moment";

  public BookIsRentedException(boolean withSameBorrower) {
    super(constructErrorMessage(withSameBorrower));
  }
  private static String constructErrorMessage(boolean withSameBorrower){
    return (withSameBorrower)?ERR_MESSAGE_WITH_SAME_BORROWER:ERR_MESSAGE_WITH_OTHER_BORROWER;
  }
}
