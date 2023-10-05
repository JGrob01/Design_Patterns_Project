package command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import environment.Cell;
import environment.Environment;
import exceptions.WeaponException;
import gui.GameBoard;
import gui.InfoPanel;
import lifeform.MockLifeForm;
import weapon.MockWeapon;

/**
 * @author isaac A class of tests to ensure proper command functionality
 */
public class TestCommand {
  private GameBoard gb;
  private Environment env;
  Cell cell;
  MockLifeForm lf;
  InfoPanel info;

  /**
   * Initializes common information used by tests
   */
  @Before
  public void init() {
    info = new InfoPanel();
    gb = new GameBoard(5, 5, info);
    env = Environment.getEnvironment(5, 5);
    cell = env.getCell(4, 4);
    lf = new MockLifeForm("Bobburt", 50, 10);
    gb.setCurrentCell(cell);
  }

  /**
   * Test the move command
   * 
   * @author Isaac
   */
  @Test
  public void testMove() {
    MoveCommand mc = new MoveCommand(gb);
    lf.setLocation(4, 4);
    env.addLifeForm(lf, 4, 4);
    mc.execute();
    assertEquals(lf, env.getLifeForm(1, 4));

    env.clearBoard();
  }

  /**
   * Test the reload command
   * 
   * @author Isaac
   * @throws WeaponException
   */
  @Test
  public void testReload() throws WeaponException {
    MockWeapon wp = new MockWeapon();
    lf.pickUpWeapon(wp);
    ReloadCommand rc = new ReloadCommand(gb);
    env.addLifeForm(lf, 4, 4);
    wp.fire(1);
    rc.execute();
    assertEquals(4, wp.getCurrentAmmo());

    env.clearBoard();
  }

  /**
   * Test the turn north command
   * 
   * @author Isaac
   */
  @Test
  public void testTurnNorth() {
    lf.faceSouth();
    TurnNorthCommand tn = new TurnNorthCommand(gb);
    env.addLifeForm(lf, 4, 4);
    tn.execute();
    assertEquals(1, lf.getDirection());

    env.clearBoard();
  }

  /**
   * Test the turn south command
   * 
   * @author Isaac
   */
  @Test
  public void testTurnSouth() {
    TurnSouthCommand ts = new TurnSouthCommand(gb);
    env.addLifeForm(lf, 4, 4);
    ts.execute();
    assertEquals(3, lf.getDirection());

    env.clearBoard();
  }

  /**
   * Test the turn east command
   * 
   * @author Isaac
   */
  @Test
  public void testTurnEast() {
    TurnEastCommand te = new TurnEastCommand(gb);
    env.addLifeForm(lf, 4, 4);
    te.execute();
    assertEquals(2, lf.getDirection());

    env.clearBoard();
  }

  /**
   * Test the turn west command
   * 
   * @author Isaac
   */
  @Test
  public void testTurnWest() {
    TurnWestCommand tw = new TurnWestCommand(gb);
    env.addLifeForm(lf, 4, 4);
    tw.execute();
    assertEquals(4, lf.getDirection());

    env.clearBoard();
  }

  /**
   * Tests drop with space available
   * 
   * @author Isaac
   */
  @Test
  public void testDropWithSpace() {
    DropCommand dc = new DropCommand(gb);
    MockWeapon wp = new MockWeapon();
    lf.pickUpWeapon(wp);
    env.addLifeForm(lf, 4, 4);
    dc.execute();
    assertNull(lf.getWeapon());

    env.clearBoard();
  }

  /**
   * Test drop with no space available
   * 
   * @author Isaac
   */
  @Test
  public void testDropWithoutSpace() {
    MockWeapon wp = new MockWeapon();
    lf.pickUpWeapon(wp);
    MockWeapon wp1 = new MockWeapon();
    MockWeapon wp2 = new MockWeapon();
    env.addWeapon(wp1, 4, 4);
    env.addWeapon(wp2, 4, 4);
    env.addLifeForm(lf, 4, 4);
    DropCommand dc = new DropCommand(gb);
    dc.execute();
    assertEquals(wp, lf.getWeapon());

    env.clearBoard();
  }

  /**
   * Test acquire command with nothing available and then with one weapon
   * available
   * 
   * @author Isaac
   */
  @Test
  public void testAcquireAvailablity() {
    env.addLifeForm(lf, 4, 4);
    AcquireCommand ac = new AcquireCommand(gb);
    ac.execute();
    assertNull(lf.getWeapon());

    MockWeapon wp1 = new MockWeapon();
    env.addWeapon(wp1, 4, 4);
    ac.execute();
    assertEquals(wp1, lf.getWeapon());

    env.clearBoard();
  }

  /**
   * Test acquire command when weapon already equipped
   * 
   * @author Isaac
   */
  @Test
  public void testAcquireWithWeaponEquipped() {
    MockWeapon wp = new MockWeapon();
    lf.pickUpWeapon(wp);
    MockWeapon wp2 = new MockWeapon();
    AcquireCommand ac = new AcquireCommand(gb);
    env.addWeapon(wp2, 4, 4);
    env.addLifeForm(lf, 4, 4);
    ac.execute();
    assertEquals(wp2, lf.getWeapon());
    assertEquals(wp, cell.getWeapon1());

    env.clearBoard();
  }

}
