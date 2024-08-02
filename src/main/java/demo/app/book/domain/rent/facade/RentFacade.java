package demo.app.book.domain.rent.facade;

import demo.app.book.domain.book.exception.NonExistingBookException;
import demo.app.book.domain.borrower.exception.NonExistingBorrowerException;
import demo.app.book.domain.rent.exception.BookAlreadyReturnedException;
import demo.app.book.domain.rent.exception.BookIsOccupiedException;
import demo.app.book.domain.rent.exception.NonExistBookRentingException;
import demo.app.book.domain.rent.mediator.BookMediator;
import demo.app.book.domain.rent.mediator.BorrowerMediator;
import demo.app.book.domain.rent.model.RentRequestDTO;
import demo.app.book.domain.rent.service.RentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RentFacade {
  @Inject
  RentService rentService;

  @Inject
  BookMediator bookMediator;

  @Inject
  BorrowerMediator borrowerMediator;

  public void rentBookFromLibary(RentRequestDTO rentRequestDTO) throws BookIsOccupiedException, NonExistingBookException, NonExistingBorrowerException {
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
    if(!bookMediator.IsExist(rentRequestDTO.getBookId()))
      throw new NonExistingBookException();
    else if (!borrowerMediator.IsExist(rentRequestDTO.getBorrowerId())) {
      throw new NonExistingBorrowerException();
    }
  }
}
