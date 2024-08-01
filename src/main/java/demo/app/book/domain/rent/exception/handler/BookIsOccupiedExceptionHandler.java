package demo.app.book.domain.rent.exception.handler;

import demo.app.book.domain.rent.exception.BookIsOccupiedException;
import demo.app.book.infra.exception.handler.GenericApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BookIsOccupiedExceptionHandler implements GenericApplicationException<BookIsOccupiedException> {
  @Override
  public Response toResponse(BookIsOccupiedException bookIsOccupiedException) {
    return Response.status(Response.Status.CONFLICT).entity(errorDetails(Response.Status.CONFLICT, bookIsOccupiedException)).build();
  }
}
