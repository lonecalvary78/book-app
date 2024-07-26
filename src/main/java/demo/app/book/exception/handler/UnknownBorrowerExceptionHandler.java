package demo.app.book.exception.handler;

import demo.app.book.exception.UnknownBorrowerException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnknownBorrowerExceptionHandler implements GenericApplicationException<UnknownBorrowerException> {
  @Override
  public Response toResponse(UnknownBorrowerException unknownBorrowerException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(errorDetails(Response.Status.BAD_REQUEST, unknownBorrowerException)).build();
  }
}
