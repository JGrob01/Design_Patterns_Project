package state.util;

public class RandBool implements Rand<Boolean> {

  /**
   * Choose a random boolean.
   * 
   * @return random boolean
   */
  public Boolean choose() {
    if (new RandInt(0, 2).choose() == 0) {
      return true;
    } else {
      return false;
    }
  }
  
}
