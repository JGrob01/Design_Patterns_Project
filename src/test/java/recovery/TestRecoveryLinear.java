package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestRecoveryLinear {
  
  private static final int R_STEP = 2;
  private RecoveryBehavior rb;

  /**
   * Initialize global variables.
   */
  @Before
  public void init() {
    rb = new RecoveryLinear(R_STEP);
  }

  /**
   * No recovery should occur if the life form already has max health.
   */
  @Test
  public void testCurrentEqualsMax() {
    assertEquals(10, rb.calculateRecovery(10, 10));
  }

  /**
   * Recovery should not set a life form's health to above its maximum.
   */
  @Test
  public void testRecoveryToMax() {
    assertEquals(10, rb.calculateRecovery(9, 10));
    assertEquals(10, rb.calculateRecovery(10 - R_STEP, 10));
  }

  /**
   * Test recovery of the full amount of recoveryStep.
   */
  @Test
  public void testRecoveryFullAmount() {
    assertEquals(9, rb.calculateRecovery(9 - R_STEP, 10));
    assertEquals(5, rb.calculateRecovery(5 - R_STEP, 10));
  }

  /**
   * A life form should not be able to recover if it is already dead.
   * A life form as dead if their current health is zero.
   */
  @Test
  public void testNoRecoveryDead() {
    assertEquals(0, rb.calculateRecovery(0, 10));
  }

}
