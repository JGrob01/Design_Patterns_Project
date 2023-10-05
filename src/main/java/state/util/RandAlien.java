package state.util;

import java.util.List;

import exceptions.RecoveryRateException;
import lifeform.Alien;

public class RandAlien implements Rand<Alien> {

  private List<String> names = List.of("Jedi", "Sith", "Count Dooku", "Yoda", "Chewbacca");

  /**
   * Choose a random alien.
   * 
   * @return random alien
   */
  public Alien choose() {
    try {
      return new Alien(new FromList<>(names).choose(),
                     new RandInt(50, 100).choose(),
                     new RandRecovery().choose(),
                     new RandInt(1, 5).choose());
    } catch (RecoveryRateException e) {
      e.printStackTrace();
      return null;
    }
  }
  
}
