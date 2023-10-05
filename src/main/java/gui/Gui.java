package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Gui extends JFrame {

  private GuiController controller;
  private GameBoard board;
  private Legend legend;
  private InfoPanel infoPanel;

  /**
   * Make a GUI
   * @param rows rows
   * @param cols columns
   */
  public Gui(int rows, int cols) {
    setLayout(new BorderLayout());

    // Legend creation
    legend = new Legend();
    add(legend, BorderLayout.WEST);
    // InfoPanel creation
    infoPanel = new InfoPanel();
    add(infoPanel, BorderLayout.SOUTH);
    // GameBoard creation
    board = new GameBoard(rows, cols, infoPanel);
    add(board, BorderLayout.CENTER);
    // Invoker creation
    // controller = new GuiController(board);
    // InvokerMaker im = new InvokerMaker(board);
    // im.createInvokerGui(controller);
    // im.displayButtons();
    // add(im, BorderLayout.EAST);

    setTitle("Lab");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * Get the game board within the GUI.
   * @return the active game board
   */
  public GameBoard getGameBoard() {
    return board;
  }

}
