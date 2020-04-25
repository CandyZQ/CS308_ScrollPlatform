package ooga.model.enums;

public enum  CharacterProperty {
  X_POS(0),
  Y_POS(1),
  Z_POS(2),
  X_SPEED(3),
  Y_SPEED(5),
  Z_SPEED(6),
  WEAPON(7),
  ATTACK(8),
  HP(9),
  SCORE(10),
  ;

  private final int index;
  CharacterProperty(int index) {
    this.index = index;
  }

  public int getIndex() {
    return this.index;
  }
}
