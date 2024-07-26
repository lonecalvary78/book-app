package demo.app.book.model;

import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public record BookRentRequestDTO(@Min(1) Long bookId, @Min(1) Long borrowerId, LocalDate fromDate, LocalDate toDate) {
}
