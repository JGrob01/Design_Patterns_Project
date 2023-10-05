package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import environment.Cell;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Weapon;

public class InfoPanel extends JPanel {
  
  private JPanel leftPanel;
  private JPanel rightPanel;
  
  private JLabel lifeFormHeader;
  private JLabel lifeForm;
  private JLabel health;
  private JLabel weapon;
  private JLabel weaponAmmo;
  private JLabel weaponShotsLeft;
  private JLabel direction;
  
  private JLabel cellHeader;
  private JLabel cellWeapon1;
  private JLabel cellWeapon1Ammo;
  private JLabel cellWeapon1ShotsLeft;
  private JLabel cellWeapon2;
  private JLabel cellWeapon2Ammo;
  private JLabel cellWeapon2ShotsLeft;
  
  /**
   * Creates the info panel that shows information
   * related to the current cell and its life form.
   */
  public InfoPanel() {
    setBackground(Color.LIGHT_GRAY);
    setLayout(new BorderLayout());
    setBorder(new EmptyBorder(10, 200, 10, 200));
    
    leftPanel = new JPanel();
    rightPanel = new JPanel();
    leftPanel.setOpaque(false);
    rightPanel.setOpaque(false);
    leftPanel.setLayout(new GridLayout(7, 1));
    rightPanel.setLayout(new GridLayout(7, 1));
    leftPanel.setBorder(new EmptyBorder(0, 0, 0, 24));
    rightPanel.setBorder(new EmptyBorder(0, 24, 0, 0));
    
    lifeFormHeader = new JLabel("LifeForm:");
    lifeForm = new JLabel("Type: None");
    health = new JLabel("Health: 0");
    weapon = new JLabel("Weapon: None");
    weaponAmmo = new JLabel("Ammo: 0");
    weaponShotsLeft = new JLabel("Shots Left: 0");
    direction = new JLabel("Direction: None");
    cellHeader = new JLabel("Cell:");
    cellWeapon1 = new JLabel("Weapon 1: None");
    cellWeapon1Ammo = new JLabel("Ammo: 0");
    cellWeapon1ShotsLeft = new JLabel("Shots Left: 0");
    cellWeapon2 = new JLabel("Weapon 2: None");
    cellWeapon2Ammo = new JLabel("Ammo: 0");
    cellWeapon2ShotsLeft = new JLabel("Shots Left: 0");
    
    lifeFormHeader.setFont(new Font("Cheese", Font.BOLD, 16));
    cellHeader.setFont(new Font("Cheese", Font.BOLD, 16));
    
    leftPanel.add(lifeFormHeader);
    leftPanel.add(lifeForm);
    leftPanel.add(health);
    leftPanel.add(direction);
    leftPanel.add(weapon);
    leftPanel.add(weaponAmmo);
    leftPanel.add(weaponShotsLeft);
    rightPanel.add(cellHeader);
    rightPanel.add(cellWeapon1);
    rightPanel.add(cellWeapon1Ammo);
    rightPanel.add(cellWeapon1ShotsLeft);
    rightPanel.add(cellWeapon2);
    rightPanel.add(cellWeapon2Ammo);
    rightPanel.add(cellWeapon2ShotsLeft);
    
    add(leftPanel, BorderLayout.WEST);
    add(rightPanel, BorderLayout.CENTER);
  }

  private void updateType(LifeForm lf) {
    if (lf != null) {
      if (lf instanceof Human) {
        lifeForm.setText("Type: Human");
      } else if (lf instanceof Alien) {
        lifeForm.setText("Type: Alien");
      }
    } else {
      lifeForm.setText("Type: None");
    }
  }

  private void updateHealth(LifeForm lf) {
    if (lf != null) {
      health.setText("Health: " + lf.getCurrentLifePoints());
    } else {
      health.setText("Health: 0");
    }
  }

  private void updateWeapon(LifeForm lf) {
    if (lf != null) {
      if (lf.hasWeapon()) {
        Weapon w = lf.getWeapon();

        weapon.setText("Weapon: " + w.toString());
        weaponAmmo.setText("Ammo: " + w.getCurrentAmmo());
        weaponShotsLeft.setText("Shots Left: " + w.getShotsLeft());
      } else {
        weapon.setText("Weapon: None");
        weaponAmmo.setText("Ammo: 0");
        weaponShotsLeft.setText("Shots Left: 0");
      }
    } else {
      weapon.setText("Weapon: None");
      weaponAmmo.setText("Ammo: 0");
      weaponShotsLeft.setText("Shots Left: 0");
    }
  }

  private void updateDirection(LifeForm lf) {
    if (lf != null) {
      if (lf.getDirection() == 1) {
        direction.setText("Direction: North");
      } else if (lf.getDirection() == 2) {
        direction.setText("Direction: East");
      } else if (lf.getDirection() == 3) {
        direction.setText("Direction: South");
      } else if (lf.getDirection() == 4) {
        direction.setText("Direction: West");
      }
    } else {
      direction.setText("Direction: None");
    }
  }
  
  private void updateCellWeapons(Cell c) {
    cellWeapon1.setText("Weapon 1: None");
    cellWeapon1Ammo.setText("Ammo: 0");
    cellWeapon1ShotsLeft.setText("Shots Left: 0");
    cellWeapon2.setText("Weapon 2: None");
    cellWeapon2Ammo.setText("Ammo: 0");
    cellWeapon2ShotsLeft.setText("Shots Left: 0");
    
    for (int i = 0; i < c.getWeaponsCount(); i++) {
      if (i == 0) {
        cellWeapon1.setText("Weapon 1: " + c.getWeapon1().toString());
        cellWeapon1Ammo.setText("Ammo: " + c.getWeapon1().getCurrentAmmo());
        cellWeapon1ShotsLeft.setText("Shots Left: " + c.getWeapon1().getShotsLeft());
      } else {
        cellWeapon2.setText("Weapon 2: " + c.getWeapon2().toString());
        cellWeapon2Ammo.setText("Ammo: " + c.getWeapon2().getCurrentAmmo());
        cellWeapon2ShotsLeft.setText("Shots Left: " + c.getWeapon2().getShotsLeft());
      }
    }
  }

  /**
   * Update the info panel with new information.
   */
  public void updateInfo(Cell c) {
    LifeForm lf;
    try {
      lf = c.getLifeForm();
    } catch (NullPointerException e) {
      lf = null;
    }
    updateType(lf);
    updateHealth(lf);
    updateWeapon(lf);
    updateDirection(lf);
    updateCellWeapons(c);
  }

}
