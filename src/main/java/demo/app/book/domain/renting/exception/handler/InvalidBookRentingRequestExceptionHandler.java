package demo.app.book.domain.renting.exception.handler;

import demo.app.book.domain.renting.exception.InvalidBookRentingRequestException;
import demo.app.book.infra.exception.handler.GenericApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidBookRentingRequestExceptionHandler implements GenericApplicationException<InvalidBookRentingRequestException> {
  @Override
  public Response toResponse(InvalidBookRentingRequestException invalidBookRentingRequestException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(errorDetails(Response.Status.BAD_REQUEST, invalidBookRentingRequestException)).build();
  }
}
