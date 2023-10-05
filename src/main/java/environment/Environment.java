package environment;

import java.util.ArrayList;
import java.util.List;

import exceptions.EnvironmentException;
import gui.GuiObserver;
import gui.GuiSubject;
import lifeform.LifeForm;
import state.util.RandInt;
import weapon.Weapon;

public class Environment implements GuiSubject {

  private static volatile Environment instance;
  
  private int rows;
  private int columns;

  private Cell[][] grid;
  private List<GuiObserver> obs = new ArrayList<>();

  /**
   * Create an environment with a certain amount of rows and columns.
   * @param rows the maximum number of rows
   * @param columns the maximum number of columns
   */
  private Environment(int rows, int columns) {
    grid = new Cell[rows][columns];
    this.rows = rows;
    this.columns = columns;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid[i][j] = new Cell();
      }
    }
  }
  
  public void addObserver(GuiObserver o) {
    obs.add(o);
  }
  
  /**
   * Notify the observers
   */
  public void notifyObservers() {
    // TODO Don't update every cell
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        for (GuiObserver o : obs) {
          o.update(i, j);
        }
      }
    }
  }

  /**
   * Get a random empty cell.
   * @return empty cell
   */
  public Cell getEmptyCell() {
    Cell c;
    int row;
    int col;
    // Classic do-while loop
    do {
      c = getRandomCell();
      row = getCellRow(c);
      col = getCellColumn(c);
    } while (getCell(row, col).getLifeForm() != null);
    
    return c;
  }

  /**
   * Get a random cell within the environment.
   * @return any random cell
   */
  public Cell getRandomCell() {
    return grid[new RandInt(0, rows).choose()]
               [new RandInt(0, columns).choose()];
  }

  /**
   * Get the cell at the given row and column.
   * @param row row where the cell is
   * @param column column where the cell is
   * @return the cell at specified the row and column,
   * or null if it doesn't exist
   */
  public Cell getCell(int row, int column) {
    if (!evaluateBorders(row, column)) {
      return null;
    }
    return grid[row][column];
  }

  /**
   * Get the row of the given cell.
   * @param cell the cell to get the location of
   * @return int containing the coordinates, or -1 if
   * it doesn't exist
   */
  public int getCellRow(Cell cell) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid[i][j] == cell) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * Get the column of the given cell.
   * @param cell the cell to get the location of
   * @return int containing the coordinates, or -1 if
   * it doesn't exist
   */
  public int getCellColumn(Cell cell) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid[i][j] == cell) {
          return j;
        }
      }
    }
    return -1;
  }

  /**
   * Get the instance of the Environment.
   */
  public static synchronized Environment getEnvironment(int row, int col) {
    if (instance == null) {
      instance = new Environment(row, col);
    }
    return instance;
  }
  
  /**
   * Remove all lifeforms and weapons from the environment
   * @author Isaac
   */
  public void clearBoard() {
    int r = 0;
    while (r < rows) {
      for (int c = 0; c < columns; c++) {
        grid[r][c].removeLifeForm();
        grid[r][c].removeWeapon(grid[r][c].getWeapon1());
        grid[r][c].removeWeapon(grid[r][c].getWeapon2());
      }
      r++;
    }
  }

  /**
   * Retrieve the life form that is on the given row and column.
   * @param row the row in which the life form to retrieve is on
   * @param column the column in which the life form to retrieve is on
   * @return the life form at the given row and column, or null if none
   */
  public LifeForm getLifeForm(int row, int column) {
    if (!evaluateBorders(row, column)) {
      return null;
    }
    return grid[row][column].getLifeForm();
  }

  /**
   * Add a life form to the environment.
   * @param entity the life form object to add
   * @param row the row in which to add the life form
   * @param column the colum in which to add the life form
   * @return true if the life form was successfully added
   */
  public boolean addLifeForm(LifeForm entity, int row, int column) {
    if (!evaluateBorders(row, column)) {
      return false;
    }
    entity.setLocation(row, column);
    return grid[row][column].addLifeForm(entity);
  }

  /**
   * Remove life form from the environment.
   * @param row the row where the life form is
   * @param column the column where the life form is
   */
  public void removeLifeForm(int row, int column) {
    grid[row][column].removeLifeForm();
  }
  
  /**
   * Get number of rows in the environment
   * @author Isaac
   * @return number of columns
   */
  public int getNumRows() {
    return rows;
  }
  
  /**
   * Get number of columns in the environment
   * @author Isaac
   * @return number of columns
   */
  public int getNumCols() {
    return  columns;
  }

  /**
   * Check the borders of the grid against the provided row and column.
   * @param row the row to evaluate
   * @param column the column to evaluate
   * @return whether the row and column are both within bounds
   */
  private boolean evaluateBorders(int row, int column) {
    if (row >= this.rows || row < 0) {
      return false;
    }
    if (column >= this.columns || column < 0) {
      return false;
    }
    return true;
  }
  
  /**
   * Add a weapon to a cell
   * @author Isaac
   * @param weapon 
   * @param row 
   * @param col 
   * @return true if weapon added to cell
   */
  public boolean addWeapon(Weapon weapon, int row, int col) {
    if (!evaluateBorders(row, col)) {
      return false;
    }
    return grid[row][col].addWeapon(weapon);
  }
  
  /**
   * Remove a weapon from a cell
   * @author Isaac
   * @param weapon 
   * @param row 
   * @param col 
   * @return the weapon that was removed. null if no weapon removed.
   */
  public Weapon removeWeapon(Weapon weapon, int row, int col) {
    if (!evaluateBorders(row, col)) {
      return null;
    }
    return grid[row][col].removeWeapon(weapon);
  }
  
  /**
   * Get the weapons in the given cell.
   * @author Isaac
   * @param row row in the grid
   * @param col column in the grid
   * @return the weapons in the cell
   */
  public Weapon[] getWeapons(int row, int col) {
    Weapon[] weapon = new Weapon[2];
    weapon[0] = grid[row][col].getWeapon1();
    weapon[1] = grid[row][col].getWeapon2();
    return weapon;
  }
  
  /**
   * Calculate distance given two rows and columns
   * @author Isaac
   * @param row1 
   * @param col1 
   * @param row2 
   * @param col2 
   * @return distance
   * @throws EnvironmentException 
   */
  public double getDistance(int row1, int col1, int row2, int col2) throws EnvironmentException {
    if (!evaluateBorders(row1, col1)) {
      throw new EnvironmentException("Invalid row1 or col1");
    }
    if (!evaluateBorders(row2, col2)) {
      throw new EnvironmentException("Invalid row2 or col2");
    }
    
    double diffRow;
    double diffCol;
    
    if (row1 == row2 || col1 == col2) {
      if (row1 == row2) {
        if (col1 > col2) {
          return (double)((col1 - col2) * 5);
        } else {
          return (double)((col2 - col1) * 5);
        }
      } else {
        if (row1 > row2) {
          return (double)((row1 - row2) * 5);
        } else {
          return (double)((row2 - row1) * 5);
        }
      }
    } else {
      if (row1 != row2) {
        if (row1 > row2) {
          diffRow = row1 - row2;
        } else {
          diffRow = row2 - row1;
        }
      } else {
        diffRow = 0;
      }
      
      if (col1 != row2) {
        if (col1 > col2) {
          diffCol = col1 - col2;
        } else {
          diffCol = col2 - col1;
        }
      } else {
        diffCol = 0;
      }
      
      double distance = Math.sqrt((diffRow * diffRow) + (diffCol * diffCol));
      return distance * 5;
    }
  }
  
  /**
   * Calculate distance given two lifeforms
   * @author Isaac
   * @param lf1 
   * @param lf2 
   * @return distance between two lifeforms
   * @throws EnvironmentException 
   */
  public double getDistance(LifeForm lf1, LifeForm lf2) throws EnvironmentException {
    if (!evaluateBorders(lf1.getRow(), lf1.getColumn())) {
      throw new EnvironmentException("Invalid row or column in first lifeform");
    }
    if (!evaluateBorders(lf2.getRow(), lf2.getColumn())) {
      throw new EnvironmentException("Invalid row or column in second lifeform");
    }
    int row1 = lf1.getRow();
    int row2 = lf2.getRow();
    int col1 = lf1.getColumn();
    int col2 = lf2.getColumn();
    
    double diffRow;
    double diffCol;
    
    if (row1 == row2 || col1 == col2) {
      if (row1 == row2) {
        if (col1 > col2) {
          return (double)((col1 - col2) * 5);
        } else {
          return (double)((col2 - col1) * 5);
        }
      } else {
        if (row1 > row2) {
          return (double)((row1 - row2) * 5);
        } else {
          return (double)((row2 - row1) * 5);
        }
      }
    } else {
      if (row1 != row2) {
        if (row1 > row2) {
          diffRow = row1 - row2;
        } else {
          diffRow = row2 - row1;
        }
      } else {
        diffRow = 0;
      }
      
      if (col1 != row2) {
        if (col1 > col2) {
          diffCol = col1 - col2;
        } else {
          diffCol = col2 - col1;
        }
      } else {
        diffCol = 0;
      }
      
      double distance = Math.sqrt((diffRow * diffRow) + (diffCol * diffCol));
      return distance * 5;
    }
    
  }

}
