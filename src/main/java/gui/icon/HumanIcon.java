package gui.icon;

import javax.swing.ImageIcon;

public class HumanIcon extends GuiIcon {

  private ImageIcon icon;

  /**
   * Create an icon to represent a human.
   * @param small whether the icon is small
   */
  public HumanIcon(boolean small) {
    if (small) {
      icon = getScaledIcon("assets/human.png", IMAGE_SIZE_SMALL);
    } else {
      icon = getScaledIcon("assets/human.png", IMAGE_SIZE_LARGE);
    }
  }

  @Override
  public ImageIcon getImageIcon() {
    return icon;
  }

}
