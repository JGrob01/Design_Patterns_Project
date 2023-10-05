package gui.icon;

import javax.swing.ImageIcon;

public class ArrowNorthIcon extends GuiIcon {

  private ImageIcon icon;

  /**
   * Create an icon to represent a weapon.
   * @param small whether the icon is small
   */
  public ArrowNorthIcon(boolean small) {
    if (small) {
      icon = getScaledIcon("assets/arrow_north.png", IMAGE_SIZE_SMALL);
    } else {
      icon = getScaledIcon("assets/arrow_north.png", IMAGE_SIZE_LARGE);
    }
  }

  @Override
  public ImageIcon getImageIcon() {
    return icon;
  }

}
