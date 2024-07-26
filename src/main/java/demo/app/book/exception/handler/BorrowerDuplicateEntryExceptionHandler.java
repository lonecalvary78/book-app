package demo.app.book.exception.handler;

import demo.app.book.exception.BorrowerDuplicateEntryException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BorrowerDuplicateEntryExceptionHandler implements GenericApplicationException<BorrowerDuplicateEntryException> {
  @Override
  public Response toResponse(BorrowerDuplicateEntryException borrowerDuplicateEntryException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(errorDetails(Response.Status.BAD_REQUEST, borrowerDuplicateEntryException)).build();
  }
}
