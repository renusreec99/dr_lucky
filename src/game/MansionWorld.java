package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Mansion World where this game is played.
 *
 * The Mansion consists of spaces where characters can go into and items are
 * present. Each space has neighbors which can be seen into i.e. the items
 * present in the room and other room information, such as it's name can be
 * viewed.
 *
 */
public class MansionWorld implements World {
  private int length;
  private int width;
  private String worldname;
  private int noofspaces;
  private int noofweapons;
  private int turnsleft;
  private Random picker;
  private Character drlucky;
  private List<Item> itemlist;
  private List<Space> spacelist;
  private Map<Character, Integer> characterpos;
  private List<Character> characterlist;
  private PlayerInterface turn;
  private Map<PlayerInterface, Integer> playerpos;
  private List<PlayerInterface> playerlist;
  private Map<PlayerInterface, List<Item>> playerinventory;
  private List<Integer> psuedorand;
  private List<Item> evidences;
  private Integer petpos;
  private String winner;
  private int totalturns;
  private Map<Integer, Supplier<String>> knownCommands;

  /**
   * Constructor for the World object by specifying each value separately.
   * 
   * @param name         string specifying the name of the world.
   * @param length       integer number representing the length of the world
   * @param width        integer number representing the width of the world
   * @param spacecount   no of spaces found in this world
   * @param weaponcount  no of weapons found in this world
   * @param itemlist     the list of all the items in this world
   * @param spacelist    the list of all spaces in this world
   * @param drlucky      the character Dr Lucky
   * @param turns        the total number of turns available for the game
   * @param psuedorandom list of integers used to stimulate random number
   *                     generation
   */
  public MansionWorld(String name, int length, int width, int spacecount, int weaponcount,
      List<Item> itemlist, List<Space> spacelist, Character drlucky, int turns,
      int... psuedorandom) {

    if (length <= 0) {
      throw new IllegalArgumentException("The length of the world has to be non zero");
    }
    if (turns <= 0) {
      throw new IllegalArgumentException("The number of turns has to be non zero");
    }
    if (width <= 0) {
      throw new IllegalArgumentException("The width of the world has to be non zero");
    }
    if (drlucky == null) {
      throw new IllegalArgumentException("The character cannot be null");
    }
    if (spacecount < 0) {
      throw new IllegalArgumentException("Space count cannot be negative");
    }
    if (weaponcount < 0) {
      throw new IllegalArgumentException("Weapon count cannot be negative");
    }
    if (itemlist == null) {
      throw new IllegalArgumentException("The list of items cannot be null");
    }
    if (spacelist == null) {
      throw new IllegalArgumentException("The list of spaces cannot be null");
    }
    if (name == null) {
      throw new IllegalArgumentException("Name of the world cannot be null");
    }

    worldname = name;
    this.length = length;
    this.width = width;
    noofspaces = spacecount;
    noofweapons = weaponcount;
    this.itemlist = itemlist;
    this.spacelist = spacelist;
    this.drlucky = drlucky;
    characterpos = new HashMap<Character, Integer>();
    characterpos.put(drlucky, 0);
    inferNeighbours();
    assignItemsToRooms();
    turnsleft = turns;
    turn = null;
    characterlist = new ArrayList<>();
    characterlist.add(drlucky);
    playerlist = new ArrayList<PlayerInterface>();
    playerinventory = new HashMap<PlayerInterface, List<Item>>();
    playerpos = new HashMap<PlayerInterface, Integer>();
    picker = null;
    psuedorand = new ArrayList<>();
    evidences = new ArrayList<>();
    petpos = 0;
    winner = "";
    totalturns = turns;

    for (int i = 0; i < psuedorandom.length; i++) {
      psuedorand.add(psuedorandom[i]);
    }

    knownCommands = new HashMap<>();
    knownCommands.put(Integer.valueOf(1), () -> {
      StringBuilder output = new StringBuilder();

      int position = playerpos.get(turn);
      Space currentspace = spacelist.get(position);

      // Pick
      List<Item> itemspresent = currentspace.getItems();
      int noofitems = itemspresent.size();

      if (noofitems == 0) {
        output.append(String.format("%s Decides to do nothing...\n", turn.getName()));
      } else {

        int itemindex;
        if (picker == null) {
          itemindex = psuedorand.get(1);
        } else {
          itemindex = picker.nextInt(noofitems);
        }
        Item itempicked = itemspresent.get(itemindex);
        playerinventory.get(turn).add(itempicked);
        output.append(String.format("%s Decides to pick item: %s\n", turn.getName(),
            itemspresent.get(itemindex).getName()));
        currentspace.removeItem(itempicked);
      }

      return output.toString();
    });

    knownCommands.put(Integer.valueOf(0), () -> {

      int position = playerpos.get(turn);
      Space currentspace = spacelist.get(position);
      StringBuilder output = new StringBuilder();
      // Move
      List<Space> neighbors = currentspace.getNeighbours();
      int noofneighbors = neighbors.size();
      List<Integer> neighborindices = new ArrayList<Integer>();

      for (Space neighbor : neighbors) {
        neighborindices.add(neighbor.getSpaceIndex());
      }

      if (noofneighbors == 0) {
        output.append(String.format("%s Decides to do nothing...\n", turn.getName()));
      } else {

        int spaceindex;
        if (picker == null) {
          spaceindex = neighborindices.get(psuedorand.get(1));
        } else {
          spaceindex = neighborindices.get(picker.nextInt(noofneighbors));
        }
        playerpos.put(turn, spaceindex);
        output.append(String.format("%s Decides to move to %s\n", turn.getName(),
            spacelist.get(spaceindex).getSpaceName()));

      }
      return output.toString();
    });

    knownCommands.put(Integer.valueOf(2), () -> {
      StringBuilder output = new StringBuilder();

      // Look
      output.append(String.format("%s Decides to look Around...\n", turn.getName()));

      return output.toString();
    });
  }

