package command;

import environment.Cell;
import environment.Environment;
import gui.GameBoard;

public class AttackCommand implements Command {
  private GameBoard board;

  /**
   * Attack another lifeform
   */
  public void execute() {
    /*
     * Selected LifeForm attacks in the direction it is facing. To keep things
     * simple a LifeForm will only attack targets in a direct line in front of the
     * LifeForm. The LifeForm should only fire its weapon if there is a target. The
     * LifeForm will always attack the closest enemy target
     */
    try {
      board.getCurrentCell().getLifeForm().hasWeapon();
    } catch (NullPointerException e) {
      return;
    }

    Cell c = board.getCurrentCell();
    Cell target = null;
    int distance = 5;
    int direction = c.getLifeForm().getDirection();
    int startR = c.getLifeForm().getRow();
    int startC = c.getLifeForm().getColumn();

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
      c.getLifeForm().attack(target.getLifeForm(), distance);
    }
    // Environment.getEnvironment(5, 5).notifyObservers();
    board.updateCurrentCell();
  }

  public AttackCommand(GameBoard gb) {
    board = gb;
  }
}
