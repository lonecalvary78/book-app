package demo.app.book.domain.rent.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@NotNull
public class RentRequestDTO {
  private Long bookId;
  private Long borrowerId;
  @JsonbDateFormat("yyyy-MM-dd")
  private LocalDate fromDate;
  @JsonbDateFormat("yyyy-MM-dd")
  private LocalDate toDate;
}
