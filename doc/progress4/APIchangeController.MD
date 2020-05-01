##API Change In Controller and Menu

* some interfaces are removed since they are not used any more, which some new, non trivial interfaces are created


###Player Controller Interface

[***Removed***]  void keyInput(KeyCode key)  
[***Removed***]  void setKeyCodeMap(Map<KeyCode, String> map);

void setMyPlayer(Movable1D player);
  
  
[***NEW***]  void setID();  
    * sets the id for the controller by player id  
[***NEW***]  int getID();  
    * returns the id of controller and player  
[***NEW***]  void keyReleased();  
    * method for when key is released  
[***NEW***]  void updateKey();  
    * Replaces keyInput since we're using different method of checking key input by user  
[***NEW***]   void setView(GameZelda2DSingle view);  
[***NEW***]  void setNewKeyMap(Map<Integer, String> map);  
    * set new key map for this player  
[***NEW***]   Movable1D getPlayer();  
    * returns the player  
[***NEW***]   boolean checkScore(int score);  
    * check if player score exceeds goal  
[***NEW***]   boolean update();  
[***NEW***]   boolean hasWon();  
[***NEW***]   void getHurt();  
[***NEW***]   boolean isHurt();  



###JumpableControl [***UNUSED***]
  void jump();

###MovableControll1D 
  void left();
  void right();
  
###MovableControll2D
  void up();
  void down();
  
###AttackerControl [***NEW***]
  void attack1();
  void attack2();
  void attack0();

###NPC Controller [***NEW***]  
  void setMyNPC(Movable1D NPC);  
  void setID();  
  void setView(GameZelda2DSingle view);  
  int getID();  
  void attack();  
  void getHurt();  
  boolean isHurt();  
 
 
 
 ###GameMenu [***NEW***]  
 Scene getMenuView();  
 void switchMode(boolean dark);  
 void setLanguage(String language);  
 void changColor(Color color);  