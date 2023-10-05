package gui;

import javax.swing.JPanel;
import gui.button.AcquireButton;
import gui.button.AttackButton;
import gui.button.DropButton;
import gui.button.MoveButton;
import gui.button.ReloadButton;
import gui.button.TurnEastButton;
import gui.button.TurnNorthButton;
import gui.button.TurnSouthButton;
import gui.button.TurnWestButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class InvokerMaker extends JPanel {

  private JPanel movePanel;
  private AcquireButton acquireButton;
  private AttackButton attackButton;
  private DropButton dropButton;
  private MoveButton moveButton;
  private ReloadButton reloadButton;
  private TurnNorthButton northButton;
  private TurnSouthButton southButton;
  private TurnEastButton eastButton;
  private TurnWestButton westButton;

  /**
   * @param gb
   */
  public InvokerMaker(GameBoard gb) {
    setBackground(Color.LIGHT_GRAY);

    movePanel = new JPanel();
    movePanel.setOpaque(false);

    acquireButton = new AcquireButton(gb, "Acquire Weapon");
    attackButton = new AttackButton(gb, "Attack");
    dropButton = new DropButton(gb, "Drop Weapon");
    moveButton = new MoveButton(gb, "Move");
    reloadButton = new ReloadButton(gb, "Reload");
    northButton = new TurnNorthButton(gb, "North");
    southButton = new TurnSouthButton(gb, "South");
    eastButton = new TurnEastButton(gb, "East");
    westButton = new TurnWestButton(gb, "West");
  }

  /**
   * @param invoker
   */
  public void createInvokerGui(GuiController invoker) {
    acquireButton.setUpActionListener(invoker);
    attackButton.setUpActionListener(invoker);
    dropButton.setUpActionListener(invoker);
    moveButton.setUpActionListener(invoker);
    reloadButton.setUpActionListener(invoker);
    northButton.setUpActionListener(invoker);
    southButton.setUpActionListener(invoker);
    eastButton.setUpActionListener(invoker);
    westButton.setUpActionListener(invoker);
  }

  /**
   * Display buttons
   */
  public void displayButtons() {
    setLayout(new GridLayout(5, 1));
    add(acquireButton);
    add(attackButton);
    add(dropButton);
    add(reloadButton);

    JLabel j = new JLabel();
    JLabel j2 = new JLabel();
    JLabel j3 = new JLabel();
    JLabel j4 = new JLabel();
    movePanel.setLayout(new GridLayout(3, 3));
    movePanel.add(j);
    movePanel.add(northButton);
    movePanel.add(j2);
    movePanel.add(westButton);
    movePanel.add(moveButton);
    movePanel.add(eastButton);
    movePanel.add(j3);
    movePanel.add(southButton);
    movePanel.add(j4);

    add(movePanel);
  }

}