package state.util;

import java.util.List;

import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

public class RandWeapon implements Rand<Weapon> {

  /**
   * Choose a random weapon.
   * 
   * @return weapon
   */
  public Weapon choose() {
    List<Weapon> weapons = List.of(new Pistol(),
                                        new ChainGun(),
                                        new PlasmaCannon());
    return new FromList<>(weapons).choose();
  }
  
}
