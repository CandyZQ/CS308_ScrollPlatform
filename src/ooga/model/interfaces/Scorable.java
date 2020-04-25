package ooga.model.interfaces;

/**
 * This interface helps players keep track of scores.
 *
 * @author cady
 * @see Character
 */
public interface Scorable {

  /**
   * Returns whether it is scoring
   *
   * @return true if is scoring
   */
  boolean isScoring();

  /**
   * Gets the current score
   *
   * @return the current score of this player
   */
  double getScore();

  /**
   * Gets the goal score
   *
   * @return the goal score of this player
   */
  double getGoalScore();

  /**
   * Sets the goal score
   *
   * @param goalScore the goal score of this player
   */
  void setGoalScore(double goalScore);

  /**
   * Sets the current score
   *
   * @param score the score of the current player
   */
  void setScore(double score);

  /**
   * Adds {@code deltaScore} to the current score
   *
   * @param deltaScore the score to be added
   */
  void addScore(double deltaScore);

  /**
   * Gets if the player has won
   *
   * @return true if the player has won
   */
  boolean hasWon();
}
