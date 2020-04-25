package ooga.model.test;

import ooga.data.*;
import ooga.game.GameType;
import ooga.model.Model;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.backend.GameParam;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelDataLoadingTest {
  DataLoaderAPI loader;
  DataStorerAPI storer;
  Model model;

  public ModelDataLoadingTest() throws DataLoadingException {
    loader = new DataLoader();
    storer = new DataStorer();
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(1));
//    storer.setPlayerParam(PlayerParam.CURRENT_LEVEL, 1, 1);

    System.out.println("--------------Basic game info in the test---------------");
    System.out.println("Game Type: " + GameType.byIndex(loader.getGameType()));
    System.out.println("NPC Number: " + loader.loadGameParam(GameParam.NPC_NUM));
    System.out.println("Player Number: " + loader.loadGameParam(GameParam.PLAYER_NUM));
  }

  @Test
  public void playersLoading() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0, 2));
    model = new Model(loader);

    Map<Integer, ZeldaPlayer> players = (HashMap<Integer, ZeldaPlayer>) model.getPlayers();
    System.out.println("--------------Loading Players---------------");
    for (ZeldaPlayer p: players.values()) {
      System.out.println("Player id: " + p.getId());
    }
    assertEquals(2, players.size());
    assertEquals(0, players.get(0).getId());
    assertEquals(2, players.get(2).getId());
  }

  @Test
  public void playerScoring() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0));
    model = new Model(loader);

    ZeldaPlayer player = ((HashMap<Integer, ZeldaPlayer>) model.getPlayers()).get(0);
    assertEquals(0, player.getScore());
    player.addScore(50);
    assertEquals(50, player.getScore());
  }

  @Test
  public void levelGoalScore() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0, 2));
    model = new Model(loader);

    Map<Integer, ZeldaPlayer> players = (HashMap<Integer, ZeldaPlayer>) model.getPlayers();
    assertEquals(100, players.get(0).getGoalScore());
    assertEquals(666, players.get(2).getGoalScore());
  }

  @Test
  public void npcLoading() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0));
    model = new Model(loader);

    Map<Integer, ZeldaCharacter> npcs = (HashMap<Integer, ZeldaCharacter>)model.getNPCs();
    assertEquals(3, npcs.size());
    assertEquals(200, npcs.get(10).getX());
    assertEquals(100, npcs.get(10).getY());
    assertEquals(0, npcs.get(11).getX());
    assertEquals(0, npcs.get(11).getY());
    assertEquals(300, npcs.get(14).getX());
    assertEquals(-250, npcs.get(14).getY());
  }

  @Test
  public void keyCodeLoading() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0));
    model = new Model(loader);
    Map<Integer, String> keyMap = loader.loadKeyCode(0);

//    for (Entry<Integer, String> entry: keyMap.entrySet()) {
//      System.out.printf("Key: %d, Value: %s\n", entry.getKey(), entry.getValue());
//    }

    assertEquals(8, keyMap.size());
    assertEquals("left", keyMap.get(263));
  }

  @Test
  public void lifeLoading() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0));
    model = new Model(loader);
    Map<Integer, ZeldaPlayer> players = (HashMap<Integer, ZeldaPlayer>) model.getPlayers();

    assertEquals(5, players.get(0).getHP());
  }

  @Test
  public void npcLifeLoading() throws DataLoadingException {
    loader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(0));
    model = new Model(loader);
    Map<Integer, ZeldaCharacter> characters = (HashMap<Integer, ZeldaCharacter>) model.getNPCs();

    assertEquals(1, characters.get(10).getHP());
  }
 }
