package demo.app.book.domain.borrower.service;

import demo.app.book.domain.borrower.repository.BorrowerRepository;
import demo.app.book.domain.borrower.exception.DuplicateBorrowerEntryException;
import demo.app.book.util.mapper.BorrowerMapper;
import demo.app.book.domain.borrower.model.BorrowerDTO;
import demo.app.book.util.mapper.BorrowerMapperImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BorrowerService {
  @Inject
  private BorrowerRepository borrowerRepository;
  private final BorrowerMapper borrowerMapper = new BorrowerMapperImpl();

  public BorrowerDTO registerNew(BorrowerDTO borrowerDTO) throws DuplicateBorrowerEntryException {
    var borrower = borrowerMapper.fromDTO(borrowerDTO);
    return borrowerMapper.toEntity(borrowerRepository.registerNew(borrower));
  }
}
