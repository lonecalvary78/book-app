package demo.app.book.domain.book.exception.handler;

import demo.app.book.domain.book.exception.NonExistingBookException;
import demo.app.book.infra.exception.handler.GenericApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NonExistingBookExceptionHandler implements GenericApplicationException<NonExistingBookException> {
  @Override
  public Response toResponse(NonExistingBookException nonExistingBookException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(errorDetails(Response.Status.BAD_REQUEST, nonExistingBookException)).build();
  }
}
