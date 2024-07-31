package demo.app.book.common.test.cleanup.util;

import demo.app.book.common.test.cleanup.repository.TestDataCleanRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TestDataCleanupUtil {
  @Inject
  TestDataCleanRepository testDataCleanRepository;

  public void cleanupTestDataForBook() {
    testDataCleanRepository.cleanTestDataForBook();
  }

  public void cleanupTestDataForBorrower() {
    testDataCleanRepository.cleanTestDataForBorrower();
  }

  public void cleanupTestDataForRenting() {
    testDataCleanRepository.cleanTestDataForBorrower();
  }
}
