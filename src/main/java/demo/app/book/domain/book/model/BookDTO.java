package demo.app.book.domain.book.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
  private Long id;
  @NotBlank(message = "ISBN should not be empty")
  private String isbn;

  @NotBlank(message = "Title should not be empty")
  private String title;

  @NotBlank(message = "Author should not be empty")
  private String author;

  @Positive(message = "Year of book published should be a positive number")
  private int yearOfPublished;
}