  /**
   * Constructor for the World object which creates from a configuration readable.
   * 
   * 
   * @param worldconfigfile the readable with world configurations
   * @param turns           the total number of turns available for the game
   * @param rand            random number generator object
   */
  public MansionWorld(Readable worldconfigfile, int turns, Random rand) {

    if (worldconfigfile == null) {
      throw new IllegalArgumentException("Name of the file cannot be null");
    }

    if (rand == null) {
      throw new IllegalArgumentException("Random number generator cannot be null");
    }

    scanFile(worldconfigfile);
    characterpos = new HashMap<Character, Integer>();
    characterpos.put(drlucky, 0);
    inferNeighbours();
    assignItemsToRooms();
    turnsleft = turns;
    turn = null;
    characterlist = new ArrayList<>();
    characterlist.add(drlucky);
    playerlist = new ArrayList<PlayerInterface>();
    playerinventory = new HashMap<PlayerInterface, List<Item>>();
    playerpos = new HashMap<PlayerInterface, Integer>();
    picker = rand;
    evidences = new ArrayList<>();
    petpos = 0;
    totalturns = turns;
    winner = "";

    knownCommands = new HashMap<>();
    knownCommands.put(Integer.valueOf(1), () -> {
      StringBuilder output = new StringBuilder();

      int position = playerpos.get(turn);
      Space currentspace = spacelist.get(position);

      // Pick
      List<Item> itemspresent = currentspace.getItems();
      int noofitems = itemspresent.size();

      if (noofitems == 0) {
        output.append(String.format("%s Decides to do nothing...\n", turn.getName()));
      } else {

        int itemindex;
        if (picker == null) {
          itemindex = psuedorand.get(1);
        } else {
          itemindex = picker.nextInt(noofitems);
        }
        Item itempicked = itemspresent.get(itemindex);
        playerinventory.get(turn).add(itempicked);
        output.append(String.format("%s Decides to pick item: %s\n", turn.getName(),
            itemspresent.get(itemindex).getName()));
        currentspace.removeItem(itempicked);
      }

      return output.toString();
    });

    knownCommands.put(Integer.valueOf(0), () -> {

      int position = playerpos.get(turn);
      Space currentspace = spacelist.get(position);
      StringBuilder output = new StringBuilder();
      // Move
      List<Space> neighbors = currentspace.getNeighbours();
      int noofneighbors = neighbors.size();
      List<Integer> neighborindices = new ArrayList<Integer>();

      for (Space neighbor : neighbors) {
        neighborindices.add(neighbor.getSpaceIndex());
      }

      if (noofneighbors == 0) {
        output.append(String.format("%s Decides to do nothing...\n", turn.getName()));
      } else {

        int spaceindex;
        if (picker == null) {
          spaceindex = neighborindices.get(psuedorand.get(1));
        } else {
          spaceindex = neighborindices.get(picker.nextInt(noofneighbors));
        }
        playerpos.put(turn, spaceindex);
        output.append(String.format("%s Decides to move to %s\n", turn.getName(),
            spacelist.get(spaceindex).getSpaceName()));

      }
      return output.toString();
    });

    knownCommands.put(Integer.valueOf(2), () -> {
      StringBuilder output = new StringBuilder();

      // Look
      output.append(String.format("%s Decides to look Around...\n", turn.getName()));

      return output.toString();
    });

  }

