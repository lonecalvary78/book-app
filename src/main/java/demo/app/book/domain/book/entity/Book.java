package demo.app.book.domain.book.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(uniqueConstraints = {
   @UniqueConstraint(name = "uk_book_1",columnNames = "isbn")
})
@Data
public class Book {
  @Id
  @GeneratedValue
  private Long id;

  private String isbn;
  private String title;
  private String author;

  @Column(name="year_of_published")
  private int yearOfPublished;
}
