package state;

import environment.Environment;
import lifeform.LifeForm;

/**
 * @author isaac
 * A lifeform state for when a lifeform's weapon is out of ammo
 */
public class OutOfAmmoState extends ActionState {

  private AiContext context;
  private LifeForm lf;

  /**
   * @param context
   */
  public OutOfAmmoState(AiContext context) {
    this.context = context;
    lf = context.getLifeForm();
  }

  @Override
  public void executeAction() {
    if (lf.getCurrentLifePoints() == 0) {
      dead();
    } else {
      reload();
    }
  }
  
  /**
   * changes state to dead state
   */
  public void dead() {
    context.setCurrentState(context.getDeadState());
    Environment.getEnvironment(5, 5).removeLifeForm(lf.getRow(), lf.getColumn());
  }
  
  /**
   * Reloads the weapon and changes state to has weapon
   */
  public void reload() {
    lf.reloadWeapon();
    context.setCurrentState(context.getHasWeaponState());
  }
  
}
