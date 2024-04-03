package game;

/**
 * The interface for the World.
 * 
 * The world is where the entire DrLucky universe happens. There can be
 * different types of worlds.
 */
public interface World extends ViewModel {

  /**
   * Moves the the current character into the specified space.
   * 
   * The space that is chosen must be a neighbor of the current space else the
   * move is not made and the turn remains
   * 
   * @param spacename The {@link Space} to which you want to move into\
   * @return the string value which details the result of the move
   */
  public String movePlayer(String spacename);

  /**
   * This allows you to pick up an item present in the current space.
   * 
   * @param itemname The name of the item to be picked
   * @return The string displaying the outcome of the action
   * 
   */
  public String pickItem(String itemname);

  /**
   * This allows you create a human or a computer controlled player.
   * 
   * @param playername the name of the payer that is being created
   * @param location   the location at which the character is to be placed
   * @param size       of the inventory
   * @param bot        whether or not the character is a bot or not
   * @return The string displaying the outcome of the action
   */
  public String createPlayer(String playername, String location, int size, boolean bot);

  /**
   * Used to create the world from a Readable.
   * 
   * @param config the Readable configuration for the world
   * @return the string output from the operation
   */
  public String scanFile(Readable config);

  /**
   * This allows a player to a move a pet into the specified room.
   * 
   * @param spacename the name of the space to move the pet into
   * @return the string output from the operation
   */
  public String movePet(String spacename);

  /**
   * Causes the player to attack the Target with the weapon specified.
   * 
   * An attack is only successful if it is not seen by any other player and the
   * target is in the same room as the player. If no weapon is present, the
   * player, pokes the eye using the finger causing a damage of 1.
   * 
   * @param itemname the name of the item to attack the target with
   * @return the string output from the operation
   */
  public String attackTarget(String itemname);

  /**
   * Creates the new world based on the specifications provided in the file.
   * 
   * Reads the new file and creates a new game and new graph based on the content
   * in the file.
   * 
   * @param newfile the file that is taken as input to start the new game.
   */
  public void resetWorld(Readable newfile);

  /**
   * Checks if the game is over.
   * 
   * The game is over when the no of turns has reached zero
   * 
   * @return true of false indicating whether the game is over
   */
  public boolean isGameOver();

  /**
   * Checks if the game is over when either the maximum turns are reached and the
   * target escapes or when the player kills the target.
   * 
   * 
   * 
   * @return returns the string if the player won the game or if the target
   *         character escaped successfully.
   */
  public String gameOverStatus();
}
