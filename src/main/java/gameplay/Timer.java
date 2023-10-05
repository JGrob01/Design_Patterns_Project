package gameplay;

public interface Timer {

  /**
   * Add an observer (TimerObserver) to the subject (Timer).
   * @param o the TimerObserver to add
   */
  public void addTimeObserver(TimerObserver o);

  /**
   * Notify all of the observers when the time is updated.
   */
  public void timeChanged();

  /**
   * Remove an observer from the list.
   * @param o the TimerObserver to remove
   */
  public void removeTimeObserver(TimerObserver o);
  
}
