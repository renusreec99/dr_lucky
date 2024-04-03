package game;


import java.util.Objects;

/**
 * 
 * The weapon class in items. A weapon will have greater than zero damage.
 * This reduces the health of any character interacting with this.
 *
 */
public class Weapon implements Item {
  
  private final int index;
  private final String itemname;
  private final int damage;
  private final int roomno;
  
  /**
   * Constructs a weapon item.
   * 
   * @param index the index of the weapon (Zero based indexing)
   * @param name Name of the weapon
   * @param damage Damage of the weapon
   * @param roomno The room in which the weapon can be found
   * 
   */
  public Weapon(int index, String name, int damage, int roomno) {
    if (damage <= 0) {
      throw new IllegalArgumentException("Damage of the weapon cannot be non-positive");
    }
    if (index < 0) {
      throw new IllegalArgumentException("Index of the weapon cannot be negative");
    }
    if (roomno < 0) {
      throw new IllegalArgumentException("Room number of the weapon cannot be negative");
    }
    if (name == null) {
      throw new IllegalArgumentException("Name of the weapon cannot be null");
    }
    
    this.damage = damage;
    this.itemname = name;
    this.index = index;
    this.roomno = roomno;  
  }
  
  /**
   * Copy Constructor for a weapon item.
   * 
   * @param weapon the weapon object to be copied
   * 
   */
  public Weapon(Weapon weapon) {
    
    if (weapon == null) {
      throw new IllegalArgumentException("The weapon object to be copied cannot be null");
    }
    
    this.damage = weapon.damage;
    this.itemname = weapon.itemname;
    this.index = weapon.index;
    this.roomno = weapon.roomno;  
  }
  
  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getRoomNo() {
    return roomno;
  }

  @Override
  public String getName() {
    return itemname;
  }
  
  @Override
  public String toString() {
    return String.format("%s - Damage : %d Room : %d", itemname, damage, roomno);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    
    if (! (o instanceof Item)) {
      return false;
    }
    
    Weapon that = (Weapon) o;
    
    return damage == that.damage && itemname == that.itemname 
        && index == that.index && this.roomno == that.roomno;
  }
  
  @Override
  public int hashCode() {
    
    return (Objects.hash(this.index, this.roomno, this.damage, this.itemname));
    
  }

}
