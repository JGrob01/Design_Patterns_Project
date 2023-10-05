package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;

public class AiContext implements TimerObserver {

  private LifeForm lifeForm;
  private Environment environment;

  private ActionState currentState;
  private DeadState deadState;
  private NoWeaponState noWeaponState;
  private HasWeaponState hasWeaponState;
  private OutOfAmmoState outOfAmmoState;

  /**
   * Create a context. This context will have different behavior
   * based on its current state. This context controls a life form's
   * actions accordingly.
   */
  public AiContext(LifeForm lifeForm, Environment environment) {
    this.lifeForm = lifeForm;
    this.environment = environment;

    deadState = new DeadState(this);
    noWeaponState = new NoWeaponState(this);
    hasWeaponState = new HasWeaponState(this);
    outOfAmmoState = new OutOfAmmoState(this);

    currentState = noWeaponState;
  }

  /**
   * Execute behavior based on the current state.
   */
  public void execute() {
    currentState.executeAction();
  }

  /**
   * Get the current state of the context.
   * @return current state
   */
  public ActionState getCurrentState() {
    return currentState;
  }

  /**
   * Set the current state of the context.
   */
  public void setCurrentState(ActionState state) {
    currentState = state;
  }

  /**
   * Get the instance of the dead state in this context.
   * @return dead state object
   */
  public DeadState getDeadState() {
    return deadState;
  }

  /**
   * Get the environment instance.
   * @return environment object
   */
  public Environment getEnvironment() {
    return environment;
  }

  /**
   * Get the has weapon state instance.
   * @return weapon state object
   */
  public HasWeaponState getHasWeaponState() {
    return hasWeaponState;
  }

  /**
   * Get the life form instance in this context.
   * @return life form
   */
  public LifeForm getLifeForm() {
    return lifeForm;
  }

  /**
   * Get the no weapon state instance.
   * @return no weapons state object
   */
  public NoWeaponState getNoWeaponState() {
    return noWeaponState;
  }

  /**
   * Get the out of ammo state instance.
   * @return out of ammo state object.
   */
  public OutOfAmmoState getOutOfAmmoState() {
    return outOfAmmoState;
  }

  @Override
  public void updateTime(int time) {
    execute();
  }

}
