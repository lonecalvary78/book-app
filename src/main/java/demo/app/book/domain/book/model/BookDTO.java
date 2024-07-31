package demo.app.book.domain.book.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
  private Long id;
  @NotBlank
  private String isbn;

  @NotBlank
  private String title;

  @NotBlank
  private String author;

  @Min(1950)
  private int yearOfPublished;
}
