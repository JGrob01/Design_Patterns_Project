package lifeform;

public class MockLifeForm extends LifeForm {
  
  /**
   * Call the super class constructor.
   * @param myName the name of the life form
   * @param currentLifePoints number of life points
   */
  public MockLifeForm(String myName, int currentLifePoints) {
    super(myName, currentLifePoints);
  }

  /**
   * Call the superclass constructor.
   * @param myName the name of the life form
   * @param currentLifePoints number of life points
   * @param attackStrength attack power
   */
  public MockLifeForm(String myName, int currentLifePoints, int attackStrength) {
    super(myName, currentLifePoints, attackStrength, 3);
  }

}
