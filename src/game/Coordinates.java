package game;


/**
 * The coordinates which are of the form (X,Y).
 * 
 */
public interface Coordinates {
  /**
   * Obtains the X coordinate value.
   * 
   * @return x coordinate value as an integer
   */
  public int getX();
  
  /**
   * Obtains the Y coordinate value.
   * 
   * @return Y coordinate value as an integer
   */
  public int getY();
  
  /**
   * Compares if two coordinate objects are equal.
   * 
   * Two coordinates are said to be equal if the x and y values of the coordinates
   * are the same
   * 
   * @param o The Object to be compared with
   * @return if the two object values are equal
   */
  public boolean equals(Object o);
  
  /**
   * Computes hashcode of the coordinates object.
   * 
   * @return hashcode value of object
   */
  public int hashCode();
  
  /**
   * Prints the coordinate in the format (X,Y).
   *
   * @return the coordinates as a string
   * 
   */
  public String toString();
  
}