  private void assignItemsToRooms() {
    Space space;
    for (Item item : itemlist) {
      space = spacelist.get(item.getRoomNo());
      space.addItem(item);
    }
  }

  private void inferNeighbours() {
    for (int i = 0; i < spacelist.size(); i++) {
      for (int j = 0; j < spacelist.size(); j++) {

        if (i == j) {
          // They are the same space so we skip it
          continue;
        }
        // Coordinates of the form ((X1,Y1),(X2,Y2))
        Coordinates space1upper = spacelist.get(i).getCoordinates().get(0);
        Coordinates space1lower = spacelist.get(i).getCoordinates().get(1);

        Coordinates space2upper = spacelist.get(j).getCoordinates().get(0);
        Coordinates space2lower = spacelist.get(j).getCoordinates().get(1);

        // THe following calculation is for determining neighboring space above and
        // below the room

        // Calculating y constraints
        int differencey1 = space1lower.getY() - space2upper.getY();
        int differencey2 = space2lower.getY() - space1upper.getY();

        // Calculating x constraints
        int differencexbelow = Math.abs(space2lower.getX() - space1upper.getX());
        int differencexabove = Math.abs(space1lower.getX() - space2upper.getX());
        if ((differencey1 >= 0) && (differencey2 >= 0)
            && (differencexabove == 1 || differencexbelow == 1)) {
          spacelist.get(i).addNeighbour(spacelist.get(j));
        }

        // The following calculation is for determining neighboring space one the sides
        // of the room

        // Calculating y constraints
        int differencex1 = space1lower.getX() - space2upper.getX();
        int differencex2 = space2lower.getX() - space1upper.getX();

        // Calculating x constraints
        int differenceybelow = Math.abs(space2lower.getY() - space1upper.getY());
        int differenceyabove = Math.abs(space1lower.getY() - space2upper.getY());
        if ((differencex1 >= 0) && (differencex2 >= 0)
            && (differenceyabove == 1 || differenceybelow == 1)) {
          spacelist.get(i).addNeighbour(spacelist.get(j));
        }

      }
    }
  }

  /**
   * Changes the turn of the player to play.
   * 
   * Also reduces the total no of turns left to play and moves Dr Lucky
   * 
   * @return The string of outputs from bots play
   */
  private String useTurn() {
    StringBuilder output = new StringBuilder();

    turnsleft -= 1;

    if (isGameOver()) {
      return output.toString();
    }

    int noofplayers = playerlist.size();
    int currentplayerno = playerlist.indexOf(turn);
    int nextplayerno = (currentplayerno + 1) % noofplayers;

    turn = playerlist.get(nextplayerno);
    moveCharacter();

    if (turn.isBot()) {
      output.append(playBot());
    }

    return output.toString();
  }

  /**
   * Moves the computer controlled player choosing an action randomly.
   * 
   * @return The string which tells you what the computer player did
   */
  private String playBot() {
    PlayerInterface bot = turn;
    StringBuilder output = new StringBuilder();

    int playerloc = playerpos.get(turn);
    boolean seen = false;
    // Check if seen by other players in the current room
    for (PlayerInterface player : playerpos.keySet()) {

      if (player == turn) {
        continue;
      }

      if (playerpos.get(player) == playerloc) {
        seen = true;
      }

    }
    // If pet is not in the current room it can be seen by neighbors
    if (playerloc != petpos) {
      // Check if seen by other players in the neighboring room
      Space spacefound = spacelist.get(playerloc);
      List<Space> neighbors = spacefound.getNeighbours();

      if (neighbors.size() != 0) {

        for (Space neighbor : neighbors) {

          for (PlayerInterface player : playerpos.keySet()) {
            if (playerpos.get(player) == neighbor.getSpaceIndex()) {
              seen = true;
            }
          }
        }

      }
    }
    // Always attack first if the target is in the room and attack is not seen
    if (playerloc == characterpos.get(drlucky) && !seen) {
      Item weapon = null;
      int maxdamage = 0;

      for (Item item : playerinventory.get(turn)) {
        if (item.getDamage() > maxdamage) {
          weapon = item;
          maxdamage = item.getDamage();
        }
      }
      int dmg = 0;

      if (weapon != null) {
        output.append(String.format("%s Decides to attack the target using %s\n", bot.getName(),
            weapon.getName()));
        dmg = weapon.getDamage();
        output.append(addEvidence(weapon));

      } else {

        output.append(
            String.format("%s Decides to attack the target by poking the eye\n", bot.getName()));
        dmg = 1;
      }

      int prevhealth = drlucky.getHealth();

      drlucky.healthUpdate(-dmg);
      output.append(String.format("Doctor Lucky's health has dropped" + " from %d to %d\n",
          prevhealth, drlucky.getHealth()));

      if (isGameOver()) {
        output.append(String.format("Player: %s has killed Doctor Lucky!\n", turn.getName()));
        winner = turn.getName();
        return output.toString();
      }

      output.append(useTurn());

      return output.toString();
    }

    // Choosing one of three choices
    int choice;

    if (picker == null) {
      choice = psuedorand.get(0);
    } else {
      choice = picker.nextInt(3);
    }

    output.append(knownCommands.get(choice).get());

    output.append(useTurn());

    return output.toString();
  }

