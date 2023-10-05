package gameplay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestSimpleTimer {

  /**
   * Test that simple timer initializes correctly.
   */
  @Test
  public void testInitialization() {
    SimpleTimer timer = new SimpleTimer();
    assertEquals(0, timer.getRound());
    assertEquals(0, timer.getNumObservers());
  }

  /**
   * Test that simple timer updates the time correctly.
   */
  @Test
  public void testUpdateTime() {
    SimpleTimer timer = new SimpleTimer();
    MockSimpleTimerObserver o = new MockSimpleTimerObserver();
    timer.addTimeObserver(o);
    timer.timeChanged();
    assertEquals(1, timer.getRound());
  }

  /**
   * Observers should be able to be added to simple timer.
   */
  @Test
  public void testAddObservers() {
    SimpleTimer timer = new SimpleTimer();
    TimerObserver o = new MockSimpleTimerObserver();
    timer.addTimeObserver(o);
    assertEquals(1, timer.getNumObservers());
  }

  /**
   * Observers should be able to receive time updates.
   */
  @Test
  public void testObserversReceiveUpdates() {
    SimpleTimer timer = new SimpleTimer();
    MockSimpleTimerObserver o = new MockSimpleTimerObserver();
    timer.addTimeObserver(o);
    assertEquals(0, o.getTime());
    timer.timeChanged();
    assertEquals(1, o.getTime());
    timer.timeChanged();
    assertEquals(2, o.getTime());
  }

  /**
   * Test that simple timer updates time correctly as a thread.
   */
  @Test
  public void testTimeThread() throws InterruptedException {
    SimpleTimer st = new SimpleTimer(1000);
    st.start();
    Thread.sleep(250);
    for (int x = 0; x < 5; x++) {
      assertEquals(x, st.getRound());
      Thread.sleep(1000);
    }
  }

}
