package demo.app.book.infra.exception.model;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@JsonbPropertyOrder({"statusCode","errorMessage"})
public record ErrorDetailDTO(
        @Schema(name="statusCode", description = "The status code from response")
        int statusCode,
        @Schema(name="errorMessage", description = "The detailed Error Message")
        String errorMessage) {
}
