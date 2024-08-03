package demo.app.book.domain.book.controller;

import demo.app.book.domain.book.exception.DuplicateBookEntryException;
import demo.app.book.domain.book.model.BookDTO;
import demo.app.book.infra.exception.model.ErrorDetailDTO;
import demo.app.book.util.pagination.model.PaginatedInquiryResult;
import demo.app.book.domain.book.service.BookService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

@Path("/books")
public class BookController {
  @Inject
  private BookService bookService;

  @GET
  @Operation(description = "To get the list of books")
  @APIResponses({
    @APIResponse(responseCode = "200",description = "Indicated the book inquiry will return the inquiry results", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(oneOf = PaginatedInquiryResult.class)))
  })
  public PaginatedInquiryResult retrieveAllBooks(
          @Parameter(name = "currentPage", description = "the selected page number")
          @QueryParam("currentPage") @DefaultValue("1") int currentPage) {
    return bookService.retrieveAllBooks(currentPage);
  }

  @POST
  @Operation(description = "To entry a new book information")
  @APIResponses({
    @APIResponse(responseCode = "200", description = "The book information is successfully created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(oneOf = BookDTO.class))),
    @APIResponse(responseCode = "409", description = "The creation of book information is rejected due to the duplicate entry", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(oneOf = ErrorDetailDTO.class)))
  })
  public BookDTO newEntry(@Valid BookDTO bookDTO) throws DuplicateBookEntryException {
    return bookService.newEntry(bookDTO);
  }
}
