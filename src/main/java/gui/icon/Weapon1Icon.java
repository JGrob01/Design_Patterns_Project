package gui.icon;

import javax.swing.ImageIcon;

public class Weapon1Icon extends GuiIcon {

  private ImageIcon icon;

  /**
   * Create an icon to represent a weapon.
   * @param small whether the icon is small
   */
  public Weapon1Icon(boolean small) {
    if (small) {
      icon = getScaledIcon("assets/weapon1.png", IMAGE_SIZE_SMALL);
    } else {
      icon = getScaledIcon("assets/weapon1.png", IMAGE_SIZE_LARGE);
    }
  }

  @Override
  public ImageIcon getImageIcon() {
    return icon;
  }

}
