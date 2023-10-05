package recovery;

public class RecoveryNone implements RecoveryBehavior {

  @Override
  public int calculateRecovery(int currentLife, int maxLife) {
    return currentLife;
  }

}
