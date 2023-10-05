package command;

import gui.GameBoard;

/**
 * @author John
 * Command to drop a weapon
 */
public class DropCommand implements Command {
  private GameBoard board;

  /**
   * Drops a weapon
   */
  public void execute() {
    /*
     * Selected LifeForm drops its current weapon. Should not allow the LifeForm to
     * drop a Weapon if no space for the weapon in the Cell
     */
    try {
      board.getCurrentCell().getLifeForm().hasWeapon();
    } catch (NullPointerException e) {
      return;
    }

    if (board.getCurrentCell().getLifeForm().hasWeapon()) {
      if (board.getCurrentCell().getWeaponsCount() < 2) {
        board.getCurrentCell().addWeapon(board.getCurrentCell().getLifeForm().dropWeapon());
      }
    }

    //Environment.getEnvironment(5, 5).notifyObservers();
    board.updateCurrentCell();
  }

  /**
   * @param gb
   */
  public DropCommand(GameBoard gb) {
    board = gb;
  }
}
