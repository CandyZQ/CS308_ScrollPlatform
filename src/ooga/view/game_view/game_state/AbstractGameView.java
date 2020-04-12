package ooga.view.game_view.game_state;

import java.util.List;

import javafx.scene.Node;
import ooga.view.game_view.agent.nonplayable.interfaces.NonPlayableAgentView;
import ooga.view.game_view.agent.playable.interfaces.PlayableAgentView;
import ooga.view.game_view.map.interfaces.MapView;
import ooga.view.game_view.object.interactive.interfaces.InteractiveObjectView;
import ooga.view.game_view.object.non_interactive.interfaces.NonInteractiveObjectView;

public class AbstractGameView implements GameStateView {

  @Override
  public Node getGameStateView() {
    return null;
  }

  @Override
  public void update() {

  }

  @Override
  public MapView getMapView() {
    return null;
  }

  @Override
  public List<PlayableAgentView> getPlayableAgents() {
    return null;
  }

  @Override
  public List<NonPlayableAgentView> getNonPlayableAgents() {
    return null;
  }

  @Override
  public List<InteractiveObjectView> getInterObjects() {
    return null;
  }

  @Override
  public List<NonInteractiveObjectView> getNonInterObjects() {
    return null;
  }
}
