package commands;

import game.View;
import game.World;

/**
 * The class for the move command in the world.
 *
 */
public class Move implements WorldCommands {

  private String spacename;

  /**
   * Constructor for initializing the move command class.
   * 
   *
   * @param spacename the name of the space to move into
   */
  public Move(String spacename) {
    if (spacename == null || ("".equals(spacename))) {
      throw new IllegalArgumentException("View Cannot be null");
    }
    this.spacename = spacename;
  }

  @Override
  public String execute(World model, View screen) {

    if (model == null || screen == null) {
      throw new IllegalArgumentException("World model or View can't be null");
    }

    String message = model.movePlayer(spacename);
    return message;

  }

}
