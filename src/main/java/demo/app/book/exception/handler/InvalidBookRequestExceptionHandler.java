package demo.app.book.exception.handler;

import demo.app.book.exception.InvalidBookRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidBookRequestExceptionHandler implements GenericApplicationException<InvalidBookRequestException> {
  @Override
  public Response toResponse(InvalidBookRequestException invalidBookRequestException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(errorDetails(Response.Status.BAD_REQUEST, invalidBookRequestException)).build();
  }
}
