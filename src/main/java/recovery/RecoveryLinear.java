package recovery;

public class RecoveryLinear implements RecoveryBehavior {

  private int recoveryStep;

  public RecoveryLinear(int recoveryStep) {
    this.recoveryStep = recoveryStep;
  }

  @Override
  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife == 0) {
      return 0;
    }
    currentLife += recoveryStep;
    if (currentLife > maxLife) {
      currentLife = maxLife;
    }
    return currentLife;
  }
  
}
