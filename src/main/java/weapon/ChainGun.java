package weapon;

import exceptions.WeaponException;

/**
 * @author isaac
 * A high-capacity weapon that is most effective at farther distances
 */
public class ChainGun extends GenericWeapon {
  
  /**
   * Constructor for the chain gun weapon
   */
  public ChainGun() {
    currentAmmo = 40;
    baseDamage = 15;
    maxRange = 60;
    rateOfFire = 4;
    maxAmmo = 40;
    shotsLeft = 4;
  }
  
  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Distance is negative");
    }
    if (currentAmmo == 0) {
      return 0;
    }
    if (shotsLeft == 0) {
      return 0;
    }
    if (distance > maxRange) {
      shotsLeft--;
      currentAmmo--;
      return 0;
    }
    shotsLeft--;
    currentAmmo--;
    double damage;
    damage = (double)baseDamage * ((double)distance / (double)maxRange);
    return Double.valueOf(Math.floor(damage)).intValue();
  }
  
  @Override
  public String toString() {
    return "ChainGun";
  }

}
