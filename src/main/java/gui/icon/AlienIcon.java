package gui.icon;

import javax.swing.ImageIcon;

public class AlienIcon extends GuiIcon {

  private ImageIcon icon;

  /**
   * Create an icon to represent an alien.
   * @param small whether the icon is small
   */
  public AlienIcon(boolean small) {
    if (small) {
      icon = getScaledIcon("assets/alien.png", IMAGE_SIZE_SMALL);
    } else {
      icon = getScaledIcon("assets/alien.png", IMAGE_SIZE_LARGE);
    }
  }

  @Override
  public ImageIcon getImageIcon() {
    return icon;
  }

}
