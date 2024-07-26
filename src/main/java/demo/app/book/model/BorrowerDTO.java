package demo.app.book.model;

import jakarta.validation.constraints.NotBlank;

public record BorrowerDTO(Long id, @NotBlank String firstName, @NotBlank String lastName) {
}