  /**
   * This moves the target character - Dr. Lucky around the world.
   */
  private void moveCharacter() {

    int position = characterpos.get(drlucky);

    if ((position + 1) > (noofspaces - 1)) {
      position = 0;
    } else {
      position++;
    }

    characterpos.put(drlucky, position);
  }

  /**
   * Shows the information of a seen neighbor.
   * 
   * This is different from the normal Display Space function in the sense we can
   * only see the items and players in this space
   * 
   * @param spacename the name of the "seen" neighbor
   * @return the string information of whatever is seen in the room
   */
  private String displaySeenSpaceInformation(String spacename) {

    StringBuilder output = new StringBuilder();

    if (spacename == null) {
      throw new IllegalArgumentException("The name of the space cannot be null");
    }

    Space spacefound = getSpace(spacename);
    if (spacefound != null) {
      output.append(String.format("Space Name: %s\n", spacefound.getSpaceName()));
      output.append(String.format("Items Present in this room: "));

      List<Item> items = spacefound.getItems();

      if (items.size() == 0) {
        output.append("None");
      } else {
        for (Item item : items) {
          output.append(String.format("%s, ", item.getName()));
        }
      }

      output.append("\n");

      output.append("People in this room: ");
      boolean personpresent = false;

      for (Character character : characterpos.keySet()) {
        if (characterpos.get(character) == spacefound.getSpaceIndex()) {
          personpresent = true;
          output.append(String.format("%s, ", character.getName()));
        }
      }

      if (spacefound.equals(spacelist.get(petpos))) {
        output.append(String.format("%s, ", drlucky.getPetName()));
      }

      for (PlayerInterface player : playerpos.keySet()) {
        if (playerpos.get(player) == spacefound.getSpaceIndex()) {
          output.append(String.format("%s, ", player.getName()));
          personpresent = true;
        }
      }

      if (!personpresent) {
        output.append("None");
      }

      output.append("\n");

    } else {
      output.append(String.format("The space %s was not found in the mansion\n", spacename));
    }

    return output.toString();
  }

  /**
   * Adds the used item to the list of evidences.
   * 
   * This removes it from the users inventory. A null item is valid it means the
   * item is the hand used to poke the eye
   * 
   * @return the output of the operation
   */
  private String addEvidence(Item item) {

    StringBuilder output = new StringBuilder();

    if (item != null) {
      playerinventory.get(turn).remove(item);
      evidences.add(item);
      output.append(String.format(
          "Item %s has removed from inventory " + "and added to evidences\n", item.getName()));
    }

    return output.toString();

  }

  @Override
  public String getPlayerInfo() {

    if (turn == null) {
      return "Current Player: --";
    }

    StringBuilder output = new StringBuilder();
    List<Item> inventory = playerinventory.get(turn);

    output.append(String.format("Player Name: %s\n", turn.getName()));

    output.append("Player Inventory: ");

    if (inventory.size() == 0) {
      output.append("EMPTY\n");
    } else {

      for (Item item : inventory) {
        output.append(String.format("%s ", item.getName()));
      }

    }
    String roomname = spacelist.get(playerpos.get(turn)).getSpaceName();
    output.append("\n");
    output.append(String.format("Room Name : %s\n", roomname));
    output.append("\n");

    return output.toString();
  }

  @Override
  public boolean isGameOver() {

    boolean gameover = false;

    if (turnsleft == 0) {
      gameover = true;
    } else if (drlucky.getHealth() <= 0) {
      gameover = true;
    }

    return gameover;
  }

  @Override
  public String toString() {
    return String.format("World %s of length: %d and width: %d", worldname, length, width);
  }

