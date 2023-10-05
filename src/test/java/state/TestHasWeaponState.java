package state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import environment.Environment;
import lifeform.Human;
import lifeform.MockLifeForm;
import weapon.PlasmaCannon;

/**
 * @author John Grobaker 
 * Tests for the has weapon state
 */
public class TestHasWeaponState {
  @Test
  public void testNoTarget() {
    MockLifeForm lf = new MockLifeForm("Joe", 100);
    Environment env = Environment.getEnvironment(5, 5);
    env.clearBoard();
    AiContext context = new AiContext(lf, env);
    context.setCurrentState(context.getHasWeaponState());
    lf.pickUpWeapon(new PlasmaCannon());
    env.addLifeForm(lf, 0, 3);
    lf.faceWest();
    assertNull(env.getCell(0, 2).getLifeForm());
    assertNull(env.getCell(0, 1).getLifeForm());
    assertNull(env.getCell(0, 0).getLifeForm());
    context.execute();
    assertTrue(lf.getWeapon().getCurrentAmmo() == lf.getWeapon().getMaxAmmo());
  }

  @Test
  public void testSameTarget() {
    Human lfh1 = new Human("Joe", 100, 0);
    Environment env = Environment.getEnvironment(5, 5);
    env.clearBoard();
    AiContext context = new AiContext(lfh1, env);
    context.setCurrentState(context.getHasWeaponState());
    lfh1.pickUpWeapon(new PlasmaCannon());
    Human lfh2 = new Human("Jo", 100, 0);
    env.addLifeForm(lfh1, 1, 0);
    env.addLifeForm(lfh2, 0, 0);
    int health = lfh2.getCurrentLifePoints();
    context.execute();
    assertTrue(health == lfh2.getCurrentLifePoints());
  }

  @Test
  public void testTarget() {
    MockLifeForm lfh1 = new MockLifeForm("Joe", 100);
    Environment env = Environment.getEnvironment(5, 5);
    env.clearBoard();
    AiContext context = new AiContext(lfh1, env);
    context.setCurrentState(context.getHasWeaponState());
    lfh1.pickUpWeapon(new PlasmaCannon());
    MockLifeForm lfa = new MockLifeForm("Jo", 100);
    env.addLifeForm(lfh1, 1, 1);
    env.addLifeForm(lfa, 0, 1);
    int health = lfa.getCurrentLifePoints();
    context.execute();
    assertTrue(health != lfa.getCurrentLifePoints());
  }

  @Test
  public void testOneShotLeft() {
    MockLifeForm lfh1 = new MockLifeForm("Joe", 100);
    Environment env = Environment.getEnvironment(5, 5);
    env.clearBoard();
    AiContext context = new AiContext(lfh1, env);
    context.setCurrentState(context.getHasWeaponState());
    lfh1.pickUpWeapon(new PlasmaCannon());
    MockLifeForm lfa = new MockLifeForm("Jo", 100);
    env.addLifeForm(lfh1, 1, 0);
    env.addLifeForm(lfa, 0, 0);
    assertTrue(lfh1.getWeapon().getShotsLeft() == 1);
    context.execute();
    assertTrue(lfh1.getWeapon().getShotsLeft() == 0);
  }

  @Test
  public void testOutOfRange() {
    MockLifeForm lfh1 = new MockLifeForm("Joe", 100);
    Environment env = Environment.getEnvironment(5, 5);
    env.clearBoard();
    AiContext context = new AiContext(lfh1, env);
    context.setCurrentState(context.getHasWeaponState());
    lfh1.pickUpWeapon(new PlasmaCannon());
    MockLifeForm lfa = new MockLifeForm("Jo", 100);
    env.addLifeForm(lfh1, 4, 0);
    env.addLifeForm(lfa, 0, 0);
    context.execute();
    assertTrue(lfh1.getWeapon().getCurrentAmmo() < lfh1.getWeapon().getMaxAmmo());
  }

  @Test
  public void testDead() {
    MockLifeForm lf = new MockLifeForm("Joe", 100);
    Environment env = Environment.getEnvironment(5, 5);
    env.clearBoard();
    AiContext context = new AiContext(lf, env);
    context.setCurrentState(context.getHasWeaponState());
    env.addLifeForm(lf, 2, 2);
    lf.takeHit(1000);
    context.execute();
    assertEquals(context.getDeadState(), context.getCurrentState());
  }
}
