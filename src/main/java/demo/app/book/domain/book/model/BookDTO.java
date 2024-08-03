package demo.app.book.domain.book.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
  @Schema(name = "Id")
  private Long id;
  @Schema(name = "isbn", description = "Book Registration Number", required = true)
  @NotBlank(message = "ISBN should not be empty")
  @Pattern(regexp = "^(?:ISBN(?:-13)?:?\\ )?(?=[0-9]{13}$|(?=(?:[0-9]+[-\\ ]){4})[-\\ 0-9]{17}$)97[89][-\\ ]?[0-9]{1,5}[-\\ ]?[0-9]+[-\\ ]?[0-9]+[-\\ ]?[0-9]$")
  private String isbn;

  @Schema(name="title", description = "Book Title", required = true)
  @NotBlank(message = "Title should not be empty")
  private String title;

  @Schema(name = "author", description = "Book Author", required = true)
  @NotBlank(message = "Author should not be empty")
  private String author;

  @Schema(name = "yearOfBookPublished", description = "The year when book was published", required = true)
  @Positive(message = "Year of book published should be a positive number")
  private int yearOfPublished;
}
