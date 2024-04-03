package commands;

import game.View;
import game.World;
import java.util.ArrayList;
import java.util.List;

/**
 * The class which represents the Create Player command.
 *
 */
public class AddPlayer implements WorldCommands {

  @Override
  public String execute(World model, View screen) {

    if (model == null) {
      throw new IllegalArgumentException("World model can't be null");
    }
    if (screen == null) {
      throw new IllegalArgumentException("Screen can't be null");
    }

    List<String> rooms = new ArrayList<String>();
    rooms = model.getAllSpaceNames();
    List<String> input = screen.addPlayerDrop(rooms);
    Boolean iscomputer = false;
    if (input.get(2).equals("true")) {
      iscomputer = true;
    }
    String output = model.createPlayer(input.get(0), input.get(1), 5, iscomputer);
    return output;
  }

}
