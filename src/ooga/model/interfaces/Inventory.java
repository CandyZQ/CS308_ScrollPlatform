package ooga.model.interfaces;

import java.util.Map;
import ooga.model.gameElements.Element;

/**
 * This interface stores information of other objects that a player can take. A few examples are
 * different weapons, coins, bombs, etc. These objects are stored as {@link Element}
 *
 * @author cady
 * @see Element
 */
public interface  Inventory {

  /**
   * Adds a certain amount of an object into the inventory
   *
   * @param element the element to be added
   * @param amount  the amount of element to add
   */
  void addElement(int element, int amount);

  /**
   * Subtracts a certain amount of an object into the inventory
   *
   * @param element the element to be subtracted
   * @param amount  the amount of element to subtract
   */
  void useElement(int element, int amount);

  /**
   * Gets a complete list of inventory
   *
   * @return a map of elements. The key is the element and the value is the amount of that element
   * in this inventory
   */
  Map<Element, Integer> getInventory();
}
