package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import environment.Cell;
import environment.Environment;
import gui.icon.AlienIcon;
import gui.icon.ArrowEastIcon;
import gui.icon.ArrowNorthIcon;
import gui.icon.ArrowSouthIcon;
import gui.icon.ArrowWestIcon;
import gui.icon.HumanIcon;
import gui.icon.NullIcon;
import gui.icon.Weapon1Icon;
import gui.icon.Weapon2Icon;
import lifeform.Human;
import lifeform.LifeForm;

public class GuiGridCell extends JPanel implements MouseListener {

  private JPanel bottomPanel;
  private JPanel weaponPanel;
  private InfoPanel ip;
  private GameBoard gb;
  private int row;
  private int column;

  /**
   * Construct a new GameBoard cell. This is used
   * to handle the rendering of Cells within the Environment.
   * 
   * @param gb the GameBoard (to keep track of the current cell)
   * @param ip info panel
   * @param row the row where this cell is
   * @param column the column where this cell is
   */
  public GuiGridCell(GameBoard gb, InfoPanel ip, int row, int column) {
    super(new BorderLayout());
    setBorder(BorderFactory.createLineBorder(Color.BLACK));
    addMouseListener(this);
    this.gb = gb;
    this.row = row;
    this.column = column;
    this.ip = ip;

    bottomPanel = new JPanel();
    weaponPanel = new JPanel();
    bottomPanel.setLayout(new BorderLayout());
    weaponPanel.setLayout(new BorderLayout());
    bottomPanel.setOpaque(false);
    weaponPanel.setOpaque(false);

    resetPanels();
    // Initialize cells with NullIcon to fill the spaces
    clearCell();
  }
  
  private void resetPanels() {
    removeAll();
    bottomPanel.removeAll();
    weaponPanel.removeAll();
    
    bottomPanel.add(weaponPanel, BorderLayout.WEST);
    // Create a placeholder so arrows can still be in the center in the bottom area.
    // Add placeholder null image icons to make sure BorderLayout correctly aligns
    // the arrow.
    JPanel placeholder = new JPanel();
    JLabel pl1 = new JLabel(new NullIcon(true).getImageIcon());
    JLabel pl2 = new JLabel(new NullIcon(true).getImageIcon());
    placeholder.setLayout(new BorderLayout());
    placeholder.setOpaque(false);
    placeholder.add(pl1, BorderLayout.WEST);
    placeholder.add(pl2, BorderLayout.EAST);
    bottomPanel.add(placeholder, BorderLayout.EAST);
    add(bottomPanel, BorderLayout.SOUTH);
  }
  
  private void anchorWeapon(JLabel lifeFormIcon) {
    JLabel weapon = new JLabel(new Weapon1Icon(true).getImageIcon());
    SpringLayout sl = new SpringLayout();
    lifeFormIcon.setLayout(sl);    
    sl.putConstraint(SpringLayout.EAST, weapon, 0, SpringLayout.EAST, lifeFormIcon);
    sl.putConstraint(SpringLayout.SOUTH, weapon, 0, SpringLayout.SOUTH, lifeFormIcon);
    lifeFormIcon.add(weapon);
  }
  
  private void clearCell() {
    setHuman(false, false);
    setAlien(false, false);
    setWestArrow(false);
    setEastArrow(false);
    setNorthArrow(false);
    setSouthArrow(false);
    setWeapon1(false);
    setWeapon2(false);
  }
  
  /**
   * Update the drawn cell based on its contents.
   */
  public void updateCell() {
    // Clear cell
    resetPanels();
    clearCell();
    
    // Redraw
    Environment env = Environment.getEnvironment(5, 5);
    LifeForm lf = env.getLifeForm(row, column);
    Cell cell = env.getCell(row, column);
    
    if (lf != null) {
      // LifeForm
      if (lf instanceof Human) {
        setHuman(true, lf.hasWeapon());
      } else {
        setAlien(true, lf.hasWeapon());
      }
      
      // Direction
      if (lf.getDirection() == 1) {
        setNorthArrow(true);
      } else if (lf.getDirection() == 2) {
        setEastArrow(true);
      } else if (lf.getDirection() == 3) {
        setSouthArrow(true);
      } else if (lf.getDirection() == 4) {
        setWestArrow(true);
      } 
    } else {
      setHuman(false, false);
      setAlien(false, false);
    }
    
    // Cell weapons
    for (int i = 0; i < cell.getWeaponsCount(); i++) {
      if (i == 0) {
        setWeapon1(true);
      } else {
        setWeapon2(true);
      }
    }
    
    // Tell the panel to redraw itself
    revalidate();
    repaint();
  }

