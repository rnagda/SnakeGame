# SnakeGame
My own version of the snake game designed in Java with all sorts of new twists!

My game is the snake game. You use the arrow keys to navigate the snake and the 
more objects you eat, the more the snake grows. My Game class creates the initial 
JFrame with a black background and add the GameCanvas which is the JPanel. The 
GameCanvas is the actual game. First off, there is a map that maps an integer 
key to a snake object. A snake object consists of a x and y position and a boolean
determining whether that specific object is the head or not. The game canvas class
initially creates a map of size 3 with a head and two bodies. Then there is a paint
component in GameCanvas that carries out the actual drawing of the game. On top,
a title and a box for the score, length and name are created. The name is inputed
upon the running of the code using a JOptionPane. I learned this via the Oracle
website that explained pop-up dialog boxes. The color of the snake is also determined
via the input of the user at the start. Then I take the input String color and
convert it to type Color. My GameCanvas then carries out the actual game, looking for
a collision between a  Fruit and the snake. This is where the Fruit class comes in.
The Fruit class initially builds a 2D array that stores the possible x and y 
positions for the placement of the fruits. I had to use an array for this, as
I needed a type of structure that could hold two values only, that were not 
associated with each other like the key and value in a map. The Fruit class
has methods to return both the X and Y position of a certain block of fruit. 
Under the Fruit Class, I created subclasses of specific fruit that extend Fruit, 
each fruit having a different ability. The apple is the normal fruit, the pear
doubles the speed, and the watermelon changes the color of the snake. 
The GameCanvas randomly selects two numbers and then these are the positions in 
the array of the 2D array that selects the position of the fruit. I added a Key
Listener so that the class knows if an arrow key is pressed. Depending on 
which arrow key is pressed, the snake's position changed. Each key in the map
is given the value of the key in front of it while the head is given a new Snake 
that has moved in the direction of the key press. Then the Action Listener 
recreates the map and repaint is called to redraw the snake. There are checks in 
the paint part to see if the snake hits a wall or hits itself to end the game.

Upon the ending of the game, the score and name of the player is written to 
scores.txt and the player can press the spacebar to restart the game. 
