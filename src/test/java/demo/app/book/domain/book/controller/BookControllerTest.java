package demo.app.book.domain.book.controller;

import demo.app.book.common.test.GenericRestControllerTest;
import demo.app.book.common.test.cleanup.util.TestDataCleanupUtil;
import demo.app.book.common.test.model.RestControllerTestCase;
import demo.app.book.domain.book.model.BookDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerTest implements GenericRestControllerTest<BookDTO> {
  @Inject
  TestDataCleanupUtil testDataCleanupUtil;

  @Test
  @Order(1)
  void retrieveAllBooks() {
    given().param("currentPage",1).when().get("/books").then().statusCode(200);
  }

  @ParameterizedTest
  @MethodSource("constructTestCases")
  @Order(2)
  void newEntry(RestControllerTestCase<BookDTO> testCase) {
    given().contentType(MediaType.APPLICATION_JSON).body(testCase.getInput()).post("/books").then().statusCode(testCase.getStatusCode());
  }

  @AfterAll
  void cleanUp() {
    testDataCleanupUtil.cleanupTestDataForBook();
  }

  private BookDTO newBookDTO(String isbn, String title, String author, int yearOfPublished) {
    var bookDTO = new BookDTO();
    bookDTO.setIsbn(isbn);
    bookDTO.setTitle(title);
    bookDTO.setAuthor(author);
    bookDTO.setYearOfPublished(yearOfPublished);
    return bookDTO;
  }

  @Override
  public Stream<RestControllerTestCase<BookDTO>> constructTestCases() {
    return Stream.of(
       ofTestCase(newBookDTO("978-0-201-48567-7","Refactoring","Martin Fowler",1999), 200),
       ofTestCase(newBookDTO("","","",0), 400),
       ofTestCase(newBookDTO("978-0-201-48567-7","Refactoring","Martin Fowler",1999), 409)
    );
  }
}
