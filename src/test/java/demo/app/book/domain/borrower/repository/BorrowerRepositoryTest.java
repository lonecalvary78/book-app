package demo.app.book.domain.borrower.repository;

import demo.app.book.common.test.cleanup.util.TestDataCleanupUtil;
import demo.app.book.common.test.GenericApplicationTest;
import demo.app.book.common.test.model.TestCase;
import demo.app.book.domain.borrower.entity.Borrower;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BorrowerRepositoryTest implements GenericApplicationTest<Borrower> {
  @Inject
  BorrowerRepository borrowerRepository;

  @Inject
  TestDataCleanupUtil testDataCleanupUtil;

  @ParameterizedTest
  @DisplayName("Register a new borrower")
  @MethodSource("constructTestCases")
  void registerNew(TestCase<Borrower> testCase) {
    Assertions.assertDoesNotThrow(()->borrowerRepository.save(testCase.getInput()));
  }

  @AfterAll
  void cleanUp() {
    testDataCleanupUtil.cleanupTestDataForBorrower();
  }

  Borrower createNewBorrower(String firstName, String lastName) {
    var borrower = new Borrower();
    borrower.setFirstName(firstName);
    borrower.setLastName(lastName);
    return borrower;
  }

  @Override
  public Stream<TestCase<Borrower>> constructTestCases() {
    return Stream.of(
            newTestCase(createNewBorrower("John","Doe"),false,null)
            );
  }
}
