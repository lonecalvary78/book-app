package demo.app.book.domain.book.repository;

import demo.app.book.common.test.cleanup.util.TestDataCleanupUtil;
import demo.app.book.common.test.GenericApplicationTest;
import demo.app.book.common.test.model.TestCase;
import demo.app.book.domain.book.entity.Book;
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
class BookRepositoryTest implements GenericApplicationTest<Book> {
   @Inject
   BookRepository bookRepository;

   @Inject
   TestDataCleanupUtil testDataCleanupUtil;

   @ParameterizedTest
   @MethodSource("constructTestCases")
   void newEntry(TestCase<Book> testCase) {
     Assertions.assertDoesNotThrow(()->bookRepository.save(testCase.getInput()));
   }

   @AfterAll
   void cleanUp() {
     testDataCleanupUtil.cleanupTestDataForBook();
   }

   private Book createBook(String isbn, String title, String author, int yearOfPublished) {
     var book = new Book();
     book.setIsbn(isbn);
     book.setTitle(title);
     book.setAuthor(author);
     book.setYearOfPublished(yearOfPublished);
     return book;
   }

  @Override
  public Stream<TestCase<Book>> constructTestCases() {
    return Stream.of(
       newTestCase(createBook("978-0-201-48567-7","Refactoring","Martin Fowler",1999),false, null)
    );
  }
}

