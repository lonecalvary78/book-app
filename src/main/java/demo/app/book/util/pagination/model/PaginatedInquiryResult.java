package demo.app.book.util.pagination.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

public record PaginatedInquiryResult(
        @Schema(name="currentPage", description = "The current page")
        int currentPage,
        @Schema(name="totalPages", description = "The total pages of inquiry result")
        int totalPages,
        @Schema(name="inquiryResults", description = "The returned inquiry results")
        List inquiryResults) {
   public static PaginatedInquiryResult of(int currentPage, int totalPages, List inquiryResults) {
     return new PaginatedInquiryResult(currentPage, totalPages, inquiryResults);
   }
}
