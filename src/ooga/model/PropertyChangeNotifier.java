package ooga.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A notifier that notifies property changes
 *
 * @author cady
 */
public class PropertyChangeNotifier {

  protected PropertyChangeSupport propertyChangeSupport;

  /**
   * Creates a new notifier
   *
   * @param sourceBean
   */
  public PropertyChangeNotifier(Object sourceBean) {
    propertyChangeSupport = new PropertyChangeSupport(sourceBean);
  }

  /**
   * Adds a new listener
   *
   * @param listener a listener to this class
   */
  public void addListener(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  /**
   * Removes a listener
   *
   * @param listener the listener to be removed
   */
  public void removeListener(PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  /**
   * Notifies the listeners that properties have been changed
   *
   * @param propertyName the name of that property
   * @param oldValue     the old value of that property
   * @param newValue     the new value of that property
   */
  public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
  }
}
