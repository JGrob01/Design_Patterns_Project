package recovery;

public interface RecoveryBehavior {

  /**
   * Calculate how much health a life form will have after recovery.
   * @param currentLife current life of life form
   * @param maxLife maximum life of life form
   * @return current life points after recovery
   */
  public int calculateRecovery(int currentLife, int maxLife);
  
}
