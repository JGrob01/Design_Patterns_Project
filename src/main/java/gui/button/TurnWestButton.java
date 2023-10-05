package gui.button;

import command.TurnWestCommand;
import gui.GuiController;
import gui.GameBoard;
import command.Command;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class TurnWestButton extends JButton {
  private Command cmd;

  public TurnWestButton(GameBoard gb, String text) {
    super(text);
    cmd = new TurnWestCommand(gb);
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