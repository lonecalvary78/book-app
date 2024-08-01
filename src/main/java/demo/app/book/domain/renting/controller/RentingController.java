package demo.app.book.domain.renting.controller;

import demo.app.book.domain.book.exception.NonExistingBookException;
import demo.app.book.domain.borrower.exception.NonExistingBorrowerException;
import demo.app.book.domain.renting.exception.BookIsOccupiedException;
import demo.app.book.domain.renting.exception.NonExistBookRentingException;
import demo.app.book.domain.renting.facade.RentingFacade;
import demo.app.book.domain.renting.model.RentingRequestDTO;
import jakarta.inject.Inject;
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
public class RentingController {
  @Inject
  private RentingFacade rentingFacade;

  @POST
  @Operation(description = "To rent the book")
  @APIResponses({
     @APIResponse(responseCode = "200", description = "The book renting request is successfully accepted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RentingRequestDTO.class))),
     @APIResponse(responseCode = "400", description = "Indicated the request is invalid due to either non-existing book or non-existing borrower profile", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
     @APIResponse(responseCode = "409", description = "Indicated the request can not be proceed since the book was rented before or rented by other person at the moment", content = @Content(mediaType = MediaType.APPLICATION_JSON))
  })
  public void rentBook(RentingRequestDTO rentingRequestDTO) throws NonExistingBookException, NonExistingBorrowerException, BookIsOccupiedException {
    rentingFacade.rentBookFromLibary(rentingRequestDTO);
  }

  @PATCH
  @Path("/{rentId}")
  @Operation(description = "To return book that being rented before")
  @APIResponses({
          @APIResponse(responseCode = "200", description = "Indicated the return book request is successfully accepted", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = RentingRequestDTO.class))),
          @APIResponse(responseCode = "400", description = "Indicated the invalid request when it could not find the book renting record", content = @Content(mediaType = MediaType.APPLICATION_JSON))
  })
  public void returnBook(@PathParam("rentId") Long rentId, @QueryParam("action") String action) throws NonExistBookRentingException {
    if (action.toUpperCase().equals("RETURN")) {
      rentingFacade.returnBookToLibrary(rentId);
    }
  }
}
