package ooga.model.interfaces;

import java.beans.PropertyChangeListener;

public interface  Notifier {
  void addListener(PropertyChangeListener listener);
  void removeListener(PropertyChangeListener listener);
}
