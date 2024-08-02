package demo.app.book.domain.rent.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotNull;
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
  @NotNull(message = "Book Id should not be null")
  @Positive(message = "Book Id should be a positive number")
  private Long bookId;

  @NotNull(message = "Borrower Id should not be null")
  @Positive(message = "Borrower Id should be a positive number")
  private Long borrowerId;

  @JsonbDateFormat("yyyy-MM-dd")
  @NotNull(message = "From Date should be empty")
  private LocalDate fromDate;

  @JsonbDateFormat("yyyy-MM-dd")
  @NotNull(message = "End Date should be empty")
  private LocalDate toDate;
}
