package lifeform;

import environment.Cell;
import environment.Environment;
import exceptions.WeaponException;
import gui.GameBoard;
import weapon.Weapon;

public abstract class LifeForm {

  private String myName;
  private int attackStrength;
  protected int currentLifePoints;
  protected int maxLifePoints;
  protected Weapon weapon;
  protected int col;
  protected int row;
  protected int direction = 1;
  protected int maxSpeed;

  /**
   * Initialize a LifeForm with the provided info and an attack power of one.
   * 
   * @param myName            the name of the LifeForm
   * @param currentLifePoints number of life points
   */
  public LifeForm(String myName, int currentLifePoints) {
    this.myName = myName;
    this.currentLifePoints = currentLifePoints;
    this.maxLifePoints = currentLifePoints;
    if (this.currentLifePoints < 0) {
      this.currentLifePoints = 0;
    }
    this.attackStrength = 1;
    row = -1;
    col = -1;
  }

  /**
   * Initialize a LifeForm.
   * 
   * @param myName            the name of the LifeForm
   * @param currentLifePoints number of life points0
   * @param attackStrength    attack power
   */
  public LifeForm(String myName, int currentLifePoints, int attackStrength) {
    this(myName, currentLifePoints);
    this.attackStrength = attackStrength;
    row = -1;
    col = -1;
  }

  /**
   * Initialize a LifeForm.
   * 
   * @param myName            the name of the LifeForm
   * @param currentLifePoints number of life points0
   * @param attackStrength    attack power
   * @param maxSpeed             speed
   */
  public LifeForm(String myName, int currentLifePoints, int attackStrength, int maxSpeed) {
    this(myName, currentLifePoints, attackStrength);
    this.maxSpeed = maxSpeed;
  }

  /**
   * Get the maximum life points of the life form.
   * @return maximum life points
   */
  public int getMaxLifePoints() {
    return maxLifePoints;
  }

  /**
   * Set health back to maximum.
   */
  public void resetHealth() {
    currentLifePoints = maxLifePoints;
  }

  /**
   * Get the name of the LifeForm.
   * 
   * @return the name of the LifeForm
   */
  public String getName() {
    return myName;
  }

  /**
   * Get the LifeForm's current life points.
   * 
   * @return current life points
   */
  public int getCurrentLifePoints() {
    return currentLifePoints;
  }

  /**
   * Take damage. In other words, the LifeForm object will lose life points.
   * 
   * @param damage the amount of life points the life form will lose
   */
  public void takeHit(int damage) {
    currentLifePoints -= damage;
    if (currentLifePoints < 0) {
      currentLifePoints = 0;
    }
  }

  /**
   * Get the current attack strength of the LifeForm.
   */
  public int getAttackStrength() {
    return attackStrength;
  }

  /**
   * Do damage to another Lifeform.
   */
  public void attack(LifeForm opponent, int distance) {
    // Check if the life form is alive
    if (currentLifePoints > 0) {
      // Weapon stuff
      if (hasWeapon() && weapon.getCurrentAmmo() > 0) {
        try {
          opponent.takeHit(weapon.fire(distance));
        } catch (WeaponException e) {
          e.printStackTrace();
        }
        return;
      }
      // Melee stuff
      if (distance <= 5) {
        opponent.takeHit(attackStrength);
      }
    }
  }

  /**
   * Drop the current weapon and return it.
   * 
   * @return the weapon being dropped
   */
  public Weapon dropWeapon() {
    Weapon w = weapon;
    weapon = null;
    return w;
  }

  /**
   * Get if the life form has a weapon.
   * 
   * @return true if the life form has a weapon; false otherwise
   */
  public boolean hasWeapon() {
    if (weapon == null) {
      return false;
    }
    return true;
  }

  /**
   * Give a weapon to this life form.
   * 
   * @param w the weapon to give
   * @return true if the weapon was acquired, false otherwise
   */
  public boolean pickUpWeapon(Weapon w) {
    if (weapon == null) {
      weapon = w;
      return true;
    }
    return false;
  }

  /**
   * Reload the weapon.
   */
  public void reloadWeapon() {
    if (weapon != null) {
      weapon.reload();
    }
  }
  
