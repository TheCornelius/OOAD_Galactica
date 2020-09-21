import java.util.ArrayList;

/**
 * This Plus class define the path of Arrow chess image and algorithm of the valid possible moves of Arrow chess
 */
public class Arrow extends GenericChessPiece{

    /**
     * This variable declare the direction of the Arrow chess
     * 0 - facing up, 1 - facing down
     */
    private int direction;

    /**
     * This variable will store the turn over of the arrow. 1 - Arrow has reached the other end and turn its direction 180 degrees
     */
    private boolean turnOver;

    /**
     * Class constructor for the Arrow class. This constructor takes in three arguments - the coordinate of the current Arrow instance, and the team of the current Arrow instance, the direction of the current Arrow instance.
     * @param coordinate A Point object which represents the current X and Y coordinate values of a Arrow instance in the Webale chess board. 
     * @param team An integer variable which represents whether this chess piece object belongs to the red or blue player.
     * @param direction An integer variable which represents the direction of the Arrow chess piece
     */
    public Arrow(Point coordinate, int team, int direction) {
        super(coordinate, team, new String[]{"./Assets/blue_arrow.png", "./Assets/red_arrow.png"});
        this.direction = direction;
        this.turnOver = false;
    }

    /**
     * Overloaded constructor for the Arrow class. This constructor takes in four arguments - the coordinate of the current Arrow instance, and the team of the current Arrow instance, the direction of the current Arrow instance, , the turn over permission of the current Arrow instance.
     * @param coordinate A Point object which represents the current X and Y coordinate values of a Arrow instance in the Webale chess board. 
     * @param team An integer variable which represents whether this chess piece object belongs to the red or blue player.
     * @param direction An integer variable which represents the direction of the Arrow chess piece
     * @param turnOver An boolean variable to define whether the Arrow chess change direction or not
     */
    public Arrow(Point coordinate, int team, int direction, boolean turnOver) {
        super(coordinate, team, new String[]{"./Assets/blue_arrow.png", "./Assets/red_arrow.png"});
        this.direction = direction;
        this.turnOver = turnOver;
    }

    /**
     * This method will calculate the valid possible moves of the Arrow chess
     * @param CHESS_BOARD A variable which represents the Webale chess board used in game.
     * @return ArrayList An ArrayList of Point instances which represents the coordinates which are valid moves on the Webale chess board for a Arrow chess piece.
     */
    @Override
    public ArrayList<Point> showValidPossibleMoves(final ChessBoard CHESS_BOARD) {
        int row = this.getCoordinate().getY();
        int col = this.getCoordinate().getX();

        ArrayList<Point> validPossibleMoves = new ArrayList<>();

        if (direction == 0) {
            // Indicates going/facing up
            for (int i = row - 1; (i > (row - 3)) && (i >= 0); i--) {
                // i is y (row), in this case
                if (ChessBoard.getInstance().hasChessPiece(i, col)) {
                    // If the board has chess piece
                    if (ChessBoard.getChessPieces()[i][col].getTeam() != this.getTeam()) {
                        // If the chess piece is for enemy
                        validPossibleMoves.add(new Point(col, i));
                    }
                    break;
                }
                else{
                    validPossibleMoves.add(new Point(col, i));
                }
            }
        } else if (direction == 1) {
            // Indicates going/facing down
            for (int i = row + 1; (i < (row + 3)) && (i <= 7); i++) {
                // i is y (row), in this case
                if (ChessBoard.getInstance().hasChessPiece(i, col)) {
                    // If the board has chess piece
                    if (ChessBoard.getChessPieces()[i][col].getTeam() != this.getTeam()) {
                        // If the chess piece is for enemy
                        validPossibleMoves.add(new Point(col, i));
                    }
                    break;
                }
                else{
                    validPossibleMoves.add(new Point(col, i));
                }
            }
        }
        return validPossibleMoves;

    }

    /**
     * This will display the details of the Arrow chess
     * @return chess piece name, coordinate(x,y), team, turn over and direction
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ";" + getCoordinate().getX() + ";" + getCoordinate().getY() + ";" + getTeam() + ";" + getTurnOver() + ";" + getDirection();
    }

    /**
     * This will display the direction of the Arrow chess piece
     * @return direction of the Arrow chess piece
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     * This will change the direction of the Arrow chess piece
     */
    public void changeDirection() {
        this.direction = this.direction == 1 ? 0 : 1;
    }

    /**
     * This will display the turn over of the Arrow chess piece
     * @return the turn over permission
     */
    public boolean getTurnOver() {
        return this.turnOver;
    }

    /**
     * This will toggle the turn over of the Arrow chess piece and change the direction
     */
    public void toggleTurnOver() {
        this.turnOver = !this.turnOver;
        this.changeDirection();
    }

}