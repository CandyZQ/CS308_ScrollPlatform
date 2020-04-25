package ooga.data;

import static ooga.model.map.GameGridInMap.ID_NOT_DEFINED;

import java.util.HashMap;
import java.util.Map;
import ooga.model.enums.backend.PlayerParam;

public class PlayerStatus {
    public static int initLevel = 1;
    public static int initLife = 5;

    private int playerID;
    private Map<PlayerParam, Integer> playerParaMap;
    private Map<Integer, String> keyCodeMap;



    public PlayerStatus(int playerID) {
        this.playerID = playerID;
        playerParaMap = new HashMap<>();
        for (PlayerParam i : PlayerParam.values()) {
            playerParaMap.put(i, ID_NOT_DEFINED);
        }
        setPlayerParam(PlayerParam.CURRENT_LEVEL, initLevel);
        keyCodeMap = new HashMap<>();
    }

    public PlayerStatus(int gameID, int playerID) {
        this(playerID);
        setPlayerParam(PlayerParam.Game, gameID);
    }

    public int getPlayerParam(PlayerParam playerParam) {
        return playerParaMap.get(playerParam);
    }
    public void setPlayerParam(PlayerParam playerParam, int paramValue) {
        playerParaMap.replace(playerParam, paramValue);
    }


    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }


    public void setKeyCodeMap(Map<Integer, String> keyCodeMap) {
        this.keyCodeMap = keyCodeMap;
    }

    public Map<Integer, String> getKeyCodeMap() {
        return keyCodeMap;
    }

    public Map<PlayerParam, Integer> getPlayerParaMap() {
        return playerParaMap;
    }

    public void setPlayerParaMap(Map<PlayerParam, Integer> playerParaMap) {
        this.playerParaMap = playerParaMap;
    }
}
