import game.Coordinates;
import game.Space;
import game.World;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;



/**
 * The mock error class for the {@link World} class.
 * 
 * This simulates a model which throws an error irrespective of the input.
 *
 */
public class MockErrorModel implements World {

  private StringBuilder log;

  /**
   * The constructor for the mock world model.
   * 
   * @param log the {@link StringBuilder} object used to store logs
   */
  public MockErrorModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public Space getSpace(String spacename) {
    return null;
  }

  @Override
  public String getPlayerInfo() {

    return "No Player Information to show";
  }

  @Override
  public String displaySpaceInformation() {
    return "No Space to Display";
  }

  @Override
  public String getCurrentPlayer() {
    return "No Current Player";
  }

  @Override
  public String lookAround() {
    return "Cannot Look Around";
  }

  @Override
  public BufferedImage getGraphicalRepresentation() {
    log.append("Graphical Error");
    return null;
  }

  @Override
  public String getWinner() {
    return "No Winner Currently";
  }

  @Override
  public List<String> getAllSpaceNames() {
    return new ArrayList<String>();
  }

  @Override
  public List<String> getAllPlayerItems() {
    return new ArrayList<String>();
  }

  @Override
  public List<String> getAllSpaceItems() {
    return new ArrayList<String>();
  }

  @Override
  public String getSpaceFromCoordinates(Coordinates c) {
    throw new IllegalArgumentException();
  }

  @Override
  public List<String> getPlayersInSpace(Space space) {
    return new ArrayList<String>();
  }

  @Override
  public List<Space> getAllSpaces() {
    return new ArrayList<Space>();
  }

  @Override
  public String movePlayer(String spacename) {
    return "Invalid Player Name";
  }

  @Override
  public String pickItem(String itemname) {
    throw new IllegalArgumentException();
  }

  @Override
  public String createPlayer(String playername, String location, int size, boolean bot) {
    return "Invalid Create Player Details";
  }

  @Override
  public String scanFile(Readable config) {
    return "Invalid File Passed";
  }

  @Override
  public String movePet(String spacename) {
    return "Invalid Space name to move pet";
  }

  @Override
  public String attackTarget(String itemname) {
    return "Invalid item passed to attack";
  }

  @Override
  public void resetWorld(Readable newfile) {
    throw new IllegalStateException();
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String gameOverStatus() {
    return "Invalid Game Over status";
  }

}
