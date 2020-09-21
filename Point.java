/**
 * This Point class define the coordinates(x,y) of the chess pieces on the chess board
 */

public class Point {
    /**
    * Variable x declare the column of the chess board
    */
    private int x;
    /**
    * Variable y declare the row of the chess board
    */
    private int y;

    /**
    * Constructor of Point class
    *@param x coordinate x
    *@param y coordinate y
    */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
    * This method will return the value of x in the coordinate.
    * @return int X coordinate value of the current Point instance.
    */
    public int getX() {
        return this.x;
    }

    /**
    * This method will set the value of x of the coordinate.
    * @param x An integer variable which represents the X coordinate value to be set for the current Point instance.
    */
    public void setX(int x) {
        this.x = x;
    }

    /**
    * This method will return the value of y in the coordinate.
    * @return int Y coordinate value of the current Point instance.
    */
    public int getY() {
        return this.y;
    }

    /**
    * This method will set the value of y of the coordinate.
    * @param y An integer variable which represents the Y coordinate value to be set for the current Point instance.
    */
    public void setY(int y) {
        this.y = y;
    }

    /**
    * This method is used to check whether the coordinate of two points are same.
    */
    @Override
    public boolean equals(Object o) {
        // To compare a Point with another Point of same X and Y values
        if (o instanceof Point) {
            return this.getX() == ((Point)o).getX() && this.getY() == ((Point)o).getY();
        }
        return false;  // If object passed in is not of Point type, return false
    }

    /**
    * This method returns a String representation of the coordinate of point
    * Invoked when trying to print the object in command line
    */
    @Override
    public String toString() {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
}