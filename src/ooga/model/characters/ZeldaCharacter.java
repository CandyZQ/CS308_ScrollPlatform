package ooga.model.characters;

import ooga.model.PropertyChangeNotifier;
import ooga.model.enums.backend.CharacterType;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.MovingState;
import ooga.model.gameElements.WeaponBase;
import ooga.model.interfaces.Alive;
import ooga.model.interfaces.Attacker;
import ooga.model.interfaces.Notifier;
import ooga.model.move.MovingObject2D;

import java.beans.PropertyChangeListener;

/**
 * This class is a specific implementation of a character moves on a 2D space, typically an npc.
 *
 * @author Cady
 * @see CharacterBase
 * @see WeaponBase
 */
public class ZeldaCharacter extends MovingObject2D implements Alive, Attacker, Notifier {

  public static final int DEFAULT_ATTACK = 0;
  public static final int DEFAULT_WEAPON = 0;

  private CharacterBase cb;
  private WeaponBase weaponBase;
  protected transient PropertyChangeNotifier notifier;

  /**
   * Creates a new instance of ZeldaCharacter with {@code initialHp}, {@code id}, and {@code type}
   *
   * @param initialHp the initial
   * @param id        the id of this CharacterBase
   * @param type      the {@link CharacterType} of this instance
   */
  public ZeldaCharacter(int initialHp, int id, CharacterType type) {
    this(initialHp, DEFAULT_WEAPON, id, type);
  }

  /**
   * Creates a new instance of ZeldaCharacter with {@code initialHp}, {@code weapon}, {@code id},
   * and {@code type}
   *
   * @param initialHp the initial
   * @param weapon    the weapon that the character will use
   * @param id        the id of this CharacterBase
   * @param type      the {@link CharacterType} of this instance
   */
  public ZeldaCharacter(int initialHp, int weapon, int id, CharacterType type) {
    this(initialHp, weapon, DEFAULT_ATTACK, id, DEFAULT_X, DEFAULT_Y, type);
  }

  /**
   * Creates a new instance of ZeldaCharacter with all properties needed
   *
   * @param initialHp the initial
   * @param weapon    the weapon that the character will use
   * @param id        the id of this CharacterBase
   * @param type      the {@link CharacterType} of this instance
   * @param attack    the attack the character uses
   * @param x         the initial x position
   * @param y         the initial y position
   */
  public ZeldaCharacter(int initialHp, int weapon, int attack, int id, double x, double y,
      CharacterType type) {
    super(x, y);
    cb = new CharacterBase(id, initialHp, type);
    this.weaponBase = new WeaponBase(weapon, attack, movingDirection) {
      @Override
      public void fire() {
        setState(MovingState.ATTACK1);
      }
    };
    notifier = new PropertyChangeNotifier(this);
  }


  /**
   * Adds a new listener
   *
   * @param listener a listener to this class
   */
  @Override
  public void addListener(PropertyChangeListener listener) {
    notifier.addListener(listener);
  }

  /**
   * Removes a listener
   *
   * @param listener the listener to be removed
   */
  @Override
  public void removeListener(PropertyChangeListener listener) {
    notifier.removeListener(listener);
  }

  /**
   * Notifies the listeners that a state has been changed
   *
   * @param property the name of that property
   * @param oldState the old value of that property
   * @param newState the new value of that property
   */
  @Override
  protected void notifyChange(String property, Object oldState, Object newState) {
    notifier.firePropertyChange(property, oldState, newState);
  }

  /**
   * Gets the id of this object
   *
   * @return the id of this object
   */
  @Override
  public int getId() {
    return cb.getId();
  }

  /**
   * Sets the id of this object
   *
   * @param id the id of this object
   */
  @Override
  public void setId(int id) {
    cb.setId(id);
  }

  /**
   * Gets the HP of this object
   *
   * @return the HP of this object
   */
  @Override
  public int getHP() {
    return cb.getHP();
  }


  /**
   * Sets the HP of this object
   *
   * @param hp the HP to set
   */
  @Override
  public void setHP(int hp) {
    cb.setHP(hp);
  }

  /**
   * Adds HP by a certain amount
   *
   * @param deltaHP the HP to add
   */
  @Override
  public void addHP(int deltaHP) {
    cb.addHP(deltaHP);
  }

  /**
   * Subtracts HP by a certain amount
   *
   * @param deltaHP the HP to subtract
   */
  @Override
  public void subtractHP(int deltaHP) {
    cb.subtractHP(deltaHP);
  }

  /**
   * Gets if this object is still alive
   *
   * @return if the HP is larger than 0
   */
  @Override
  public boolean isAlive() {
    return cb.isAlive();
  }

  /**
   * Sets the type of this character
   *
   * @param type the {@link CharacterType} of this character
   */
  @Override
  public void setType(CharacterType type) {
    cb.setType(type);
  }

  /**
   * Gets the type of this character
   *
   * @return the {@link CharacterType} of this character
   */
  @Override
  public CharacterType getType() {
    return cb.getType();
  }

  /**
   * Sets the weapon id
   *
   * @param weapon the weapon id
   */
  @Override
  public void setWeapon(int weapon) {
    weaponBase.setWeapon(weapon);
  }

  /**
   * Gets the attack id
   *
   * @return the attack id
   */
  @Override
  public int getWeapon() {
    return weaponBase.getWeapon();
  }

  /**
   * Sets the attack id
   *
   * @param attack an int corresponds to this attack of the current weapon
   */
  @Override
  public void setAttack(int attack) {
    weaponBase.setAttack(attack);
  }

  /**
   * Gets the attack id
   *
   * @return the attack id
   */
  @Override
  public int getAttack() {
    return weaponBase.getAttack();
  }

  /**
   * Sets the direction of attack
   *
   * @param direction the direction in which the attack is performed
   */
  @Override
  public void setFiringDirection(Direction direction) {
    weaponBase.setFiringDirection(direction);
  }

  /**
   * Actions to be performed once fired
   */
  @Override
  public void fire() {
    weaponBase.fire();
  }
}
