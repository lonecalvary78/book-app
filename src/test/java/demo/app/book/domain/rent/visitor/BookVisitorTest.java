package demo.app.book.domain.rent.visitor;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@QuarkusTest
class BookVisitorTest {
   @Inject
   BookVisitor bookVisitor;

   @Test
   @DisplayName("To check the book is exist based on the input book ID")
   void isExist() throws NoSuchAlgorithmException {
     var randomBookId = SecureRandom.getInstanceStrong().nextLong();
     Assertions.assertFalse(()-> bookVisitor.isExist(randomBookId));
   }
}
