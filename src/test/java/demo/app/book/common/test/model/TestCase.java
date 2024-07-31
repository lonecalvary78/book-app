package demo.app.book.common.test.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TestCase<T extends Object> {
  private final T input;
  private final boolean isThrowException;
  private final Class exceptionClass;
}