  @Override
  public String movePlayer(String spacename) {

    if (turn == null) {
      return "Please create a player first\n";
    }

    StringBuilder output = new StringBuilder();

    if (spacename == null) {
      throw new IllegalArgumentException("The spacename cannot be null");
    }

    int position = playerpos.get(turn);
    Space currentspace = spacelist.get(position);
    List<Space> neighbors = currentspace.getNeighbours();
    boolean isneighbor = false;
    Space nextspace = null;

    for (Space neighbor : neighbors) {
      if (neighbor.getSpaceName().equals(spacename)) {
        isneighbor = true;
        nextspace = neighbor;
        break;
      }
    }

    if (!isneighbor) {
      return "You cannot move into a non-neighboring space \n";
    } else {

      playerpos.put(turn, nextspace.getSpaceIndex());
      output.append(String.format("Player has moved into %s\n", spacename));
      output.append(useTurn());
    }

    return output.toString();
  }

  @Override
  public String getCurrentPlayer() {
    if (turn == null) {
      return "--";
    } else {
      return turn.getName();
    }
  }

  @Override
  public Space getSpace(String spacename) {

    if (spacename == null) {
      throw new IllegalArgumentException("The name of the space cannot be null");
    }

    for (Space space : spacelist) {
      if (space.getSpaceName().equals(spacename)) {
        return space;
      }
    }
    return null;
  }

  @Override
  public String displaySpaceInformation() {

    if (turn == null) {
      return "";
    }

    String spacename = spacelist.get(playerpos.get(turn)).getSpaceName();

    StringBuilder output = new StringBuilder();

    Space spacefound = getSpace(spacename);
    if (spacefound != null) {
      output.append(String.format("You are in the %s\n", spacefound.getSpaceName()));
      output.append(String.format("Items Present in this room: "));

      List<Item> items = spacefound.getItems();

      if (items.size() == 0) {
        output.append("None");
      } else {
        for (Item item : items) {
          output.append(String.format("%s, ", item.getName()));
        }
      }

      output.append("\n");

      output.append("Spaces seen from this room: ");
      List<Space> neighbors = spacefound.getNeighbours();

      if (neighbors.size() == 0) {
        output.append("None");
      } else {
        for (Space neighbor : neighbors) {
          if (neighbor == spacelist.get(petpos)) {
            continue;
          }
          output.append(String.format("%s, ", neighbor.getSpaceName()));
        }
      }

      output.append("\n");

      output.append("People in this room: ");
      boolean personpresent = false;

      for (Character character : characterpos.keySet()) {
        if (characterpos.get(character) == spacefound.getSpaceIndex()) {
          personpresent = true;
          output.append(String.format("%s, ", character.getName()));
        }
      }

      if (spacefound.equals(spacelist.get(petpos))) {
        output.append(String.format("%s, ", drlucky.getPetName()));
      }

      for (PlayerInterface player : playerpos.keySet()) {
        if (playerpos.get(player) == spacefound.getSpaceIndex()) {
          output.append(String.format("%s, ", player.getName()));
          personpresent = true;
        }
      }

      if (!personpresent) {
        output.append("None");
      }

      output.append("\n");

      output.append(String.format("Target Character at: %s\n",
          spacelist.get(characterpos.get(drlucky)).getSpaceName()));

    } else {
      output.append(String.format("The space %s was not found in the mansion\n", spacename));
    }

    return output.toString();
  }

  @Override
  public String lookAround() {

    if (turn == null) {
      return "Please create a player first\n";
    }

    StringBuilder output = new StringBuilder();

    int position = playerpos.get(turn);
    Space currentspace = spacelist.get(position);

    output.append(
        String.format("Current %s\n", displaySeenSpaceInformation(currentspace.getSpaceName())));

    output.append("Neighboring rooms:\n");

    for (Space neighbor : currentspace.getNeighbours()) {

      if (neighbor.equals(spacelist.get(petpos))) {
        continue;
      }
      output.append(String.format("%s\n", displaySeenSpaceInformation(neighbor.getSpaceName())));
    }

    output.append(useTurn());

    return output.toString();
  }

