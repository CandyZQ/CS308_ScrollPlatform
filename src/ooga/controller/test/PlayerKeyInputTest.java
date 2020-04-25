//package ooga.controller.test;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.lang.reflect.InvocationTargetException;
//import javafx.scene.input.KeyCode;
//import ooga.controller.gamecontrol.player.ZeldaPlayerControl;
//import ooga.model.characters.ZeldaPlayer;
//import ooga.model.enums.backend.CharacterType;
//import ooga.model.enums.backend.Direction;
//import ooga.model.enums.backend.MovingState;
//import org.junit.jupiter.api.Test;
//
//
//public class PlayerKeyInputTest {
//    ZeldaPlayer player;
//    ZeldaPlayerControl control;
//
//    void setUp(){
//        player = new ZeldaPlayer(100,0, 0, 0, CharacterType.PLAYER);
//        control = new ZeldaPlayerControl();
//        control.setMyPlayer(player);
//    }
//
//    @Test
//    void testLeft () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        setUp();
//        assertEquals(MovingState.IDLE, player.getState());
//
//        //walk left
//        control.keyInput(KeyCode.LEFT);
//        assertEquals(MovingState.WALK, player.getState());
//        assertEquals(Direction.W, player.getDirection());
//    }
//
//    @Test
//    void testWalkAround () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        setUp();
//
//        //walk right
//        control.keyInput(KeyCode.RIGHT);
//        assertEquals(MovingState.WALK, player.getState());
//        assertEquals(Direction.E, player.getDirection());
//
//        //walk upwards
//        control.keyInput(KeyCode.UP);
//        assertEquals(MovingState.WALK, player.getState());
//        assertEquals(Direction.N, player.getDirection());
//    }
//
//    @Test
//    void testReleased () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        setUp();
//
//        //default
//        assertEquals(MovingState.IDLE, player.getState());
//
//        //walk upwards
//        control.keyInput(KeyCode.UP);
//        assertEquals(MovingState.WALK, player.getState());
//        assertEquals(Direction.N, player.getDirection());
//
//        //key released (stop)
//        control.keyReleased();
//        assertEquals(MovingState.IDLE, player.getState());
//        assertEquals(Direction.N, player.getDirection());
//    }
//
//    @Test
//    void testDown () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        setUp();
//
//        //walk downwards
//        control.keyInput(KeyCode.DOWN);
//        assertEquals(MovingState.WALK, player.getState());
//        assertEquals(Direction.S, player.getDirection());
//    }
//
//    @Test
//    void testAttack () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        setUp();
//
//        control.keyInput(KeyCode.Q);
//        assertEquals(MovingState.ATTACK1, player.getState());
//        assertEquals(0, player.getAttack());
//
//        //different attack
//        control.keyInput(KeyCode.W);
//        assertEquals(1, player.getAttack());
//    }
//
//    @Test
//    void testNonExistingInput () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        setUp();
//
//        MovingState state = player.getState();
//        Direction direction = player.getDirection();
//
//        //nothing changes since keycode is not part of the map
//        control.keyInput(KeyCode.K);
//        assertEquals(state, player.getState());
//        assertEquals(direction, player.getDirection());
//
//        //key released (stop)
//        control.keyReleased();
//        assertEquals(MovingState.IDLE, player.getState());
//        assertEquals(direction, player.getDirection());
//
//    }
//
//
//}
