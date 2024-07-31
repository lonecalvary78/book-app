package demo.app.book.domain.borrower.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ReadOnlyBorrowerRepository {
  @Inject
  private EntityManager entityManager;

  public boolean anyRegisteredBorrowerFor(Long borrowerId) {
    return entityManager.createQuery("select count(borrower) from Borrower borrower where borrower.id=:borrowerId",Long.class)
            .setParameter("borrowerId",borrowerId)
            .getSingleResult().longValue()>0;
  }
}
