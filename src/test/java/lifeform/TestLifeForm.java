package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import exceptions.WeaponException;
import weapon.Pistol;

public class TestLifeForm {

  private static final String LF_INITIAL_NAME = "LF";
  private static final int LF_INITIAL_LIFE_POINTS = 5;
  private static final int LF_INITIAL_ATTACK_STRENGTH = 5;
  private LifeForm lf;

  /**
   * This method is run before each test call.
   */
  @Before
  public void init() {
    lf = new MockLifeForm(LF_INITIAL_NAME, LF_INITIAL_LIFE_POINTS);
  }

  /**
   * Test that max life points is properly initialized.
   */
  @Test
  public void testMaxLifePointsInit() {
    assertEquals(LF_INITIAL_LIFE_POINTS, lf.getMaxLifePoints());
  }


  // Lab 6 and below tests

  /**
   * A lifeform's default direction should return 1 for north
   * 
   * @author Isaac
   */
  @Test
  public void testDefaultDirection() {
    lf = new MockLifeForm("Bajorge", 100);

    assertEquals(1, lf.direction);
  }

  /**
   * A lifeform should return the following values for the coinciding directions:
   * 1 - North, 2 - East, 3 - South, 4 - West
   * 
   * @author Isaac
   */
  @Test
  public void testChangeDirection() {
    lf = new MockLifeForm("Flanders", 50);

    lf.faceEast();
    assertEquals(2, lf.direction);

    lf.faceSouth();
    assertEquals(3, lf.direction);

    lf.faceWest();
    assertEquals(4, lf.direction);

    lf.faceNorth();
    assertEquals(1, lf.direction);
  }

  // Lab 5 and before tests

  /**
   * Test can store valid row and col number
   * 
   * @author John
   */
  @Test
  public void testVaild() {
    lf = new MockLifeForm("John", 5);
    lf.setLocation(1, 2);
    assertEquals(1, lf.getRow());
    assertEquals(2, lf.getColumn());
  }

  /**
   * Test row and col initialized to -1’s
   * 
   * @author John
   */
  @Test
  public void testInit() {
    lf = new MockLifeForm("John", 5);
    lf.setLocation(-1, -1);
    assertEquals(-1, lf.getRow());
    assertEquals(-1, lf.getColumn());
  }

  /**
   * Test row and col remain -1 if either parameter from setLocation is negative
   * 
   * @author John
   */
  @Test
  public void testNeg() {
    lf = new MockLifeForm("John", 5);
    lf.setLocation(-10, -100);
    assertEquals(-1, lf.getRow());
    assertEquals(-1, lf.getColumn());
  }

  // Strategy tests

  /**
   * Test initiolization of the first constructor.
   */
  @Test
  public void testInitC1() {
    assertTrue(LF_INITIAL_NAME.equals(lf.getName()));
    assertEquals(LF_INITIAL_LIFE_POINTS, lf.getCurrentLifePoints());
  }

  /**
   * Test initialization of the second constructor.
   */
  @Test
  public void testInitC2() {
    lf = new MockLifeForm(LF_INITIAL_NAME, LF_INITIAL_LIFE_POINTS, LF_INITIAL_ATTACK_STRENGTH);
    assertTrue(LF_INITIAL_NAME.equals(lf.getName()));
    assertEquals(LF_INITIAL_LIFE_POINTS, lf.getCurrentLifePoints());
  }

  /**
   * Test that life points are correct.
   */
  @Test
  public void testLifePoints() {
    assertEquals(LF_INITIAL_LIFE_POINTS, lf.getCurrentLifePoints());
  }

  /**
   * Test that the name is correct.
   */
  @Test
  public void testName() {
    assertTrue(LF_INITIAL_NAME.equals(lf.getName()));
  }

  /**
   * Test taking a hit on the first attack.
   */
  @Test
  public void testTakeFirstHit() {
    lf.takeHit(LF_INITIAL_LIFE_POINTS - 1);
    assertEquals(1, lf.getCurrentLifePoints());
  }

  /**
   * Test taking a hit on a subsequent attack. A hit that does more damage than
   * Lifeform's current life points should not set its current life points below
   * zero.
   */
  @Test
  public void testTakeHardHit() {
    lf.takeHit(LF_INITIAL_LIFE_POINTS - 1);
    lf.takeHit(500);
    assertEquals(0, lf.getCurrentLifePoints());
  }

