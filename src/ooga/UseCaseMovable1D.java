package ooga;

import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.backend.CharacterType;
import ooga.model.enums.backend.MovingState;
import ooga.model.interfaces.movables.Movable1D;

public class UseCaseMovable1D {

  public static void main(String[] args) {
    Movable1D player = new ZeldaPlayer(0,0,0,0, CharacterType.ENGINEERBOT);

    System.out.println("Initial X pos: " + player.getX());
    player.setX(0.5);
    System.out.println("X pos changed to: " + player.getX());
    player.moveInX(0.5);
    System.out.println("X pos changed to: " + player.getX());

    System.out.printf("Initial state: %s\n", player.getState());
    player.setState(MovingState.WALK);
    System.out.printf("State changed to: %s\n", player.getState());
  }

}
