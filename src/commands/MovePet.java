package commands;

import game.View;
import game.World;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for the move pet command in the world.
 *
 */
public class MovePet implements WorldCommands {

  private String spacename;

  @Override
  public String execute(World model, View screen) {

    if (model == null || screen == null) {
      throw new IllegalArgumentException("World model or View can't be null");
    }
    List<String> rooms = new ArrayList<String>();
    rooms = model.getAllSpaceNames();
    spacename = screen.showDropDown(rooms);
    if (spacename != null) {
      String output = model.movePet(spacename);
      return output;
    }
    return "PLEASE SELECT A ROOM";
  }

}
