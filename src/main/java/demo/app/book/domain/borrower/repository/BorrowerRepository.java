package demo.app.book.domain.borrower.repository;

import demo.app.book.domain.borrower.entry.Borrower;
import demo.app.book.domain.borrower.exception.DuplicateBorrowerEntryException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BorrowerRepository {
  @Inject
  private EntityManager entityManager;

  @Transactional
  public Borrower registerNew(Borrower borrower) throws DuplicateBorrowerEntryException {
    if(anyRegisteredBorrowerForThisProfile(borrower))
      throw new DuplicateBorrowerEntryException();
    entityManager.persist(borrower);
    return borrower;
  }

  private boolean anyRegisteredBorrowerForThisProfile(Borrower borrower) {
    return ((Long)entityManager.createNativeQuery("select count(1) from borrower b where lower(b.firstname)=:firstName and lower(b.lastname)=:lastName")
            .setParameter("firstName",borrower.getFirstName().toLowerCase())
            .setParameter("lastName",borrower.getLastName().toLowerCase())
            .getSingleResult()).longValue()>0;
  }
}
