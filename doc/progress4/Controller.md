###Use Case Example for using the Menu View Interface

```

Stage myStage = new Stage();

MenuView view = new GameMenuView(); //GameMenuView is a specific class that implements the view interface
myStage.setScene(view.getScene()); //sets the scene of the stage using the getScene method
myStage.show(); //shows the menu


//other methods:
view.setLanguage("Chinese"); //sets the language to chinese, which uses property files to replace original texts in the buttons/label/etc
view.switchMode(false); //switches the view to "light mode" (true = dark mode)
view.changColor(Color.BLUE); //switches the background color to blue

```

This interface is used for ALL menus in the game, and thus all menus are capable of switching mode, changing 
color, and providing a scene for the stage to show.