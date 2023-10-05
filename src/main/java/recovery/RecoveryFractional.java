package recovery;

public class RecoveryFractional implements RecoveryBehavior {

  private double percentRecovery;

  public RecoveryFractional(double percentRecovery) {
    this.percentRecovery = percentRecovery;
  }

  @Override
  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife == 0) {
      return 0;
    }
    currentLife += Math.ceil(currentLife * percentRecovery);
    if (currentLife > maxLife) {
      currentLife = maxLife;
    }
    return currentLife;
  }
  
}
