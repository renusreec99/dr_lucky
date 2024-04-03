package commands;

import game.View;
import game.World;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for the attack command in the world.
 *
 */
public class Attack implements WorldCommands {

  private String itemname;

  @Override
  public String execute(World model, View screen) {

    if (model == null || screen == null) {
      throw new IllegalArgumentException("World model or View can't be null");
    }
    List<String> items = new ArrayList<String>();
    items = model.getAllPlayerItems();
    itemname = screen.showDropDown(items);
    if (itemname != null) {
      String output = model.attackTarget(itemname);
      return output;
    }
    return "PLEASE SELECT AN OPTION";

  }

}
