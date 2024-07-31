package demo.app.book.domain.book.service;

import demo.app.book.common.test.cleanup.util.TestDataCleanupUtil;
import demo.app.book.common.test.GenericApplicationTest;
import demo.app.book.common.test.model.TestCase;
import demo.app.book.domain.book.exception.DuplicateBookEntryException;
import demo.app.book.domain.book.model.BookDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest implements GenericApplicationTest<BookDTO> {
  @Inject
  BookService bookService;

  @Inject
  TestDataCleanupUtil testDataCleanupUtil;

  @ParameterizedTest
  @MethodSource("constructTestCases")
  void newEntry(TestCase<BookDTO> testCase) {
    if (!testCase.isThrowException()) {
      Assertions.assertDoesNotThrow(()->bookService.newEntry(testCase.getInput()));
    } else {
      if (testCase.getExceptionClass() != null)
        Assertions.assertThrows(testCase.getExceptionClass(), ()->bookService.newEntry(testCase.getInput()));
      else
        doFailWhenExpectedThrownExceptionClassIsMissing();
    }
  }

  @AfterAll
  void cleanUp() {
    testDataCleanupUtil.cleanupTestDataForBook();
  }

  private BookDTO createBookDTO(String isbn, String title, String author, int yearOfPublished) {
    var bookDTO = new BookDTO();
    bookDTO.setIsbn(isbn);
    bookDTO.setTitle(title);
    bookDTO.setAuthor(author);
    bookDTO.setYearOfPublished(yearOfPublished);
    return bookDTO;
  }

  @Override
  public Stream<TestCase<BookDTO>> constructTestCases() {
    return Stream.of(
       newTestCase(createBookDTO("978-0-201-48567-7","Refactoring","Martin Fowler",1999),false,null),
       newTestCase(createBookDTO("978-0-201-48567-7","Refactoring","Martin Fowler",1999),true, DuplicateBookEntryException.class)
    );
  }
}

