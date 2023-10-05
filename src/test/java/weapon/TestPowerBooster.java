package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * @author isaac
 * testing power booster on weapons
 */
public class TestPowerBooster {

  /**
   * @author isaac
   * Tests power booster attached to a chain gun with no other attachments
   * @throws WeaponException
   * @throws AttachmentException 
   */
  @Test
  public void testPowerBoosterAlone() throws WeaponException, AttachmentException {
    ChainGun gun = new ChainGun();
    PowerBooster pb = new PowerBooster(gun);
    
    assertEquals(24, pb.fire(50));
    
    while (pb.getCurrentAmmo() != 22) {
      pb.fire(10);
      if (pb.getShotsLeft() == 0) {
        pb.updateTime(0);
      }
    }
    assertEquals(18, pb.fire(50));
    assertEquals(1, pb.getNumAttachments());
  }

  /**
   * @author isaac
   * Tests power booster and scope attached to a chain gun
   * @throws WeaponException
   * @throws AttachmentException 
   */
  @Test
  public void testPowerBoosterAndScope() throws WeaponException, AttachmentException {
    ChainGun gun = new ChainGun();
    PowerBooster pb = new PowerBooster(gun);
    Scope scope = new Scope(pb);
    
    assertEquals(19, scope.fire(25));
    
    ChainGun gun2 = new ChainGun();
    Scope scope2 = new Scope(gun2);
    PowerBooster pb2 = new PowerBooster(scope2);
    
    assertEquals(18, pb2.fire(25));
  }
  
  /**
   * @author isaac
   * Tests two power boosters attached to a chain gun
   * @throws WeaponException 
   * @throws AttachmentException 
   */
  @Test
  public void testTwoPowerBoosters() throws WeaponException, AttachmentException {
    ChainGun gun = new ChainGun();
    PowerBooster pb = new PowerBooster(gun);
    PowerBooster pb2 = new PowerBooster(pb);
    
    assertEquals(48, pb2.fire(50));
    
    while (pb2.getCurrentAmmo() != 7) {
      pb2.fire(10);
      if (pb2.getShotsLeft() == 0) {
        pb2.updateTime(0);
      }
    }
    assertEquals(9, pb2.fire(31));
  }
  
  /**
   * @author isaac
   * Tests a power booster and stabilizer attached to a chain gun
   * @throws WeaponException 
   * @throws AttachmentException 
   */
  @Test
  public void testPowerBoosterAndStabilizer() throws WeaponException, AttachmentException {
    ChainGun gun = new ChainGun();
    PowerBooster pb = new PowerBooster(gun);
    Stabilizer stab = new Stabilizer(pb);
    
    while (stab.getCurrentAmmo() != 1) {
      stab.fire(10);
      if (stab.getShotsLeft() == 0) {
        stab.updateTime(0);
      }
    }
    stab.updateTime(0);
    assertEquals(1, stab.getCurrentAmmo());
    stab.fire(10);
    assertEquals(40, stab.getCurrentAmmo());
    
    ChainGun gun2 = new ChainGun();
    Stabilizer stab2 = new Stabilizer(gun2);
    PowerBooster pb2 = new PowerBooster(stab2);
    
    while (pb2.getCurrentAmmo() != 1) {
      pb2.fire(10);
      if (pb2.getShotsLeft() == 0) {
        pb2.updateTime(0);
      }
    }
    pb2.updateTime(0);
    assertEquals(1, pb2.getCurrentAmmo());
    pb2.fire(10);
    assertEquals(40, pb2.getCurrentAmmo());
  }

}