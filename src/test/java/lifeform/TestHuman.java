package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestHuman {

  private static final String H_INITIAL_NAME = "H";
  private static final int H_INITIAL_LIFE_POINTS = 5;
  private static final int H_INITIAL_ARMOR_POINTS = 5;
  private Human human;

  @Before
  public void init() {
    human = new Human(H_INITIAL_NAME, H_INITIAL_LIFE_POINTS, H_INITIAL_ARMOR_POINTS);
  }
  
  /**
   * Max speed should be 3 for humans
   * @author Isaac
   */
  @Test
  public void testMaxSpeed() {
    assertEquals(3, human.maxSpeed);
  }
  
  
  // Lab 5 and before tests
  
  /**
   * Test that the human initializes with the correct values.
   */
  @Test
  public void testInitialization() {
    assertTrue(H_INITIAL_NAME.equals(human.getName()));
    assertEquals(H_INITIAL_LIFE_POINTS, human.getCurrentLifePoints());
    assertEquals(H_INITIAL_ARMOR_POINTS, human.getArmorPoints());
  }

  /**
   * Test that setting armor points correctly sets the value.
   */
  @Test
  public void testSetArmorPoints() {
    human.setArmorPoints(10);
    assertEquals(10, human.getArmorPoints());
  }

  /**
   * Test that getting armor points returns the correct value.
   */
  @Test
  public void testGetArmorPoints() {
    assertEquals(H_INITIAL_ARMOR_POINTS, human.getArmorPoints());
  }

  /**
   * Test that armor points cannot go below zero. If a negative value
   * is entered, then set the armor points to zero.
   */
  @Test
  public void testArmorPointsBorders() {
    human.setArmorPoints(-5);
    assertEquals(0, human.getArmorPoints());
  }

  /**
   * Test that the human initializes with a minimum of zero armor points.
   */
  @Test
  public void testInitArmorBorders() {
    assertTrue(human.getArmorPoints() >= 0);
  }

  /**
   * Test that the default strength is what it should be.
   */
  @Test
  public void testDefaultStrength() {
    Human b = new Human("H1", 10, 10);
    assertEquals(5, b.getAttackStrength());
  }

  /**
   * Armor should absorb all the damage when damage is less than armor.
   */
  @Test
  public void testArmorAbsorbsAllDamage() {
    Human b = new Human("H1", 10, 5);
    b.takeHit(4);
    assertEquals(10, b.getCurrentLifePoints());
  }

  /**
   * Armor should reduce the damage that the life form takes.
   */
  @Test
  public void testArmorReducesDamage() {
    Human b = new Human("H1", 10, 5);
    b.takeHit(6);
    assertEquals(9, b.getCurrentLifePoints());
  }

  /**
   * Armor should absorb all the damage of damage is equal to armor.
   */
  @Test
  public void testArmorAbsorbsAllDamageBorders() {
    Human b = new Human("H1", 10, 5);
    b.takeHit(5);
    assertEquals(10, b.getCurrentLifePoints());
  }

}
