package game;

import java.util.List;

/**
 * Spaces represent the different rooms present in a world.
 * 
 * These spaces have coordinates specifying their position in the world. Each
 * space contains items and have neighboring spaces. Neighboring spaces can see
 * into its neighbors
 *
 */
public interface Space {
  /**
   * Obtain the index number of this space.
   * 
   * @return Index of type integer of the room
   */
  public int getSpaceIndex();

  /**
   * Obtains the name of the space.
   * 
   * @return A String containing the name of the space
   */
  public String getSpaceName();

  /**
   * Gets the list of items present in this space.
   * 
   * @return A {@link List} of {@link Item} present in this room
   */
  public List<Item> getItems();

  /**
   * Gets all the neighboring rooms of the current space.
   * 
   * @return {@link List} of {@link Space} which are neighbors of this room
   */
  public List<Space> getNeighbours();

  /**
   * Obtain the Coordinates of this space.
   * 
   * @return {@link List} of {@link Coordinates} which are the upper and lower
   *         coordinates of this room
   */
  public List<Coordinates> getCoordinates();

  /**
   * Add a neighboring {@link Space} to the list of neighbors of this space.
   * 
   * @param neighbor the neighboring room of the space
   */
  public void addNeighbour(Space neighbor);

  /**
   * Add an {@link Item} to the list of items present in this room.
   * 
   * @param item the item present in the room
   */
  public void addItem(Item item);

  /**
   * Removes an {@link Item} to the list of items present in this room.
   * 
   * @param item the item present in the room
   */
  public void removeItem(Item item);

  /**
   * Checks if two {@link Space} objects are equal.
   * 
   * Two spaces are said to be equal when the name, index and the coordinates of
   * the spaces are the same
   * 
   * @param o the object to be compared
   * @return True if the two space objects are equal else False
   */
  public boolean equals(Object o);

  /**
   * returns all the names of items that are present in the space.
   * 
   * 
   * @return the list of item names present in the space.
   */
  public List<String> getAllSpaceItems();

}
