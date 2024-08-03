package demo.app.book.domain.borrower.controller;

import demo.app.book.domain.borrower.exception.DuplicateBorrowerEntryException;
import demo.app.book.domain.borrower.model.BorrowerDTO;
import demo.app.book.domain.borrower.service.BorrowerService;
import demo.app.book.infra.exception.model.ErrorDetailDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/borrowers")
public class BorrowerController {
  @Inject
  private BorrowerService borrowerService;

  @POST
  @Operation(description = "To register a new borrower")
  @APIResponses({
     @APIResponse(responseCode = "200", description = "The new borrower profile is successfully registered", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = BorrowerDTO.class))),
     @APIResponse(responseCode = "409", description = "The registration of new borrower profile is rejected due to the duplicate entry", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(oneOf = ErrorDetailDTO.class)))
  })
  public BorrowerDTO registerNew(@Valid BorrowerDTO borrowerDTO) throws DuplicateBorrowerEntryException {
    return borrowerService.registerNew(borrowerDTO);
  }
}
