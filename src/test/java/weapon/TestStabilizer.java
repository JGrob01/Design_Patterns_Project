package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestStabilizer {

  /**
   * Tests PlasmaCannon with Stabilizer
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testPlasamaStab() throws WeaponException, AttachmentException {
    PlasmaCannon pc = new PlasmaCannon();
    Stabilizer s = new Stabilizer(pc);
    assertEquals(62, s.fire(40));
  }

  /**
   * Tests PlasmaCannon with 2 Stabilizers
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testPlasamaStabStab() throws WeaponException, AttachmentException {
    Weapon s = new Stabilizer(new PlasmaCannon());
    Stabilizer s2 = new Stabilizer(s);
    assertEquals(77, s2.fire(40));
  }
  
  /**
   * Tests Pistol with Stabilizer and Scope
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testPistolScopeStab() throws WeaponException, AttachmentException {
    Weapon s = new Scope(new Pistol());
    Stabilizer s2 = new Stabilizer(s);
    assertEquals(6, s2.fire(40));
  }
  
  /**
   * Tests ChainGun with Stabilizer and Powerbooster
   * @author John
   * @throws WeaponException
   */
  @Test
  public void testChainStabPowerB() throws WeaponException, AttachmentException {
    Weapon s = new PowerBooster(new ChainGun());
    Stabilizer s2 = new Stabilizer(s);
    assertEquals(25, s2.fire(40));
  }
}