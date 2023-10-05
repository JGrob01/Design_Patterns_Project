package gui.button;

import command.TurnSouthCommand;
import gui.GuiController;
import gui.GameBoard;
import command.Command;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class TurnSouthButton extends JButton {
  private Command cmd;

  public TurnSouthButton(GameBoard gb, String text) {
    super(text);
    cmd = new TurnSouthCommand(gb);
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