package demo.app.book.domain.repository;

import demo.app.book.domain.entity.Borrower;
import demo.app.book.exception.BorrowerDuplicateEntryException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class BorrowerRepository {
  @Inject
  private EntityManager entityManager;

  public Borrower registerNew(Borrower borrower) throws BorrowerDuplicateEntryException {
    if(anyRegisteredBorrowerForThisProfile(borrower))
      throw new BorrowerDuplicateEntryException();
    entityManager.persist(borrower);
    return borrower;
  }

  private boolean anyRegisteredBorrowerForThisProfile(Borrower borrower) {
    return entityManager.createQuery("select count(borrower) from Borrower borrower where borrower.firstName=:firstName and borrower.lastName=:lastName",Long.class)
            .setParameter("firstName",borrower.getFirstName())
            .setParameter("lastName",borrower.getLastName()).getSingleResult().longValue()>0;
  }
}
