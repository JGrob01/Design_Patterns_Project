package gui.icon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;


public abstract class GuiIcon {

  protected static final int IMAGE_SIZE_LARGE = 35;
  protected static final int IMAGE_SIZE_SMALL = 15;

  protected ImageIcon getScaledIcon(String path, int size) {
    ImageIcon image = null;
    try {
      image = new ImageIcon(new File(path).getCanonicalPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    Image scaled = image.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
    ImageIcon scaledImage = new ImageIcon(scaled);
    return scaledImage;
  }

  public abstract ImageIcon getImageIcon();
  
}
