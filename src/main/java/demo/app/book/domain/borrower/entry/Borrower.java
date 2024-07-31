package demo.app.book.domain.borrower.entry;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="borrower")
@Data
public class Borrower {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name="firstname")
  private String firstName;

  @Column(name="lastname")
  private String lastName;
}
