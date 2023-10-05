package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lifeform.MockLifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.Weapon;
import lifeform.LifeForm;

public class TestCell {

  private Cell cell;
  private LifeForm lf;

  /**
   * Initialize member variables before each test method call.
   */
  @Before
  public void init() {
    cell = new Cell();
    lf = new MockLifeForm("L1", 5);
  }

  /**
   * Clean up member variables after each test
   * @author Devin
   */
  @After
  public void after() {
    cell.removeLifeForm();
    cell.removeWeapon(cell.getWeapon1());
    cell.removeWeapon(cell.getWeapon2());
  }

  /**
   * Upon object creation, Cell should have a null life form instance variable.
   * @author Devin
   */
  @Test
  public void testInitialization() {
    assertNull(cell.getLifeForm());
  }

  /**
   * Weapons should be able to be added to the cell.
   * @author Devin
   */
  @Test
  public void testAddWeapons() {
    Weapon w = new Pistol();
    Weapon w2 = new ChainGun();
    assertTrue(cell.addWeapon(w));
    assertEquals(w, cell.getWeapon1());
    assertTrue(cell.addWeapon(w2));
    assertEquals(w2, cell.getWeapon2());
    // Both weapon slots should not point to the same instance
    cell.removeWeapon(w2);
    assertFalse(cell.addWeapon(w));
    assertNull(cell.getWeapon2());
  }

  /**
   * Weapons should be able to be removed from the cell.
   * @author Devin
   */
  @Test
  public void testRemoveWeapons() {
    Weapon w = new Pistol();
    assertNull(cell.getWeapon1());
    assertNull(cell.getWeapon2());
    cell.addWeapon(w);
    assertEquals(w, cell.removeWeapon(w));
    assertNull(cell.getWeapon1());
  }

  /**
   * Weapons should not be able to be added if the cell is already full.
   * @author Devin
   */
  @Test
  public void testFull() {
    Weapon w1 = new Pistol();
    Weapon w2 = new Pistol();
    Weapon w3 = new Pistol();
    cell.addWeapon(w1);
    cell.addWeapon(w2);
    assertFalse(cell.addWeapon(w3));
    assertEquals(w2, cell.getWeapon2());
  }

  /**
   * The number of weapons should be returned correctly.
   * @author Devin
   */
  @Test
  public void testWeaponCount() {
    Weapon w1 = new Pistol();
    assertEquals(0, cell.getWeaponsCount());
    cell.addWeapon(w1);
    assertEquals(1, cell.getWeaponsCount());
    Weapon w2 = new Pistol();
    cell.addWeapon(w2);
    assertEquals(2, cell.getWeaponsCount());
    cell.removeWeapon(w1);
    assertEquals(1, cell.getWeaponsCount());
  }


  // LifeForm tests
  
  /**
   * The method addLifeForm() should return true if it does not already have a life
   * form.
   */
  @Test
  public void testAddLifeForm() {
    assertEquals(true, cell.addLifeForm(lf));
    LifeForm lf2 = cell.getLifeForm();
    assertTrue(lf == lf2);
  }

  /**
   * The method addLifeForm() should return false if it already has a life form.
   */
  @Test
  public void testAddLifeFormFails() {
    cell.addLifeForm(lf);
    assertEquals(false, cell.addLifeForm(lf));
  }

  /**
   * The method addLifeForm() should return true if the life form was properly
   * removed by the method removeLifeForm().
   */
  @Test
  public void testRemoveLifeForm() {
    cell.addLifeForm(lf);
    cell.removeLifeForm();
    assertEquals(true, cell.addLifeForm(lf));
  }

}
