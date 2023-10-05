package weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import exceptions.WeaponException;

/**
 * Test PlasmaCannon.
 * 
 * @author John
 */
public class TestPlasmaCannon {

  /**
   * Weapons should do a certain amount of damage based on the range of target.
   * This tests the validity of different ranges while also testing how the ammo
   * capacity affects damage output
   * 
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testPlasmaCannonRanges() throws WeaponException {
    PlasmaCannon pc = new PlasmaCannon();
    assertEquals(50, pc.fire(40));
    pc.updateTime(1);
    assertEquals(37, pc.fire(6));
  }
}
