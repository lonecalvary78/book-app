package demo.app.book.common.test.cleanup.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TestDataCleanRepository {
  @Inject
  EntityManager entityManager;

  @Transactional
  public void cleanTestDataForBook() {
    entityManager.createNativeQuery("delete from book").executeUpdate();
  }

  @Transactional
  public void cleanTestDataForBorrower() {
    entityManager.createNativeQuery("delete from borrower").executeUpdate();
  }

  @Transactional
  public void cleanTestDataForRenting() {
    entityManager.createNativeQuery("delete from renting").executeUpdate();
  }
}
