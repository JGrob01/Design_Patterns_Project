package gui.button;

import command.MoveCommand;
import gui.GuiController;
import gui.GameBoard;
import command.Command;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MoveButton extends JButton {
  private Command cmd;

  public MoveButton(GameBoard gb, String text) {
    super(text);
    cmd = new MoveCommand(gb);
  }

  /**
   * @param invoker
   */
  public void setUpActionListener(GuiController invoker) {
    addActionListener(e -> {
      invoker.setCommand(cmd);
      invoker.buttonPressed();
    });
  }
}