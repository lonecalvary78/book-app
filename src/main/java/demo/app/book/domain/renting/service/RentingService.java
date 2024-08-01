package demo.app.book.domain.renting.service;

import demo.app.book.domain.renting.exception.BookIsOccupiedException;
import demo.app.book.domain.renting.model.RentingRequestDTO;
import demo.app.book.domain.renting.repository.RentingRepository;
import demo.app.book.util.mapper.RentingMapper;
import demo.app.book.util.mapper.RentingMapperImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;

@ApplicationScoped
public class RentingService {
  @Inject
  private RentingRepository rentingRepository;

  private final RentingMapper rentingMapper = new RentingMapperImpl();

  public void saveForNewRenting(RentingRequestDTO rentingRequestDTO) throws BookIsOccupiedException {
      var renting = rentingMapper.fromDTO(rentingRequestDTO);
      rentingRepository.save(renting);
  }

  public void saveForReturningBook(Long rentingId) {
    var renting = rentingRepository.findRentingId(rentingId);
    renting.setStatus("RETURN");
    renting.setReturnedAt(LocalDateTime.now(ZoneId.systemDefault()));
    rentingRepository.save(renting);
  }

  public boolean anyBookRentingForThisUniqueId(Long rentId) {
    return Optional.ofNullable(rentingRepository.findRentingId(rentId)).isPresent();
  }

  public void verifyIfTheRequestedIsOccupiedAtTheMoment(RentingRequestDTO rentingRequestDTO) throws BookIsOccupiedException {
     if (rentingRepository.anyBookIsOccupiedBySameBorrowerAtTheMoment(rentingRequestDTO.getBookId(), rentingRequestDTO.getBorrowerId()))
       throw new BookIsOccupiedException(true);
     else if(rentingRepository.anyBookIsOccupiedByOtherBorrowerAtTheMoment(rentingRequestDTO.getBookId(), rentingRequestDTO.getBorrowerId()))
       throw new BookIsOccupiedException(false);
  }
}
