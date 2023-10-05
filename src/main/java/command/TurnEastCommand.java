package command;

import gui.GameBoard;

/**
 * @author John
 * Command to turn east
 */
public class TurnEastCommand implements Command {
  private GameBoard board;

  /**
   * turn a lifeform east
   */
  public void execute() {
    /* Will turn the LifeForm a direction depending on the button pressed */
    try {
      board.getCurrentCell().getLifeForm().faceEast();
    } catch (NullPointerException e) {
      return;
    }
    board.getCurrentCell().getLifeForm().faceEast();
    //Environment.getEnvironment(5, 5).notifyObservers();
    board.updateCurrentCell();
  }

  /**
   * @param gb
   */
  public TurnEastCommand(GameBoard gb) {
    board = gb;
  }
}
