final
====

This project creates a program for user to play multiple related games.

#### Names:
Wenyue Gu (wg74)  
QiaoYi Fang (qf30)  
Cady Zhou (zz160)  
Guangyu Feng (gf55) 

### Timeline

Start Date: March 31, 2020
Finish Date: April 28, 2020 

Hours Spent: >= 100hr

### Primary Roles
#### Cady
- write model API and implement the model
- write the main class and controller methods that makes the game run
- write data module to ensure the deliverance of data to model during initialization

#### Lucy
- write controller API and implement the controller 
- write menu UI to connect multiple games
- implement menu related features such as user log in, background color change, etc

#### Guangyu 
- Handle data storage and management
- Create example data files

#### Qiaoyi
- Implemented front-end
- Worked with control and data management on ensuring the deliverance of front-end in the overall application


### Resources Used
- [Gson](https://github.com/google/gson)
- [OpenGL](https://www.opengl.org/documentation/)
- [JavaFX](https://openjfx.io/)
- [Orcale Java Documentation](https://docs.oracle.com/en/java/)

### Running the Program

**Main class:** src/ooga/game/GameMain.java

**Data files needed:** all json files in /data and properties files in resources

**Features implemented:**
- Dark Mode that allows the user to change the background to a dark color 
- Different background colors that can be selected from a dropdown panel 
- Saves the player's progress on a game on quit
- Load and store last saved game progress
- Artificial Players that automatically attack the player 
- Allows two players to play one game at the same time
- Allows user to log in and save scores


### Notes/Assumptions

- Assumptions or Simplifications:
    - for two player cooperation game, if one player dies the game 
    is over with player death (fail) screen
    - only loads last saved game. If a new game is played and saved it 
    over writes the information for the last game and user would not be 
    able to retrieve it

Interesting data files:
    - playeri.json files contains information on the type of game, id of player, keymap for player to move
    - user.properties saves the username and password combination of all signed up users
    - user_(username).properties save the user name, high score, and last score of a user

Known Bugs:
    - the open gl window refuses to run on a mac computer
    - If one opens a game, close it, opens another game multiple times, javafx freezes and stops responsing. 
    We suspect it is a problem with a new Game controller and thus a new timer created each time the user reopens 
    a game window, but was not able to fix the problem.
    - sprinting animation extremely slow / not fully implemented
    

- Extra credit:
    - A partially completed 3D game 

### Impressions

A very massive project where each person is responsible for implementing their 
own API; not a single individual is able to fully know the internal logic of everyone else's code. 
Large amount of communication is required to integrate.