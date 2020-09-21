import java.util.ArrayList;

// This class is the GenericChessPiece class, the parent class for individual chess piece classes
// This class is written and managed by Cornelius Pang Sh'ng How (1171103676)

/**
 * This is an abstract class which is the parent class for individual chess piece classes.
 * The GenericChessPiece class contains generalized attributes and methods which are applicable to all of its subclasses.
 */
public abstract class GenericChessPiece{
    /**
     * The Point object which represents the coordinate of the current chess piece.
     */
    private Point coordinate; // Stores the current coordinate of the chess piece object on the chess board
    /**
     * An integer variable which represents the player team which has ownership of the current chess piece.
     */
    private int team;         //1:blue 2:red
    // Since there are only two colors for players, we can use a boolean to represent whether this chess piece belongs to either the blue or red player
    /**
     * A String[] array which stores the path of chess icon images used for both the blue and red players.
     */
    private String[] iconImage;  // 0 - blue, 1 - inverted blue, 2 - red, 3 -inverted red

    /**
     * Class constructor for the subclasses of the GenericChessPiece class. This constructor takes in three arguments - the coordinate of the current Triangle instance, the player owning the current Triangle instance, and a String[] array which stores the path of chess icon images used for both the blue and red players.
     * @param coordinate A Point object which represents the current X and Y coordinate values of a Triangle instance in the Webale chess board. 
     * @param team An integer variable which represents whether this chess piece object belongs to the red or blue player.
     * @param iconImage A String[] array which stores the path of chess icon images used for both the blue and red players.
     */
    public GenericChessPiece(Point coordinate, int team, String[] iconImage) {
        this.coordinate = coordinate;
        this.team = team;
        this.iconImage = iconImage;  // Stores the paths of the red and blue versions of the chess piece icons
    }

    /**
     * A getter method for the Point object which represents the coordinate of the current chess piece.
     * @return Point The Point object which represents the coordinate of the current chess piece.
     */
    public Point getCoordinate() {
        return this.coordinate;
    }

    /**
     * A setter method for setting the X and Y coordinate values of the current chess piece.
     * @param x X coordinate value of the current chess piece.
     * @param y Y coordinate value of the current chess piece.
     */
    public void setCoordinate(int x, int y) {
        this.coordinate.setX(x);
        this.coordinate.setY(y);
    }

    /**
     * A getter method for an integer variable which represents the player team which has ownership of the current chess piece.
     * @return int An integer variable which represents the player team which has ownership of the current chess piece.
     */
    public int getTeam() {
        return this.team;
    }

    
    /**
     * A getter method for a String[] array which stores the path of chess icon images used for both the blue and red players.
     * @return String[] A String[] array which stores the path of chess icon images used for both the blue and red players.
     */
    public String[] getIconImage() {
        return this.iconImage;
    }

    // Abstract method which shows the possible points available for chess piece movement
    /**
     * An abstract method to be overriden in the subclasses of the GenericChessPiece class, which returns an ArrayList of Points.
     * In the subclasses of the GenericChessPiece class, this method contains the algorithm used for generating the possible available moves of a GenericChessPiece instance in the Webale chess game.
     * @param CHESS_BOARD A variable which represents the Webale chess board used in game.
     * @return ArrayList An ArrayList of Point instances which represents the coordinates which are valid moves on the Webale chess board for a chess piece.
     */
    public abstract ArrayList<Point> showValidPossibleMoves(final ChessBoard CHESS_BOARD);
    
    /**
     * An abstract method to be overriden in the subclasses of the GenericChessPiece class, which returns a human readable String representation of an instance of the GenericChessPiece type.
     * In the subclasses, this method is also used in the process of creating game save files, when writing contents of chess piece objects to the game save file.
     * @return String A human readable String representation of an instance of the Triangle class.
     */
    public abstract String toString();
    
}
