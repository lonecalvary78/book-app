package demo.app.book.exception.handler;

import demo.app.book.exception.BookDuplicateEntryException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BookDuplicateEntryExceptionHandler implements GenericApplicationException<BookDuplicateEntryException> {
  @Override
  public Response toResponse(BookDuplicateEntryException bookDuplicateEntryException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(errorDetails(Response.Status.BAD_REQUEST,bookDuplicateEntryException)).build();
  }
}
