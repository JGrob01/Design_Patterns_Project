package gui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestGameBoard {

  /**
   * Test that getting the grid cells work properly.
   * @author Devin
   */
  @Test
  public void testGetGridCell() {
    GameBoard gb = new GameBoard(5, 5, null);
    assertNotNull(gb.getGridCell(0, 0));
    assertNotNull(gb.getGridCell(4, 4));

    assertNull(gb.getGridCell(0, -1));
    assertNull(gb.getGridCell(-1, 0));
    assertNull(gb.getGridCell(5, 4));
    assertNull(gb.getGridCell(4, 5));
  }
  
}
