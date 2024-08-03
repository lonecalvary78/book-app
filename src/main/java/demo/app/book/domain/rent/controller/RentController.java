package demo.app.book.domain.rent.controller;

import demo.app.book.domain.book.exception.NonExistingBookException;
import demo.app.book.domain.borrower.exception.NonExistingBorrowerException;
import demo.app.book.domain.rent.exception.BookAlreadyReturnedException;
import demo.app.book.domain.rent.exception.BookIsOccupiedException;
import demo.app.book.domain.rent.exception.NonExistBookRentingException;
import demo.app.book.domain.rent.mediator.RentMediator;
import demo.app.book.domain.rent.model.RentRequestDTO;
import demo.app.book.infra.exception.model.ErrorDetailDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/rents")
public class RentController {
  @Inject
  private RentMediator rentMediator;

  @POST
  @Operation(description = "To rent the book")
  @APIResponses({
     @APIResponse(responseCode = "204", description = "The request of book renting is successfully recorded", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
     @APIResponse(responseCode = "400", description = "The request of book renting is rejected by some reasons", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(oneOf = ErrorDetailDTO.class))),
     @APIResponse(responseCode = "409", description = "The request of book renting is rejected because the book was rented at the moment", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(oneOf = ErrorDetailDTO.class)))
  })
  public void rentBook(@Valid RentRequestDTO rentRequestDTO) throws NonExistingBookException, NonExistingBorrowerException, BookIsOccupiedException {
    rentMediator.rentBookFromLibrary(rentRequestDTO);
  }

  @PATCH
  @Path("/{rentId}")
  @Operation(description = "To return book that being rented before")
  @APIResponses({
          @APIResponse(responseCode = "204", description = "The request of returning book is successfully recorded", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RentRequestDTO.class))),
          @APIResponse(responseCode = "409", description = "The request of returning book is failed because the book already returned before", content = @Content(mediaType = MediaType.APPLICATION_JSON))
  })
  public void returnBook(
          @Parameter(name = "rentId", description = "Unique ID of the book renting", required = true)
          @PathParam("rentId") Long rentId,
          @Parameter(name="action", description = "the kind of user action want to perform")
          @QueryParam("action") String action) throws NonExistBookRentingException, BookAlreadyReturnedException {
    if (action.equalsIgnoreCase("RETURN")) {
      rentMediator.returnBookToLibrary(rentId);
    }
  }
}
