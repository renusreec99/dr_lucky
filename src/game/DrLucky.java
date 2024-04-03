package game;


import java.util.Objects;

/**
 * The implementation of Dr Lucky for the game.
 * 
 * Dr Lucky is the character that plays the main role in this game
 * He has a health attribute and a name attribute.
 *
 */
public class DrLucky implements Character {
  
  private int health;
  private final String name;
  private final Animal pet;
  
  /**
   * Creates instance of Dr Lucky.
   * 
   * @param name The name of the character presumably Dr Lucky
   * @param health The health of Dr Lucky
   * @param pet Dr Lucky's pet
   * 
   * @throws IllegalArgumentException if the values provided to health is non-positive
   */
  public DrLucky(String name, int health, Animal pet) {
    if (health <= 0) {
      throw new IllegalArgumentException("Health has to be a positive value");
    }
    
    if (name == null) {
      throw new IllegalArgumentException("Name of the character cannot be null");
    }
    
    if (pet == null) {
      throw new IllegalArgumentException("Character's pet cannot be null");
    }
    
    this.health = health;
    this.name = name;
    this.pet = pet;
  }
  
  @Override
  public int getHealth() {
    return health;
  }

  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public String toString() {
    return String.format("Character: %s , Health: %d", name, health);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    
    if (! (o instanceof Character)) {
      return false;
    }
    
    Character that = (DrLucky) o;
    
    return that.getName() == name;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public void healthUpdate(int value) {
    
    if (health + value < 0) {
      health = 0;
    } else {
      health += value;
    }
        
  }

  @Override
  public String getPetName() {
    return pet.getName();
  }

}
