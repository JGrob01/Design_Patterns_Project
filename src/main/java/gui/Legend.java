package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gui.icon.AlienIcon;
import gui.icon.ArrowNorthIcon;
import gui.icon.GuiIcon;
import gui.icon.HumanIcon;
import gui.icon.Weapon1Icon;

public class Legend extends JPanel {
  
  /**
   * 
   */
  public Legend() {
    setLayout(new GridLayout(5, 1));
    setBorder(new EmptyBorder(20, 20, 20, 20));
    setBackground(Color.LIGHT_GRAY);
    
    addPanelIcon(new HumanIcon(false), "Human");
    addPanelIcon(new AlienIcon(false), "Alien");
    addPanelIcon(new ArrowNorthIcon(false), "Direction");
    addPanelIcon(new Weapon1Icon(false), "Weapon");
  }
  
  private void addPanelIcon(GuiIcon icon, String text) {
    JPanel panel = new JPanel();
    panel.setOpaque(false);
    panel.setLayout(new BorderLayout());
    panel.add(new JLabel(icon.getImageIcon()), BorderLayout.CENTER);
    panel.add(new JLabel(text, SwingConstants.CENTER), BorderLayout.SOUTH);
    add(panel);
  }
  
}
