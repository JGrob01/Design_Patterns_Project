package command;

import environment.Cell;
import environment.Environment;
import gui.GameBoard;
import weapon.Weapon;

public class AcquireCommand implements Command {
  private GameBoard board;

  /**
   * Acquire a weapon
   */
  public void execute() {
    /*
     * Selected LifeForm picks up a weapon, but only if one exists in the Cell. If
     * the LifeForm is already holding a weapon, it will swap the old weapon for the
     * new weapon. This command will try to get the weapon in slot 1 first, and if
     * there is no weapon in slot 1, it will try to get the weapon in slot 2
     */
    try {
      board.getCurrentCell().getLifeForm().hasWeapon();
    } catch (NullPointerException e) {
      return;
    }

    Weapon a;
    Cell c = board.getCurrentCell();
    if (c.getWeaponsCount() > 0) {
      if (c.getLifeForm().hasWeapon()) {
        a = c.getLifeForm().dropWeapon();
        if (c.getWeapon1() != null) {
          Weapon w = c.getWeapon1();
          c.removeWeapon(w);
          c.getLifeForm().pickUpWeapon(w);
          c.addWeapon(a);
        } else {
          Weapon w = c.getWeapon2();
          c.removeWeapon(w);
          c.getLifeForm().pickUpWeapon(w);
          c.addWeapon(a);
        }
      } else {
        if (c.getWeapon1() != null) {
          Weapon w = c.getWeapon1();
          c.removeWeapon(w);
          c.getLifeForm().pickUpWeapon(w);
        } else {
          Weapon w = c.getWeapon2();
          c.removeWeapon(w);
          c.getLifeForm().pickUpWeapon(w);
        }
        if (c.getWeapon1() == null) {
          Weapon w = c.getWeapon2();
          c.removeWeapon(w);
          c.addWeapon(w);
        }
      }
    }

    // Environment.getEnvironment(5, 5).notifyObservers();
    board.updateCurrentCell();

  }

  public AcquireCommand(GameBoard gb) {
    board = gb;
  }
}
