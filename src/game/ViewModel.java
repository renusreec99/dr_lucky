package game;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The read-only model interface.
 * 
 * this restricts the action to only reading the data and consists only of the
 * mutable methods.
 * 
 *
 */
public interface ViewModel {

  /**
   * Obtains the specified object from the list of spaces in the world.
   * 
   * @param spacename The name of the space to be retrieved
   * @return The {@link Space} object of the space found, null if not found
   */
  public Space getSpace(String spacename);

  /**
   * This method allows you to get the information about the current player.
   * 
   * @return The string displaying the details of the current character
   * 
   */
  public String getPlayerInfo();

  /**
   * Return the information of the space occupied by the current player.
   * 
   * @return The information of the space as a String
   */
  public String displaySpaceInformation();

  /**
   * Gets the {@link PlayerInterface} whose turn it currently is.
   * 
   * @return the name of the player whose turn it is.
   */
  public String getCurrentPlayer();

  /**
   * This method allows the current player to look around from the current space.
   * 
   * @return The string displaying the neighbors of the current space
   */
  public String lookAround();

  /**
   * Creates the graphical representation of the world and outputs an image.
   *
   * @return the result from the operation
   */
  public BufferedImage getGraphicalRepresentation();

  /**
   * This function returns the name of the winner of the game.
   * 
   * If there are no winners yet, the string is empty
   * 
   * @return The string name of the winning player
   */
  public String getWinner();

  /**
   * This function returns all the names of spaces in the world.
   * 
   *
   * 
   * @return The list of space names in the world in string format.
   */
  public List<String> getAllSpaceNames();

  /**
   * This function returns all the items the player is carrying.
   * 
   *
   * 
   * @return The list of item names with the player in string format.
   */
  public List<String> getAllPlayerItems();

  /**
   * This function returns all the items in a particular space.
   * 
   *
   * 
   * @return The list of item names in the space in string format.
   */
  public List<String> getAllSpaceItems();

  /**
   * This function that returns the name of the space based on the coordinates
   * passed.
   * 
   *
   * @param c the coordinates for which the space name has to be found.
   * @return the name of the space for the given coordinates.
   */
  public String getSpaceFromCoordinates(Coordinates c);

  /**
   * This function returns all the players in a particular space.
   * 
   *
   * @param space the space for which the players have to be found.
   * @return The list of players in the space in string format.
   */
  List<String> getPlayersInSpace(Space space);

  /**
   * function to return all the spaces in the world.
   * 
   * @return the list of all spaces in the world.
   */
  List<Space> getAllSpaces();
}
