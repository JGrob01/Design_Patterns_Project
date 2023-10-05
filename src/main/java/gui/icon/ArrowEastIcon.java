package gui.icon;

import javax.swing.ImageIcon;

public class ArrowEastIcon extends GuiIcon {

  private ImageIcon icon;

  /**
   * Create an icon to represent a weapon.
   * @param small whether the icon is small
   */
  public ArrowEastIcon(boolean small) {
    if (small) {
      icon = getScaledIcon("assets/arrow_east.png", IMAGE_SIZE_SMALL);
    } else {
      icon = getScaledIcon("assets/arrow_east.png", IMAGE_SIZE_LARGE);
    }
  }

  @Override
  public ImageIcon getImageIcon() {
    return icon;
  }

}
