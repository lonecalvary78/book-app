package demo.app.book.domain.rent.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@NoArgsConstructor
@Getter
@Setter
@NotNull
public class RentRequestDTO {
  @Schema(name="bookId", description = "Unique ID of the book", required = true)
  @NotNull(message = "Book Id should not be empty")
  @Positive(message = "Book Id should be greater than 0")
  private Long bookId;

  @Schema(name="borrowerId", description = "Unique ID of the borrower who request to rent the book", required = true)
  @NotNull(message = "Borrower Id should not be empty")
  @Positive(message = "Borrower Id should be greater than 0")
  private Long borrowerId;

  @Schema(name="startDate", description = "the date when the book renting is started", required = true)
  @NotNull(message = "From Date should be empty")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Star Date should follow the proper this format(YYYY-MM-DD)")
  private String fromDate;

  @Schema(name="endDate", description = "the date when the book renting is ended", required = true)
  @NotNull(message = "End Date should be empty")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "End Date should follow the proper this format(YYYY-MM-DD)")
  private String toDate;
}
