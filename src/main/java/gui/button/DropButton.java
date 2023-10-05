package gui.button;

import command.DropCommand;
import gui.GuiController;
import gui.GameBoard;
import command.Command;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class DropButton extends JButton {
  private Command cmd;

  public DropButton(GameBoard gb, String text) {
    super(text);
    cmd = new DropCommand(gb);
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