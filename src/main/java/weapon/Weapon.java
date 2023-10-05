package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;

/**
 * Weapon interface. This interface contains the necessary methods
 * for weapon functionality.
 */
public interface Weapon extends TimerObserver {

  /**
   * Fire the weapon. This should reduce ammo by one bullet.
   * @param distance distance to the target
   * @return the damage done to the target
   */
  public int fire(int distance) throws WeaponException;

  /**
   * Get the base damage of the weapon.
   * @return base damage
   */
  public int getBaseDamage();

  /**
   * Get the current ammo of the weapon.
   * @return current ammo
   */
  public int getCurrentAmmo();

  /**
   * Get the maximum ammo of the weapon.
   * @return maximum ammo
   */
  public int getMaxAmmo();

  /**
   * Get the maximum range of the weapon.
   * @return maximum range
   */
  public int getMaxRange();

  /**
   * Get the number of attachments applied to the weapon.
   * @return number of the weapon's attachments
   */
  public int getNumAttachments();

  /**
   * Get the number of times a weapon may fire during the round.
   * @return the weapon's rate of fire
   */
  public int getRateOfFire();

  /**
   * Get the number of shots the weapon has left in the given round
   * @return shots left for the current round
   */
  public int getShotsLeft();

  /**
   * Reload the weapon so it has full ammo. Assume the number of
   * reloads is infinite.
   */
  public void reload();

  /**
   * Get the string representation of the weapon and its attachments.
   * @return weapon and its attachments
   */
  @Override
  public String toString();
  
}
