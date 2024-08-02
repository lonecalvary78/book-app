package demo.app.book.infra.exception.handler;

import io.quarkus.arc.ArcUndeclaredThrowableException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MiscExceptionHandler implements GenericApplicationException<ArcUndeclaredThrowableException> {
  @Override
  public Response toResponse(ArcUndeclaredThrowableException arcUndeclaredThrowableException) {
    return Response.status(Response.Status.BAD_REQUEST).build();
  }
}
