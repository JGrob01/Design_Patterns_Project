package command;

import gui.GameBoard;

/**
 * @author John
 * Command to move lifeform
 */
public class MoveCommand implements Command {
  private GameBoard board;

  /**
   * Moves a lifeform
   */
  public void execute() {
    /*
     * Attempts to move the selected LifeForm maxSpeed spaces in the direction it is
     * facing using the movement rules of the Environment
     */
    try {
      board.getCurrentCell().getLifeForm().move(board);
    } catch (NullPointerException e) {
      return;
    }
    // Environment.getEnvironment(5, 5).notifyObservers();
  }

  /**
   * @param gb
   */
  public MoveCommand(GameBoard gb) {
    board = gb;
  }
}
