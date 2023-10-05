package state.util;

import java.util.List;

import lifeform.Human;

public class RandHuman implements Rand<Human> {

  private List<String> names = List.of("Amanda", "Zach", "Reggie", "Lucy", "Ryan");

  /**
   * Choose a random human.
   * 
   * @return random human
   */
  public Human choose() {
    return new Human(new FromList<>(names).choose(),
                     new RandInt(50, 100).choose(),
                     new RandInt(0, 10).choose());
  }
  
}
