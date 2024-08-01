package demo.app.book.domain.renting.controller;

import demo.app.book.domain.renting.model.RentingRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@QuarkusTest
class RentingControllerTest {
  @Test
  void rentBook() {
    var rentingRequestDTO = new RentingRequestDTO();
    rentingRequestDTO.setBookId(1L);
    rentingRequestDTO.setBorrowerId(1L);
    rentingRequestDTO.setFromDate(LocalDate.now());
    rentingRequestDTO.setToDate(LocalDate.now());
    given().contentType(MediaType.APPLICATION_JSON).body(rentingRequestDTO).when().post("/rents").then().statusCode(400);
  }

  @Test
  void returnBookToBookeeper() {
    var rentId = 1L;
    given().queryParam("action","RETURN").when().patch("/rents/{rentId}",rentId).then().statusCode(400);
  }
}
