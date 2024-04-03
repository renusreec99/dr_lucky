package game;


import java.util.Objects;

/**
 * The Player class of the game.
 * 
 * There can be two types of players - Human Controlled Player and
 * Computer Controlled.
 *
 */
public class Player implements PlayerInterface {

  private String playername;
  private int inventorysize;
  private boolean isbot;
  
  /**
   * Constructor for the Player class, which is created using the user inputs.
   * 
   * @param name the name of the player
   * @param size the size of the inventory
   * @param bot whether the player is human or computer controlled. True means it 
   *     is computer controlled, False means it's human controlled
   */
  public Player(String name, int size, boolean bot) {
    
    if (size < 0) {
      throw new IllegalArgumentException("The size of the inventory cannot be negative");
    }
    if (name == null) {
      throw new IllegalArgumentException("The PlayerInterface name cannot be null");
    }
    
    playername = name;
    inventorysize = size;
    isbot = bot;
        
  }
  
  @Override
  public String getName() {
    return playername;
  }

  @Override
  public boolean isBot() {
    return isbot;
  }

  @Override
  public int getInventorySize() {
    return inventorysize;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    
    if (! (o instanceof PlayerInterface)) {
      return false;
    }
    
    Player that = (Player) o;
    
    return this.playername == that.playername;
  }
  
  @Override
  public int hashCode() {
    
    return (Objects.hash(this.playername));
    
  }

}
