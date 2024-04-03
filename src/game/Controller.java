package game;

import commands.AddPlayer;
import commands.Attack;
import commands.LookAround;
import commands.Move;
import commands.MovePet;
import commands.Pick;
import commands.WorldCommands;
import java.io.StringReader;
import java.util.NoSuchElementException;

/**
 * The controller class for the game which uses the command pattern and user
 * input.
 * 
 * The features interface is used to enable the controller to be a listener
 * for the view events.
 *
 */
public class Controller implements ControllerInterface, Features {

  private World mansion;
  private WorldCommands command;
  private View screen;

  /**
   * Creates the controller from the given configuration file and input and output
   * streams.
   * 
   * @param v {@link View} the view for the GUI.
   * @param w the {@link World} model to play the game
   */
  public Controller(View v, World w) {

    if (v == null) {
      throw new IllegalArgumentException("View object cannot be null");
    }

    if (w == null) {
      throw new IllegalArgumentException("World object cannot be null");
    }
    this.screen = v;
    this.mansion = w;

  }

  @Override
  public void startGame() {
    screen.showView();
    screen.setFeatures(this);
    screen.refresh();
  }

  @Override
  public void movePlayer(int x, int y) {
    try {
      String s = mansion.getSpaceFromCoordinates(new Coordinates2D(x, y));
      if (!("".equals(s))) {
        command = new Move(s);
        String string = command.execute(mansion, screen);
        if (!(string == null)) {
          screen.showMessage(string);
          screen.refresh();
        } else {
          screen.showErrorMessage("Invalid Move attempt");
          screen.refresh();
        }
        String messagegame;
        if (mansion.isGameOver()) {
          messagegame = mansion.gameOverStatus();
          if (messagegame != null) {
            screen.showGameEnd(messagegame);
            screen.exitGame();
          }
        } else {
          String messagedetails = mansion.getPlayerInfo();
          if (messagedetails != null) {
            screen.showCurrentPlayerDetails(messagedetails);
            screen.refresh();
          }
        }
      }
    } catch (IllegalArgumentException e) {
      screen.showErrorMessage("You have moved beyond the world area!");
      screen.refresh();
    }
  }

  @Override
  public void loadNewWorld() {
    String file = screen.getFile();
    if (!"".equals(file)) {
      StringReader sr = new StringReader(file);
      try {
        mansion.resetWorld(sr);
        screen.showNewView();
        screen.refresh();
      } catch (IllegalStateException e) {
        screen.showErrorMessage("Invalid World File Passed!!");
        screen.refresh();
      } catch (NoSuchElementException e) {
        screen.showErrorMessage("Invalid World File Passed!!");
        screen.refresh();
      }
    }
  }

  @Override
  public void movePet() {
    command = new MovePet();
    String message = command.execute(mansion, screen);
    screen.showMessage(message);
    String messagegame;
    screen.refresh();
    if (mansion.isGameOver()) {
      messagegame = mansion.gameOverStatus();
      screen.showGameEnd(messagegame);
      screen.exitGame();

    } else {
      String messagedetails = mansion.getPlayerInfo();
      screen.showCurrentPlayerDetails(messagedetails);
      screen.refresh();
    }
  }

  @Override

  public void addPlayer() {

    command = new AddPlayer();

    try {

      screen.showMessage(command.execute(mansion, screen));

    } catch (IllegalArgumentException e) {

      String msg = "The name cannot be null or empty";

      screen.showErrorMessage(msg);

    }

    String message = mansion.getPlayerInfo();

    screen.showCurrentPlayerDetails(message);

    screen.refresh();

  }

  @Override
  public void pickItem() {
    command = new Pick();
    try {
      screen.showMessage(command.execute(mansion, screen));
    } catch (IllegalArgumentException e) {
      String error = "NO ITEMS LEFT TO PICK";
      screen.showErrorMessage(error);
    }
    String messagegame;
    screen.refresh();
    if (mansion.isGameOver()) {

      messagegame = mansion.gameOverStatus();
      screen.showGameEnd(messagegame);
      screen.exitGame();

    } else {
      String message = mansion.getPlayerInfo();
      screen.showCurrentPlayerDetails(message);
      screen.refresh();
    }
  }

  @Override
  public void lookAround() {
    command = new LookAround();
    String messagegame;
    String s1 = (command.execute(mansion, screen));
    screen.showMessage(s1);
    screen.refresh();
    if (mansion.isGameOver()) {
      messagegame = mansion.gameOverStatus();
      screen.showGameEnd(messagegame);
      screen.exitGame();
    } else {
      String message = mansion.getPlayerInfo();
      screen.showCurrentPlayerDetails(message);
      screen.refresh();
    }
  }

  @Override
  public void attack() {
    command = new Attack();
    screen.showMessage(command.execute(mansion, screen));
    String messagegame;
    screen.refresh();
    if (mansion.isGameOver()) {

      messagegame = mansion.gameOverStatus();
      screen.showGameEnd(messagegame);
      screen.exitGame();

    } else {
      String message = mansion.getPlayerInfo();
      screen.showCurrentPlayerDetails(message);
      screen.refresh();
    }
  }

  @Override
  public void exitProgram() {
    screen.exitGame();

  }

  @Override
  public void loadWorld() {

    screen.showNewView();

    screen.refresh();

  }

  @Override
  public void getPlayerDetails() {
    String message = mansion.getPlayerInfo();
    screen.showSelectedPlayerDetails(message);
    
  }

  @Override
  public String toString() {
    return "Game Controller";
  }

}
