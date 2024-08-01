package demo.app.book.domain.renting.mediator;

import demo.app.book.domain.borrower.repository.BorrowerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class BorrowerMediator {
  @Inject
  private BorrowerRepository borrowerRepository;

  public boolean IsExist(Long borrowerId) {
    return Optional.ofNullable(borrowerRepository.findBorrowerById(borrowerId)).isPresent();
  }

}
