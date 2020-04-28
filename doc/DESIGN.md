# Design
Wenyue Gu (wg74)  
QiaoYi Fang (qf30)  
Cady Zhou (zz160)  
Guangyu Feng (gf55) 

# Roles
Qiaoyi: mainly view on the rendering 2/3D game scenes

Cady:  write and implement the model; write the main class and controller methods that makes the game run; write data module to ensure the deliverance of data to model during initialization

Lucy: write and implement the controller; write menu UI to connect multiple games; implement menu related features such as user log in, background color change, etc

Guangyu: Handle data storage and management; Create example data files


# Design Goals
### Frontend:
The design goal is to cater the flexibility of extending 2D to 3D. For example, 2D animation requires the change on the image texture attached to the mesh object while 3D animation remains the same mesh and, however, render new mesh model at each frame. The main goal was to design such structure that could make these changes flexible to add.

In addition to 3D, the frontend uses OpenGL pipeline which contains many complications of technical nuances. Another design goal is to hide the pipeline implemenation from the rest of the modules in the project. 

### Controller & menu view:
The design goal is to create a bridge to connect front end, backend, and data management. A few additional features that have been implemented on the game menus are DarkMode, BackgroundColor selection, Language selection, and User Login/Profile. Background color selection changes the color of all menu in the current run of the game. Darkmode is similar to background color selection, but it only allows for two modes, light and dark, and also changes the color of button and texts accordingly. The language selection allows the user to choose a language in the drop down menu - currently, only Chinese and English are implemented, but since they use resrouce property files, it is easy to add a new language by just changing the data files. The user login and profile feature is incomplete but already has the skeleton. It allows user to log in to pre-existing accounts and/or create new accounts, and the window shows different messages when one fails to type in the correct password or try to recreate an existing user.

### Model
The design goal is to create a model that is able to support 2D and 3D games. Specifically, we need to be able to record and update player and characters' states and positional information. As an extension, information for weapons, scoring, lives, inventory, dialogue, etc. should also be stored in the model. The model should also notify whenever its state got changed. 


# High-level Design

Controller: The structure of the control is to have a large control that contains smaller individual controls for the game and each of the menu. This way, it is easier to implement an additional menu when required, thus making the program more flexible and open for extension.

Frontend: The main external APIs are GameState2DView and GameState3DView, both extending GameStateView class. Model and Controller could call this API to generate OpenGL window, update the animation state of agents, and render game scenes. The _engine_ package implements the infrastructure of OpenGL piplines from animating sprites to building basic mesh objects, from to fragment/vertex shaders. The _view_ package contains internal APIs that are specific to the game. For example, the AgentView class, with its children Agent2DView and Agent3DView classes, that animates agent and allows others to modify its animation states.

Model: The model should contain a great amount of APIs, whether they are needed to suuport current design or to future ones. Specifically, several interfaces including Movable1D, Movable2D, and Jumpable support all kinds of movement for players and characters, all interfaces underneath /gameMap supports map loading and storing, and other interfaces, including Alive, Attacker, Notifier, Scroable, etc, that are able to add additional features to this game. 

# Assumptions

Our data management is only able to load json files already existing in the folder. If the user want to create personalized game, he/she can only directly go into the original json files and change the data in the files (as compared to being able to select another file from the computer, etc).


# Adding New Features

To add a new menu, create a view class that extends the menu view, create a controller for the view class, and add the controller to Window or Game control to connect with other controllers as appropriated. Menus that we did not have time to create for the additional features include a menu for the user to change the keymap directly using UI. This should be possible to achieve by adding a new view with textfields that take in integers(keys) that maps to the actions.

To add a new language, change the menu.properties file so that it has the name of the third language, and add a new menu_[language name] property file to the resource folder.