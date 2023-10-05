package command;

import gui.GameBoard;

/**
 * @author John
 * Command to reload a weapon
 */
public class ReloadCommand implements Command {
  private GameBoard board;

  /**
   * Reload a weapon
   */
  public void execute() {
    /*
     * Reloads the weapon theLifeForm is holding. If the LifeFormis not holding a
     * weapon, then nothing happens
     */
    try {
      board.getCurrentCell().getLifeForm().hasWeapon();
    } catch (NullPointerException e) {
      return;
    }
    if (board.getCurrentCell().getLifeForm().hasWeapon()) {
      board.getCurrentCell().getLifeForm().reloadWeapon();
    }
    //Environment.getEnvironment(5, 5).notifyObservers();
    board.updateCurrentCell();
  }

  /**
   * @param gb
   */
  public ReloadCommand(GameBoard gb) {
    board = gb;
  }
}
