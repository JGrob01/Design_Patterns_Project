package state.util;

import java.util.ArrayList;
import java.util.List;

public class RandList<A> implements Rand<List<A>> {

  Rand<A> rand;
  int size;

  /**
   * Constructor to prepare the random list generator.
   * 
   * @param rand tool to use to generate random items
   * @param size number of elements to generate
   */
  public RandList(Rand<A> rand, int size) {
    this.rand = rand;
    this.size = size;
  }

  /**
   * Choose a random list.
   * 
   * @return random list
   */
  public List<A> choose() {
    List<A> items = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      items.add(rand.choose());
    }
    return items;
  }
  
}