  @Override
  public String scanFile(Readable config) {

    if (config == null) {
      throw new IllegalArgumentException("The world specification input cannot be null");
    }

    StringBuilder output = new StringBuilder();

    worldname = "";
    length = 0;
    width = 0;
    noofspaces = 0;
    noofweapons = 0;
    this.itemlist = new ArrayList<>();
    this.spacelist = new ArrayList<>();
    this.drlucky = null;
    characterpos = new HashMap<Character, Integer>();

    Scanner scnr1;

    scnr1 = new Scanner(config);

    String firstline = scnr1.nextLine().trim();

    Pattern pattern2 = Pattern.compile(".txt");
    Matcher fileornot = pattern2.matcher(firstline);
    scnr1.close();

    File f = null;
    Scanner scnr = null;

    try {

      if (fileornot.find()) {

        f = new File(firstline);
        scnr = new Scanner(f);

      } else {
        scnr = new Scanner(firstline);
      }

      // Reading each line of the file using Scanner class
      int linenumber = 1;

      while (scnr.hasNextLine()) {
        if (linenumber == 1) {

          // World Description lines
          try {
            length = scnr.nextInt();
            width = scnr.nextInt();
            worldname = scnr.nextLine().trim();

          } catch (InputMismatchException e) {

            throw new IllegalStateException("Invalid world description specification");
          }

          output.append(
              String.format("World Specifications \nlength: %d\nWidth: " + "%d\nWorld Name: %s\n",
                  length, width, worldname));

        } else if (linenumber == 2) {
          // Character Description Lines
          String name;
          int health;
          Animal animal;
          try {
            health = scnr.nextInt();
            name = scnr.nextLine().trim();
            animal = new Pet(scnr.nextLine().trim());
            drlucky = new DrLucky(name, health, animal);
          } catch (InputMismatchException e) {
            throw new IllegalStateException("Invalid character description specification");
          }

        } else {

          // Scanning for rooms

          noofspaces = scnr.nextInt();
          scnr.nextLine();

          Coordinates uppercoords;
          Coordinates lowercoords;

          for (int i = 0; i < noofspaces; i++) {
            uppercoords = new Coordinates2D(scnr.nextInt(), scnr.nextInt());
            lowercoords = new Coordinates2D(scnr.nextInt(), scnr.nextInt());
            spacelist.add(new MansionSpace(i, scnr.nextLine().trim(), uppercoords, lowercoords,
                new ArrayList<Item>(), new ArrayList<Space>()));
          }

          // Scanning for weapons
          noofweapons = scnr.nextInt();
          scnr.nextLine();

          Item temp;
          String wname;
          int dmg;
          int loc;

          for (int i = 0; i < noofweapons; i++) {
            loc = scnr.nextInt();
            dmg = scnr.nextInt();
            wname = scnr.nextLine().trim();
            temp = new Weapon(i, wname, dmg, loc);
            itemlist.add(temp);
          }
        }

        linenumber += 1;
      }

    } catch (FileNotFoundException e) {

      throw new IllegalStateException("File Provided was not found, world could not be created");

    } finally {
      // Closing the scanner after the exception is thrown
      if (scnr != null) {
        scnr.close();
      }
    }

    return output.toString();
  }

  @Override
  public String pickItem(String itemname) {

    if (turn == null) {
      return "Please create a player first\n";
    }

    StringBuilder output = new StringBuilder();

    if (itemname == null) {
      throw new IllegalArgumentException("The name of the item cannot be null");
    }

    int position = playerpos.get(turn);
    Space currentspace = spacelist.get(position);
    boolean found = false;
    Item itemfound = null;

    for (Item item : currentspace.getItems()) {
      if (item.getName().equals(itemname)) {
        found = true;
        itemfound = item;
        break;
      }
    }

    if (found) {
      int noofitems = playerinventory.get(turn).size();

      if ((noofitems + 1) > turn.getInventorySize()) {
        output.append("Inventory Full!\n");
      } else {
        currentspace.removeItem(itemfound);
        playerinventory.get(turn).add(itemfound);
        output.append(String.format("Item %s has been added to your inventory\n", itemname));
      }

      // Only change turn if the item search was successful
      output.append(useTurn());

    } else {
      output.append(String.format("Item %s was not found in this space\n", itemname));
    }

    return output.toString();
  }

  @Override
  public String createPlayer(String playername, String location, int size, boolean bot) {

    StringBuilder output = new StringBuilder();

    if (playername == null || "".equals(playername)) {
      throw new IllegalArgumentException("The name of the player cannot be null");
    }

    if (location == null) {
      throw new IllegalArgumentException("The name of the location cannot be null");
    }

    Space spacefound = getSpace(location);
    int loc;

    if (spacefound != null) {
      loc = spacefound.getSpaceIndex();
    } else {

      return "Space " + location + " was not found\n";
    }
    PlayerInterface newplayer;
    if (!bot) {
      newplayer = new Player(playername, size, false);
      playerinventory.put(newplayer, new ArrayList<Item>());
      playerpos.put(newplayer, loc);
      playerlist.add(newplayer);
      output.append(String.format("Player %s was created succesfully\n", playername));
    } else {

      if (playerlist.size() == 0) {
        output.append("Please create a human player first\n");
        return output.toString();
      }

      newplayer = new Player(playername, size, true);
      playerinventory.put(newplayer, new ArrayList<Item>());
      playerpos.put(newplayer, loc);
      playerlist.add(newplayer);
      output.append(String.format("Player %s was created succesfully\n", playername));
    }

    if (playerlist.size() == 1) {
      // If this is the first player being created we need to set the turn to this
      // player
      turn = newplayer;
    }

    return output.toString();
  }

