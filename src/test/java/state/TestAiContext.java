package state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import lifeform.Human;
import lifeform.LifeForm;

public class TestAiContext {

  private Environment env;
  private LifeForm lf;
  private AiContext context;

  /**
   * Initialize some instance variables before each test.
   */
  @Before
  public void init() {
    env = Environment.getEnvironment(5, 5);
    lf = new Human("LF", 100, 5);
    context = new AiContext(lf, env);
  }

  /**
   * Test that the state of the context is changed properly.
   * 
   * @author Devin
   */
  @Test
  public void testChangeState() {
    ActionState hasWeaponState = context.getHasWeaponState();
    context.setCurrentState(context.getHasWeaponState());
    assertEquals(hasWeaponState, context.getCurrentState());

    ActionState outOfAmmoState = context.getOutOfAmmoState();
    context.setCurrentState(context.getOutOfAmmoState());
    assertEquals(outOfAmmoState, context.getCurrentState());

    ActionState noWeaponState = context.getNoWeaponState();
    context.setCurrentState(context.getNoWeaponState());
    assertEquals(noWeaponState, context.getCurrentState());
  }

  /**
   * Test that we can get the correct states of the context.
   * 
   * @author Devin
   */
  @Test
  public void testGetState() {
    assertNotNull(context.getHasWeaponState());
    assertNotNull(context.getDeadState());
    assertNotNull(context.getOutOfAmmoState());
    assertNotNull(context.getNoWeaponState());
  }
  
}
