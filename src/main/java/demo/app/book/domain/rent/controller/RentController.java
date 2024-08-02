package demo.app.book.domain.rent.controller;

import demo.app.book.domain.book.exception.NonExistingBookException;
import demo.app.book.domain.borrower.exception.NonExistingBorrowerException;
import demo.app.book.domain.rent.exception.BookAlreadyReturnedException;
import demo.app.book.domain.rent.exception.BookIsOccupiedException;
import demo.app.book.domain.rent.exception.NonExistBookRentingException;
import demo.app.book.domain.rent.facade.RentFacade;
import demo.app.book.domain.rent.model.RentRequestDTO;
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
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/rents")
public class RentController {
  @Inject
  private RentFacade rentFacade;

  @POST
  @Operation(description = "To rent the book")
  @APIResponses({
     @APIResponse(responseCode = "204", description = "The book renting request is successfully accepted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RentRequestDTO.class))),
     @APIResponse(responseCode = "400", description = "Indicated the request is invalid book renting request by some reasons", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
     @APIResponse(responseCode = "409", description = "Indicated the request can not be proceed since the book was rented before or rented by other person at the moment", content = @Content(mediaType = MediaType.APPLICATION_JSON))
  })
  public void rentBook(@Valid RentRequestDTO rentRequestDTO) throws NonExistingBookException, NonExistingBorrowerException, BookIsOccupiedException {
    rentFacade.rentBookFromLibary(rentRequestDTO);
  }

  @PATCH
  @Path("/{rentId}")
  @Operation(description = "To return book that being rented before")
  @APIResponses({
          @APIResponse(responseCode = "204", description = "Indicated the return book request is successfully accepted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RentRequestDTO.class))),
          @APIResponse(responseCode = "400", description = "Indicated the invalid request when the book renting have not created yet", content = @Content(mediaType = MediaType.APPLICATION_JSON))
  })
  public void returnBook(@PathParam("rentId") Long rentId, @QueryParam("action") String action) throws NonExistBookRentingException, BookAlreadyReturnedException {
    if (action.toUpperCase().equals("RETURN")) {
      rentFacade.returnBookToLibrary(rentId);
    }
  }
}
