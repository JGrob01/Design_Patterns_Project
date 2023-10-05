package weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestScope {

  /**
   * Test that the damage and range of the scope is correct
   * when attached to a pistol.
   * @author Devin
   */
  @Test
  public void testOneWithPistol() throws WeaponException, AttachmentException {
    // Test damage
    Pistol p = new Pistol();
    Scope s = new Scope(p);
    assertEquals(21, s.fire(1));
    assertEquals(7, s.fire(55));
    // Test range
    assertEquals(p.getMaxRange() + 10, s.getMaxRange());
  }

  /**
   * Test that the damage and range of the scope is correct
   * when attached to a pistol and an extra scope.
   * @author Devin
   */
  @Test
  public void testTwoWithPistol() throws WeaponException, AttachmentException {
    // Test damage
    Scope b = new Scope(new Pistol());
    Scope s = new Scope(b);
    assertEquals(41, s.fire(1));
    assertEquals(12, s.fire(65));
    // Test range
    assertEquals(b.getMaxRange() + 10, s.getMaxRange());
  }

  /**
   * Test that damage and range is correct when attached
   * to the given attachment and weapon.
   * @author Devin
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void testScopePowerBoosterChainGun() throws AttachmentException, WeaponException {
    Weapon b = new PowerBooster(new ChainGun());
    Scope s = new Scope(b);
    // Test damage
    assertEquals(22, s.fire(30));
    assertEquals(20, s.fire(30));
    // Test range
    assertEquals(70, s.getMaxRange());
  }

  /**
   * Test that damage and range is correct when attached
   * to the given attachment and weapon.
   * @author Devin
   * @throws AttachmentException
   * @throws WeaponException
   */
  @Test
  public void testScopeStabilizerPlasmaCannon() throws AttachmentException, WeaponException {
    Weapon c = new PlasmaCannon();
    Weapon b = new Stabilizer(c);
    Scope s = new Scope(b);
    // Test damage
    assertEquals(111, s.fire(10));
    // Reset shotsLeft
    c.updateTime(1);
    assertEquals(82, s.fire(10));
    // Test range
    assertEquals(50, s.getMaxRange());
  }

  /**
   * Test that no more than two attachments can be used.
   * @author Devin
   */
  @Test
  public void testAttachmentCount() {
    try {
      Scope b = new Scope(new Scope(new Scope(new Pistol())));
      fail();
    } catch (AttachmentException e) {
      // Pass
    }
    try {
      Scope b = new Scope(new Scope(new Pistol()));
      // Pass
    } catch (AttachmentException e) {
      fail();
    }
  }
  
}
