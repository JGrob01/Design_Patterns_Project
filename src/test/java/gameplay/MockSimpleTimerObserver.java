package gameplay;

public class MockSimpleTimerObserver implements TimerObserver {

  private int myTime = 0;

  /**
   * Update this class's time with the given time.
   * @param time new time
   */
  public void updateTime(int time) {
    myTime = time;
  }

  /**
   * Get the current time.
   * @return time
   */
  public int getTime() {
    return myTime;
  }

}
