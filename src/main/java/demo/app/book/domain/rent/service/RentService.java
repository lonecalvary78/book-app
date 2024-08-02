package demo.app.book.domain.rent.service;

import demo.app.book.domain.rent.exception.BookAlreadyReturnedException;
import demo.app.book.domain.rent.exception.BookIsOccupiedException;
import demo.app.book.domain.rent.model.RentRequestDTO;
import demo.app.book.domain.rent.repository.RentRepository;
import demo.app.book.util.mapper.RentMapper;
import demo.app.book.util.mapper.RentMapperImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@ApplicationScoped
public class RentService {
  @Inject
  private RentRepository rentRepository;

  private final RentMapper rentMapper = new RentMapperImpl();
  private static final String STATUS_RENT = "RENT";
  private static final String STATUS_RETURN = "RETURN";

  public void saveForNewRenting(RentRequestDTO rentRequestDTO) throws BookIsOccupiedException {
      var rent = rentMapper.fromDTO(rentRequestDTO);
      rent.setStatus(STATUS_RENT);
      rentRepository.save(rent);
  }

  @Transactional
  public void saveForReturningBook(Long rentingId) throws BookAlreadyReturnedException {
    var rent = rentRepository.findRentingId(rentingId);
    if(rent.getStatus().equals(STATUS_RETURN))
      throw new BookAlreadyReturnedException();
    rent.setStatus(STATUS_RETURN);
    rent.setReturnedAt(LocalDateTime.now(ZoneId.systemDefault()));
    rentRepository.save(rent);
  }

  public boolean anyBookRentingForThisUniqueId(Long rentId) {
    return Optional.ofNullable(rentRepository.findRentingId(rentId)).isPresent();
  }

  public void verifyIfTheRequestedIsOccupiedAtTheMoment(RentRequestDTO rentRequestDTO) throws BookIsOccupiedException {
     if (rentRepository.anyBookIsOccupiedBySameBorrowerAtTheMoment(rentRequestDTO.getBookId(), rentRequestDTO.getBorrowerId()))
       throw new BookIsOccupiedException(true);
     else if(rentRepository.anyBookIsOccupiedByOtherBorrowerAtTheMoment(rentRequestDTO.getBookId(), rentRequestDTO.getBorrowerId()))
       throw new BookIsOccupiedException(false);
  }
}
