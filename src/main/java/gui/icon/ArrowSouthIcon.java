package gui.icon;

import javax.swing.ImageIcon;

public class ArrowSouthIcon extends GuiIcon {

  private ImageIcon icon;

  /**
   * Create an icon to represent a weapon.
   * @param small whether the icon is small
   */
  public ArrowSouthIcon(boolean small) {
    if (small) {
      icon = getScaledIcon("assets/arrow_south.png", IMAGE_SIZE_SMALL);
    } else {
      icon = getScaledIcon("assets/arrow_south.png", IMAGE_SIZE_LARGE);
    }
  }

  @Override
  public ImageIcon getImageIcon() {
    return icon;
  }

}
