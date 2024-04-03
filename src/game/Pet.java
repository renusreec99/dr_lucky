package game;


import java.util.Objects;

/**
 * Class representing the pets in the game.
 * 
 * A pet is an animal that is with the target character. Rooms in which a pet is 
 * present is hidden from the neighboring spaces.
 *
 */
public class Pet implements Animal {
  
  private String name;
  
  /**
   * Constructor for the Pet class.
   * 
   * @param name the name of the pet
   */
  public Pet(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Pet name can't be null");
    }
    
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public String toString() {
    return String.format("Pet: %s", name);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    
    if (! (o instanceof Pet)) {
      return false;
    }
    
    Pet that = (Pet) o;
    
    return that.name == this.name;
  }
  
  @Override
  public int hashCode() {
    
    return (Objects.hash(this.name));
    
  }

}
