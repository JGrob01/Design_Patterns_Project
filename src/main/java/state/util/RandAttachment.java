package state.util;

import java.util.List;

import exceptions.AttachmentException;
import weapon.PowerBooster;
import weapon.Scope;
import weapon.Stabilizer;
import weapon.Weapon;

public class RandAttachment implements Rand<Weapon> {

  private Weapon weapon;

  /**
   * Attach a random attachment to the weapon. Note that
   * this may not add an attachment (chosen by random).
   * @param weapon
   */
  public RandAttachment(Weapon weapon) {
    this.weapon = weapon;
  }

  /**
   * Choose a random weapon.
   * 
   * @return random weapon
   */
  public Weapon choose() {
    // Add attachment?
    if (new RandBool().choose()) {
      return weapon;

    } else {
      try {
        List<Weapon> weapons = List.of(new Scope(weapon),
                                       new PowerBooster(weapon),
                                       new Stabilizer(weapon));
        return new FromList<>(weapons).choose();
      } catch (AttachmentException e) {
        e.printStackTrace();
        return weapon;
      }
    }
  }
  
}
