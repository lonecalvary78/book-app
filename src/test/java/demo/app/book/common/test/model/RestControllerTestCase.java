package demo.app.book.common.test.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RestControllerTestCase<T extends Object> {
  private final T input;
  private final int statusCode;
}
