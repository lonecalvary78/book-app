package demo.app.book.domain.rent.exception.handler;

import demo.app.book.domain.rent.exception.BookAlreadyReturnedException;
import demo.app.book.infra.exception.handler.GenericApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BookAlreadyReturnedExceptionHandler implements GenericApplicationException<BookAlreadyReturnedException> {
  @Override
  public Response toResponse(BookAlreadyReturnedException bookAlreadyReturnedException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(ofErrorDetail(Response.Status.BAD_REQUEST, bookAlreadyReturnedException)).build();
  }
}
