package demo.app.book.infra.exception.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionHandler implements GenericApplicationException<IllegalArgumentException> {
  @Override
  public Response toResponse(IllegalArgumentException illegalArgumentException) {
    return Response.status(Response.Status.BAD_REQUEST).entity(ofErrorDetail(Response.Status.BAD_REQUEST, illegalArgumentException)).build();
  }
}
