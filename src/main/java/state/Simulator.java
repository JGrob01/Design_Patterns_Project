package state;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import environment.Cell;
import environment.Environment;
import exceptions.AttachmentException;
import gameplay.SimpleTimer;
import gameplay.TimerObserver;
import gui.Gui;
import lifeform.LifeForm;
import state.util.RandAlien;
import state.util.RandAttachment;
import state.util.RandHuman;
import state.util.RandWeapon;
import weapon.Weapon;

public class Simulator implements TimerObserver {

  private Gui gui;
  private Environment env;
  private SimpleTimer timer;
  protected List<AiContext> contexts = new ArrayList<>();

  /**
   * Create a simulator. Upon construction, the simulator will
   * update the world with the number of humans and aliens randomly.
   * It will also make sure there is an equal number of weapons
   * as there are life forms.
   * 
   * @param env the environment
   * @param timer a simple timer
   * @param humans the number of humans
   * @param aliens the number of aliens
   */
  public Simulator(Environment env, SimpleTimer timer, int humans, int aliens) {
    this.env = env;
    this.timer = timer;

    // Human context
    for (int i = 0; i < humans; i++) {
      LifeForm ooman = new RandHuman().choose();
      createAiContext(ooman);
    }

    // Alien context
    for (int j = 0; j < aliens; j++) {
      LifeForm lol = new RandAlien().choose();
      createAiContext(lol);
    }

    // Weapons
    for (int i = 0; i < humans + aliens; i++) {
      // Add up to 2 attachments to a weapon.
      Weapon w = new RandAttachment(new RandAttachment(
                 new RandWeapon().choose()).choose()).choose();
      // Make the weapons observers
      timer.addTimeObserver(w);
      // Add weapon to env
      addWeaponToEnv(w);
    }
    
    env.notifyObservers();
  }

  private void addWeaponToEnv(Weapon w) {
    // Find a cell with open weapon slots
    Cell c;
    do {
      c = env.getRandomCell();
    } while (c.getWeaponsCount() >= 2);
    c.addWeapon(w);
  }

  private void createAiContext(LifeForm lf) {
    AiContext aic = new AiContext(lf, env);
    contexts.add(aic);
    
    Cell c = env.getEmptyCell();
    env.addLifeForm(lf, env.getCellRow(c), env.getCellColumn(c));
  }

  /**
   * The program's entry point. This method should never be called manually.
   * 
   * @param args commandline arguments
   * 
   * @throws AttachmentException
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      int rows = 9;
      int cols = 9;

      // Initialize an environment with proper sizing
      Environment env = Environment.getEnvironment(rows, cols);
      
      // Add some stuff
      SimpleTimer timer = new SimpleTimer(1000);
      Simulator s = new Simulator(env, timer, 10, 10);

      // Create the GUI
      s.gui = new Gui(rows, cols);
      env.notifyObservers();

      // Activate the timer
      timer.addTimeObserver(s);
      timer.start();
    });
  }

  /**
   * Update time.
   * 
   * @param time time
   */
  public void updateTime(int time) {
    for (AiContext ai : contexts) {
      // Get the previous cell row and column
      int prevRow = ai.getLifeForm().getRow();
      int prevColumn = ai.getLifeForm().getColumn();
      // Have the AIContext do its behavior
      ai.updateTime(time);

      if (gui != null) {
        // Update the previous cell
        gui.getGameBoard().getGridCell(prevRow, prevColumn).updateCell();
        
        // Update the new cell (if any)
        int newRow = ai.getLifeForm().getRow();
        int newColumn = ai.getLifeForm().getColumn();
        //if (newRow != prevRow || newColumn != prevColumn) {
          gui.getGameBoard().getGridCell(newRow, newColumn).updateCell();
        //}
      }
    }
  }
  
}
