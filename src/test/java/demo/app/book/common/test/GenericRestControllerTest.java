package demo.app.book.common.test;

import demo.app.book.common.test.model.RestControllerTestCase;

import java.util.stream.Stream;

public interface GenericRestControllerTest<T extends Object> {
  public Stream<RestControllerTestCase<T>> constructTestCases();

  default RestControllerTestCase<T> ofTestCase(T input, int statusCode) {
    return new RestControllerTestCase<T>(input, statusCode);
  }
}
