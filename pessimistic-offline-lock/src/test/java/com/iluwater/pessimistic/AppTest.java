package com.iluwater.pessimistic;

import com.iluwatar.pessimistic.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Application test
 */
class AppTest {
  /**
  * Issue: Add at least one assertion to this test case.
  */
  @Test
  void shouldExecuteApplicationWithoutException() {
    assertDoesNotThrow(() -> App.main(new String[]{}));
  }
}
