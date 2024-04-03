package commands;

import game.View;
import game.World;


/**
 * The class which represents the Look Around command.
 *
 */
public class LookAround implements WorldCommands {

  @Override
  public String execute(World model, View screen) {

    if (model == null || screen == null) {
      throw new IllegalArgumentException("World model can't be null");
    }

    String s1 = model.lookAround();

    return s1;

  }

}
