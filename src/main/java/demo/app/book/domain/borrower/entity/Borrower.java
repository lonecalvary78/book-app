package demo.app.book.domain.borrower.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(uniqueConstraints = {
   @UniqueConstraint(name = "uk_borrower_1",columnNames = {"firstName","lastName"})
})
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
