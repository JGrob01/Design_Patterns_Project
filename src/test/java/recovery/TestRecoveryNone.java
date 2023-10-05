package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestRecoveryNone {

  private RecoveryBehavior rb;

  @Before
  public void init() {
    rb = new RecoveryNone();
  }

  @Test
  public void testCurrentEqualsMax() {
    assertEquals(5, rb.calculateRecovery(5, 5));
  }

  @Test
  public void testCurrentLessThanMax() {
    assertEquals(3, rb.calculateRecovery(3, 5));
  }

}
