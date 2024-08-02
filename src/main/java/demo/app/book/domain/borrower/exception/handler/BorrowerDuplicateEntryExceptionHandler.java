package demo.app.book.domain.borrower.exception.handler;

import demo.app.book.domain.borrower.exception.DuplicateBorrowerEntryException;
import demo.app.book.infra.exception.handler.GenericApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BorrowerDuplicateEntryExceptionHandler implements GenericApplicationException<DuplicateBorrowerEntryException> {
  @Override
  public Response toResponse(DuplicateBorrowerEntryException duplicateBorrowerEntryException) {
    return Response.status(Response.Status.CONFLICT).entity(ofErrorDetail(Response.Status.CONFLICT, duplicateBorrowerEntryException)).build();
  }
}
