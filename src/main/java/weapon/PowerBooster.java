package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * @author isaac
 * An attachment that increases the damage based on how many rounds the weapon has left in its
 * magazine
 */
public class PowerBooster extends Attachment {
  
  /**
   * @param baseWeapon
   */
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    if (baseWeapon.getNumAttachments() >= 2) {
      throw new AttachmentException("Weapons cannot have more than 2 attachments.");
    }
    base = baseWeapon;
  }
  
  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Distance is negative");
    }
    if (distance > base.getMaxRange()) {
      base.fire(distance);
      return 0;
    }
    double ammo = base.getCurrentAmmo();
    double maxAmmo = base.getMaxAmmo();
    
    double damage = base.fire(distance);
    double pbDamage = (double)damage * (1 + (ammo / maxAmmo));
    
    return Double.valueOf(Math.floor(pbDamage)).intValue();
  }
  
  @Override
  public String toString() {
    return base.toString() + " +PowerBooster";
  }
  
  @Override
  public int getNumAttachments() {
    return base.getNumAttachments() + 1;
  }
}
