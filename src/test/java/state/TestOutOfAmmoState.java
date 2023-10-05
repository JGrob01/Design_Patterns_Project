package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Cell;
import environment.Environment;
import exceptions.WeaponException;
import lifeform.MockLifeForm;
import weapon.MockWeapon;

/**
 * @author isaac
 * Tests for the out of ammo state
 */
public class TestOutOfAmmoState {
  
  /**
   * Tests initialization
   * @throws WeaponException
   */
  @Test
  public void testInitialization() throws WeaponException {
    MockLifeForm lf = new MockLifeForm("Joel", 10);
    MockWeapon gun = new MockWeapon();
    lf.pickUpWeapon(gun);
    assertEquals(gun, lf.getWeapon());
  }
  
  /**
   * A gun should reload properly
   * @throws WeaponException
   */
  @Test
  public void testReload() throws WeaponException {
    MockWeapon gun = new MockWeapon();
    while(gun.getCurrentAmmo() > 0) {
      gun.fire(10);
    }
    gun.reload();
    assertEquals(gun.getMaxAmmo(), gun.getCurrentAmmo());
  }

  /**
   * Tests that
   * @throws WeaponException
   */
  @Test
  public void testMoveReload() throws WeaponException {
    MockLifeForm lf = new MockLifeForm("Joel", 10);
    Environment env = Environment.getEnvironment(5, 5);
    AiContext ac = new AiContext(lf, env);
    
    MockWeapon gun = new MockWeapon();
    lf.pickUpWeapon(gun);
    env.addLifeForm(lf, 2, 2);
    while(gun.getCurrentAmmo() > 0) {
      gun.fire(10);
    }
    ac.setCurrentState(ac.getOutOfAmmoState());
    ac.execute();
    assertEquals(ac.getHasWeaponState(), ac.getCurrentState());
  }
  
  /**
   * tests when dead
   * @throws WeaponException
   */
  @Test
  public void testDead() throws WeaponException {
    MockLifeForm lf = new MockLifeForm("Joel", 10);
    Environment env = Environment.getEnvironment(5, 5);
    AiContext ac = new AiContext(lf, env);
    
    MockWeapon gun = new MockWeapon();
    lf.pickUpWeapon(gun);
    env.addLifeForm(lf, 2, 2);
    while(gun.getCurrentAmmo() > 0) {
      gun.fire(10);
    }
    lf.takeHit(400);
    ac.execute();
    assertEquals(ac.getDeadState(), ac.getCurrentState());
  }

}
