package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A class that is used to display the game to the user. Based on the user
 * action it calls the controller. It implements the View Interface.
 * 
 * @author renuchava
 *
 */
public class ViewImpl extends JFrame implements View {

  private static final long serialVersionUID = -4858151456770909928L;
  private ViewModel readonlymodel;
  private JPanel mainpanel;
  private JPanel panel;
  private JPanel inputpanel;
  private JMenuBar menuBar;
  private JMenu menu;
  private JButton addButton;
  private JLabel details;

  private JTextField playername;

  private JLabel playername1;
  private JLabel roomName1;

  private JRadioButton human;
  private JRadioButton computer;

  private JTextArea area;

  private JLabel header;
  private JScrollPane scrollpanel;

  private JLabel label1;
  private JLabel label2;
  private JLabel label3;
  private JLabel label4;
  private JLabel label5;
  private JLabel label6;
  private JLabel label7;
  private JLabel picLabel;
  private ButtonGroup jbg;
  private JComboBox<String> dropbox;
  private JMenuItem start;
  private JMenuItem own;
  private JMenuItem exit;
  private int width;
  private int height;
  private int scale;
  private int padding;
  private int heightdisp;
  private int widthdisp;
  private int offset1;
  private int offset2;

  /**
   * The constructor of the viewimpl class.
   * 
   * @param m the read only model's{@link VIewModel} object that is being passed
   *          to the view.
   */
  public ViewImpl(ViewModel m) {
    super("Dr Lucky's Mansion");

    if (m == null) {
      throw new IllegalArgumentException("model or the controller can not be null");
    }

    this.readonlymodel = m;
    mainpanel = new JPanel();
    mainpanel.setPreferredSize(new Dimension(300, 300));
    setTitle("Kill Doctor Lucky");

    setPreferredSize(new Dimension(1000, 1000));
    setLocation(0, 0);
    header = new JLabel(new ImageIcon("./res/kdl.png"));

    header.setVisible(true);
    mainpanel.setBackground(Color.CYAN);

    mainpanel.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 10.0;
    c.weighty = 10.0;

    picLabel = new JLabel("WELCOME TO DR LUCKY'S MANSION");
    picLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 32));

    c.gridx = 2;
    c.anchor = GridBagConstraints.CENTER;
    c.gridy = 0;
    c.insets = new Insets(10, 10, 10, 10);
    mainpanel.add(picLabel, c);
    details = new JLabel("JAISON JOHN & RENUSREE CHAVA");
    details.setFont(new Font(Font.SERIF, Font.ITALIC, 24));
    c.gridx = 2;
    c.anchor = GridBagConstraints.CENTER;
    c.gridy = 2;
    mainpanel.add(details, c);

    c.gridx = 1;
    c.gridy = 1;

    c.gridwidth = 2;
    mainpanel.add(header, c);

    mainpanel.setVisible(true);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.add(mainpanel);

    panel = new WorldPanel();

    panel.setBackground(Color.WHITE);

    inputpanel = new JPanel();

    inputpanel.setPreferredSize(new Dimension(400, 100));
    inputpanel.setBackground(Color.WHITE);
    area = new JTextArea();

    area.setEditable(false);

    area.setWrapStyleWord(true);
    area.setLineWrap(true);
    inputpanel.add(area);
    area.setPreferredSize(new Dimension(400, 70));

    area.setVisible(true);
    addButton = new JButton("ADD PLAYER");

    playername1 = new JLabel("Player Name:");
    playername = new JTextField(20);
    playername.setSize(new Dimension(10, 5));
    playername1.setLabelFor(playername);

    playername1.setVisible(false);
    playername.setVisible(false);
    roomName1 = new JLabel("Room Name:");

    playername.setVisible(true);
    playername1.setVisible(true);
    roomName1.setVisible(true);

    jbg = new ButtonGroup();
    human = new JRadioButton("HUMAN");
    computer = new JRadioButton("COMPUTER");
    human.setVisible(true);
    computer.setVisible(true);
    jbg.add(human);
    jbg.add(computer);
    label1 = new JLabel("WELCOME TO THE GAME");
    label1.setPreferredSize(new Dimension(400, 15));
    inputpanel.add(label1);
    label1.setVisible(true);
    label2 = new JLabel("PRESS p TO PICK ITEM");
    label2.setPreferredSize(new Dimension(400, 15));
    inputpanel.add(label2);
    label2.setVisible(true);
    label3 = new JLabel("PRESS l TO LOOK AROUND");
    label3.setPreferredSize(new Dimension(400, 15));
    inputpanel.add(label3);
    label3.setVisible(true);
    label4 = new JLabel("PRESS a TO ATTACK TARGET");
    label4.setPreferredSize(new Dimension(400, 15));
    inputpanel.add(label4);
    label4.setVisible(true);
    label5 = new JLabel("PRESS m TO MOVE PET");
    label5.setPreferredSize(new Dimension(400, 15));
    inputpanel.add(label5);
    label5.setVisible(true);
    label6 = new JLabel("CLICK ON THE PLAYER FOR DETAILS");
    label6.setPreferredSize(new Dimension(400, 15));
    inputpanel.add(label6);
    label6.setVisible(true);
    label7 = new JLabel("CLICK ON THE IN GRAPH TO MOVE PLAYER");
    label7.setPreferredSize(new Dimension(400, 15));
    inputpanel.add(label7);
    label7.setVisible(true);
    dropbox = new JComboBox<String>();
    inputpanel.add(addButton);
    start = new JMenuItem("START GAME");
    own = new JMenuItem("CREATE GAME");
    exit = new JMenuItem("EXIT GAME");
    panel.setVisible(true);
    inputpanel.setVisible(true);
    this.setJMenuBar(createMenuBar());

    menu.add(start);
    menu.add(own);
    menu.add(exit);

    scrollpanel = new JScrollPane(panel);
    panel.setAutoscrolls(true);

    width = 1000;
    height = 1000;
    scale = 20;
    padding = 20;
    heightdisp = 6;
    widthdisp = 5;
    offset1 = 0;
    offset2 = 0;
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.pack();

  }

  private class WorldPanel extends JPanel {
    private static final long serialVersionUID = -5827583645252834301L;

    @Override
    protected void paintComponent(Graphics g) {
      if (g == null) {
        throw new IllegalArgumentException("Graphics g  can not be null");
      }
      super.paintComponent(g);
      drawWorld(g);
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(1000, 1000);
    }
  }

  /**
   * Accept the set of callbacks from the controller, and hook up as needed to
   * various things in this view.
   * 
   * @param f the set of feature callbacks as a Features object
   */
  @Override
  public void setFeatures(Features f) {
    if (f == null) {
      throw new IllegalArgumentException("The features can not be null");
    }
    panel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {

        // String position is bottom left
        boolean playernamespot = (e.getX() >= offset1 && e.getX() <= offset1 + 30)
            && (e.getY() >= offset2 - 10 && e.getY() <= offset2);

        if (playernamespot) {
          f.getPlayerDetails();
          resetFocus();
        } else {
          int absx = (e.getY() - (width / widthdisp)) / scale + 1;
          int absy = (e.getX() - (height / heightdisp)) / scale - 2;
          f.movePlayer(absx, absy);
          resetFocus();
        }

      }
    });
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        f.exitProgram();
        resetFocus();
      }
    });
    start.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        f.loadWorld();
        resetFocus();
      }
    });
    own.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        f.loadNewWorld();
        resetFocus();
      }
    });

    this.addKeyListener(new KeyAdapter() {

      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'l') {
          f.lookAround();
          resetFocus();
        } else if (e.getKeyChar() == 'm') {
          f.movePet();
          resetFocus();
        } else if (e.getKeyChar() == 'p') {

          f.pickItem();
          resetFocus();
        } else if (e.getKeyChar() == 'a') {
          f.attack();

          resetFocus();
        }
      }
    });

    addButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        f.addPlayer();

        resetFocus();
      }
    });

  }

  @Override
  public void showView() {
    this.setVisible(true);
  }

  private JMenuBar createMenuBar() {

    menuBar = new JMenuBar();

    menu = new JMenu("MENU");
    menuBar.add(menu);

    return menuBar;
  }

  private void drawWorld(Graphics g) {
    if (g == null) {
      throw new IllegalArgumentException("Graphics object cannot be null");
    }
    Graphics2D g2d = (Graphics2D) g;
    BufferedImage image = readonlymodel.getGraphicalRepresentation();

    g2d.drawImage(image, 0, 0, this);
    for (Space space : readonlymodel.getAllSpaces()) {
      List<String> people = readonlymodel.getPlayersInSpace(space);
      Coordinates space1upper = space.getCoordinates().get(0);
      Coordinates space1lower = space.getCoordinates().get(1);
      int roomlength = space1lower.getY() - space1upper.getY();
      int yoffset = 15;
      for (String person : people) {
        g2d.setColor(Color.black);
        if (readonlymodel.getCurrentPlayer() == person) {
          offset1 = (width / widthdisp + space1upper.getY() * scale) + (roomlength / 2 * scale)
              + padding;
          offset2 = (height / heightdisp + space1upper.getX() * scale) + yoffset;
          person = "YOU";
        }
        g2d.drawString(person,
            (width / widthdisp + space1upper.getY() * scale) + (roomlength / 2 * scale) + padding,
            (height / heightdisp + space1upper.getX() * scale) + yoffset);
        yoffset += 10;
      }
    }
  }

  @Override
  public void showMessage(String message) {
    if ((message == null) || ("".equals(message))) {
      throw new IllegalArgumentException("the string message cannot be null or empty");
    }

    JOptionPane.showMessageDialog(panel, message, "End of turn Results", JOptionPane.PLAIN_MESSAGE);

  }

  @Override
  public void showCurrentPlayerDetails(String message) {
    if ((message == null) || ("".equals(message))) {
      throw new IllegalArgumentException("the string message cannot be null or empty");
    }

    area.setText(message);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public String showDropDown(List<String> list) {
    if (list == null) {
      throw new IllegalArgumentException("List cannot be empty");
    }
    String[] array = list.toArray(new String[0]);
    String input = (String) JOptionPane.showInputDialog(panel, "Choose one", "Select",
        JOptionPane.QUESTION_MESSAGE, null, array, array[0]);
    return input;
  }

  @Override
  public List<String> addPlayerDrop(List<String> list) {
    if (list == null) {
      throw new IllegalArgumentException("List cannot be empty");
    }
    String[] rooms = list.toArray(new String[0]);
    for (String s : rooms) {
      dropbox.addItem((String) s);
    }
    Object[] object = { playername1, playername, roomName1, dropbox, human, computer };

    JOptionPane.showConfirmDialog(panel, object, "enter all", JOptionPane.OK_CANCEL_OPTION);
    List<String> new1 = new ArrayList<String>();

    new1.add(playername.getText());
    new1.add((String) dropbox.getSelectedItem());
    String s1;
    if (computer.isSelected() == true) {
      s1 = "true";
    } else {
      s1 = "false";
    }
    new1.add(s1);
    dropbox.removeAllItems();
    playername.setText("");

    return new1;
  }

  @Override
  public void showNewView() {
    this.add(scrollpanel, BorderLayout.CENTER);

    this.add(inputpanel, BorderLayout.LINE_END);
    mainpanel.setVisible(false);
    panel.setVisible(true);
    inputpanel.setVisible(true);
    area.setText("");
    start.setEnabled(false);
  }

  @Override
  public void showErrorMessage(String error) {
    
    if ((error == null) || ("".equals(error))) {
      throw new IllegalArgumentException("the string message cannot be null or empty");
    }
    
    JOptionPane.showMessageDialog(panel, error, "ERROR ERROR ERROR", JOptionPane.ERROR_MESSAGE);
    panel.repaint();
  }

  @Override
  public void refresh() {
    panel.repaint();
  }

  @Override
  public void showSelectedPlayerDetails(String message) {

    if ((message == null) || ("".equals(message))) {
      throw new IllegalArgumentException("the string message cannot be null or empty");
    }
    JOptionPane.showMessageDialog(panel, message, "Selected Player Details",
        JOptionPane.PLAIN_MESSAGE);

  }

  @Override
  public void exitGame() {
    this.dispose();
  }

  @Override
  public void showGameEnd(String message) {
    if ((message == null) || ("".equals(message))) {
      throw new IllegalArgumentException("the string message cannot be null or empty");
    }
    JOptionPane.showMessageDialog(panel, message, "GAME OVER", JOptionPane.PLAIN_MESSAGE);

  }

  @Override
  public String getFile() {
    JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("World Files", "txt");
    fileChooser.setFileFilter(filter);
    int r = fileChooser.showOpenDialog(null);
    if (r == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    }
    return "";

  }
}