  @Override
  public String movePet(String spacename) {

    if (turn == null) {
      return "Please create a player first\n";
    }

    StringBuilder output = new StringBuilder();

    if (spacename == null) {
      throw new IllegalArgumentException("The name of the space cannot be null");
    }

    Space spacefound = getSpace(spacename);
    if (spacefound != null) {

      petpos = spacefound.getSpaceIndex();

      output.append(String.format("You have moved the pet into %s\n", spacename));

    } else {
      output.append(String.format(
          "The space %s to move the pet into " + "was not found in the mansion\n", spacename));
    }

    output.append(useTurn());

    return output.toString();
  }

  @Override
  public String attackTarget(String itemname) {

    if (turn == null) {
      return "Please create a player first\n";
    }

    if (itemname == null) {
      throw new IllegalArgumentException("The name of the item cannot be null");
    }

    Item item = null;
    for (Item itemm : playerinventory.get(turn)) {
      if (itemm.getName().equals(itemname)) {
        item = itemm;
      }
    }

    StringBuilder output = new StringBuilder();

    int dmg = 0;
    if ("finger".equals(itemname.toLowerCase())) {
      dmg = 1;

    } else if (item == null) {
      output.append(String.format("You do not have the item: %s\n", itemname));
      output.append("Using fingers to poke eye instead\n");
      dmg = 1;

    } else {
      dmg = item.getDamage();
    }

    int playerloc = playerpos.get(turn);

    if (playerloc != characterpos.get(drlucky)) {
      output.append("ATTEMPT FAILED: Character not in this room!\n");

      if (!"finger".equals(itemname.toLowerCase())) {
        output.append(addEvidence(item));
      }

      output.append(useTurn());
      return output.toString();
    }

    for (PlayerInterface player : playerpos.keySet()) {

      if (player == turn) {
        continue;
      }

      if (playerpos.get(player) == playerloc) {
        output.append("ATTEMPT FAILED: Seen by other player in the room!\n");

        if (!"finger".equals(itemname.toLowerCase())) {
          output.append(addEvidence(item));
        }

        output.append(useTurn());
        return output.toString();
      }

    }

    // If pet is not in the current room it can be seen by neighbors
    if (playerloc != petpos) {

      Space spacefound = spacelist.get(playerloc);
      List<Space> neighbors = spacefound.getNeighbours();

      if (neighbors.size() != 0) {

        for (Space neighbor : neighbors) {

          for (PlayerInterface player : playerpos.keySet()) {

            if (playerpos.get(player) == neighbor.getSpaceIndex()) {

              output.append("ATTEMPT FAILED: Seen by a player in the next room!\n");

              if (!"finger".equals(itemname.toLowerCase())) {
                output.append(addEvidence(item));
              }
              output.append(useTurn());
              return output.toString();

            }
          }

        }

      }
    }

    int prevhealth = drlucky.getHealth();

    output.append("ATTEMPT SUCCESS: Doctor Lucky has been hurt\n");
    drlucky.healthUpdate(-dmg);
    output.append(String.format("Doctor Lucky's health has dropped" + " from %d to %d\n",
        prevhealth, drlucky.getHealth()));

    if (!"finger".equals(itemname.toLowerCase())) {
      output.append(addEvidence(item));
    }

    if (isGameOver()) {
      output.append(String.format("Player: %s has killed Doctor Lucky!\n", turn.getName()));
      winner = turn.getName();
      return output.toString();
    }

    output.append(useTurn());

    return output.toString();
  }

  @Override
  public String getWinner() {
    return winner;
  }

  @Override
  public List<String> getAllSpaceNames() {
    List<String> spacenames = new ArrayList<String>();
    for (Space sp : spacelist) {
      spacenames.add(sp.getSpaceName());
    }
    return spacenames;
  }

  @Override
  public List<String> getAllPlayerItems() {

    List<String> playeritems = new ArrayList<String>();

    if (turn == null) {
      playeritems.add("finger");
      return playeritems;
    }

    for (int i = 0; i < playerinventory.get(turn).size(); i++) {
      playeritems.add(playerinventory.get(turn).get(i).getName());
    }

    playeritems.add("finger");
    return playeritems;

  }

