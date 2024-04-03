import game.Features;
import game.View;
import java.util.List;


/**
 * The mock  error class for the {@link View} class.
 * 
 * This simulates a view which throws an error irrespective of the input.
 *
 */
public class MockView implements View {

  
  private StringBuilder log;
  private final String uniqueString;
  private final List<String> uniquestringlist;

  
  
  /**
   * The constructor for the mock view model.
   * 
   * @param log the {@link StringBuilder} object used to store logs
   * @param uniquestring the unique string value to be returned from a function
   * @param stringlist The list of unique string values to be returned
   */
  public MockView(StringBuilder log, String uniquestring, List<String> stringlist) {
    this.log = log;
    this.uniqueString = uniquestring;
    this.uniquestringlist = stringlist;
  }
  
  @Override
  public void showView() {
    log.append("Show View called\n");

  }

  @Override
  public void setFeatures(Features f) {
    log.append(String.format("Get Set Feature called with %s\n", f));
  }

  @Override
  public void showMessage(String s1) {
    log.append(String.format("Show message called with : %s\n", s1));
  }

  @Override
  public void resetFocus() {
    log.append(String.format("Reset focus called\n"));

  }

  @Override
  public void showCurrentPlayerDetails(String message) {
    log.append(String.format("Show current player details called with : %s\n", message));
  }

  @Override
  public String showDropDown(List<String> list) {
    StringBuilder sb = new StringBuilder();
    
    for (String string : list) {
      sb.append(string);
      sb.append(" ");
    }
    log.append(String.format("Show Drop Down called with %s\n", sb));
    
    return uniqueString;
  }

  @Override
  public List<String> addPlayerDrop(List<String> list) {
    StringBuilder sb = new StringBuilder();
    
    for (String string : list) {
      sb.append(string);
      sb.append(" ");
    }
    
    log.append(String.format("Show Add Player Drop called with %s\n", sb));
    
    return uniquestringlist;
  }

  @Override
  public void showNewView() {
    log.append(String.format("Show New View called\n"));
  }

  @Override
  public void refresh() {
    log.append(String.format("Refresh called\n"));
  }

  @Override
  public void showErrorMessage(String string) {
    log.append(String.format("Show Error Message Called\n"));

  }

  @Override
  public void showSelectedPlayerDetails(String message) {
    log.append(String.format("Show Selected Player Details called with %s\n", message));
  }

  @Override
  public void exitGame() {
    log.append(String.format("Exit game called\n"));

  }

  @Override
  public void showGameEnd(String message) {
    log.append(String.format("Show Game End called with %s\n", message));
  }

  @Override
  public String getFile() {
    log.append(String.format("Get File called with\n"));
    
    return uniqueString;
  }

}
