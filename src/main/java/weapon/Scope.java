package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Scope extends Attachment {

  /**
   * Create a scope attachment. This attachment will be attached
   * to a base weapon (which could also be another attachment).
   * @throws AttachmentException
   */
  public Scope(Weapon weapon) throws AttachmentException {
    if (weapon.getNumAttachments() >= 2) {
      throw new AttachmentException("Weapons cannot have more than 2 attachments.");
    }
    base = weapon;
  }

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Distance cannot be negative");
    }
    if (base.getMaxRange() < distance) {
      if (distance <= getMaxRange()) {
        return 5 + base.fire((base.getMaxRange()));
      } else {
        // Fire outside of max range to decrement ammo
        base.fire(base.getMaxRange() + 1);
        return 0;
      }
    } else {
      double dmg = Double.valueOf(base.fire(distance));
      double range = Double.valueOf((getMaxRange()));
      double finalDamage = dmg * (1 + ((range - distance) / (range)));
      return Double.valueOf(Math.floor(finalDamage)).intValue();
    }
  }

  @Override
  public int getMaxRange() {
    return base.getMaxRange() + 10;
  }

  @Override
  public String toString() {
    return base.toString() + " +Scope";
  }

  @Override
  public int getNumAttachments() {
    return 1 + base.getNumAttachments();
  }

}
