package demo.app.book.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Borrower {
  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
}
