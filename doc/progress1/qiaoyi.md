## Implementation Plan
#### Genre
We are implementing Scrolling Platformer games with a focus on classical Zelda games. We allows player free to move in four directions and make interaction with non-playable characters (e.g. engaging in a dialogue, making an attacking and etc). Following the tradition of Zelda and also to more efficiently utilize memory, the whole game map will be broken down up several minimaps which collectively makes up the whole map.

#### Work on part of the project
I mainly work on the visualization of game state (map, agents, objects, and animation). If time permits, I will also implement the game menu.

#### Extensions
- 3D
- frontend for dialogue feature
- frontend for inventory feature
- frontend for user preference

#### Timeline
- Sprint 1: develop the main game engine, develop map and agents
- Sprint 2: develop interactive object, animations and game menu
- Complete: develop inventory, NPC dialogues, and visual effects

## Design Plan 
#### Design and Architecture Goals
My design plan is to aim for the high extensibility in the front-end APIs. As we intend to extend the game to 3D if time permits, the required architecture in front-end would be challenging. 

I hope to begin the coding with the implementation of an engine that supports both 2D and 3D by utilizing the Javafx and lwjgl packages. The engine would take on the task of rendering mesh objects and providing camera views. The engine should follows open/closed principle.

After that, I would implement the game view, which would be the main component of the game visualization. As game view needs to support a variety of game types, its architecture would be separated into several common components in a game: map, agents, objects, animation, and each modualized to maximize its extensibility. The specific implementation of each part will build upon the engine previously developed.

#### Project Overview 
This project uses the MVC model with an additional data management module. View is responsible for rendering UI components. It talks to Model with Java Listener and uses heads up display to update UI in real-time. Controller handles user inputs and updates Model information accordingly. Model stores information about game elements. It also notifies View whenever a piece of information is changed. Data management is responsible for storing information about games and loads/saves them. This information includes that of player(s), NPCs, game elements, map, etc. It communicates with View to load UI components at the beginning of the game, Controller to load a keyMap, and Model to load/save games information at appropriate times.

#### Two APIs in detail 
1. *public interface GameStateView*: used by the main controller to visualize the game state (loaded from back-end) at each frame
It assembles other visualizing components (e.g. agents, objects, maps) and, to follow the encapsulation principle, generate a unified scene object that could be directly added to the main game animation loop. 
+ void update(): calls each component to update
+ MapView getMapView(): gets the generated map view (needs to call getView() to retrieve the actual visualization components)
+ List<PlayableAgentView> getPlayableAgents(): gets the generated view for the playable agents
+ List<NonPlayableAgentView> getPlayableAgents(): gets the generated view for the non-playable agents
+ List<InteractiveObjectView> getInterObjects(): gets the generated view for the interactive objects
+ List<NonInteractiveObjectView> getNonInterObjects(): gets the generated view for the non-interactive objects
+ GamePanel getGamePanel(): gets the game panel

2. *public interface MapController*: controls the update of the map
The API serves as the controller in the MVC model for map in front-end. It implements the listerner binding to the map model in back-end and actively update the rendering of map after called by the main controller.
- MapView getMapView(): sets the view object
- void update(): updates the map (only when _fixed_ is false)


#### Two Use Cases in Detail 
- *Player presses right and the playable agent meets the right border, then the game switches to a new map*: The main game loop in the controller detects the KeyCode event of moving rights and then calls the update method in the model. The model sets the state of the playable character to moving right. Then the listener in the front-end notices the change in the state, the view initializes the animation of moving. After noticing a border collision, it halts the animation and calls the model. Since there is a new map following the current one, the controller will update the map. Rightly after, the map listener in the view will notice and update the map and also reallocating the playable agent to its new location.

- *Player engages in a dialogue with an NPC*: After the player comes close to an NPC and presses T to talk, the controller would detect the beginning of a conversation. It would call the backend to set the dialogue status of the NPC to true, the dialogue panel in the front-end would detect it and display the dialogue. If the conversation is longer than one page, the user could press any key to continue. The controller will make checking to the model to confirm it is during a conversation period and then, if true, calls the dialogue in the front-end to scroll to a new page of conversation. When the dialogue panel finds out the conversation text is out, it would notice the backend to set the conversation status of the NPC to false. In the next round of an update, the dialogue panel would disappear.

#### An Alternative Design 
An alternative design would be to have one controller updating each visualizing component. The benefit would that the updating phase for agent could more easily communicate with others' (e.g. to detect collision). The tradeoff would be that it would make the controller class huge and hard to debug. So I planned to have separate controllers for each part of the visualizing components to finish the tasks that could be achieved alone (e.g. construction or update) and then leave the tasks that require communication to the central controller _GameViewController_ in order to achieve the balance between functionality and design.