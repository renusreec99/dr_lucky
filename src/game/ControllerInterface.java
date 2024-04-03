package game;

/**
 *
 * Interface for the controller of the game.
 * 
 * The controller is part of the MVC which takes commands from the user through
 * the GUI. and passes on the information to the model to take the appropriate
 * actions.
 *
 *
 *
 */
public interface ControllerInterface {
  /**
   * Initializes the world and starts the game.
   * 
   */
  public void startGame();
}
