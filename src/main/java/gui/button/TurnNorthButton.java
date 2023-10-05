package gui.button;

import command.TurnNorthCommand;
import gui.GuiController;
import gui.GameBoard;
import command.Command;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class TurnNorthButton extends JButton {
  private Command cmd;

  public TurnNorthButton(GameBoard gb, String text) {
    super(text);
    cmd = new TurnNorthCommand(gb);
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