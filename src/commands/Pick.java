package commands;

import game.View;
import game.World;
import java.util.ArrayList;
import java.util.List;


/**
 * The class which represents the Pick an item command.
 *
 */
public class Pick implements WorldCommands {

  private String item;

  @Override
  public String execute(World model, View screen) {

    if ((model == null) || screen == null) {
      throw new IllegalArgumentException("World model and View can't be null");
    }
    List<String> items = new ArrayList<String>();

    items = model.getAllSpaceItems();

    item = screen.showDropDown(items);
    if (item != null) {
      String output = model.pickItem(item);
      return output;
    }
    String string = "PLEASE SELECT AN ITEM TO PICK";
    return string;

  }

}
