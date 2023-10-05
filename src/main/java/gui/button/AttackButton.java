package gui.button;

import command.AttackCommand;
import command.Command;
import gui.GuiController;
import gui.GameBoard;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AttackButton extends JButton {
  private Command cmd;

  public AttackButton(GameBoard gb, String text) {
    super(text);
    cmd = new AttackCommand(gb);
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