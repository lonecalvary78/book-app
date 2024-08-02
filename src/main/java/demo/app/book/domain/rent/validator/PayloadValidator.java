package demo.app.book.domain.rent.validator;

import demo.app.book.domain.rent.model.RentRequestDTO;

public class PayloadValidator {
  public static void validatePayload(RentRequestDTO payload) {
    if(isEmptyId(payload.getBookId()))
      throw new IllegalArgumentException("the unique ID for the requested book is not correct or empty");
    else if(isEmptyId(payload.getBorrowerId()))
      throw new IllegalArgumentException("The unique for the registered Borrower is not correct or empty");
    else if(isEmpty(payload.getFromDate()))
      throw new IllegalArgumentException("The start date to rent should not be empty");
    else if(isEmpty(payload.getToDate()))
      throw new IllegalArgumentException("The end date to rent should not be empty");
  }

  private static boolean isEmpty(Object fieldValue) {
    return fieldValue == null;
  }

  private static boolean isEmptyId(Long uniqueId) {
    return (uniqueId == null || (uniqueId != null && uniqueId.longValue()<=0));
  }
}
