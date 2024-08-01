package demo.app.book.domain.rent.facade;

import demo.app.book.domain.book.exception.NonExistingBookException;
import demo.app.book.domain.rent.exception.NonExistBookRentingException;
import demo.app.book.domain.rent.model.RentRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@QuarkusTest
class RentFacadeTest {
  @Inject
  RentFacade rentFacade;

  @Test
  @DisplayName("Perform Book Renting")
  void rentBook() {
    var rentRequestDTO = new RentRequestDTO();
    rentRequestDTO.setBookId(1L);
    rentRequestDTO.setBorrowerId(1L);
    Assertions.assertThrows(NonExistingBookException.class,()-> rentFacade.rentBookFromLibary(rentRequestDTO));
  }

  @Test
  @DisplayName("Return book to bookeeper")
  void returnBook() throws NoSuchAlgorithmException {
    var rentId = SecureRandom.getInstanceStrong().nextLong();
    Assertions.assertThrows(NonExistBookRentingException.class,()-> rentFacade.returnBookToLibrary(rentId));
  }
}
