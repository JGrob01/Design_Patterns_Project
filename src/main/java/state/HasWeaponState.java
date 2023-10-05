package state;

import environment.Environment;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import state.util.RandInt;
import environment.Cell;

/**
 * @author John Grobaker
 * Tests for the has weapon state
 */
public class HasWeaponState extends ActionState {
  private AiContext context;
  private Environment env;
  private LifeForm lifeform;

  /**
   * Constructor for has weapon state, sets the context variable
   * 
   * @param context
   */
  public HasWeaponState(AiContext context) {
    this.context = context;
    env = context.getEnvironment();
    lifeform = context.getLifeForm();
  }

  @Override
  public void executeAction() {
    if (isDead()) {
      return;
    }
    findTarget();
    search();
  }

  /**
   * Finds a target, If no target within range, call search.
   */
  private void findTarget() {
    int distance = 5;
    int direction = lifeform.getDirection();
    int startR = lifeform.getRow();
    int startC = lifeform.getColumn();
    Cell c = env.getCell(startR, startC);
    Cell target = null;

    if (direction == 1) {
      for (int i = 1; i < 5; i++) {
        Cell tempCell = Environment.getEnvironment(5, 5).getCell(startR - i, startC);
        if (startR - i < 0) {
          continue;
        } else if (tempCell.getLifeForm() == null) {
          distance += 5;
          continue;
        } else if (tempCell.getLifeForm() != null) {
          target = tempCell;
          break;
        }
      }
    } else if (direction == 2) {
      for (int i = 1; i < 5; i++) {
        Cell tempCell = Environment.getEnvironment(5, 5).getCell(startR, startC + i);
        if (startC + i >= Environment.getEnvironment(5, 5).getNumCols()) {
          continue;
        } else if (tempCell.getLifeForm() == null) {
          distance += 5;
          continue;
        } else if (tempCell.getLifeForm() != null) {
          target = tempCell;
          break;
        }
      }
    } else if (direction == 3) {
      for (int i = 1; i < 5; i++) {
        Cell tempCell = Environment.getEnvironment(5, 5).getCell(startR + i, startC);
        if (startR + i >= Environment.getEnvironment(5, 5).getNumRows()) {
          continue;
        } else if (tempCell.getLifeForm() == null) {
          distance += 5;
          continue;
        } else if (tempCell.getLifeForm() != null) {
          target = tempCell;
          break;
        }
      }
    } else if (direction == 4) {
      for (int i = 1; i < 5; i++) {
        Cell tempCell = Environment.getEnvironment(5, 5).getCell(startR, startC - i);
        if (startC - i < 0) {
          continue;
        } else if (tempCell.getLifeForm() == null) {
          distance += 5;
          continue;
        } else if (tempCell.getLifeForm() != null) {
          target = tempCell;
          break;
        }
      }
    }
    if (target != null) {
      if((target.getLifeForm() instanceof Human) && (lifeform instanceof Human)) {
        return;
      }
      if((target.getLifeForm() instanceof Alien) && (lifeform instanceof Alien)) {
        return;
      } 
      lifeform.attack(target.getLifeForm(), distance);
      try {
        lifeform.getWeapon().getCurrentAmmo();
      } catch (NullPointerException e) {
        context.setCurrentState(context.getNoWeaponState());
        return;
      }
      if (lifeform.getWeapon().getCurrentAmmo() == 0) {
        context.setCurrentState(context.getOutOfAmmoState());
      }
    }
  }

  /**
   * If lifeform is dead, go to dead state
   */
  private boolean isDead() {
    if (lifeform.getCurrentLifePoints() == 0) {
      env.removeLifeForm(lifeform.getRow(), lifeform.getColumn());
      context.setCurrentState(context.getDeadState());
      return true;
    }
    return false;
  }

  /**
   * Searches for a cell to move in direction facing
   */
  private void search() {
    int random = new RandInt(0, 4).choose();
    if (random == 1) {
      lifeform.faceNorth();
    } else if (random == 2) {
      lifeform.faceEast();
    } else if (random == 3) {
      lifeform.faceSouth();
    } else {
      lifeform.faceWest();
    }
    random = new RandInt(0, 2).choose();
    if (random == 1) {
      lifeform.move(null);
    }
  }

}
