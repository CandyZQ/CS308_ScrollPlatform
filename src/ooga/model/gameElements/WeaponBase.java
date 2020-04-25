package ooga.model.gameElements;

import ooga.model.enums.backend.Direction;
import ooga.model.interfaces.Attacker;

/**
 * This is the base class for all weapons
 *
 * @author cady
 */
public abstract class  WeaponBase implements Attacker {

  protected int weapon;
  protected int attack;
  protected Direction attackingDirection;

  public WeaponBase(int weapon, int attack, Direction attackingDirection) {
    this.weapon = weapon;
    this.attack = attack;
    this.attackingDirection = attackingDirection;
  }

  @Override
  public void setWeapon(int weaponBase) {
    this.weapon = weaponBase;
  }

  @Override
  public int getWeapon() {
    return weapon;
  }

  @Override
  public void setAttack(int attack) {
    this.attack = attack;
  }

  @Override
  public int getAttack() {
    return attack;
  }

  @Override
  public void setFiringDirection(Direction direction) {
    this.attackingDirection = direction;
  }

  @Override
  public abstract void fire();
}
