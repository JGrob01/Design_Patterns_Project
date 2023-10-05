package command;

import gui.GameBoard;

/**
 * @author John
 * Turn north command
 */
public class TurnNorthCommand implements Command {
  private GameBoard board;

  /**
   * Turn a lifeform north
   */
  public void execute() {
    /* Will turn the LifeForm a direction depending on the button pressed */
    try {
      board.getCurrentCell().getLifeForm().faceNorth();
    } catch (NullPointerException e) {
      return;
    }
    board.getCurrentCell().getLifeForm().faceNorth();
    //Environment.getEnvironment(5, 5).notifyObservers();
    board.updateCurrentCell();
  }

  public TurnNorthCommand(GameBoard gb) {
    board = gb;
  }
}
