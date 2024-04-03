package game;


/**
 * Interface defining all the actions that a character in the game performs.
 * A character is an entity in the game that moves around the world trying to avoid players.
 * Each character has a name and a starting health. The target character is named Doctor Lucky.
 *
 */
public interface Character {
  /**
   * Obtain the health of the character.
   * 
   * If the health is zero it means that the character is dead.
   * 
   * @return the integer value of health
   */
  public int getHealth();
  
  /**
   * Updates the health of the character.
   * 
   * Updates the health of the character based on the value provided. 
   * Damages are negative and healing is positive. The character's health 
   * cannot go below zero. This means that the character is dead.
   * 
   * @param value The value with which the health is initialized
   */
  public void healthUpdate(int value);
 
  /**
   * Obtain the name of the current character .
   * 
   * @return the string value of the name.
   */
  public String getName();
  
  /**
   * Obtain the name of the current character's pet .
   * 
   * @return the string value of the pet's name.
   */
  public String getPetName();
}
