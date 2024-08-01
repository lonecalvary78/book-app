package demo.app.book.domain.rent.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class RentRequestDTO {
  @Min(1)
  private Long bookId;
  @Min(1)
  private Long borrowerId;

  @JsonbDateFormat("yyyy-MM-dd")
  private LocalDate fromDate;
  @JsonbDateFormat("yyyy-MM-dd")
  private LocalDate toDate;
}
