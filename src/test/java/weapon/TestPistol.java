package weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import exceptions.WeaponException;

/**
 * Test pistol functionality.
 * @author Devin
 */
public class TestPistol {

  /**
   * Weapons should do a certain amount of damage based on how far
   * away the target is.
   * @author Devin
   */
  @Test
  public void testRangeDamage() throws WeaponException {
    Pistol p = new Pistol();
    assertEquals(11, p.fire(1));
    assertEquals(2, p.fire(p.getMaxRange()));
  }

  /**
   * Test that passing in a negative distance returns an exception,
   * and that passing in a positive distance does not.
   * @author Devin
   */
  @Test
  public void testNegativeDistance() {
    Pistol p = new Pistol();
    try {
      p.fire(0);
      // Pass
    } catch (WeaponException e) {
      fail();
    }
    try {
      p.fire(-1);
      fail();
    } catch (WeaponException e) {
      // Pass
    }
  }
  
}
