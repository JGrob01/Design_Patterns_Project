package state.util;

import java.util.List;

public class FromList<A> implements Rand<A> {

  private List<A> options;

  /**
   * Pick a random element from the given list.
   * 
   * @param options list of options
   */
  public FromList(List<A> options) {
    this.options = options;
  }

  /**
   * Choose a random element from the list.
   * 
   * @return an element
   */
  public A choose() {
    return options.get(new RandInt(0, options.size() - 1).choose());
  }
  
}
