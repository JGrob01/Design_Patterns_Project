package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import exceptions.EnvironmentException;
import gui.GameBoard;
import gui.InfoPanel;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.MockWeapon;
import weapon.Weapon;

public class TestEnvironment {
  
  private static final String LF_INITIAL_NAME = "LF";
  private static final int LF_INITIAL_LIFE_POINTS = 5;
  private static final int LF_INITIAL_ATTACK_STRENGTH = 5;
  private static final int ENV_ROWS = 5;
  private static final int ENV_COLUMNS = 5;
  private Environment env;
  private LifeForm lf;

  /**
   * This method is run before each test call.
   */
  @Before
  public void init() {
    env = Environment.getEnvironment(ENV_ROWS, ENV_COLUMNS);
    lf = new MockLifeForm(LF_INITIAL_NAME, LF_INITIAL_LIFE_POINTS, 3);
  }
  
  /**
   * Lifeforms should move north with or without obstacles
   * @author Isaac
   */
  @Test
  public void testMoveNorth() {
    env.addLifeForm(lf, 4, 4);
    assertTrue(lf.move(null));
    assertEquals(4, lf.getColumn());
    assertEquals(1, lf.getRow());
    assertEquals(lf, env.getLifeForm(1, 4));
    assertNull(env.getLifeForm(4, 4));
    
    env.clearBoard();
    
    env.addLifeForm(lf, 4, 4);
    MockLifeForm lf2 = new MockLifeForm("lifeform 2", 50);
    env.addLifeForm(lf2, 1, 4);
    
    assertTrue(lf.move(null));
    assertEquals(4, lf.getColumn());
    assertEquals(2, lf.getRow());
    assertEquals(lf, env.getLifeForm(2, 4));
    assertNull(env.getLifeForm(4, 4));
    
    env.clearBoard();
  }
  
  /**
   * Lifeforms should move south with or without obstacles
   * @author Isaac
   */
  @Test
  public void testMoveSouth() {
    lf.faceSouth();
    env.addLifeForm(lf, 0, 0);
    assertTrue(lf.move(null));
    assertEquals(0, lf.getColumn());
    assertEquals(3, lf.getRow());
    assertEquals(lf, env.getLifeForm(3, 0));
    assertNull(env.getLifeForm(0, 0));
    
    env.clearBoard();
    
    env.addLifeForm(lf, 0, 0);
    MockLifeForm lf2 = new MockLifeForm("lifeform 2", 50);
    env.addLifeForm(lf2, 3, 0);
    
    assertTrue(lf.move(null));
    assertEquals(0, lf.getColumn());
    assertEquals(2, lf.getRow());
    assertEquals(lf, env.getLifeForm(2, 0));
    assertNull(env.getLifeForm(0, 0));
    
    env.clearBoard();
  }
  
  /**
   * Lifeforms should move west with or without obstacles
   * @author Isaac
   */
  @Test
  public void testMoveWest() {
    lf.faceWest();
    env.addLifeForm(lf, 1, 3);
    assertTrue(lf.move(null));
    assertEquals(1, lf.getRow());
    assertEquals(0, lf.getColumn());
    assertEquals(lf, env.getLifeForm(1, 0));
    assertNull(env.getLifeForm(3, 1));
    
    env.clearBoard();
    
    env.addLifeForm(lf, 1, 3);
    MockLifeForm lf2 = new MockLifeForm("lifeform 2", 50);
    env.addLifeForm(lf2, 1, 0);
    
    assertTrue(lf.move(null));
    assertEquals(1, lf.getRow());
    assertEquals(1, lf.getColumn());
    assertEquals(lf, env.getLifeForm(1, 1));
    assertNull(env.getLifeForm(1, 3));
    
    env.clearBoard();
  }
  
  /**
   * Lifeforms should move east with or without obstacles
   * @author Isaac
   */
  @Test
  public void testMoveEast() {
    lf.faceEast();
    env.addLifeForm(lf, 2, 1);
    assertTrue(lf.move(null));
    assertEquals(2, lf.getRow());
    assertEquals(4, lf.getColumn());
    assertEquals(lf, env.getLifeForm(2, 4));
    assertNull(env.getLifeForm(2, 1));
    
    env.clearBoard();
    
    env.addLifeForm(lf, 2, 1);
    MockLifeForm lf2 = new MockLifeForm("lifeform 2", 50);
    env.addLifeForm(lf2, 2, 4);
    
    assertTrue(lf.move(null));
    assertEquals(2, lf.getRow());
    assertEquals(3, lf.getColumn());
    assertEquals(lf, env.getLifeForm(2, 3));
    assertNull(env.getLifeForm(2, 1));
    
    env.clearBoard();
  }
  