  /**
   * Toggle a human in the grid cell. Call prepareChanges()
   * before using this method, and call finishedChanges() after.
   * @param show whether to show the human or not
   */
  public void setHuman(boolean show, boolean weapon) {
    if (show) {
      JLabel l = new JLabel(new HumanIcon(false).getImageIcon());
      // Anchor a weapon to the bottom right of the LifeForm if
      // they are holding a weapon.
      if (weapon) {
        anchorWeapon(l);
      }
      add(l, BorderLayout.CENTER);
    } else {
      JLabel l = new JLabel(new NullIcon(false).getImageIcon());
      add(l, BorderLayout.CENTER);
    }
  }

  /**
   * Toggle an alien in the grid cell. Call prepareChanges()
   * before using this method, and call finishedChanges() after.
   * @param show whether to show the alien or not
   */
  public void setAlien(boolean show, boolean weapon) {
    if (show) {
      JLabel l = new JLabel(new AlienIcon(false).getImageIcon());
      // Anchor a weapon to the bottom right of the LifeForm if
      // they are holding a weapon.
      if (weapon) {
        anchorWeapon(l);
      }
      add(l, BorderLayout.CENTER);
    } else {
      JLabel l = new JLabel(new NullIcon(false).getImageIcon());
      add(l, BorderLayout.CENTER);
    }
  }

  /**
   * Toggle a weapon in the grid cell. Call prepareChanges()
   * before using this method, and call finishedChanges() after.
   * @param show whether to show the weapon or not
   */
  public void setWeapon1(boolean show) {
    if (show) {
      ImageIcon image = new Weapon1Icon(true).getImageIcon();
      weaponPanel.add(new JLabel(image), BorderLayout.WEST);
    } else {
      JLabel l = new JLabel(new NullIcon(true).getImageIcon());
      weaponPanel.add(l, BorderLayout.WEST);
    }
  }

  /**
   * Toggle a weapon in the grid cell. Call prepareChanges()
   * before using this method, and call finishedChanges() after.
   * @param show whether to show the weapon or not
   */
  public void setWeapon2(boolean show) {
    if (show) {
      ImageIcon image = new Weapon2Icon(true).getImageIcon();
      weaponPanel.add(new JLabel(image), BorderLayout.EAST);
    } else {
      JLabel l = new JLabel(new NullIcon(true).getImageIcon());
      weaponPanel.add(l, BorderLayout.EAST);
    }
  }

  /**
   * Toggle an arrow in the grid cell. Call prepareChanges()
   * before using this method, and call finishedChanges() after.
   * @param show whether to show the arrow or not
   */
  public void setNorthArrow(boolean show) {
    if (show) {
      ImageIcon image = new ArrowNorthIcon(true).getImageIcon();
      add(new JLabel(image), BorderLayout.NORTH);
    } else {
      JLabel l = new JLabel(new NullIcon(true).getImageIcon());
      add(l, BorderLayout.NORTH);
    }
  }

  /**
   * Toggle an arrow in the grid cell. Call prepareChanges()
   * before using this method, and call finishedChanges() after.
   * @param show whether to show the arrow or not
   */
  public void setSouthArrow(boolean show) {
    if (show) {
      ImageIcon image = new ArrowSouthIcon(true).getImageIcon();
      bottomPanel.add(new JLabel(image), BorderLayout.CENTER);
    } else {
      JLabel l = new JLabel(new NullIcon(true).getImageIcon());
      bottomPanel.add(l, BorderLayout.CENTER);
    }
  }

  /**
   * Toggle an arrow in the grid cell. Call prepareChanges()
   * before using this method, and call finishedChanges() after.
   * @param show whether to show the arrow or not
   */
  public void setWestArrow(boolean show) {
    if (show) {
      ImageIcon image = new ArrowWestIcon(true).getImageIcon();
      add(new JLabel(image), BorderLayout.WEST);
    } else {
      JLabel l = new JLabel(new NullIcon(true).getImageIcon());
      add(l, BorderLayout.WEST);
    }
  }

  /**
   * Toggle an arrow in the grid cell. Call prepareChanges()
   * before using this method, and call finishedChanges() after.
   * @param show whether to show the arrow or not
   */
  public void setEastArrow(boolean show) {
    if (show) {
      ImageIcon image = new ArrowEastIcon(true).getImageIcon();
      add(new JLabel(image), BorderLayout.EAST);
    } else {
      JLabel l = new JLabel(new NullIcon(true).getImageIcon());
      add(l, BorderLayout.EAST);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Environment env = Environment.getEnvironment(5, 5);
    setBackground(Color.PINK);
    // Un-highlight the previously highlighted cell
    if (gb.getCurrentCell() != null) {
      int cellRow = env.getCellRow(gb.getCurrentCell());
      int cellColumn = env.getCellColumn(gb.getCurrentCell());
      gb.getGridCell(cellRow, cellColumn).setBackground(null);
    }

    gb.setCurrentCell(env.getCell(row, column));
    ip.updateInfo(env.getCell(row, column));
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // Do nothing
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // Do nothing
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // Do nothing
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // Do nothing
  }

}
