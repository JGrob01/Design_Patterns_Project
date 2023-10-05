package state;

import java.util.ArrayList;

import environment.Environment;
import gameplay.SimpleTimer;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.ChainGun;
import weapon.Weapon;

public class MockSimulator extends Simulator {

  public MockSimulator(Environment env, SimpleTimer timer, int humans, int aliens) {
    super(env, timer, humans, aliens);
    
    // Clear Simulator stuff, set up other stuff
    env.clearBoard();
    contexts = new ArrayList<>();

    LifeForm lf = new Human("Bob", 100, 5);
    env.addLifeForm(lf, 0, 0);
    Weapon w = new ChainGun();
    env.getCell(0, 0).addWeapon(w);
    contexts.add(new AiContext(lf, env));
  }

  protected AiContext getTestContext() {
    return contexts.get(0);
  }
  
}
