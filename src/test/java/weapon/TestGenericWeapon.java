package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.WeaponException;

/**
 * Test Generic Weapon.
 * 
 * @author John
 */
public class TestGenericWeapon {

  /**
   * Weapons should use ammo
   * 
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testUsesAmmo() throws WeaponException {
    MockWeapon m = new MockWeapon();
    assertEquals(4, m.currentAmmo);
    m.fire(30);
    assertEquals(3, m.currentAmmo);
  }

  /**
   * Weapons should have a rate of fire
   * 
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testRateOfFire() throws WeaponException {
    MockWeapon m = new MockWeapon();
    assertEquals(1, m.rateOfFire);
    m.fire(30);
    assertEquals(0, m.rateOfFire);
  }

  /**
   * Weapons should reload
   * 
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testReloaded() throws WeaponException {
    MockWeapon m = new MockWeapon();
    assertEquals(4, m.currentAmmo);
    m.fire(30);
    m.reload();
    assertEquals(4, m.currentAmmo);
  }

  /**
   * Weapons should not do damage if out of ammo
   * 
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testOutOfAmmo() throws WeaponException {
    MockWeapon m = new MockWeapon();
    assertEquals(4, m.currentAmmo);
    m.fire(30);
    m.fire(30);
    m.fire(30);
    m.fire(30);
    assertEquals(0, m.currentAmmo);
    assertEquals(0, m.fire(30));
  }

  /**
   * Weapons should not do damage if target is past max range
   * 
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testOverMaxRange() throws WeaponException {
    MockWeapon m = new MockWeapon();
    assertEquals(0, m.fire(100));
  }
}