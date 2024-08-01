package demo.app.book.domain.renting.facade;

import demo.app.book.domain.book.exception.NonExistingBookException;
import demo.app.book.domain.renting.exception.NonExistBookRentingException;
import demo.app.book.domain.renting.model.RentingRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@QuarkusTest
class RentingFacadeTest {
  @Inject
  RentingFacade rentingFacade;

  @Test
  @DisplayName("Perform Book Renting")
  void rentBook() {
    var rentingRequestDTO = new RentingRequestDTO();
    rentingRequestDTO.setBookId(1L);
    rentingRequestDTO.setBorrowerId(1L);
    Assertions.assertThrows(NonExistingBookException.class,()->rentingFacade.rentBookFromLibary(rentingRequestDTO));
  }

  @Test
  @DisplayName("Return book to bookeeper")
  void returnBook() throws NoSuchAlgorithmException {
    var rentId = SecureRandom.getInstanceStrong().nextLong();
    Assertions.assertThrows(NonExistBookRentingException.class,()-> rentingFacade.returnBookToLibrary(rentId));
  }
}
