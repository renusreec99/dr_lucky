package game;

import java.io.StringReader;
import java.util.Random;

/**
 * The driver class for this project.
 * 
 * @author Jaison John
 *
 */
public class Main {

  /**
   * Creates and demonstrates the working of the world.
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {

    if (args.length != 2) {

      System.out.println(
          "Please provide only the world config txt file and no of turns as the argument");
        
    } else {

      String file = String.format(args[0]);
      int turns = Integer.parseInt(args[1]);

      World model = new MansionWorld(new StringReader(file), turns, new Random());
      View gamescreen = new ViewImpl(model);

      ControllerInterface c = new Controller(gamescreen, model);
      c.startGame();
    }

  }

}
