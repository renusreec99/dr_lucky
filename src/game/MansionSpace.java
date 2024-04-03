package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The spaces in this game which represents a room in DrLucy's Mansion.
 * 
 * The space is placed in the world with set of integer coordinates. Each room
 * has a list of items present in the room, it also has neighbors. A neighbor is
 * a space that shares a wall with this space. Dr Lucky can be either present or
 * absent in this space.
 *
 */
public class MansionSpace implements Space {

  private final int index;
  private final String spacename;
  private final Coordinates upperleft;
  private final Coordinates lowerright;
  private final List<Space> neighborslist;
  private List<Item> itemlist;

  /**
   * Constructor for a space object in the mansion.
   * 
   * @param index     The index of the room
   * @param name      The name of the room
   * @param upper     Upper right {@link Coordinates} of the room
   * @param lower     Lower left {@link Coordinates} of the room
   * @param itemslist The list of items present in the room
   * @param neighbors The list of neighboring spaces of the room
   * 
   */
  public MansionSpace(int index, String name, Coordinates upper, Coordinates lower,
      List<Item> itemslist, List<Space> neighbors) {

    if (upper == null || lower == null) {
      throw new IllegalArgumentException("The coordinates of the room has to have non null values");
    }

    if (upper.getX() > lower.getX() || upper.getY() > lower.getY()) {
      throw new IllegalArgumentException(
          "The coordinates of upperright corner has to be lesser " + "than lower right");
    }

    if (upper.equals(lower)) {
      throw new IllegalArgumentException("The Room size cannot be zero");
    }

    if (index < 0) {
      throw new IllegalArgumentException("Index of the space cannot be negative");
    }

    if (itemslist == null) {
      throw new IllegalArgumentException("The list of items cannot be null");
    }

    if (neighbors == null) {
      throw new IllegalArgumentException("The list of neighbors cannot be null");
    }

    if (name == null) {
      throw new IllegalArgumentException("Name of the space cannot be null");
    }

    this.index = index;
    spacename = name;
    upperleft = upper;
    lowerright = lower;
    this.itemlist = itemslist;
    this.neighborslist = neighbors;
  }

  /**
   * Copy constructor for the Mansion Space Class.
   * 
   * @param space the space object to be copied
   */
  public MansionSpace(MansionSpace space) {

    if (space == null) {
      throw new IllegalArgumentException("The space object to be copied cannot be null");
    }

    this.index = space.index;
    this.spacename = space.spacename;
    this.upperleft = space.upperleft;
    this.lowerright = space.lowerright;
    this.itemlist = space.itemlist;
    this.neighborslist = space.neighborslist;
  }

  @Override
  public int getSpaceIndex() {
    return index;
  }

  @Override
  public String getSpaceName() {
    return spacename;
  }

  @Override
  public List<Item> getItems() {
    return new ArrayList<Item>(itemlist);
  }

  @Override
  public List<Space> getNeighbours() {
    
    List<Space> copySpace = new ArrayList<Space>();
    for (Space sp : neighborslist) {
      copySpace.add(sp);
    }
    
    return copySpace;

  }

  @Override
  public void addNeighbour(Space neighbor) {

    if (neighbor == null) {
      throw new IllegalArgumentException("The neighboring space cannot be null");
    }

    if (!(neighbor instanceof MansionSpace)) {
      throw new IllegalArgumentException("The neighboring space has to be a mansion space");
    }

    neighborslist.add(new MansionSpace((MansionSpace) neighbor));
  }

  @Override
  public String toString() {

    return String.format("Room %s @ %s %s", spacename, upperleft, lowerright);
  }

  @Override
  public List<Coordinates> getCoordinates() {

    List<Coordinates> coordinates = new ArrayList<Coordinates>();
    coordinates.add(upperleft);
    coordinates.add(lowerright);

    List<Coordinates> copies = new ArrayList<>();
    for (Coordinates c : coordinates) {
      copies.add(c);
    }
    
    return copies;
  }

  @Override
  public void addItem(Item item) {

    if (item == null) {
      throw new IllegalArgumentException("The item cannot be null");
    }

    if (!(item instanceof Weapon)) {
      throw new IllegalArgumentException("The item has to be a weapon");
    }

    itemlist.add((Weapon) item);
  }

  @Override
  public void removeItem(Item item) {

    if (item == null) {
      throw new IllegalArgumentException("The item cannot be null");
    }

    itemlist.remove(item);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof MansionSpace)) {
      return false;
    }

    MansionSpace that = (MansionSpace) o;

    return (this.index == that.index && this.lowerright.equals(that.lowerright)
        && this.upperleft.equals(that.upperleft) && this.spacename == that.spacename);
  }

  @Override
  public int hashCode() {

    return (Objects.hash(index, lowerright, upperleft, spacename));

  }

  @Override
  public List<String> getAllSpaceItems() {
    List<String> itemnames = new ArrayList<String>();
    for (Item item : itemlist) {
      itemnames.add(item.getName());
    }
    return itemnames;
  }

}
