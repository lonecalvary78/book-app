package demo.app.book.service;

import demo.app.book.domain.repository.BorrowerRepository;
import demo.app.book.exception.BorrowerDuplicateEntryException;
import demo.app.book.mapper.BorrowerMapper;
import demo.app.book.mapper.BorrowerMapperImpl;
import demo.app.book.model.BorrowerDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BorrowerService {
  @Inject
  private BorrowerRepository borrowerRepository;
  private final BorrowerMapper borrowerMapper = new BorrowerMapperImpl();

  public BorrowerDTO registerNew(BorrowerDTO borrowerDTO) throws BorrowerDuplicateEntryException {
    var borrower = borrowerMapper.fromDTO(borrowerDTO);
    return borrowerMapper.toEntity(borrowerRepository.registerNew(borrower));
  }
}