  /**
   * Tests movement at the edge of the gameboard
   * @author Isaac
   */
  @Test
  public void testMoveAtEdges() {
    lf.setLocation(0, 0);
    env.addLifeForm(lf, 0, 0);
    assertFalse(lf.move(null));
    lf.faceWest();
    assertFalse(lf.move(null));
    env.clearBoard();
    lf.setLocation(5, 5);
    env.addLifeForm(lf, 5, 5);
    lf.faceEast();
    assertFalse(lf.move(null));
    lf.faceSouth();
    assertFalse(lf.move(null));
    
    env.clearBoard();
  }

  
  // Misc lab 6 tests
  
  /**
   * Test that the correct cell is returned.
   * @author Devin
   */
  @Test
  public void testGetCell() {
    assertNotNull(env.getCell(0, 0));
    assertNull(env.getCell(-1, 0));
    assertNull(env.getCell(0, -1));
    assertNull(env.getCell(4, 5));
    assertNull(env.getCell(5, 4));
  }

  /**
   * Test that the correct cell is returned.
   * @author Devin
   */
  @Test
  public void testGetCellRow() {
    Cell c = new Cell();
    assertEquals(-1, env.getCellRow(c));
    c = env.getCell(2, 2);
    assertEquals(2, env.getCellRow(c));
  }

  /**
   * Test that the correct cell is returned.
   * @author Devin
   */
  @Test
  public void testGetCellColumn() {
    Cell c = new Cell();
    assertEquals(-1, env.getCellColumn(c));
    c = env.getCell(2, 2);
    assertEquals(2, env.getCellColumn(c));
  }


  // Lab 5 tests

  /**
   * Test initialization of Environment.
   * @author Devin
   */
  @Test
  public void testInitialization() {
    Environment env2 = Environment.getEnvironment(ENV_ROWS, ENV_COLUMNS);
    assertEquals(env, env2);
    assertNull(env.getLifeForm(0, 0));
    assertNull(env.getLifeForm(ENV_ROWS - 1, ENV_COLUMNS - 1));
    
    env.clearBoard();
  }
  
  /**
   * A weapon should be able to be added to a cell
   * @author Isaac
   */
  @Test
  public void testAddWeapon() {
    MockWeapon weapon = new MockWeapon();
    assertTrue(env.addWeapon(weapon, 1, 1));
    
    MockWeapon weapon2 = new MockWeapon();
    assertTrue(env.addWeapon(weapon2, 1, 1));
    
    MockWeapon weapon3 = new MockWeapon();
    assertFalse(env.addWeapon(weapon3, 1, 1));
    
    env.clearBoard();
  }
  
  /**
   * A weapon should be able to be removed from a cell
   * @author Isaac
   */
  @Test
  public void testRemoveWeapon() {
    MockWeapon weapon = new MockWeapon();
    env.addWeapon(weapon, 1, 1);
    
    MockWeapon weapon2 = new MockWeapon();
    env.addWeapon(weapon2, 1, 1);
    
    assertEquals(weapon, env.removeWeapon(weapon, 1, 1));
    assertEquals(weapon2, env.removeWeapon(weapon2, 1, 1));
    
    env.clearBoard();
  }
  
  /**
   * Tests distance accuracy along the same row
   * @author Isaac
   * @throws EnvironmentException 
   */
  @Test
  public void testDistanceSameRow() throws EnvironmentException {
    LifeForm temp = new MockLifeForm("Temp", 60);
    env.addLifeForm(temp, 1, 3);
    
    LifeForm temp2 = new MockLifeForm("Temp2", 60);
    env.addLifeForm(temp2, 1, 2);
    
    double result = env.getDistance(temp, temp2);
    assertEquals(5.0, result, 0.0);
    
    assertEquals(5.0, env.getDistance(1, 3, 1, 2), 0.0);
    
    env.clearBoard();
    
  }
    
