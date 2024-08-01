package demo.app.book.domain.borrower.service;

import demo.app.book.domain.borrower.entity.Borrower;
import demo.app.book.domain.borrower.repository.BorrowerRepository;
import demo.app.book.domain.borrower.exception.DuplicateBorrowerEntryException;
import demo.app.book.util.mapper.BorrowerMapper;
import demo.app.book.domain.borrower.model.BorrowerDTO;
import demo.app.book.util.mapper.BorrowerMapperImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class BorrowerService {
  @Inject
  private BorrowerRepository borrowerRepository;
  private final BorrowerMapper borrowerMapper = new BorrowerMapperImpl();

  public Optional<BorrowerDTO> findBorrowerById(Long borrowerId) {
    return Optional.ofNullable(borrowerRepository.findBorrowerById(borrowerId)).map(borrower->borrowerMapper.fromEntity(borrower));
  }

  public BorrowerDTO registerNew(BorrowerDTO borrowerDTO) throws DuplicateBorrowerEntryException {
    var borrower = borrowerMapper.fromDTO(borrowerDTO);
    if(anyRegisteredBorrowerForThisProfile(borrower))
      throw new DuplicateBorrowerEntryException();
    else
      return borrowerMapper.fromEntity(borrowerRepository.save(borrower));
  }

  private boolean anyRegisteredBorrowerForThisProfile(Borrower borrower) {
    return borrowerRepository.countRegisteredBorrowerForThisProfile(borrower).longValue()>0;
  }
}
