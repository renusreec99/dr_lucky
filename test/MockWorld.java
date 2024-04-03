import game.Coordinates;
import game.Space;
import game.World;
import java.awt.image.BufferedImage;
import java.util.List;


/**
 * The mock class for the {@link World} class.
 * 
 *
 */
public class MockWorld implements World {

  private StringBuilder log;
  private final String uniqueString;
  private final Space uniquespace;
  private final boolean uniquebool;
  private final List<String> uniquestringlist;
  private final List<Space> uniquespacelist;

  /**
   * The constructor for the mock world model.
   * 
   * @param log the {@link StringBuilder} object used to store logs
   * @param uniquestring the unique string value to be returned from a function
   * @param uniquespace the unique space value to be returned from a function
   * @param bool the unique boolean value to be returned from a function
   * @param stringlist the unique list of strings value to be returned from a function
   * @param spacelist the unique list of spaces to be returned from a function
   */
  public MockWorld(StringBuilder log, String uniquestring, Space uniquespace, boolean bool,
      List<String> stringlist, List<Space> spacelist) {
    this.log = log;
    this.uniqueString = uniquestring;
    this.uniquespace = uniquespace;
    this.uniquebool = bool;
    this.uniquestringlist = stringlist;
    this.uniquespacelist = spacelist;
  }

  @Override
  public String getCurrentPlayer() {
    log.append("Get Current Player called\n");
    return uniqueString;
  }

  @Override
  public String movePlayer(String spacename) {

    log.append("Move Player Input: " + spacename + "\n");

    return uniqueString;
  }

  @Override
  public Space getSpace(String spacename) {
    log.append("Get Space Input with" + spacename + "\n");

    return uniquespace;
  }

  @Override
  public String displaySpaceInformation() {
    log.append("Display Space Information called\n");
    return uniqueString;
  }

  @Override
  public boolean isGameOver() {
    log.append("Is Game Over Called\n");
    return uniquebool;
  }

  @Override
  public String lookAround() {
    log.append("Look Around called\n");
    return uniqueString;
  }

  @Override
  public String getPlayerInfo() {
    log.append("Get Player Info called\n");
    return uniqueString;
  }

  @Override
  public String pickItem(String itemname) {
    log.append("Pick Item called with " + itemname + "\n");
    return uniqueString;
  }

  @Override
  public String createPlayer(String playername, String location, int size, boolean bot) {
    log.append("Create Player called with " + playername + " " + location + " " + bot + "\n");
    return uniqueString;
  }

  @Override
  public String scanFile(Readable config) {

    log.append("Scan File called\n");

    return uniqueString;
  }

  @Override
  public String movePet(String spacename) {
    log.append("Move Pet called with: " + spacename + "\n");

    return uniqueString;
  }

  @Override
  public String attackTarget(String itemname) {
    log.append("Attack Target with: " + itemname + "\n");

    return uniqueString;
  }

  @Override
  public String getWinner() {

    log.append("Get Winner called\n");

    return uniqueString;
  }

  @Override
  public List<String> getAllSpaceNames() {
    log.append(String.format("Get All Space called\n"));
    return uniquestringlist;
  }

  @Override
  public List<String> getAllPlayerItems() {
    log.append(String.format("Get All Player Items called\n"));

    return uniquestringlist;
  }

  @Override
  public List<String> getAllSpaceItems() {
    log.append(String.format("Get All Space Items called\n"));

    return uniquestringlist;
  }

  @Override
  public List<Space> getAllSpaces() {
    log.append(String.format("Get All Spaces called\n"));

    return uniquespacelist;
  }

  @Override
  public List<String> getPlayersInSpace(Space space) {

    log.append(String.format("Get Players in Space called with: %s\n", space));

    return uniquestringlist;
  }

  @Override
  public String getSpaceFromCoordinates(Coordinates c) {

    log.append(String.format("Get Space from Coordinates called with: %s\n", c));

    return uniqueString;
  }

  @Override
  public void resetWorld(Readable newfile) {
    log.append(String.format("Reset World called with %s\n", newfile));
  }

  @Override
  public BufferedImage getGraphicalRepresentation() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String gameOverStatus() {
    log.append(String.format("Game Over Status called\n"));
    return uniqueString;
  }

}
