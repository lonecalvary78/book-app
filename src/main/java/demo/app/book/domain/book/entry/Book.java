package demo.app.book.domain.book.entry;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name="book")
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
