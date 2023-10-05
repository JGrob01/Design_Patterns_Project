package weapon;

import exceptions.WeaponException;

public class PlasmaCannon extends GenericWeapon {

  /**
   * Constructor for the PlasmaCannon, 
   * sets the base values for the weapon.
   */
  public PlasmaCannon() {
    baseDamage = 50;
    currentAmmo = 4;
    maxAmmo = 4;
    maxRange = 40;
    rateOfFire = 1;
    shotsLeft = 1;
  }

  /**
  * The fire method. Takes a given distance to target and calculates the damage
  * the target takes
  */
  @Override
  public int fire(int distance) throws WeaponException {
    if (distance <= 0) {
      throw new WeaponException("distance can't be less than zero");
    }
    if (currentAmmo == 0) {
      return 0;
    }
    if (shotsLeft == 0) {
      return 0;
    }
    shotsLeft--;
    currentAmmo--;
    if (distance > maxRange) {
      return 0;
    }
    double ca = Double.valueOf(currentAmmo + 1);
    double ma = Double.valueOf(maxAmmo);
    return (int) (baseDamage * (ca / ma));
  }

  @Override
  public String toString() {
    return "PlasmaCannon";
  }

}
