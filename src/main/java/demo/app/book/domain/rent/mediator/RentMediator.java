package demo.app.book.domain.rent.mediator;

import demo.app.book.domain.book.exception.NonExistingBookException;
import demo.app.book.domain.borrower.exception.NonExistingBorrowerException;
import demo.app.book.domain.rent.exception.BookAlreadyReturnedException;
import demo.app.book.domain.rent.exception.BookIsOccupiedException;
import demo.app.book.domain.rent.exception.NonExistBookRentingException;
import demo.app.book.domain.rent.visitor.BookVisitor;
import demo.app.book.domain.rent.visitor.BorrowerVisitor;
import demo.app.book.domain.rent.model.RentRequestDTO;
import demo.app.book.domain.rent.service.RentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RentMediator {
  @Inject
  RentService rentService;

  @Inject
  BookVisitor bookVisitor;

  @Inject
  BorrowerVisitor borrowerVisitor;

  public void rentBookFromLibrary(RentRequestDTO rentRequestDTO) throws BookIsOccupiedException, NonExistingBookException, NonExistingBorrowerException {
    verifyRentingRequest(rentRequestDTO);
    rentService.verifyIfTheRequestedIsOccupiedAtTheMoment(rentRequestDTO);
    rentService.saveForNewRenting(rentRequestDTO);
  }

  public void returnBookToLibrary(Long rentId) throws NonExistBookRentingException, BookAlreadyReturnedException {
    if(!rentService.anyBookRentingForThisUniqueId(rentId))
      throw new NonExistBookRentingException();
    rentService.saveForReturningBook(rentId);
  }


  private void verifyRentingRequest(RentRequestDTO rentRequestDTO) throws NonExistingBookException, NonExistingBorrowerException {
    if(!bookVisitor.isExist(rentRequestDTO.getBookId()))
      throw new NonExistingBookException();
    else if (!borrowerVisitor.isExist(rentRequestDTO.getBorrowerId())) {
      throw new NonExistingBorrowerException();
    }
  }
}
