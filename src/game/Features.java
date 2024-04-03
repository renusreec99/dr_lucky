package game;

/**
 * Features is an interface that acts like a Facade between the controller and
 * the view.
 * 
 * On mouse click , keypress or any action event the controller is called from
 * the view through this interface.
 * 
 * This enables the controller to listen to the view.
 */
public interface Features {
  /**
   * Moves a player in the graph and the based on mouse click. gets the exact
   * mouse click coordinates and gets the space from to the coordinates , verifies
   * if it is a neighbor and then moves the player
   * 
   * @param x the x coordinate of the mouse click.
   * @param y the y coordinate of the mouse click
   */
  void movePlayer(int x, int y);

  /**
   * creates the new game on click of the create game menu item. once the item is
   * clicked the user is shown a prompt to choose a file. It then reads the file
   * and loads the new game and draws the new graph according to the new file.
   *
   * 
   */
  void loadNewWorld();

  /**
   * This allows a player to a move a pet into the specified room. 
   * 
   * It allows the player to select the room to which he wants to move 
   * the pet to
   */
  void movePet();

  /**
   * This allows the player to create a human or a computer controlled player from
   * the view.
   * 
   * this is prompted when the user presses the key 'm'
   * 
   * Once the add button is clicked this method is called.
   */
  void addPlayer();

  /**
   * Allows the player to pick an item among the list of items present in the same
   * room as of the player.
   *
   * this is prompted when the user presses the key 'p'
   */
  void pickItem();

  /**
   * Allows the player to look around so that the player knows the information
   * about his present location and the neighboring spaces information.
   *
   * this is prompted when the user presses the key 'l'
   */
  void lookAround();

  /**
   * Allows the player to attack the target. attempt successful only if the target
   * and the player are in the same room and are not seen.
   *
   * this is prompted when the user presses the key 'a'
   */
  void attack();

  /**
   * exits the game and terminates. this occurs when the menu item exit is
   * selected or when the max number of turns are reached.
   *
   *
   */
  void exitProgram();

  /**
   * loads the new game. Shows the game play screen from the first welcome screen.
   * 
   * it loads the game for the existing file that is passed from the arguments.S
   */
  void loadWorld();

  /**
   * the current player details susch as the player name, the items in the player
   * inventory and the space it is present in is displayed on screen.
   * 
   * 
   * This occurs when the user clicks on the player on the graph and also to hint
   * the user on whose current turn it is displayed in the text field on screen.
   */
  void getPlayerDetails();

}
