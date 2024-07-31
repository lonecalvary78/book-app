package demo.app.book.domain.renting.service;

import demo.app.book.domain.renting.exception.BookIsRentedException;
import demo.app.book.domain.renting.exception.InvalidBookRentingRequestException;
import demo.app.book.domain.renting.model.RentingRequestDTO;
import demo.app.book.domain.renting.model.ReturningRequestDTO;
import demo.app.book.domain.renting.repository.RentingRepository;
import demo.app.book.util.mapper.RentingMapper;
import demo.app.book.util.mapper.RentingMapperImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RentingService {
  @Inject
  private RentingRepository rentingRepository;

  private final RentingMapper rentingMapper = new RentingMapperImpl();

  public void saveForNewRenting(RentingRequestDTO rentingRequestDTO) throws BookIsRentedException {
      var renting = rentingMapper.fromDTO(rentingRequestDTO);
      rentingRepository.saveForNewRenting(renting);
  }

  public void saveForReturningBook(ReturningRequestDTO returningRequestDTO) throws InvalidBookRentingRequestException {
     rentingRepository.saveForReturningBook(returningRequestDTO.getBookId(), returningRequestDTO.getBorrowerId());
  }
}
