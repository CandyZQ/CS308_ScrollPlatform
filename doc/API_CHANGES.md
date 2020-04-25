# ooga
Depricated methods in DataManagement:  
 void storeWeapons(int ID, WeaponBase weapon);  
 void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level)  
 int getNextSubMapID(Direction direction, int current);  
 int loadCharacter(int ID, CharacterProperty property)  
 int loadWeapon(int ID, int property)  

 All these methods are depricated because they are no longer needed after conversation with teammates or they are there are better replacement for it. 

 void storeWeapons(int ID, WeaponBase weapon);  
 int loadWeapon(int ID, int property)  
 are gone because weapon is no longer a thing. 

 void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level)  
 is gone because we can actually assign an ID with better percision and people using it actually can provide specific information about submapID through the access of gameInfo object. 


 int getNextSubMapID(Direction direction, int current);     
 is gone because we can't support exploring next submap by now yet.


 int loadCharacter(int ID, CharacterProperty property)
 is gone because backend found it's more useful to load character object directly rather than loading single property.

 



