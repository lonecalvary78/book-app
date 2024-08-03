package demo.app.book.domain.rent.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@NotNull
public class RentRequestDTO {
  @NotNull(message = "Book Id should not be empty")
  @Positive(message = "Book Id should be greater than 0")
  private Long bookId;

  @NotNull(message = "Borrower Id should not be empty")
  @Positive(message = "Borrower Id should be greater than 0")
  private Long borrowerId;

  @NotNull(message = "From Date should be empty")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Star Date should follow the proper this format(YYYY-MM-DD)")
  private String fromDate;

  @NotNull(message = "End Date should be empty")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "End Date should follow the proper this format(YYYY-MM-DD)")
  private String toDate;
}
