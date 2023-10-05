package state;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import lifeform.MockLifeForm;
import weapon.MockWeapon;

public class TestDeadState {
  private Environment env;
  private MockLifeForm lf;
  private AiContext context;

  /**
   * Initialize some instance variables before each test.
   */
  @Before
  public void init() {
    env = Environment.getEnvironment(5, 5);
    lf = new MockLifeForm("Joe", 100, 5);
    context = new AiContext(lf, env);
    lf.setLocation(2, 2);
  }

  @Test
  public void testWithWeapon() {
    lf.pickUpWeapon(new MockWeapon());
    context.setCurrentState(context.getHasWeaponState());
    lf.takeHit(1000);
    context.execute();
    context.execute();
    assertFalse(lf.hasWeapon());
  }

  @Test
  public void testWithoutWeapon() {
    assertFalse(lf.hasWeapon());
    context.setCurrentState(context.getNoWeaponState());
    lf.takeHit(1000);
    context.execute();
    assertFalse(lf.hasWeapon());
  }

}
