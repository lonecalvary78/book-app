package demo.app.book.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class BookRenting {
  @Id
  @GeneratedValue
  private Long id;
  private Long bookId;
  private Long borrowerId;
  private LocalDate fromDate;
  private LocalDate toDate;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime lastModifiedAt;

  @PrePersist
  public void beforeSaveNew() {
    setCreatedAt(LocalDateTime.now());
  }

  @PreUpdate
  public void beforeUpdate() {
    setLastModifiedAt(LocalDateTime.now());
  }
}
