package demo.app.book.common.test;


import demo.app.book.common.test.model.TestCase;
import org.junit.jupiter.api.Assertions;

import java.util.stream.Stream;

public interface GenericApplicationTest<T extends Object> {
  final String ERROR_MESSAGE_FOR_MISSING_EXCEPTION_CLASS="The expected exception class is missing";

  public Stream<TestCase<T>> constructTestCases();

  default TestCase<T> newTestCase(T testInput, boolean isThrowAnyException, Class thrownExceptionClass) {
    return new TestCase<T>(testInput, isThrowAnyException, thrownExceptionClass);
  }

  default void doFailWhenExpectedThrownExceptionClassIsMissing() {
    Assertions.fail(ERROR_MESSAGE_FOR_MISSING_EXCEPTION_CLASS);
  }
}
