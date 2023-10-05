package weapon;

import exceptions.WeaponException;

public class Pistol extends GenericWeapon {

  /**
   * Create a new pistol with default values.
   */
  public Pistol() {
    baseDamage = 10;
    maxRange = 50;
    rateOfFire = 2;
    maxAmmo = 10;
    currentAmmo = 10;
    shotsLeft = 2;
  }

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Distance cannot be negative");
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
    // Calculate damage
    double dmg = Double.valueOf(baseDamage);
    double range = Double.valueOf(maxRange);
    return Double.valueOf(Math.floor(dmg * (range - distance + 10)
            / range)).intValue();
  }

  @Override
  public String toString() {
    return "Pistol";
  }

  @Override
  public int getNumAttachments() {
    return 0;
  }

}
