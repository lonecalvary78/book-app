package demo.app.book.domain.renting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
public class Renting {
  @Id
  @GeneratedValue
  private Long id;
  private Long bookId;
  private Long borrowerId;
  private LocalDate fromDate;
  private LocalDate toDate;
  private String status;
  private LocalDateTime startRentedAt;
  private LocalDateTime returnedAt;

  @PrePersist
  public void beforeSave() {
    if(getBookId()==null)
      setStartRentedAt(LocalDateTime.now(ZoneId.systemDefault()));
  }
}
