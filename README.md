# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Name:** Jaison John, Renusree Chava 

**Email:** john.jai@northeastern.edu
			chava.r@northeastern.edu

**Preferred Name:** Jaison, Renusree



### About/Overview

A Java twist on the popular board game Kill Dr Lucky. In this game we have a world in which the target character is named Doctor Lucky. The world consists of spaces that contains items in it. Players can move around the world and pick up the items. Players use these items to attack the target character and kill him. Whoever deals the final blow and reduces the characters health to zero before the rounds end, wins.


### List of Features
* You can now move Doctor Lucky's pet around by shooing it off to another room.
* You can now view the graph on screen when you enter the game by clicking either start game or create game.
*Start game starts the game with the file provided in the arguments
*Create game opens a file chooser where you can choose a text file and that file will be used to create the new world
*World of any size can fit on the screen.
*you can add the players to the game with the help of a input dialog where we enter the name of the player in the text field.
*room name can be selected using the drop box that has the list of roomnames
*the computer and human type can be decided based on the radio buttons
* we can click on the space wherever we want to move the player too.
* we can click on the player name to know the details of the player
* we can use the keypad to move the pet to a different room(m is pressed), pickup an item present in the room(p is pressed) and to attack the target(a to attack).
* the results of every turn is displayed on the screen after the end of every turn.
* Display information about a specified space in the world.
* Create a graphical representation of the world map and provide the ability to save the graphical representation to a file as a PNG file.
* You can add a human-controlled player to the game.
* You can add a human-controlled player to the game.
* You can move a players.
* You can pick up an item and add it to your inventory.
* You can look around to see into neighboring spaces.
* You can display the description of a specific player.
* You can attack the target character - Doctor Lucky.
### How to Run

From the res/ folder the program can be run with the JAR file using the command:

java -jar BestDrLuckyGame.jar mansion.txt 12

The number 12 represents the number of turns in the game. You can set to any integer value eg. 10, 5, 4 etc.


### How to Use the Program

This is an interactive program. You will have the menu displayed for the possible options.
You have to select if you want to start a game from previous specification or if you want to create new game. 
You can add the player using the add player button.
You can press the key 'm' to move the  pet to a different room.
You can press the key 'p' to pick an  item in the room.
You can press the key 'a' to attack the target.
You can press the key 'l' to look around.


**Note:** You will have to create at least human player first before you can use a few of the functionalities such as Move, Pick etc.

### Limitations

The instructions while playing might seem understandable by the developer (us) but may not be understandable to other users. This can however only be gauged after usage by clients.

If a user wants to restart the game they will have create a new game from the same folder.

### Assumptions

Assumptions used is that we are allowed to create players in between the game, The order of play is however still based on the order of creation. We have to create at least one human player first before you can use a few of the functionalities such as Move, Pick etc. This has been enforced via code as well.

### Example Runs

The example run can be found in the res/ folder. It is a text file containing the output from the driver function.
This demonstrates the following feature:

* Moving a character in the world
* Inferring the neighboring spaces of a room.
* Listing out the description of the room including the items present and it's neighbors

Example run for Milestone 2 is called Example 2. The example run displays:

- Adding a human-controlled player to the world
- Adding a computer-controlled player to the world
- The player moving around the world
- The player picking up an item
- The player looking around
- Taking turns between multiple players
- Displaying the description of a specific player
- Displaying information about a specific space in the world
- Creating and saving a graphical representation of the world map to the current directory
- The game ending after reaching the maximum number of turns
- Bots taking their actions randomly

There are multiple example runs for Milestone 3:

The first run called **Milestone3_ex1** shows the following:

* The target character's pet effect on the visibility of a space from neighboring spaces (People can attack even if there are players in the neighboring room, but not in the same room)
* The player moving the target character's pet
* A human-player making an attack attempt on the target character's life
* A computer-controlled player making an attack attempt on the target character's life
* Game ending with the target character escaping with his life

The second run called **Milestone3_ex2** shows the following: (For this run Dr Lucky's Health has been reduced from the original 50 to 1, to demonstrate a win)

* The target character's pet effect on the visibility of a space from neighboring spaces (You cannot see the room from which the pet is in in our case Armory isn't visible from Billiard Room even though they are neighbors)
* A human-player winning the game by successfully killing the target 

The third run called **Milestone3_ex3** shows the following: (For this run Dr Lucky's Health has been reduced from the original 50 to 1, to demonstrate a win)

* A computer-controlled player winning the game by killing the target character


### Design/Model Changes

Milestone1 : 
* Changed the Singleton implementation of Mansion World and Dr. Lucky to a normal class. 
* Created a World model which is used to integrate all the classes and to run the model.
* Driver class demonstrates the functionalities of the model

Milestone 2:

* Created a new Player Interface and a Player class for both human and computer players
* Creation of the world has been done in the world model and not using a World Creator class
* The ContollerInterface does not extend the WorldCommands anymore 
* Removed Commands which are not really functional such as Game Over command

Milestone 3:

* Decided to remove **roomno** attribute from the Pet class. The pet location will be taken care of the model since it has the responsibility of keeping track of the entities in the game.
* Added a winner attribute to the world model which stores the name of the winner also added a getter method for the same.
* The controller now uses polymorphism to execute the commands instead of a switch case
* Descriptions provided to the player are more significant and target character location is displayed on every turn 

Milestone 4:

* we seperated our main world class into two parts.
* Readonlymodel is our ViewModel which has the methods that cannot change their values.
* World is our main class model that has the other methods.
* We now have a features interface to act as a facade between the controller and the view.
* We added four new methods that were required to populate the dropboxes and the inputMessagePanes for the user to select their choice.


### Citations

Baeldung, Check If a String Contains a Substring. October 30, 2019. Accessed on: March 5, 2022. [Online]. Available: https://www.baeldung.com/java-string-contains-substring 

GeeksforGeeks, Variable Arguments (Varargs) in Java. Accessed on: March 2, 2022.[Online]. Available: https://www.geeksforgeeks.org/variable-arguments-varargs-in-java/

Baeldung, Iterate over a Map in Java. February 11, 2022. Accessed on: March 5, 2022. [Online]. Available: https://www.baeldung.com/java-iterate-map

Kiourtzoglou B, Create image file from graphics object, Java Code Geeks, November 11th, 2012. Accessed on: February 10, 2022. [Online]. Available: https://examples.javacodegeeks.com/desktop-java/imageio/create-image-file-from-graphics-object/

The swing components used,accessed on April 21
https://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html
The Layout managers,accessed on April 24, April 20,  April 17
https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
Event Listeners,accessed on April 18, April 19  April 20
https://docs.oracle.com/javase/tutorial/uiswing/events/index.html