  /**
   * Tests distance accuracy along the same column
   * @author Isaac
   * @throws EnvironmentException 
   */
  @Test
  public void testDistanceSameColumn() throws EnvironmentException {
    LifeForm temp = new MockLifeForm("Temp", 60);
    env.addLifeForm(temp, 2, 4);
    
    LifeForm temp2 = new MockLifeForm("Temp2", 60);
    env.addLifeForm(temp2, 4, 4);
    
    assertEquals(10.0, env.getDistance(temp, temp2), 0.0);
    
    assertEquals(10.0, env.getDistance(2, 4, 4, 4), 0.0);
    
    env.clearBoard();
  }
  
  /**
   * Tests distance accuracy on different row and collumn
   * @author Isaac
   * @throws EnvironmentException 
   */
  @Test
  public void testDistanceDifferent() throws EnvironmentException {
    LifeForm temp = new MockLifeForm("Temp", 60);
    env.addLifeForm(temp, 0, 2);
    
    LifeForm temp2 = new MockLifeForm("Temp2", 60);
    env.addLifeForm(temp2, 4, 4);
    
    assertEquals(22.35, env.getDistance(temp, temp2), 0.1);
    
    assertEquals(22.35, env.getDistance(0, 2, 4, 4), 0.1);
    
    env.clearBoard();
  }

  /**
   * Adding a life form should make a new life form object and put it
   * in the given row and column. If the row and column is not available,
   * then it should return false.
   */
  @Test
  public void testAddLifeForm() {
    LifeForm lf2 = new MockLifeForm(LF_INITIAL_NAME, LF_INITIAL_LIFE_POINTS);
    assertTrue(env.addLifeForm(lf, 1, 1));
    assertFalse(env.addLifeForm(lf2, 1, 1));
    assertEquals(lf, env.getLifeForm(1, 1));
    // Cleanup
    env.removeLifeForm(1, 1);
  }

  /**
   * Life forms should not be able to be added in an index that is outside
   * the array.
   */
  @Test
  public void testAddLifeFormBorders() {
    // Valid
    assertTrue(env.addLifeForm(lf, 0, 0));
    assertTrue(env.addLifeForm(lf, ENV_ROWS - 1, ENV_COLUMNS - 1));
    // Invalid
    assertFalse(env.addLifeForm(lf, -1, -1));
    assertFalse(env.addLifeForm(lf, 0, -1));
    assertFalse(env.addLifeForm(lf, -1, 0));
    assertFalse(env.addLifeForm(lf, ENV_ROWS, ENV_COLUMNS));
    assertFalse(env.addLifeForm(lf, ENV_ROWS - 1, ENV_COLUMNS));
    assertFalse(env.addLifeForm(lf, ENV_ROWS, ENV_COLUMNS - 1));
    // Cleanup
    env.removeLifeForm(0, 0);
    env.removeLifeForm(4, 4);
  }

  /**
   * Prevent users from accessing indices outside of bounds.
   */
  @Test
  public void testGetLifeFormBorders() {
    // Valid
    env.addLifeForm(lf, ENV_ROWS - 1, ENV_COLUMNS - 1);
    assertEquals(lf, env.getLifeForm(ENV_ROWS - 1, ENV_COLUMNS - 1));
    env.addLifeForm(lf, 0, 0);
    assertEquals(lf, env.getLifeForm(0, 0));
    // Invalid
    assertNull(env.getLifeForm(-1, -1));
    assertNull(env.getLifeForm(0, -1));
    assertNull(env.getLifeForm(-1, 0));
    assertNull(env.getLifeForm(ENV_ROWS, ENV_COLUMNS));
    assertNull(env.getLifeForm(ENV_ROWS - 1, ENV_COLUMNS));
    assertNull(env.getLifeForm(ENV_ROWS, ENV_COLUMNS - 1));
    // Cleanup
    env.removeLifeForm(0, 0);
    env.removeLifeForm(4, 4);
  }

  /**
   * Removing a life form should free up the row and column.
   */
  @Test
  public void testRemoveLifeForm() {
    env.addLifeForm(lf, 1, 1);
    env.removeLifeForm(1, 1);
    assertNull(env.getLifeForm(1, 1));
  }

}
