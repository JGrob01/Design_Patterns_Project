package state;

import environment.Cell;
import environment.Environment;
import lifeform.LifeForm;
import state.util.RandInt;
import weapon.Weapon;

/**
 * @author isaac
 * A lifeform state for when a lifeform does not have a weapon
 */
public class NoWeaponState extends ActionState {

  private AiContext context;
  private Environment env;
  private LifeForm lf;

  /**
   * @param context
   */
  public NoWeaponState(AiContext context) {
    this.context = context;
    env = context.getEnvironment();
    lf = context.getLifeForm();
  }

  @Override
  public void executeAction() {
    Cell c = env.getCell(lf.getRow(), lf.getColumn());

    if (lf.getCurrentLifePoints() == 0) {
      dead();
    } else if (c.getWeaponsCount() > 0) {
      acquireWeapon(c);
    } else {
      search();
    }
  }
  
  /**
   * changes the state to the dead state
   */
  private void dead() {
    env.removeLifeForm(lf.getRow(), lf.getColumn());
    context.setCurrentState(context.getDeadState());
  }
  
  /**
   * acquires weapon in the cell
   */
  private void acquireWeapon(Cell c) {
    Weapon w = c.getWeapon1();
    lf.pickUpWeapon(w);
    c.removeWeapon(w);
    context.setCurrentState(context.getHasWeaponState());
  }
  
  /**
   * moves the lifeform if no weapon in cell
   */
  private void search() {
    int dir = new RandInt(0, 4).choose();
    if (dir == 1) {
      lf.faceNorth();
    } else if (dir == 3) {
      lf.faceSouth();
    } else if (dir == 2) {
      lf.faceEast();
    } else {
      lf.faceWest();
    }
    lf.move(null);
  }
  
}
