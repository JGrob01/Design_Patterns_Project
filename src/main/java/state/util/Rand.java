package state.util;

public interface Rand<A> {

  /**
   * Select a random A based on information
   * given in the constructor.
   */
  public A choose();
  
}
