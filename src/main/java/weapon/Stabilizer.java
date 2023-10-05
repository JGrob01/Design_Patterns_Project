package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Stabilizer extends Attachment {
  /**
   * @param base
   * @throws AttachmentException
   */
  public Stabilizer(Weapon base) throws AttachmentException {
    if (base.getNumAttachments() >= 2) {
      throw new AttachmentException("Weapons cannot have more than 2 attachments.");
    }
    this.base = base;
  }

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("distance can't be less than zero");
    }
    if (distance > base.getMaxRange()) {
      base.fire(distance);
      return 0;
    }
    int dmg = base.fire(distance);
    if (base.getCurrentAmmo() == 0) {
      base.reload();
    }
    return Double.valueOf(Math.floor((.25 * dmg) + dmg)).intValue();
  }

  @Override
  public int getNumAttachments() {
    return 1 + base.getNumAttachments();
  }

  @Override
  public String toString() {
    return base.toString() + " +Stabilizer";
  }

}