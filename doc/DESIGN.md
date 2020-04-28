#### Revisit the design from the original plan and compare it to your current version (as usual, focus on the behavior and communication between modules, not implementation details):

- Difference: 
1. In original plan, we design a project that supports different kinds of  Scrolling Platformer games, like Zelda and Super Mario Brothers. Now, we only have Zelda characters and it's more of a NPC game. 
2. In dataMangagement, not all data will be loaded into GameMapGraph object anymore since there are too much data to store. All data are loaded into GameObjectConfiguration object instead.
3. In example games, we consider 3D example games as one of the example games.

- Similarity:
1. We still use the MVC project with an additional data module. The entire project structure has not been changed a lot. 
2. In dataManagement, the basic strcture of storer + loader + data processor is not changed 

#### revisit the design's goals: is it as flexible/open as you expected it to be and how have you closed the core parts of the code in a data driven way?  

Not completely. In data management, the strcture of storer + loader + data processor actually should have been splitted up to obtain further flexiblity. Otherwise as the storing requrests become more complex and in a greater amount of variety, single loader will be too large and vague in functionality. We close the core parts of the code by having the storer and loaders as the interfaces between core code and data. Core part of the code wouldn't be able to know how the data are stored or implemented since they receive data in desired data strcture by calling API in the loader and storer. 

Other than data management, model, controller and frontend have insisted to use interfaces, abstract classes and APIs to make the project as extensible as possible. 

#### Describe two APIs in detail (one from the first presentation and a new one):   

- DataLoaderAPI
```java
public interface DataLoaderAPI {
/**
    * load the param of current player
    * @param playerParam
    * @return
    * @throws DataLoadingException
    */
int loadCurrentPlayerPara(PlayerParam playerParam) throws DataLoadingException;

/**
    * Load the param of any specific player
    * @param playerParam the parameter of player
    * @param playerID id of player
    * @return the parameter of player
    */
int loadPlayerParam(PlayerParam playerParam, int playerID) throws DataLoadingException;

/**
    * load game's parameter using Gamepara enum
    * @param param the parameter of the game
    * @return the value of the parameter of the game
    */
int loadGameParam(GameParam param);

/**
    * load available directions that is available in current game
    * @return the list of Directions that are available
    */
List<Direction> loadAvailableDirection();

/**
    * ***this method has to be called before using any of the loader/storer.
    *
    * @param GameID the ID representing the type of the current Game
    * @param PlayersID the ID representing player
    */
void setGameAndPlayer(int GameID, List<Integer> PlayersID);

/**
    * get the type of the game people currently are working on
    * @return int indicating the type of the game
    */
int getGameType();

/**
    * load a specific cell
    * @param row the row the cell is at
    * @param col the column the cell is at
    * @param subMapID the ID indicating which submap the cell locates
    * @param level the level the cell is used in
    * @return the Cell at the specified locatioin
    */
Cell loadCell(int row, int col, int subMapID, int level);
/**
    * get the subMapID for the map in certain direction
    * @param direction direction of the next submap relative to the current submap
    * @param current the current sumap
    * @return the ID of the next submap at the specified direction
    */
@Deprecated
int getNextSubMapID(Direction direction, int current);
/**
    * get the whole gameMapGraph object from data
    * @param level
    * @param subMapID
    * @return
    * @throws DataLoadingException
    */
GameMapGraph loadMap(int level, int subMapID) throws DataLoadingException;
/**
    * load buffered Image by providing the image category and ID
    * @param ImageID
    * @param category
    * @return
    * @throws DataLoadingException
    */
BufferedImage loadBufferImage(int ImageID, ImageCategory category) throws DataLoadingException;

/**
    * load text files from the database. Keyword specifies one piece of data out of a category. Category can be Dialog content
    */
String loadText(String keyword, String category) throws DataLoadingException;
/**
    * load charcter's property using ID
    * @param ID
    * @param property
    * @return
    * @throws DataLoadingException
    */
@Deprecated
int loadCharacter(int ID, CharacterProperty property) throws DataLoadingException;
/**
    * load weapon's property using ID
    * @param ID
    * @param property
    * @return
    * @throws DataLoadingException
    */
@Deprecated
int loadWeapon(int ID, int property) throws DataLoadingException;
/**
    * load current level
    * @return
    */
int currentLevel();
/**
    * keycode are stored in the player files.
    *
    * @param playerID
    * @return
    */
Map<Integer, String> loadKeyCode(int playerID) throws DataLoadingException;
/**
    * in Json, <int, String> always returns <Stirng, String>
    *
    * @param imageID
    * @param category
    * @return
    */
String loadImagePath(int imageID, ImageCategory category) throws DataLoadingException;

/**
    * get the list of current available zelda characters
    * @return
    */
List<ZeldaCharacter> getZeldaCharacters();
/**
    * get the list of current available players
    * @return
    */
List<PlayerStatus> getCurrentPlayers();
/**
    * get certain type of animation
    * @param animationType
    * @return
    */
Map<String, Animation2D> loadAnimation(AnimationType animationType);
```
- Movable1D
```java
package ooga.model.interfaces.movables;

import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.MovingState;

/**
 * This interface can be used by objects that move on a 1D line.
 *
 * @author cady
 */
public interface  Movable1D {

  /**
   * Sets the x position of this object
   *
   * @param x the x position of this object
   */
  void setX(double x);

  /**
   * Moves this object on the line for a certain distance. Note that this command uses the Cartesian
   * coordinate system. For example, move right for 5 should call moveInX(5)
   *
   * @param deltaX the distance in which the object moves in the x direction
   */
  void moveInX(double deltaX);

  /**
   * Gets the x position of this object
   *
   * @return the x position of this object
   */
  double getX();

  /**
   * Sets the speed this object moves in the x direction
   *
   * @param xSpeed the speed this object moves in the x direction
   */
  void setXSpeed(double xSpeed);

  /**
   * Gets the speed this object moves in the x direction
   *
   * @return the speed this object moves in the x direction
   */
  double getXSpeed();

  /**
   * Gets if the frontend is busy animating the last movement. If yes, backend states will not be
   * changed
   *
   * @return if frontend is animating
   */
  boolean isBusy();

  /**
   * Sets the current moving state
   * @param movingState the current moving state
   */
  void setState(MovingState movingState);

  /**
   * Gets the current moving state
   * @return  the current moving state
   */
  MovingState getState();

  /**
   * Sets the direction of moving
   * @param direction of moving
   */
  void setDirection(Direction direction);

  /**
   * Gets the direction of moving
   * @return the direction of moving
   */
  Direction getDirection();
}

```

