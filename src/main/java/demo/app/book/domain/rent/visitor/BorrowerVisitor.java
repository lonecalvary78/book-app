package demo.app.book.domain.rent.visitor;

import demo.app.book.domain.borrower.repository.BorrowerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class BorrowerVisitor {
  @Inject
  private BorrowerRepository borrowerRepository;

  public boolean isExist(Long borrowerId) {
    return Optional.ofNullable(borrowerRepository.findBorrowerById(borrowerId)).isPresent();
  }

}
