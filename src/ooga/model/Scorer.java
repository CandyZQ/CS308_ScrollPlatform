package ooga.model;

import ooga.model.interfaces.Scorable;

public class Scorer implements Scorable {

  protected double score;
  protected double goalScore;
  private boolean isScoring;

  /**
   * Creates a new instance of a scorer
   *
   * @param goalScore the goal score
   */
  public Scorer(double goalScore) {
    this(0, goalScore);
  }

  /**
   * Creates a new instance of a scorer
   *
   * @param score     the current score
   * @param goalScore the goal score
   */
  public Scorer(double score, double goalScore) {
    this(score, goalScore, true);
  }

  /**
   * Creates a new instance of a scorer
   *
   * @param score     the current score
   * @param goalScore the goal score
   * @param isScoring if it is scoring
   */
  public Scorer(double score, double goalScore, boolean isScoring) {
    this.score = score;
    this.goalScore = goalScore;
    this.isScoring = isScoring;
  }

  /**
   * Gets the goal score
   *
   * @return the goal score of this player
   */
  @Override
  public double getGoalScore() {
    return goalScore;
  }

  /**
   * Sets the goal score
   *
   * @param goalScore the goal score of this player
   */
  @Override
  public void setGoalScore(double goalScore) {
    this.goalScore = goalScore;
  }

  /**
   * Returns whether it is scoring
   *
   * @return true if is scoring
   */
  @Override
  public boolean isScoring() {
    return isScoring;
  }

  /**
   * Gets the current score
   *
   * @return the current score of this player
   */
  @Override
  public double getScore() {
    return score;
  }

  /**
   * Sets the current score
   *
   * @param score the score of the current player
   */
  @Override
  public void setScore(double score) {
    this.score = score;
  }

  /**
   * Adds {@code deltaScore} to the current score
   *
   * @param deltaScore the score to be added
   */
  @Override
  public void addScore(double deltaScore) {
    score += deltaScore;
  }

  /**
   * Gets if the player has won
   *
   * @return true if the player has won
   */
  @Override
  public boolean hasWon() {
    return goalScore <= score;
  }

}