##### How it supports new features:
- DataLoader API:   
    The data loading required by most of the new features can be provided by a linear combination of the currently available data loader methods. In this case, loader doesn't need to change. In addition, methods like loadGameParam allows data management to support loading more different game prarmeters such as loudness, special effect level by growing the GameParam enum list.
- Movable1D  
Movable1D is very extensible. It can support 1D movement by itself, and 2D movement if combined with some other methods. Interfaces can also extend from it. Movable2D is a great example.


##### how does it support users (your team mates) to write readable, well design code?

- DataLoader API: 
    The methods are well-named and those methods provides an interface to free the teammates fron knowing the exact data implementation behind. Most of the data error handling are done internally as well.
- Movable1D  
Movable1D allows other part of the program to get information of a character/player in the game easily by calling its public methods. All players/character can implement it even though they might extends from different implementations. 

how has it changed during the Sprints (if at all)?  

- DataLoader API:    
Yes. Some methods are no longer needed (marked with @depricate) and new methods such as getZeldaCharacterList are added to help teammates obtain data in their desired strcture.  
- Movable1D:   
    No. This is a very solid implementation from eh very beginning.  

show two Use Cases implemented in Java code in detail that show off how to use each of the APIs described above

- DataLoader API:  

```java
    private void initialize() {
    for (int i = 0; i < loader.loadGameParam(GameParam.GRID_NUM); i++) {
      GridInMap grid = new GameGridInMap(loader, i);
      grid.loadGrid(grid.getID(), level);
      allGrids.put(i, grid);
    }
  }
```

- Movable1D 
```java
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

```
#### Describe one element of the design that has changed based on your deeper understanding of the project: how were those changes discussed and what trade-offs ultimately led to the changes

Data loading changes from distributive loading to centralized loading: 
Design Decision centralized loading and storing:

Before code refactoring, I found I had to access the disk everytime when a load or store function is called on the loader/storer. However, this is not feasible due to the slow speed of disk data loading and storing. In addition, the data storing will be a big problem since everytime the data is destroyed whenever a new loader or storer is created. Thus, I chose to make all the data being loaded and stored at the same time and all data are stored under one big object. All data changes are synchronized in that object and all data won't be written into the disk unless the a function is called. This centralized system has its own disadvantage as well ---- this object has to be implemented using singleton method, which limits the possibility of multiple games happening in parallel.
