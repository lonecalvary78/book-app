package demo.app.book.domain.borrower.controller;

import demo.app.book.common.test.GenericRestControllerTest;
import demo.app.book.common.test.cleanup.util.TestDataCleanupUtil;
import demo.app.book.common.test.model.RestControllerTestCase;
import demo.app.book.domain.borrower.model.BorrowerDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BorrowerControllerTest implements GenericRestControllerTest<BorrowerDTO> {
  @Inject
  TestDataCleanupUtil testDataCleanupUtil;

  @ParameterizedTest
  @DisplayName("Register a new borrower")
  @MethodSource("constructTestCases")
  void registerNew(RestControllerTestCase<BorrowerDTO> testCase) {
      given().contentType(MediaType.APPLICATION_JSON).body(testCase.getInput()).when().post("/borrowers").then().statusCode(testCase.getStatusCode());
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
  public Stream<RestControllerTestCase<BorrowerDTO>> constructTestCases() {
    return Stream.of(
       ofTestCase(createNewBorrowerDTO("John", "Doe"),200),
       ofTestCase(createNewBorrowerDTO("John", "Doe"),409)
    );
  }
}
