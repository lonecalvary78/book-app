package demo.app.book.domain.renting.exception.handler;

import demo.app.book.domain.renting.exception.NonExistBookRentingException;
import demo.app.book.infra.exception.handler.GenericApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NonExistBookRentingExceptionHandler implements GenericApplicationException<NonExistBookRentingException> {

  @Override
  public Response toResponse(NonExistBookRentingException nonExistBookRentingException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(errorDetails(Response.Status.BAD_REQUEST, nonExistBookRentingException)).build();
  }
}
