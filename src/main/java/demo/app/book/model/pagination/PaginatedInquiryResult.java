package demo.app.book.model.pagination;

import java.util.List;

public record PaginatedInquiryResult(int currentPage, int totalPages, List inquiryResults) {
   public static PaginatedInquiryResult of(int currentPage, int totalPages, List inquiryResults) {
     return new PaginatedInquiryResult(currentPage, totalPages, inquiryResults);
   }
}
