package demo.app.book.model;

import jakarta.validation.constraints.Min;

public record BookReturnRequestDTO(@Min(1) Long bookId, @Min(1) Long borrowerId) {
}
