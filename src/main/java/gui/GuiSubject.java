package gui;

public interface GuiSubject {
  
  public void notifyObservers();
  
  public void addObserver(GuiObserver o);

}
