package ooga.model.test.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import ooga.model.characters.ZeldaCharacter;

public class  ExampleController implements PropertyChangeListener {

  public static final String STATE_CHANGE = "State";
//  public static final String STATE_CHANGE = "State";
//  public static final String STATE_CHANGE = "State";
  private ArrayList<ZeldaCharacter> characters;

  public ExampleController() {
    characters = new ArrayList<>();
  }


  public void addModel(ZeldaCharacter character) {
    characters.add(character);
    character.addListener(this);
  }

  public void removeModel(ZeldaCharacter character) {
    characters.remove(character);
    character.removeListener(this);
  }

//  public void addView(AbstractViewPanel view) {
//    registeredViews.add(view);
//  }

//  public void removeView(AbstractViewPanel view) {
//    registeredViews.remove(view);
//  }


  //  Use this to observe property changes from registered models
  //  and propagate them on to all the views.


  public void propertyChange(PropertyChangeEvent evt) {
//    for (AbstractViewPanel view: registeredViews) {
//      view.modelPropertyChange(evt);
//    }

//    System.out.println("model changed!");
    System.out.println(evt.getPropertyName() + " is changed to " + evt.getNewValue());
  }
}