package state;

import environment.Environment;
import environment.Cell;
import lifeform.LifeForm;

public class DeadState extends ActionState {
  private AiContext context;
  private Environment env;
  private LifeForm lifeform;

  /**
   * Constructor for dead state, sets the context variable
   * 
   * @param context
   */
  public DeadState(AiContext context) {
    this.context = context;
    env = context.getEnvironment();
    lifeform = context.getLifeForm();
  }

  @Override
  public void executeAction() {
    if (lifeform.hasWeapon()) {
      removeWeapon();
    }
    respawn();
  }

  public void removeWeapon() {
    Cell c = env.getRandomCell();
    env.addWeapon(lifeform.dropWeapon(), env.getCellRow(c), env.getCellColumn(c));
  }

  /**
   * respawns the lifeform at a random cell
   */
  public void respawn() {
    lifeform.resetHealth();
    Cell c = env.getRandomCell();
    env.addLifeForm(lifeform, env.getCellRow(c), env.getCellColumn(c));
    context.setCurrentState(context.getNoWeaponState());
  }

}
