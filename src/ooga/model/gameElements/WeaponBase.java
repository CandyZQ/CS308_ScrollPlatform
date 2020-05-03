package ooga.model.gameElements;

import ooga.model.enums.backend.Direction;
import ooga.model.interfaces.Attacker;

/**
 * This is the base class for all weapons
 *
 * @author cady
 */
public abstract class WeaponBase implements Attacker {

  protected int weapon;
  protected int attack;
  protected Direction attackingDirection;

  /**
   * Creates a new instance of a weapon base
   *
   * @param weapon             the id of that weapon
   * @param attack             the id of that attack with that weapon
   * @param attackingDirection the direction in which the attack is made
   */
  public WeaponBase(int weapon, int attack, Direction attackingDirection) {
    this.weapon = weapon;
    this.attack = attack;
    this.attackingDirection = attackingDirection;
  }

  /**
   * Sets the weapon id
   *
   * @param weaponBase the weapon id
   */
  @Override
  public void setWeapon(int weaponBase) {
    this.weapon = weaponBase;
  }

  /**
   * Gets the weapon id
   *
   * @return the weapon id
   */
  @Override
  public int getWeapon() {
    return weapon;
  }

  /**
   * Sets the attack id
   *
   * @param attack an int corresponds to this attack of the current weapon
   */
  @Override
  public void setAttack(int attack) {
    this.attack = attack;
  }

  /**
   * Gets the attack id
   *
   * @return the attack id
   */
  @Override
  public int getAttack() {
    return attack;
  }

  /**
   * Sets the direction of attack
   *
   * @param direction the direction in which the attack is performed
   */
  @Override
  public void setFiringDirection(Direction direction) {
    this.attackingDirection = direction;
  }

  /**
   * Actions to be performed once fired
   */
  @Override
  public abstract void fire();
}
