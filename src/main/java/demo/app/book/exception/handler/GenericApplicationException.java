package demo.app.book.exception.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

import java.util.HashMap;
import java.util.Map;

public interface GenericApplicationException<E extends Exception> extends ExceptionMapper<E> {
  default Map<String,String> errorDetails(Response.Status responseStatus, E thrownException) {
    var errorDetails = new HashMap<String, String>();
    errorDetails.put("statusCode",String.valueOf(responseStatus.getStatusCode()));
    errorDetails.put("errorMessage",thrownException.getMessage());
    return errorDetails;
  }
}
