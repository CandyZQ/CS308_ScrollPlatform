//package ooga.controller.gamecontrol.player;
//
//import ooga.controller.gamecontrol.playerInterface.AttackerControl;
//import ooga.controller.gamecontrol.playerInterface.JumpableControl;
//import ooga.controller.gamecontrol.playerInterface.MovableControll1D;
//import ooga.controller.gamecontrol.PlayerControlInterface;
//import javafx.scene.input.KeyCode;
//import ooga.game.GameZelda2DSingle;
//import ooga.model.characters.MarioPlayer;
//import ooga.model.enums.backend.Direction;
//import ooga.model.enums.backend.MovingState;
//import ooga.model.interfaces.movables.Movable1D;
//import ooga.view.game_view.game_state.state2d.GameState2DView;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.Map;
//
//public class MarioPlayerControl implements PlayerControlInterface, MovableControll1D, JumpableControl, AttackerControl {
//
//  private MarioPlayer myPlayer;
//  private Map<KeyCode, String> myKeyCodeMap;
//  private int myID;
//  private GameState2DView myView;
//
//  public MarioPlayerControl(){
//
//  }
//
//  @Override
//  public void setMyPlayer(Movable1D myPlayer) {
//    this.myPlayer = (MarioPlayer)myPlayer;
//  }
//
//  @Override
//  public void setKeyCodeMap(Map<KeyCode, String> map) {
//    myKeyCodeMap = map;
//  }
//
//  @Override
//  public void setID() {
//
//  }
//
//  @Override
//  public int getID() {
//    return 0;
//  }
//
//  @Override
//  public void keyReleased() {
//    myPlayer.setState(MovingState.IDLE);
//  }
//
//
//  @Override
//  public void keyInput(KeyCode key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//    if(myKeyCodeMap.get(key)==null) return;
//    this.getClass().getDeclaredMethod(myKeyCodeMap.get(key)).invoke(this);
//  }
//
//
//  @Override
//  public void jump() {
//    myPlayer.setState(MovingState.JUMP);
//    myPlayer.setDirection(Direction.UP);
//  }
//
//  @Override
//  public void left() {
//    myPlayer.setState(MovingState.WALK);
//    myPlayer.setDirection(Direction.W);
//  }
//
//  @Override
//  public void right() {
//    myPlayer.setState(MovingState.WALK);
//    myPlayer.setDirection(Direction.E);
//  }
//
//
//  @Override
//  public void attack0() {
//    myPlayer.setState(MovingState.ATTACK1);
//    //myPlayer.setAttack(0);
//  }
//
//  @Override
//  public void attack1() {
//    myPlayer.setState(MovingState.ATTACK1);
//    //myPlayer.setAttack(1);
//
//  }
//
//  @Override
//  public void attack2() {
//    myPlayer.setState(MovingState.ATTACK1);
//    //myPlayer.setAttack(2);
//
//  }
//  @Override
//  public void updateKey(){
//   }
//
//  @Override
//  public void setView(GameZelda2DSingle view) {
//    myView = view;
//  }
//
//  @Override
//  public void setNewKeyMap(Map<Integer, String> map) {
//
//  }
//
//  @Override
//  public Movable1D getPlayer() {
//    return myPlayer;
//  }
//
//  @Override
//  public boolean checkScore(int score) {
//    return false;
//  }
//}
