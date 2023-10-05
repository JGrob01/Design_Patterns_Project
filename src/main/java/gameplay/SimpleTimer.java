package gameplay;

import java.util.ArrayList;
import java.util.List;

public class SimpleTimer extends Thread implements Timer {

  private List<TimerObserver> theObservers;
  private int round;
  private int timeInterval;

  /**
   * Create a simple timer.
   */
  public SimpleTimer() {
    this.theObservers = new ArrayList<>();
    this.round = 0;
    this.timeInterval = 1000;
  }

  /**
   * Create a simpler timer, specifying the interval at which time
   * updates (in milliseconds).
   * @param interval time interval
   */
  public SimpleTimer(int interval) {
    this();
    this.timeInterval = interval;
  }

  @Override
  public void run() {
    for (int i = 0; i < 50; i++) {
      try {
        Thread.sleep(timeInterval);
        timeChanged();
      } catch (InterruptedException e) {
        System.out.println("Oops, thread interrupted");
      }
    }
  }

  public void addTimeObserver(TimerObserver o) {
    theObservers.add(o);
  }

  public void timeChanged() {
    round++;
    theObservers.forEach(ob -> ob.updateTime(round));
  }

  public void removeTimeObserver(TimerObserver o) {
    theObservers.remove(o);
  }
  
  /**
   * Get the number of observers currently listening.
   */
  public int getNumObservers() {
    return theObservers.size();
  }

  /**
   * Get the current round the game is on.
   * @return the current round
   */
  public int getRound() {
    return round;
  }
  
}