  /**
   * Makes a lifeform face north
   */
  public void faceNorth() {
    direction = 1;
  }
  
  /**
   * Makes a lifeform face east
   */
  public void faceEast() {
    direction = 2;
  }
  
  /**
   * Make a lifeform face south
   */
  public void faceSouth() {
    direction = 3;
  }
  
  /**
   * 
   * Make a lifeform face west
   */
  public void faceWest() {
    direction = 4;
  }
  
  /**
   * Get direction.
   * 
   * @return direction the LifeForm is facing
   * north = 1
   * east = 2
   * south = 3
   * west = 4
   */
  public int getDirection() {
    return direction;
  }

  /**
   * Get weapon.
   * 
   * @return LifeForm's weapon
   */
  public Weapon getWeapon() {
    return weapon;
  }
  
  /**
   * Make a lifeform move along the environment
   */
  public boolean move(GameBoard gb) {
    int startR = row;
    int startC = col;
    if (direction == 4) {
      for (int i = maxSpeed; i > 0; i--) {
        Cell tempCell = Environment.getEnvironment(5, 5).getCell(startR, startC - i);
        if (startC - i < 0) {
          continue;
        } else if (tempCell.getLifeForm() == null) {
          setLocation(startR, startC - i);
          Environment.getEnvironment(5, 5).addLifeForm(this, row, col);
          Environment.getEnvironment(5, 5).removeLifeForm(startR, startC);
          // update GUI
          if (gb != null) {
            gb.updateCurrentCell();
            gb.update(row, col);
            gb.setHighlightedCell(row, col);
          }
          return true;
        }
      }
    } else if (direction == 3) {
      for (int i = maxSpeed; i > 0; i--) {
        Cell tempCell = Environment.getEnvironment(5, 5).getCell(startR + i, startC);
        if (startR + i >= Environment.getEnvironment(5, 5).getNumRows()) {
          continue;
        } else if (tempCell.getLifeForm() == null) {
          setLocation(startR + i, startC);
          Environment.getEnvironment(5, 5).addLifeForm(this, row, col);
          Environment.getEnvironment(5, 5).removeLifeForm(startR, startC);
          // update GUI
          if (gb != null) {
            gb.updateCurrentCell();
            gb.update(row, col);
            gb.setHighlightedCell(row, col);
          }
          return true;
        }
      }
      
    } else if (direction == 2) {
      for (int i = maxSpeed; i > 0; i--) {
        Cell tempCell = Environment.getEnvironment(5, 5).getCell(startR,  startC + i);
        if (startC + i >= Environment.getEnvironment(5, 5).getNumCols()) {
          continue;
        } else if (tempCell.getLifeForm() == null) {
          setLocation(startR, startC + i);
          Environment.getEnvironment(5, 5).addLifeForm(this, row, col);
          Environment.getEnvironment(5, 5).removeLifeForm(startR, startC);
          // update GUI
          if (gb != null) {
            gb.updateCurrentCell();
            gb.update(row, col);
            gb.setHighlightedCell(row, col);
          }
          return true;
        }
      }
    } else if (direction == 1) {
      for (int i = maxSpeed; i > 0; i--) {
        Cell tempCell = Environment.getEnvironment(5, 5).getCell(startR - i, startC);
        if (startR - i < 0) {
          continue;
        } else if (tempCell.getLifeForm() == null) {
          setLocation(startR - i, startC);
          Environment.getEnvironment(5, 5).addLifeForm(this, row, col);
          Environment.getEnvironment(5, 5).removeLifeForm(startR, startC);
          // update GUI
          if (gb != null) {
            gb.updateCurrentCell();
            gb.update(row, col);
            gb.setHighlightedCell(row, col);
          }
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Gets the row.
   */
  public int getRow() {
    return row;
  }

  /**
   * Gets the row.
   */
  public int getColumn() {
    return col;
  }

  /**
   * Gets the row.
   */
  public void setLocation(int r, int c) {
    if (r <= -1) {
      row = row;
    } else {
      row = r;
    }
    if (c <= -1) {
      col = col;
    } else {
      col = c;
    }
  }
}
