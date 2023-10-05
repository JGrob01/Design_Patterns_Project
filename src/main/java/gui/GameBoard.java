package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import environment.Cell;
import environment.Environment;
import lifeform.Human;
import lifeform.LifeForm;

public class GameBoard extends JPanel implements GuiObserver {

  private Cell currentCell;
  private GuiGridCell[][] guiCells;
  private InfoPanel ip;
  private int rows;
  private int columns;

  /**
   * Construct a new grid, which will contain all of the
   * cells for the game.
   * @param rows number of rows
   * @param cols number of columns
   */
  public GameBoard(int rows, int cols, InfoPanel ip) {
    super(new GridLayout(rows, cols));
    Environment.getEnvironment(5, 5).addObserver(this);

    this.ip = ip;
    this.rows = rows;
    this.columns = cols;
    setBorder(BorderFactory.createLineBorder(Color.BLACK));

    guiCells = new GuiGridCell[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        guiCells[i][j] = new GuiGridCell(this, ip, i, j);

        add(guiCells[i][j]);
      }
    }
  }

  /**
   * Get the GUI cell at the given location.
   * @param row the row where the cell is
   * @param col the column where the cell is
   * @returns the cell at the given location, or null if it doesn't exist
   */
  public GuiGridCell getGridCell(int row, int col) {
    if (row < rows && row >= 0) {
      if (col < columns && col >= 0) {
        return guiCells[row][col];
      }
    }
    return null;
  }

  /**
   * Set the currently selected cell. This is the cell
   * that is currently selected by the mouse cursor.
   */
  public void setCurrentCell(Cell cell) {
    currentCell = cell;
  }
  
  /**
   * Set the highlighted cell.
   */
  public void setHighlightedCell(int row, int col) {
    guiCells[row][col].mouseClicked(null);
  }

  /**
   * Get the currently highlighted cell.
   * @return the currently highlighted cell
   */
  public Cell getCurrentCell() {
    return currentCell;
  }

  @Override
  public void update(int row, int col) {
    // Redraw the cell
    guiCells[row][col].updateCell();
    // Update the info panel
    if (currentCell != null) {
      ip.updateInfo(currentCell);
    }
  }

  /**
   * Update the current cell.
   */
  public void updateCurrentCell() {
    Environment env = Environment.getEnvironment(5, 5);

    int i = env.getCellRow(currentCell);
    int j = env.getCellColumn(currentCell);
    guiCells[i][j].updateCell();
    ip.updateInfo(currentCell);
  }
  
}
