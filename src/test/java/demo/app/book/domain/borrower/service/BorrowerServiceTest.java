package demo.app.book.domain.borrower.service;

import demo.app.book.common.test.cleanup.util.TestDataCleanupUtil;
import demo.app.book.common.test.GenericApplicationTest;
import demo.app.book.common.test.model.TestCase;
import demo.app.book.domain.borrower.exception.DuplicateBorrowerEntryException;
import demo.app.book.domain.borrower.model.BorrowerDTO;
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
class BorrowerServiceTest implements GenericApplicationTest<BorrowerDTO>
{
  @Inject
  BorrowerService borrowerService;

  @Inject
  TestDataCleanupUtil testDataCleanupUtil;

  @ParameterizedTest
  @DisplayName("Register a new borrower")
  @MethodSource("constructTestCases")
  void registerNew(TestCase<BorrowerDTO> testCase) {
    if (!testCase.isThrowException()) {
      Assertions.assertDoesNotThrow(()->borrowerService.registerNew(testCase.getInput()));
    } else {
      if (testCase.getExceptionClass() != null)
        Assertions.assertThrows(testCase.getExceptionClass(),()->borrowerService.registerNew(testCase.getInput()));
      else
        doFailWhenExpectedThrownExceptionClassIsMissing();
    }
  }

  @AfterAll
  void cleanUp() {
    testDataCleanupUtil.cleanupTestDataForBorrower();
  }

  BorrowerDTO createNewBorrowerDTO(String firstName, String lastName) {
    var borrowerDTO = new BorrowerDTO();
    borrowerDTO.setFirstName(firstName);
    borrowerDTO.setLastName(lastName);
    return borrowerDTO;
  }

  @Override
  public Stream<TestCase<BorrowerDTO>> constructTestCases() {
    return Stream.of(
       newTestCase(createNewBorrowerDTO("John","Doe"),false,null),
       newTestCase(createNewBorrowerDTO("John","Doe"),true, DuplicateBorrowerEntryException.class)
    );
  }
}
