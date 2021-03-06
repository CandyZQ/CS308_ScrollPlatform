package ooga.model.interfaces;

/**
 * This interface helps players keep track of scores.
 *
 * @author cady
 * @see Character
 */
public interface Scorable {

  boolean isScoring();

  double getScore();

  void setScore(double score);

  void addScore(double deltaScore);

}
