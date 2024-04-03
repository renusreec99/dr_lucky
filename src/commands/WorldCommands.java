package commands;

import game.View;
import game.World;

/**
 * Interface for the commands in the controller.
 * 
 * This enables the Command Pattern for the controller. Each class implementing
 * this would represent particular commands that a human player can use.
 *
 */
public interface WorldCommands {

  /**
   * This method executes the command using the given world object.
   * 
   * @param model The {@link World} model which is used to execute the commands
   * @param screen the view object used for executing the command
   * @return the result of the command executed
   */
  public String execute(World model, View screen);

}
