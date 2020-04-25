package ooga.model.characters;

import ooga.model.Scorer;
import ooga.model.enums.backend.CharacterType;
import ooga.model.interfaces.Scorable;

/**
 * This class implements a zelda player that controls by a human player.
 *
 * @author cady
 */
public class ZeldaPlayer extends ZeldaCharacter implements Scorable {

  Scorer scorer;

  /**
   * Creates a new instance of ZeldaPlayer
   *
   * @param initialHp    the initial
   * @param id           the id of this CharacterBase
   * @param type         the {@link CharacterType} of this instance
   * @param currentScore the player's current score
   * @param goal         the goal score of this player
   */
  public ZeldaPlayer(int initialHp, int id, double currentScore, double goal, CharacterType type) {
    super(initialHp, id, type);
    scorer = new Scorer(currentScore, goal);
  }

  /**
   * Creates a new instance of ZeldaPlayer
   *
   * @param initialHp    the initial
   * @param id           the id of this CharacterBase
   * @param type         the {@link CharacterType} of this instance
   * @param currentScore the player's current score
   * @param goal         the goal score of this player
   * @param weapon       the weapon that the character will use
   */
  public ZeldaPlayer(int initialHp, int weapon, int id, double currentScore, double goal,
      CharacterType type) {
    super(initialHp, weapon, id, type);
    scorer = new Scorer(currentScore, goal);
  }

  /**
   * Creates a new instance of ZeldaPlayer
   *
   * @param initialHp    the initial
   * @param id           the id of this CharacterBase
   * @param type         the {@link CharacterType} of this instance
   * @param currentScore the player's current score
   * @param goal         the goal score of this player
   * @param weapon       the weapon that the character will use
   * @param x            the initial x position
   * @param y            the initial y position
   */
  public ZeldaPlayer(int initialHp, int weapon, int attack, int id, int x, int y,
      double currentScore, double goal, CharacterType type) {
    super(initialHp, weapon, attack, id, x, y, type);
    scorer = new Scorer(currentScore, goal);
  }

  /**
   * Returns whether it is scoring
   *
   * @return true if is scoring
   */
  @Override
  public boolean isScoring() {
    return scorer.isScoring();
  }

  /**
   * Gets the current score
   *
   * @return the current score of this player
   */
  @Override
  public double getScore() {
    return scorer.getScore();
  }

  /**
   * Gets the goal score
   *
   * @return the goal score of this player
   */
  @Override
  public double getGoalScore() {
    return scorer.getGoalScore();
  }

  /**
   * Sets the goal score
   *
   * @param goalScore the goal score of this player
   */
  @Override
  public void setGoalScore(double goalScore) {
    scorer.setScore(goalScore);
  }

  /**
   * Sets the current score
   *
   * @param score the score of the current player
   */
  @Override
  public void setScore(double score) {
    scorer.setScore(score);
  }

  /**
   * Adds {@code deltaScore} to the current score
   *
   * @param deltaScore the score to be added
   */
  @Override
  public void addScore(double deltaScore) {
    scorer.addScore(deltaScore);
  }

  /**
   * Gets if the player has won
   *
   * @return true if the player has won
   */
  @Override
  public boolean hasWon() {
    return scorer.hasWon();
  }
}
