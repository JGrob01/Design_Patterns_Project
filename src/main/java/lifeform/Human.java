package lifeform;

public class Human extends LifeForm {

  private int armorPoints;

  public Human(String myName, int currentLifePoints, int armorPoints) {
    super(myName, currentLifePoints, 5, 3);
    setArmorPoints(armorPoints);
  }

  /**
   * Get the human's armor points.
   * @return armor points
   */
  public int getArmorPoints() {
    return armorPoints;
  }

  /**
   * Set the human's armor points. This will be set to zero if given
   * a negative value.
   * @param armorPoints armor points
   */
  public void setArmorPoints(int armorPoints) {
    this.armorPoints = armorPoints;
    if (this.armorPoints < 0) {
      this.armorPoints = 0;
    }
  }

  @Override
  public void takeHit(int damage) {
    currentLifePoints -= Math.max(0, damage - armorPoints);

    if (currentLifePoints < 0) {
      currentLifePoints = 0;
    }
  }
  
}
