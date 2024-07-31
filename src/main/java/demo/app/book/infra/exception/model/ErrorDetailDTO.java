package demo.app.book.infra.exception.model;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"statusCode","errorMessage"})
public record ErrorDetailDTO(int statusCode, String errorMessage) {
}
