package demo.app.book.domain.book.exception.handler;

import demo.app.book.domain.book.exception.DuplicateBookEntryException;
import demo.app.book.infra.exception.handler.GenericApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DuplicateBookEntryExceptionHandler implements GenericApplicationException<DuplicateBookEntryException> {
  @Override
  public Response toResponse(DuplicateBookEntryException duplicateBookEntryException) {
    return Response.status(Response.Status.CONFLICT).entity(errorDetails(Response.Status.CONFLICT,duplicateBookEntryException)).build();
  }
}
