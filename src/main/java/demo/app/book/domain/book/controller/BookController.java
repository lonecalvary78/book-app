package demo.app.book.domain.book.controller;

import demo.app.book.domain.book.exception.DuplicateBookEntryException;
import demo.app.book.domain.renting.exception.BookIsRentedException;
import demo.app.book.domain.renting.exception.InvalidBookRentingRequestException;
import demo.app.book.domain.renting.exception.UnknownBorrowerException;
import demo.app.book.domain.book.model.BookDTO;
import demo.app.book.domain.renting.model.RentingRequestDTO;
import demo.app.book.domain.renting.model.ReturningRequestDTO;
import demo.app.book.util.pagination.model.PaginatedInquiryResult;
import demo.app.book.domain.book.service.BookService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
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
    @APIResponse(responseCode = "200",description = "Indicated the book inquiry will return the inquiry results", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = List.class)))
  })
  public PaginatedInquiryResult retrieveAllBooks(@QueryParam("currentPage") @DefaultValue("1") int currentPage) {
    return bookService.retrieveAllBooks(currentPage);
  }

  @POST
  @Operation(description = "To entry a new book")
  @APIResponses({
    @APIResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PaginatedInquiryResult.class))),
    @APIResponse(responseCode = "409", description = "Indicated the creation of new book is failed since it already recorded/entry before", content = @Content(mediaType = MediaType.APPLICATION_JSON))
  })
  public BookDTO newEntry(@Valid BookDTO bookDTO) throws DuplicateBookEntryException {
    return bookService.newEntry(bookDTO);
  }

  @POST
  @Path("/rent")
  @Operation(description = "To rent the book")
  @APIResponses({
          @APIResponse(responseCode = "200", description = "The book renting request is successfully accepted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RentingRequestDTO.class))),
          @APIResponse(responseCode = "400", description = "Indicated the request is invalid due to the non-existing borrower profile", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
          @APIResponse(responseCode = "409", description = "Indicated the request can not be proceed since the book was rented before or rented by other person at the moment", content = @Content(mediaType = MediaType.APPLICATION_JSON))
  })
  public void rentBook(@Valid RentingRequestDTO rentingRequestDTO) throws UnknownBorrowerException, BookIsRentedException {
    bookService.rentBook(rentingRequestDTO);
  }

  @POST
  @Path("/return")
  @Operation(description = "To return book that being rented before")
  @APIResponses({
          @APIResponse(responseCode = "200", description = "Indicated the return book request is successfully accepted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RentingRequestDTO.class))),
          @APIResponse(responseCode = "400", description = "Indicated the invalid request when it could not find the book renting record", content = @Content(mediaType = MediaType.APPLICATION_JSON))
  })
  public void returnBook(@Valid ReturningRequestDTO returningRequestDTO) throws InvalidBookRentingRequestException {
    bookService.returnBook(returningRequestDTO);
  }

}
