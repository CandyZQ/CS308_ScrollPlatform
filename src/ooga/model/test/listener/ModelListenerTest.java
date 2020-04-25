package ooga.model.test.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.backend.CharacterType;
import ooga.model.enums.backend.MovingState;
import org.junit.jupiter.api.Test;

class  ModelListenerTest {
  ExampleController ec = new ExampleController();
  ZeldaCharacter zeldaCharacter = new ZeldaCharacter(0,0, CharacterType.PLAYER);

  @Test
  void listenCharacterStateChange() {
    ec.addModel(zeldaCharacter);
    zeldaCharacter.setState(MovingState.DEATH);
    assertEquals(zeldaCharacter.getState(), MovingState.DEATH);

    zeldaCharacter.setState(MovingState.ATTACK1);
    assertEquals(zeldaCharacter.getState(), MovingState.ATTACK1);
  }
}
