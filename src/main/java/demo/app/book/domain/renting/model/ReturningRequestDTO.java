package demo.app.book.domain.renting.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@Setter
@Getter
public class ReturningRequestDTO {
  @Min(1)
  private Long bookId;
  @Min(1)
  private Long borrowerId;

  @JsonbDateFormat("yyyy-MM-dd")
  private OffsetDateTime returnedAt;
}
