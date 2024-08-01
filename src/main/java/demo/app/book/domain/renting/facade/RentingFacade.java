package demo.app.book.domain.renting.facade;

import demo.app.book.domain.book.exception.NonExistingBookException;
import demo.app.book.domain.borrower.exception.NonExistingBorrowerException;
import demo.app.book.domain.renting.exception.BookIsOccupiedException;
import demo.app.book.domain.renting.exception.NonExistBookRentingException;
import demo.app.book.domain.renting.mediator.BookMediator;
import demo.app.book.domain.renting.mediator.BorrowerMediator;
import demo.app.book.domain.renting.model.RentingRequestDTO;
import demo.app.book.domain.renting.service.RentingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RentingFacade {
  @Inject
  RentingService rentingService;

  @Inject
  BookMediator bookMediator;

  @Inject
  BorrowerMediator borrowerMediator;

  public void rentBookFromLibary(RentingRequestDTO rentingRequestDTO) throws BookIsOccupiedException, NonExistingBookException, NonExistingBorrowerException {
    verifyRentingRequest(rentingRequestDTO);
    rentingService.verifyIfTheRequestedIsOccupiedAtTheMoment(rentingRequestDTO);
    rentingService.saveForNewRenting(rentingRequestDTO);
  }

  public void returnBookToLibrary(Long rentId) throws NonExistBookRentingException {
    if(!rentingService.anyBookRentingForThisUniqueId(rentId))
      throw new NonExistBookRentingException();
    rentingService.saveForReturningBook(rentId);
  }

  private void verifyRentingRequest(RentingRequestDTO rentingRequestDTO) throws NonExistingBookException, NonExistingBorrowerException {
    if(!bookMediator.IsExist(rentingRequestDTO.getBookId()))
      throw new NonExistingBookException();
    else if (!borrowerMediator.IsExist(rentingRequestDTO.getBorrowerId())) {
      throw new NonExistingBorrowerException();
    }
  }
}
