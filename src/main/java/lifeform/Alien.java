package lifeform;

import exceptions.RecoveryRateException;
import gameplay.TimerObserver;
import recovery.RecoveryBehavior;

public class Alien extends LifeForm implements TimerObserver {

  private RecoveryBehavior rb;
  private int recoveryRate;
  private int currentRecoveryInterval;
  private int time;

  /**
   * Make Alien.
   * @param myName name
   * @param maxLifePoints maximum life
   */
  public Alien(String myName, int maxLifePoints) {
    super(myName, maxLifePoints, 10, 2);
    this.maxLifePoints = maxLifePoints;
    this.time = 0;
    this.recoveryRate = 0;
    this.currentRecoveryInterval = 0;
  }

  /**
   * Construct Alien with a recovery behavior.
   * @param myName name
   * @param maxLifePoints maximum life
   * @param rb recovery behavior
   */
  public Alien(String myName, int maxLifePoints, RecoveryBehavior rb) {
    this(myName, maxLifePoints);
    this.rb = rb;
  }

  /**
   * Construct Alien with a recovery rate.
   * @param myName name
   * @param maxLifePoints maximum life
   * @param rb recovery behavior
   * @param recoveryRate how many intervals until Alien recovers
   * @throws RecoveryRateException
   */
  public Alien(String myName, int maxLifePoints, RecoveryBehavior rb,
               int recoveryRate) throws RecoveryRateException {
    this(myName, maxLifePoints);
    this.rb = rb;
    this.recoveryRate = recoveryRate;
    if (recoveryRate < 0) {
      throw new RecoveryRateException("Cannot recover at intervals less than zero");
    }
    this.currentRecoveryInterval = recoveryRate;
  }

  /**
   * Get the recovery rate.
   * @return recovery rate
   */
  public int getRecoveryRate() {
    return recoveryRate;
  }

  /**
   * Recover a certain amount of health.
   */
  protected void recover() {
    currentLifePoints = rb.calculateRecovery(currentLifePoints, maxLifePoints);
  }

  /**
   * Update recovery status based on time intervals.
   */
  @Override
  public void updateTime(int time) {
    this.time = time;
    currentRecoveryInterval -= 1;
    if (currentRecoveryInterval == 0) {
      currentRecoveryInterval = recoveryRate;
      recover();
    }
  }

}
