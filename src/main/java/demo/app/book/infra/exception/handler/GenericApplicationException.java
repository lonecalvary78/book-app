package demo.app.book.infra.exception.handler;

import demo.app.book.infra.exception.model.ErrorDetailDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public interface GenericApplicationException<E extends Exception> extends ExceptionMapper<E> {
  default ErrorDetailDTO ofErrorDetail(Response.Status responseStatus, E thrownException) {
    return new ErrorDetailDTO(responseStatus.getStatusCode(), thrownException.getMessage());
  }
}
