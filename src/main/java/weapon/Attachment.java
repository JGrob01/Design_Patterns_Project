package weapon;

import exceptions.WeaponException;

/**
 * @author Isaac
 *
 */
public abstract class Attachment implements Weapon {
  protected Weapon base;
  
  /**
   * Most attachments will change the damage the weapoon does when it fires
   */
  public abstract int fire(int distance) throws WeaponException;
  
  /**
   * returns the damage of the unadorned base Weapon
   */
  public int getBaseDamage() {
    return base.getBaseDamage();
  }
  
  /**
   * returns the current ammo of the unadorned base Weapon
   */
  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }
  
  /**
   * returns the max ammo of the base Weapon
   */
  public int getMaxAmmo() {
    return base.getMaxAmmo();
  }
  
  /**
   * returns the maximum range of the unadorned base Weapon
   */
  public int getMaxRange() {
    return base.getMaxRange();
  }
  
  /**
   * returns number of attachments equipped to the base Weapon
   */
  public int getNumAttachments() {
    return base.getNumAttachments();
  }
  
  /**
   * returns the rate of fire of the unadorned base Weapon
   */
  public int getRateOfFire() {
    return base.getRateOfFire();
  }
  
  /**
   * returns the number of shots left in the base Weapon
   */
  public int getShotsLeft() {
    return base.getShotsLeft();
  }
  
  /**
   * reload the clip to full capacity
   */
  public void reload() {
    base.reload();
  }
  
  /**
   * update time
   */
  public void updateTime(int time) {
    base.updateTime(time);
  }
  
}
