package command;

import gui.GameBoard;

/**
 * @author John
 * Command to turn west
 */
public class TurnWestCommand implements Command {
  private GameBoard board;

  /**
   * Turn the lifeform west
   */
  public void execute() {
    /* Will turn the LifeForm a direction depending on the button pressed */
    try {
      board.getCurrentCell().getLifeForm().faceWest();
    } catch (NullPointerException e) {
      return;
    }
    board.getCurrentCell().getLifeForm().faceWest();
    //Environment.getEnvironment(5, 5).notifyObservers();
    board.updateCurrentCell();
  }

  /**
   * @param gb
   */
  public TurnWestCommand(GameBoard gb) {
    board = gb;
  }
}
