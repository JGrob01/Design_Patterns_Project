package environment;

import lifeform.LifeForm;
import weapon.Weapon;

public class Cell {

  private LifeForm lf;
  private Weapon weapon1;
  private Weapon weapon2;

  /**
   * Add a life form to the cell if one is already not present.
   * @param entity the life form to add
   * @return true if added, false if the cell object already has a life form
   */
  public boolean addLifeForm(LifeForm entity) {
    if (lf != null) {
      return false;
    }
    lf = entity;
    return true;
  }

  /**
   * Remove the cell's current life form.
   */
  public void removeLifeForm() {
    lf = null;
  }

  /**
   * Returns the cell's current life form
   * @return the cell's current life form
   */
  public LifeForm getLifeForm() {
    return lf;
  }

  /**
   * Add a weapon to the cell. 
   * @return whether the weapon was successfully added
   */
  public boolean addWeapon(Weapon w) {
    if (w == weapon1 || w == weapon2) {
      return false;
    }
    if (weapon1 == null) {
      weapon1 = w;
      return true;
    }
    if (weapon2 == null) {
      weapon2 = w;
      return true;
    }
    return false;
  }

  /**
   * Get the first weapon from the cell.
   * @return the first weapon
   */
  public Weapon getWeapon1() {
    return weapon1;
  }

  /**
   * Get the second weapon from the cell.
   * @return the second weapon
   */
  public Weapon getWeapon2() {
    return weapon2;
  }

  /**
   * Get the number of weapons in the cell.
   * @return the number of weapons
   */
  public int getWeaponsCount() {
    int count = 0;
    if (weapon1 != null) {
      count++;
    }
    if (weapon2 != null) {
      count++;
    }
    return count;
  }

  /**
   * Remove the weapon if it is in the cell.
   * @return the weapon being removed, or null if none was found
   */
  public Weapon removeWeapon(Weapon weapon) {
    if (weapon1 == weapon) {
      Weapon w = weapon1;
      weapon1 = null;
      return w;
    }
    if (weapon2 == weapon) {
      Weapon w = weapon2;
      weapon2 = null;
      return w;
    }
    return null;
  }

}
