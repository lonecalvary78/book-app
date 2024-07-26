package demo.app.book.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record BookDTO(Long id, @NotBlank String isbn, @NotBlank String title, @NotBlank String author, @Min(1950) int yearOfPublished) {
}
