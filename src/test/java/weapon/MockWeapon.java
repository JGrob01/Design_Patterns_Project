package weapon;

import exceptions.WeaponException;

public class MockWeapon extends GenericWeapon {
  /**
   * Constructor for the MockWeapon, sets the base values for the weapon.
   */
  public MockWeapon() {
    super.baseDamage = 50;
    super.currentAmmo = 4;
    super.maxAmmo = 4;
    super.maxRange = 40;
    super.rateOfFire = 1;
    super.shotsLeft = 4;
  }

  /**
   * The fire method. Takes a given distance to target and calculates the damage
   * the target takes
   */
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Distance is negative");
    }
    if (currentAmmo <= 0 || distance > maxRange) {
      rateOfFire--;
      currentAmmo--;
      return 0;
    }
    rateOfFire--;
    currentAmmo--;
    return baseDamage;
  }

  public String toString() {
    return "MockWeapon";
  }

}