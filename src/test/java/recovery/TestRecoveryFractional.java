package recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestRecoveryFractional {

  private static final double R_FRACTIONAL = (double) 1 / 10;
  RecoveryBehavior rb;

  /**
   * Initialize fields.
   */
  @Before
  public void init() {
    rb = new RecoveryFractional(R_FRACTIONAL);
  }

  /**
   * Life forms should not recover if their current health
   * is already maximum.
   */
  @Test
  public void testCurrentEqualsMax() {
    assertEquals(10, rb.calculateRecovery(10, 10));
  }

  /**
   * Life forms should be able to recover to max health, but not
   * go over.
   */
  @Test
  public void testRecoverToMax() {
    assertEquals(100, rb.calculateRecovery(91, 100));
    assertEquals(10, rb.calculateRecovery(9, 10));
    assertEquals(5, rb.calculateRecovery(5, 5));
  }

  /**
   * Life forms should be able to recover the full amount of recovery
   * if it does not go over their maximum health..
   */
  @Test
  public void testRecoverFull() {
    assertEquals(100, rb.calculateRecovery(91, 100));
    assertEquals(88, rb.calculateRecovery(80, 100));
    assertEquals(55, rb.calculateRecovery(50, 100));
    assertEquals(4, rb.calculateRecovery(3, 5));
  }

  /**
   * Life forms should not be able to recover if they are dead.
   * A life form is dead if their current health points is zero.
   */
  @Test
  public void testNoRecoveryDead() {
    assertEquals(0, rb.calculateRecovery(0, 10));
  }
  
}
