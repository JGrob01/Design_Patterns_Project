package gui;

import command.Command;

public class GuiController {
  private Command command;
  private GameBoard board;

  public void setCommand(Command c) {
    command = c;
  }

  public void buttonPressed() {
    command.execute();
  }

  public GuiController(GameBoard gb) {
    board = gb;
  }
}
