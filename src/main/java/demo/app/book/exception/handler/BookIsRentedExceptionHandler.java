package demo.app.book.exception.handler;

import demo.app.book.exception.BookIsRentedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BookIsRentedExceptionHandler implements GenericApplicationException<BookIsRentedException> {
  @Override
  public Response toResponse(BookIsRentedException bookIsRentedException) {
    return Response.status(Response.Status.CONFLICT).entity(errorDetails(Response.Status.CONFLICT, bookIsRentedException)).build();
  }
}
