package ooga.model.characters;

import ooga.model.enums.backend.CharacterType;
import ooga.model.interfaces.Alive;

/**
 * This class is the base class for all characters, including players and npcs, that will be
 * rendered in the game. It records the life information.
 *
 * @author cady
 * @version 1.1
 * @since 1.1
 */

class CharacterBase implements Alive {

  protected int hp;
  protected int id;
  protected CharacterType characterType;

  /**
   * Creates a new instance of {@link CharacterBase}
   *
   * @param id            the id of this CharacterBase
   * @param initialHP     the initial
   * @param characterType the {@link CharacterType} of this instance
   */
  public CharacterBase(int id, int initialHP, CharacterType characterType) {
    this.id = id;
    this.hp = initialHP;
    this.characterType = characterType;
  }

  /**
   * Gets the id of this object
   *
   * @return the id of this object
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * Sets the id of this object
   *
   * @param id the id of this object
   */
  @Override
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the HP of this object
   *
   * @return the HP of this object
   */
  @Override
  public int getHP() {
    return hp;
  }

  /**
   * Sets the HP of this object
   *
   * @param hp the HP to set
   */
  @Override
  public void setHP(int hp) {
    this.hp = hp;
  }

  /**
   * Adds HP by a certain amount
   *
   * @param deltaHP the HP to add
   */
  @Override
  public void addHP(int deltaHP) {
    hp += deltaHP;
  }

  /**
   * Subtracts HP by a certain amount
   *
   * @param deltaHP the HP to subtract
   */
  @Override
  public void subtractHP(int deltaHP) {
    hp -= deltaHP;
  }

  /**
   * Gets if this object is still alive
   *
   * @return if the HP is larger than 0
   */
  @Override
  public boolean isAlive() {
    return hp > 0;
  }

  /**
   * Sets the type of this character
   *
   * @param type the {@link CharacterType} of this character
   */
  @Override
  public void setType(CharacterType type) {
    this.characterType = type;
  }

  /**
   * Gets the type of this character
   *
   * @return the {@link CharacterType} of this character
   */
  @Override
  public CharacterType getType() {
    return characterType;
  }
}
