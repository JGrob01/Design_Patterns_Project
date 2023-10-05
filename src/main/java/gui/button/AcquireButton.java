package gui.button;

import command.AcquireCommand;
import command.Command;
import gui.GuiController;
import gui.GameBoard;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AcquireButton extends JButton {
  private Command cmd;

  public AcquireButton(GameBoard gb, String text) {
    super(text);
    cmd = new AcquireCommand(gb);
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
