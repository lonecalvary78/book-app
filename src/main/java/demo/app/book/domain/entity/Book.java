package demo.app.book.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
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
