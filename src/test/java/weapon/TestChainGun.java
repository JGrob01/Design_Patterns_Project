package weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import exceptions.WeaponException;

/**
 * Functionality tests for the weapon "Chain Gun"
 * @author Isaac
 */
public class TestChainGun {

  /**
   * If the distance is within range, damage should be calculated.
   * @throws WeaponException 
   * @author Isaac
   */
  @Test
  public void testWithinRange() throws WeaponException {
    ChainGun gun = new ChainGun();
    assertEquals(2, gun.fire(10));
    assertEquals(12, gun.fire(50));
  }
 
  /**
   * No damage if negative range.
   * @throws WeaponException
   * @author Isaac
   */
  @Test(expected = WeaponException.class)
  public void testNegativeDistance() throws WeaponException {
    ChainGun gun = new ChainGun();
    gun.fire(-5);
  }

}
