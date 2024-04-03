package game;

import java.util.List;

/**
 * A view for the game: display the game map and provide visual interface for
 * users.
 */
public interface View {

  /**
   * Make the view visible to start the game session.
   */

  public void showView();

  /**
   * the method to call the features interface(controller) from the view.
   * 
   * @param f the feature that is being called in the constructor based on user
   *          action.
   */
  public void setFeatures(Features f);

  /**
   * The message or the results after each turn is displayed to the user using
   * this method. This opens a dialog box in which the message is displayed.
   * 
   * @param s1 the message that is being passed from the controller that needs to
   *           be displayed to the user.
   */
  public void showMessage(String s1);

  /*
   * In order to make this frame respond to keyboard events, it must be within
   * strong focus. Since there could be multiple components on the screen that
   * listen to keyboard events, we must set one as the "currently focused" one so
   * that all keyboard events are passed to that component. This component is said
   * to have "strong focus".
   * 
   * We do this by first making the component focusable and then requesting focus
   * to it. Requesting focus makes the component have focus AND removes focus from
   * whoever had it before.
   */
  /**
   * the method that resets the focus from the previous action.
   * 
   * Such that after every type of action the controller doesn't wait for the same
   * type of action again.
   */
  public void resetFocus();

  /**
   * the method to display the details of the current player to the user during
   * each turn. it populates the text area with the current player details. is
   * called after every user turn.
   * 
   * @param message the string that is being displayed in the text area. this
   *                message is received from the controller and is being validated
   *                each time before it is passed.
   */
  void showCurrentPlayerDetails(String message);

  /**
   * A dropdown is displayed with the appropriate items list that is being
   * received from the controller and waits for the user to select one item.
   * 
   * @param list the list that is received from controller.
   * @return the selected item in string format to the controller.
   */
  String showDropDown(List<String> list);

  /**
   * To show the input message pane that is displayed whenever the user wants to
   * add more players to the game. it displays the text field for player name , a
   * drop box consisting all the spaces that are present to which the player can
   * be added and if the player is computer or human.
   * 
   * @param list the list of all the spaces in the given name for the user to
   *             select which space he wants to add the player into
   * @return the list of string items that consist of the player name , room name
   *         and the type of the player.
   */
  List<String> addPlayerDrop(List<String> list);

  /**
   * A method to show the game screen of the game after the selection of a menu
   * item for playing new game.
   * 
   */
  public void showNewView();

  /**
   * a method to repaint the screen after each user action.
   */
  void refresh();

  /**
   * A method to show the error message dialog on the screen to the user. the
   * string message received from the controller is being displayed to the user.
   * 
   * @param string the error message being passed by the controller that has to be
   *               displayed to the user.
   */
  void showErrorMessage(String string);

  /**
   * A method to display the details about the selected player on the screen. the
   * details are received from the controller.
   * 
   * @param message the string received from the controller that is displayed.
   */
  void showSelectedPlayerDetails(String message);

  /**
   * the action that takes place when the games that has to end or the player
   * wants to exit the game.
   * 
   */
  void exitGame();

  /**
   * A method display the dialog that displays the message that the game has
   * reached its end.
   * 
   * @param message the string that has to be displayed on the screen that is sent
   *                from the controller.
   */
  public void showGameEnd(String message);

  /**
   * A method to choose the file from the system.
   * 
   * @return the file path of the file chosen in the form of string.
   */
  String getFile();
}
