package demo.app.book.domain.borrower.repository;

import demo.app.book.domain.borrower.entity.Borrower;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BorrowerRepository {
  @Inject
  private EntityManager entityManager;

  @Transactional
  public Borrower findBorrowerById(Long borrowerId) {
    return entityManager.find(Borrower.class,borrowerId);
  }

  @Transactional
  public Borrower save(Borrower borrower) {
    entityManager.persist(borrower);
    return borrower;
  }

  public Long countRegisteredBorrowerForThisProfile(Borrower borrower) {
    return entityManager.createQuery("select count(borrower) from Borrower borrower where lower(borrower.firstName)=:firstName and lower(borrower.lastName)=:lastName", Long.class)
            .setParameter("firstName",borrower.getFirstName().toLowerCase())
            .setParameter("lastName",borrower.getLastName().toLowerCase())
            .getSingleResult();
  }
}