  @Override
  public List<String> getAllSpaceItems() {
    List<String> spaceitems = new ArrayList<String>();
    if (playerpos.size() == 0) {
      throw new IllegalArgumentException("Please add players first");
    }
    String spacename = spacelist.get(playerpos.get(turn)).getSpaceName();
    Space spacefound = getSpace(spacename);

    for (int i = 0; i < spacefound.getAllSpaceItems().size(); i++) {
      spaceitems.add(spacefound.getAllSpaceItems().get(i));
    }
    if (spaceitems.size() == 0) {
      throw new IllegalArgumentException("Sorry no items left to pick");

    }

    return spaceitems;
  }

  @Override
  public List<Space> getAllSpaces() {

    List<Space> copySpace = new ArrayList<Space>();
    for (Space sp : spacelist) {
      copySpace.add(sp);
    }

    return copySpace;
  }

  @Override
  public List<String> getPlayersInSpace(Space space) {
    if (space == null) {
      throw new IllegalArgumentException("The space cannot be null");
    }
    List<String> people = new ArrayList<>();

    for (Character character : characterpos.keySet()) {
      if (characterpos.get(character) == space.getSpaceIndex()) {
        people.add(character.getName());
      }
    }

    for (PlayerInterface player : playerpos.keySet()) {

      if (playerpos.get(turn) == space.getSpaceIndex()) {
        // Everyone in the current space is visible in current player's room
        if (playerpos.get(player) == space.getSpaceIndex()) {
          people.add(player.getName());
        }

      } else if (petpos != space.getSpaceIndex()) {
        // Only visible if the pet is not in the current space
        if (playerpos.get(player) == space.getSpaceIndex()) {
          people.add(player.getName());
        }

      }
    }

    return people;
  }

  @Override
  public String getSpaceFromCoordinates(Coordinates c) {
    if (c == null) {
      throw new IllegalArgumentException("The coordaintes for the space cannot be null");
    }
    String spacefound = "";

    for (Space space : spacelist) {
      if ((c.getX() >= space.getCoordinates().get(0).getX()
          && c.getX() <= space.getCoordinates().get(1).getX())
          && (c.getY() >= space.getCoordinates().get(0).getY()
              && c.getY() <= space.getCoordinates().get(1).getY())) {
        spacefound = space.getSpaceName();
        break;
      }
    }
    return spacefound;
  }

  @Override
  public void resetWorld(Readable newfile) {

    if (newfile == null) {
      throw new IllegalArgumentException("The world file cannot be null");
    }
    scanFile(newfile);
    characterpos = new HashMap<Character, Integer>();
    characterpos.put(drlucky, 0);
    inferNeighbours();
    assignItemsToRooms();
    turnsleft = totalturns;
    turn = null;
    characterlist = new ArrayList<>();
    characterlist.add(drlucky);
    playerlist = new ArrayList<PlayerInterface>();
    playerinventory = new HashMap<PlayerInterface, List<Item>>();
    playerpos = new HashMap<PlayerInterface, Integer>();
    picker = new Random();
    evidences = new ArrayList<>();
    petpos = 0;
    winner = "";

  }

  @Override
  public BufferedImage getGraphicalRepresentation() {

    int width = 1000;
    int height = 1000;
    int scale = 20;
    int padding = 20;

    int heightdisp = 6;
    int widthdisp = 5;
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = bufferedImage.createGraphics();
    g2d.setStroke(new BasicStroke(3.0f));

    // Fill the background with white
    g2d.setColor(Color.white);
    g2d.fillRect(0, 0, width, height);

    for (Space space : spacelist) {
      g2d.setColor(Color.red);
      Coordinates space1upper = space.getCoordinates().get(0);
      Coordinates space1lower = space.getCoordinates().get(1);

      int roomwidth = space1lower.getX() - space1upper.getX();
      int roomlength = space1lower.getY() - space1upper.getY();

      g2d.drawRect(width / widthdisp + space1upper.getY() * scale,
          height / heightdisp + space1upper.getX() * scale, roomlength * scale + padding,
          roomwidth * scale + padding);

      g2d.setColor(Color.black);
      g2d.drawString(space.getSpaceName(),
          width / widthdisp + space1upper.getY() * scale + roomlength * scale / 6,
          height / heightdisp + space1upper.getX() * scale + roomwidth * scale);
    }

    return bufferedImage;

  }

  @Override
  public String gameOverStatus() {
    if (isGameOver()) {

      if (!getWinner().equals("")) {
        return String.format("Player : %s has killed dr lucky", getWinner());
      } else {
        return String.format("Dr LUCKY ESCAPES YOU LOOSE HAHA");
      }

    }

    return "";

  }

}
