package demo.app.book.util.pagination.model;

import java.util.List;

public record PaginatedInquiryResult(int currentPage, int totalPages, List inquiryResults) {
   public static PaginatedInquiryResult of(int currentPage, int totalPages, List inquiryResults) {
     return new PaginatedInquiryResult(currentPage, totalPages, inquiryResults);
   }
}
