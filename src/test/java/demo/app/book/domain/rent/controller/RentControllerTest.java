package demo.app.book.domain.rent.controller;

import demo.app.book.domain.rent.model.RentRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

@QuarkusTest
class RentControllerTest {
  @Test
  void rentBook() {
    var rentRequestDTO = new RentRequestDTO();
    rentRequestDTO.setBookId(1L);
    rentRequestDTO.setBorrowerId(1L);
    rentRequestDTO.setFromDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    rentRequestDTO.setToDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    given().contentType(MediaType.APPLICATION_JSON).body(rentRequestDTO).when().post("/rents").then().statusCode(400);
  }

  @Test
  void returnBookToBookeeper() {
    var rentId = 1L;
    given().queryParam("action","RETURN").when().patch("/rents/{rentId}",rentId).then().statusCode(400);
  }
}
