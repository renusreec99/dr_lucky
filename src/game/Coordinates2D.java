package game;


import java.util.Objects;

/**
 * This creates a 2D coordinate with X and Y coordinates.
 * 
 * The X and Y coordinates values represent the values for the first quadrant
 * in a two dimensional rectangular coordinate system. This means all the values are 
 * positive and are also expected to be integers
 *
 */
public class Coordinates2D implements Coordinates {

  private final int xval;
  private final int yval;
  
  /**
   * 
   * Creates the 2D coordinate object.
   * 
   * @param x The x value of the coordinate
   * @param y The y value of the coordinate
   * 
   * @IllegalArgumentException if either the X or Y value is negative
   */
  public Coordinates2D(int x, int y) {
    if (x < 0) {
      throw new IllegalArgumentException("The x value of the coordinate cannot be negative");
    }
    
    if (y < 0) {
      throw new IllegalArgumentException("The y value of the coordinate cannot be negative");
    }
    
    this.xval = x;
    this.yval = y;
  }
  
  /**
   * Copy constructor for the Coordinates Class.
   * 
   * @param coords the Coordinates object to be copied
   */
  public Coordinates2D(Coordinates2D coords) {
    this.xval = coords.xval;
    this.yval = coords.yval;
  }
  
  @Override
  public int getX() {
    return xval;
  }

  @Override
  public int getY() {
    return yval;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    
    if (! (o instanceof Coordinates)) {
      return false;
    }
    
    Coordinates that = (Coordinates) o;
    
    return (this.yval == that.getY()) && (this.xval == that.getX());
  }
  
  @Override
  public int hashCode() {
    
    return (Objects.hash(xval, yval));
    
  }
  
  @Override
  public String toString() {
    
    return (String.format("(%d, %d)", xval, yval));
    
  }

}
