package demo.app.book.domain.renting.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Entity
@Data
public class Renting {
  @Id
  @GeneratedValue
  private Long id;
  private Long bookId;
  private Long borrowerId;
  private OffsetDateTime fromDate;
  private OffsetDateTime toDate;
  private String status;
  private OffsetDateTime startRentedAt;
  private OffsetDateTime returnedAt;

  @PrePersist
  public void beforeSaveNew() {
    setStartRentedAt(OffsetDateTime.now(ZoneId.systemDefault()));
  }
}
