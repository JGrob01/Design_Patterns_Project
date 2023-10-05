package command;

import gui.GameBoard;

/**
 * @author John
 * Command to turn south
 */
public class TurnSouthCommand implements Command {
  private GameBoard board;

  /**
   * Turn a lifeform south
   */
  public void execute() {
    /* Will turn the LifeForm a direction depending on the button pressed */
    try {
      board.getCurrentCell().getLifeForm().faceSouth();
    } catch (NullPointerException e) {
      return;
    }
    board.getCurrentCell().getLifeForm().faceSouth();
    //Environment.getEnvironment(5, 5).notifyObservers();
    board.updateCurrentCell();
  }

  /**
   * @param gb
   */
  public TurnSouthCommand(GameBoard gb) {
    board = gb;
  }
}
