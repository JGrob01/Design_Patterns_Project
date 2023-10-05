package state;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Cell;
import environment.Environment;
import lifeform.MockLifeForm;
import weapon.MockWeapon;

/**
 * @author isaac
 * Tests for the NoWeaponState
 */
public class TestNoWeaponState {

  /**
   * if weapon in cell, change to has weapon state
   */
  @Test
  public void testWeaponInCell() {
    MockLifeForm lf = new MockLifeForm("Joel", 10);
    Environment env = Environment.getEnvironment(5, 5);
    AiContext ac = new AiContext(lf, env);
    
    MockWeapon gun = new MockWeapon();
    env.addLifeForm(lf, 2, 2);
    Cell c = env.getCell(2, 2);
    c.addWeapon(gun);
    ac.execute();
    assertEquals(ac.getHasWeaponState(), ac.getCurrentState());
  }
  
  /**
   * If no weapon in cell it stays in no weapon state
   */
  @Test
  public void testNoWeaponInCell() {
    MockLifeForm lf = new MockLifeForm("Joel", 10);
    Environment env = Environment.getEnvironment(5, 5);
    AiContext ac = new AiContext(lf, env);
    
    env.addLifeForm(lf, 2, 2);
    Cell c = env.getCell(2, 2);
    c.removeWeapon(c.getWeapon1());
    c.removeWeapon(c.getWeapon2());
    
    ac.execute();
    assertEquals(ac.getNoWeaponState(), ac.getCurrentState());
  }
  
  /**
   * If dead, change state to dead state
   */
  @Test
  public void testDead() {
    MockLifeForm lf = new MockLifeForm("Joel", 10);
    Environment env = Environment.getEnvironment(5, 5);
    AiContext ac = new AiContext(lf, env);
    
    env.addLifeForm(lf, 2, 2);
    lf.takeHit(100);
    ac.execute();
    assertEquals(ac.getDeadState(), ac.getCurrentState());
  }

}
