package demo.app.book.util.pagination;

import demo.app.book.util.pagination.model.PaginatedInquiryResult;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Collections;
import java.util.List;

@Singleton
public class PaginationUtil {
  @ConfigProperty(name = "pagination.maximum-records-per-page")
  private int maximumRecordsPerPage;

  public PaginatedInquiryResult newPaginatedInquiryResult(int currentPage, List inquiryResults) {
    if(inquiryResults != null && !inquiryResults.isEmpty()) {
      var totalRecords = inquiryResults.size();
      var totalPages = Math.ceilDiv(totalRecords, maximumRecordsPerPage);
      return PaginatedInquiryResult.of(currentPage, totalPages, slice(currentPage, inquiryResults));
    } else
        return PaginatedInquiryResult.of(0,0, Collections.emptyList());
  }

  private List slice(int currentPage, List inquiryResults) {
    return (inquiryResults != null && !inquiryResults.isEmpty())?inquiryResults.subList(start(currentPage),end(currentPage,inquiryResults.size())): Collections.emptyList();
  }

  private int start(int currentPage) {
    return (currentPage<=1)?0:(currentPage-1)*maximumRecordsPerPage;
  }

  private int end(int currentPage, int totalRecords) {
    var end = (currentPage*maximumRecordsPerPage)-1;
    return (end<=totalRecords)?end:totalRecords-1;
  }
}
