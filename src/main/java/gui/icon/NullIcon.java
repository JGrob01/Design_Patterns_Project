package gui.icon;

import javax.swing.ImageIcon;

public class NullIcon extends GuiIcon {

  private ImageIcon icon;

  /**
   * Create an icon to represent an alien.
   * @param small whether the icon should be small or large
   */
  public NullIcon(boolean small) {
    if (small) {
      icon = getScaledIcon("assets/transparent.png", IMAGE_SIZE_SMALL);
    } else {
      icon = getScaledIcon("assets/transparent.png", IMAGE_SIZE_LARGE);
    }
  }

  @Override
  public ImageIcon getImageIcon() {
    return icon;
  }

}
