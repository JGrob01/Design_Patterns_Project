package state.util;

import java.util.Random;

public class RandInt implements Rand<Integer> {

  int low;
  int high;

  /**
   * Constructor to prepare the random generator for an integer.
   * 
   * @param low lowest number to generate
   * @param high highest number to generate
   */
  public RandInt(int low, int high) {
    this.low = low;
    this.high = high;
  }

  /**
   * Choose a random int.
   * 
   * @return random int
   */
  public Integer choose() {
    return new Random().nextInt(high - low) + low;
  }
  
}
