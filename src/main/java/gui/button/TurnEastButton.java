package gui.button;

import command.TurnEastCommand;
import gui.GuiController;
import gui.GameBoard;
import command.Command;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class TurnEastButton extends JButton {
  private Command cmd;

  public TurnEastButton(GameBoard gb, String text) {
    super(text);
    cmd = new TurnEastCommand(gb);
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