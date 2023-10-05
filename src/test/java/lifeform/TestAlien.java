package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import exceptions.RecoveryRateException;
import gameplay.SimpleTimer;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;

public class TestAlien {
  
  private static final String A_INITIAL_NAME = "H";
  private static final int A_MAX_LIFE_POINTS = 5;
  private Alien alien;

  @Before
  public void init() {
    alien = new Alien(A_INITIAL_NAME, A_MAX_LIFE_POINTS);
  }
  
  /**
   * Max speed should be 2 for an alien
   * @author Isaac
   */
  @Test
  public void testMaxSpeed() {
    assertEquals(2, alien.maxSpeed);
  }
  
  
  // Lab 5 and before tests
  
  /**
   * Test that the alien initializes with the correct values.
   */
  @Test
  public void testInitialization() {
    assertTrue(A_INITIAL_NAME.equals(alien.getName()));
    assertEquals(A_MAX_LIFE_POINTS, alien.getCurrentLifePoints());
    assertEquals(A_MAX_LIFE_POINTS, alien.getMaxLifePoints());
  }

  /**
   * Test that in alien life form recovers properly using RecoveryLinear.
   */
  @Test
  public void testRecoveryLinear() {
    Alien b = new Alien(A_INITIAL_NAME, A_MAX_LIFE_POINTS, new RecoveryLinear(3));
    b.takeHit(4);
    b.recover();
    assertEquals(A_MAX_LIFE_POINTS - 1, b.getCurrentLifePoints());
  }

  /**
   * Test that in alien life form recovers properly using RecoveryFractional.
   */
  @Test
  public void testRecoveryFractional() {
    Alien b = new Alien(A_INITIAL_NAME, A_MAX_LIFE_POINTS, new RecoveryFractional((double) 1 / 10));
    b.takeHit(4);
    b.recover();
    assertEquals(A_MAX_LIFE_POINTS - 3, b.getCurrentLifePoints());
  }

  /**
   * Test that the default strength is what it should be.
   */
  @Test
  public void testDefaultStrength() {
    Alien b = new Alien("A1", 10);
    assertEquals(10, b.getAttackStrength());
  }

  /**
   * Recovery should be set in the constructor.
   */
  @Test
  public void testRecoveryInit() throws RecoveryRateException {
    Alien b = new Alien("A1", 10, new RecoveryLinear(3), 5);
    assertEquals(5, b.getRecoveryRate());
  }

  /**
   * Recovery shouldn't occur if it is zero.
   */
  @Test
  public void testRecoveryZero() throws RecoveryRateException {
    Alien b = new Alien("A1", 100, new RecoveryLinear(1), 0);
    SimpleTimer t = new SimpleTimer(1000);
    t.addTimeObserver(b);
    b.takeHit(50);
    t.timeChanged();
    assertEquals(50, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(50, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(50, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(50, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(50, b.getCurrentLifePoints());
  }

  /**
   * Recovery should occur every n rounds.
   */
  @Test
  public void testRecoverGreater() throws RecoveryRateException {
    Alien b = new Alien("A1", 100, new RecoveryLinear(1), 1);
    SimpleTimer t = new SimpleTimer(1000);
    t.addTimeObserver(b);
    b.takeHit(50);
    t.timeChanged();
    assertEquals(51, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(52, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(53, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(54, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(55, b.getCurrentLifePoints());
  }

  /**
   * Test another recovery rate.
   */
  @Test
  public void testRecoverAlternate() throws RecoveryRateException {
    Alien b = new Alien("A1", 100, new RecoveryLinear(1), 3);
    SimpleTimer t = new SimpleTimer(1000);
    t.addTimeObserver(b);
    b.takeHit(50);
    assertEquals(50, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(50, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(50, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(51, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(51, b.getCurrentLifePoints());
  }

  /**
   * Alien should be able to keep track of time.
   */
  @Test
  public void testTrackTime() throws InterruptedException, RecoveryRateException {
    Alien b = new Alien("A1", 10, new RecoveryLinear(1), 2);
    SimpleTimer t = new SimpleTimer(1000);
    t.addTimeObserver(b);
    b.takeHit(5);
    assertEquals(5, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(5, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(6, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(6, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(7, b.getCurrentLifePoints());
  }

  /**
   * The alien should not recover any further if
   * it is no longer an observer.
   */
  @Test
  public void testRemoveObserver() throws RecoveryRateException {
    Alien b = new Alien("A1", 10, new RecoveryLinear(1), 2);
    SimpleTimer t = new SimpleTimer(1000);
    t.addTimeObserver(b);
    b.takeHit(5);
    assertEquals(5, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(5, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(6, b.getCurrentLifePoints());
    t.timeChanged();
    assertEquals(6, b.getCurrentLifePoints());
    t.removeTimeObserver(b);
    t.timeChanged();
    assertEquals(6, b.getCurrentLifePoints());
  }

  /**
   * An exception should be thrown if recovery is negative.
   */
  @Test
  public void testRecoverNeg() {
    try {
      Alien b = new Alien("A1", 10, new RecoveryLinear(1), -2);
      fail();
    } catch (RecoveryRateException e) {
      // Expected
    }
  }
  
}
