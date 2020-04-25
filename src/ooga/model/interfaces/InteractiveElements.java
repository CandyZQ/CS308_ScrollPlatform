package ooga.model.interfaces;

import ooga.model.enums.ElementState;

/**
 * This interface records all game elements that can interact with players/NPCs
 *
 * @author cady
 */
public interface  InteractiveElements {

  /**
   * Sets the state of this element
   *
   * @param state the element of this element
   */
  void setState(ElementState state);

  /**
   * Gets the state of this element
   *
   * @return the element of this element
   */
  ElementState setState();

  /**
   * Activate this element
   */
  void activate();
}
