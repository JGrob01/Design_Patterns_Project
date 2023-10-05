package state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import environment.Environment;
import gameplay.SimpleTimer;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;

public class TestSimulator {

  /**
   * Test that populating the world works, where
   * a minimum amount of humans and aliens are added, 
   * with one weapon on the board per alien and human.
   * 
   * @author Devin
   */
  @Test
  public void testPopulateWorld() {
    Environment e = Environment.getEnvironment(5, 5);
    e.clearBoard();
    SimpleTimer t = new SimpleTimer();
    Simulator s = new Simulator(e, t, 10, 10);

    int humans = 0;
    int aliens = 0;
    int weapons = 0;
    
    for (int i = 0; i < e.getNumRows(); i++) {
      for (int j = 0; j < e.getNumCols(); j++) {
        weapons += e.getCell(i, j).getWeaponsCount();

        LifeForm lf = e.getCell(i, j).getLifeForm();
        if (lf != null) {
          if (lf instanceof Human) {
            humans++;
          } else if (lf instanceof Alien) {
            aliens++;
          }
        }
      }
    }

    if (humans != 10) {
      fail("Incorrect number of humans (" + humans + ") were created");
    }

    if (aliens != 10) {
      fail("Incorrect number of aliens (" + aliens + ") were created");
    }

    if (weapons != 20) {
      fail("Incorrect number of weapons (" + weapons + ") were created");
    }
  }

  /**
   * Upon updating the time, each life form should be given their turn.
   * Each AIContext should perform a behavior for its respective life form,
   * based on its current state.
   * 
   * @author Devin
   */
  @Test
  public void testUpdateTime() {
    Environment e = Environment.getEnvironment(5, 5);
    SimpleTimer t = new SimpleTimer();
    e.clearBoard();
    MockSimulator s = new MockSimulator(e, t, 5, 0);

    assertEquals(s.getTestContext().getNoWeaponState(),
                 s.getTestContext().getCurrentState());
    
    s.updateTime(1337);

    assertEquals(s.getTestContext().getHasWeaponState(),
                s.getTestContext().getCurrentState());
  }
  
}
