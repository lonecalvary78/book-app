package demo.app.book.domain.borrower.exception.handler;

import demo.app.book.domain.borrower.exception.NonExistingBorrowerException;
import demo.app.book.infra.exception.handler.GenericApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NonExistingBorrowerExceptionHandler implements GenericApplicationException<NonExistingBorrowerException> {
  @Override
  public Response toResponse(NonExistingBorrowerException nonExistingBorrowerException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(ofErrorDetail(Response.Status.BAD_REQUEST, nonExistingBorrowerException)).build();
  }
}
