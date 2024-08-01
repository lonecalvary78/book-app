package demo.app.book.domain.renting.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class RentingRequestDTO {
  @Min(1)
  private Long bookId;
  @Min(1)
  private Long borrowerId;

  @JsonbDateFormat("yyyy-MM-dd")
  private LocalDate fromDate;
  @JsonbDateFormat("yyyy-MM-dd")
  private LocalDate toDate;
}
