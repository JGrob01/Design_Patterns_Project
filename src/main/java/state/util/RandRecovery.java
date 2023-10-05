package state.util;

import java.util.List;

import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;

public class RandRecovery implements Rand<RecoveryBehavior> {

  /**
   * Choose a random recovery behavior.
   * 
   * @return random recovery behavior
   */
  public RecoveryBehavior choose() {
    List<RecoveryBehavior> recoveryList = List.of(new RecoveryNone(),
                    new RecoveryFractional(new RandInt(10, 20).choose()),
                    new RecoveryLinear(new RandInt(1, 5).choose()));
    return new FromList<>(recoveryList).choose();
  }
  
}
