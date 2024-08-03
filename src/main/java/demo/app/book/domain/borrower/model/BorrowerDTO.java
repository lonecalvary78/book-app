package demo.app.book.domain.borrower.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@NoArgsConstructor
@Setter
@Getter
public class BorrowerDTO {
  @Schema(name="id", description = "Unique ID of borrower")
  private Long id;

  @Schema(name="firstName", description = "First name", required = true)
  @NotBlank(message = "first name should not be empty")
  private String firstName;

  @Schema(name="lastName", description = "Last name", required = true)
  @NotBlank(message = "last name should not be empty")
  private String lastName;
}
