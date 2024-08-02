package demo.app.book.domain.borrower.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BorrowerDTO {
  private Long id;

  @NotBlank(message = "firstName should not be empty")
  private String firstName;

  @NotBlank(message = "lastName should not be empty")
  private String lastName;
}