  @Test
  public void testAttack() {
    MockLifeForm lf1 = new MockLifeForm("L1", 10, 5);
    MockLifeForm lf2 = new MockLifeForm("L2", 10, 5);
    lf2.attack(lf1, 1);
    assertEquals(5, lf1.getCurrentLifePoints());
  }

  @Test
  public void testNoAttackIfDead() {
    MockLifeForm lf1 = new MockLifeForm("L1", 10, 5);
    MockLifeForm lf2 = new MockLifeForm("L2", 0, 5);
    lf2.attack(lf1, 1);
    assertEquals(10, lf1.getCurrentLifePoints());
  }

  // Decorator tests

  /**
   * Test that the life form can pick up a weapon.
   * 
   * @author Devin
   */
  @Test
  public void testPickupWeapon() {
    MockLifeForm f = new MockLifeForm("L1", 100);
    assertFalse(f.hasWeapon());
    assertTrue(f.pickUpWeapon(new Pistol()));
    assertTrue(f.hasWeapon());
  }

  /**
   * Test that the life form can not pick up a weapon p if it already has one.
   * 
   * @author Devin
   */
  @Test
  public void testAlreadyHasWeapon() {
    MockLifeForm f = new MockLifeForm("L1", 100);
    assertTrue(f.pickUpWeapon(new Pistol()));
    assertFalse(f.pickUpWeapon(new Pistol()));
  }

  /**
   * Test that the life form can drop its weapon.
   * 
   * @author Devin
   */
  @Test
  public void testDropWeapon() {
    MockLifeForm f = new MockLifeForm("L1", 100);
    Pistol p = new Pistol();
    f.pickUpWeapon(p);
    assertEquals(p, f.dropWeapon());
  }

  /**
   * The weapon should be used if it has ammo and is within its range.
   * 
   * @author Devin
   */
  @Test
  public void testUseWeapon() {
    MockLifeForm f = new MockLifeForm("L1", 100);
    MockLifeForm o = new MockLifeForm("L2", 100);
    Pistol p = new Pistol();
    f.pickUpWeapon(p);
    f.attack(o, 1);
    assertEquals(89, o.getCurrentLifePoints());
    assertEquals(9, p.getCurrentAmmo());
  }

  /**
   * A melee attack should be performed if the weapon is out of ammo.
   * 
   * @author Devin
   */
  @Test
  public void testUseMelee() throws WeaponException {
    MockLifeForm f = new MockLifeForm("L1", 100, 5);
    Pistol p = new Pistol();
    f.pickUpWeapon(p);
    for (int i = 0; i < p.getMaxAmmo(); i++) {
      p.fire(1);
      // Reset shotsLeft
      p.updateTime(1);
    }
    // The weapon should now be out of ammo
    assertEquals(0, p.getCurrentAmmo());
    MockLifeForm o = new MockLifeForm("L2", 100);
    f.attack(o, 1);
    assertEquals(95, o.getCurrentLifePoints());
  }

  /**
   * If the distance is greater than 5, a melee attack cannot be performed.
   * 
   * @author Devin
   */
  @Test
  public void testMeleeTooFar() throws WeaponException {
    MockLifeForm f = new MockLifeForm("L1", 100, 5);
    MockLifeForm o = new MockLifeForm("L2", 100);
    Pistol p = new Pistol();
    f.pickUpWeapon(p);
    for (int i = 0; i < p.getMaxAmmo(); i++) {
      p.fire(1);
      // Reset shotsLeft
      p.updateTime(1);
    }
    // The weapon should now be out of ammo
    f.attack(o, 6);
    assertEquals(100, o.getCurrentLifePoints());
  }

  /**
   * The life form should be able to reload the weapon.
   * 
   * @author Devin
   */
  @Test
  public void testReload() {
    MockLifeForm f = new MockLifeForm("L1", 100, 5);
    MockLifeForm o = new MockLifeForm("L2", 100);
    Pistol p = new Pistol();
    f.pickUpWeapon(p);
    f.attack(o, 10);
    assertEquals(9, p.getCurrentAmmo());
    f.reloadWeapon();
    assertEquals(10, p.getCurrentAmmo());
  }

}
