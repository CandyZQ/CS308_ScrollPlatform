package ooga.model.enums.backend;

import ooga.data.GameInfo;

public enum GameParam {
  NPC_NUM{
    @Override
    public int getValue(GameInfo gameInfo) {
      return gameInfo.getNPC_ID().size();
    }
  },
  PLAYER_NUM {
    @Override
    public int getValue(GameInfo gameInfo) {
      return gameInfo.getPlayer_ID().size();
    }
  },
  LEVEL_NUM {
    @Override
    public int getValue(GameInfo gameInfo) {
      return gameInfo.getLevelNum();
    }
  },
  GRID_NUM {
    @Override
    public int getValue(GameInfo gameInfo) {
      return gameInfo.getSubMapInfo().get(gameInfo.getLevelNum()).size();
    }
  },
  GAME_TYPE {
    @Override
    public int getValue(GameInfo gameInfo) {
      return gameInfo.getGameType();
    }
  },
  INIT_POS_X {
    @Override
    public int getValue(GameInfo gameInfo) {
      return gameInfo.getInitialPosition()[0];
    }
  },
  INIT_POS_Y {
    @Override
    public int getValue(GameInfo gameInfo) {
      return gameInfo.getInitialPosition()[1];
    }
  };
  public abstract int getValue(GameInfo gameInfo);
}
