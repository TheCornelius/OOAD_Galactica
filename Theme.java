import javafx.scene.control.Button;

/**
 * This class represents the color themes available for use within the Webale chess game, and contains methods for retrieving information related to the color themes, and for switching themes.
 * Colors used are mostly represented using hex color codes; where as the hue values which are used for manipulating the hue of chess piece icon images are stored as double values with a range between -1.00 and 1.00.
 */
public class Theme {
    /**
     * A String[] array which stores the hex codes used in the three themes for tiles where the sum of their row and column values are even (row + col % 2 == 0)
     */
    private static String[] color1 = {"#7E7F83", "#F4ACB7", "#0D2149"};
    /**
     * A String[] array which stores the hex codes used in the three themes for tiles where the sum of their row and column values are odd (row + col % 2 == 1)
     */
    private static String[] color2 = {"#F3F3F4", "#FFCAD4", "#208AAE"};
    /**
     * A double[] array which stores the hue values which are used for manipulating the hue of chess piece icon image belonging to the blue player. The hue values are stored as double values with a range between -1.00 and 1.00. 
     */
    private static double[] hueSet1 = {0, 0.2, -0.0555};
     /**
     * A double[] array which stores the hue values which are used for manipulating the hue of chess piece icon image belonging to the red player. The hue values are stored as double values with a range between -1.00 and 1.00. 
     */
    private static double[] hueSet2 = {0, -0.15, 1};
    /**
     * An integer which represents the current theme in use.
     */
    private static int currentTheme;
    /**
     * A String array containing the names of themes available for use.
     */
    private static String[] themesList = {"Monochrome B&W", "Sakura Pink", "Galactic Blue"};


    /**
     * This method sets the color theme used for the chess piece icons and tiles, in the view class.
     * @param theme An integer variable which represents the choice of color theme to use.
     */
    public static void setTheme(int theme) {
        currentTheme = theme;

        Button[][] chessButton = ChessBoardView.getChessButton();

        // Set tile colors
        for (int row = 0; row < 8 ; row++){
            for (int col = 0; col < 7; col++){
                if ((row + col) % 2 == 0) {
                    // Boxes with even/odd number of (row + col) value need to have different color
                    chessButton[row][col].setStyle(String.format("-fx-background-color: %s;", color1[theme]));
                } else {
                    chessButton[row][col].setStyle(String.format("-fx-background-color: %s;", color2[theme]));
                }
            }
        }

        ChessBoardController.updateChessButtonIconImage();
    }

    
    /**
     * This method returns a double array containing the hue configuration values of chess piece icons for the current theme in use.
     * @return double[] The hue configuration values of chess piece icons for current theme in use
     */
    public static double[] getHueSet() {
        return new double[]{hueSet1[currentTheme], hueSet2[currentTheme]};
    }

    /**
     * This method returns a String array containing the names of themes available for use.
     * @return String[] A String array containing the names of themes available for use.
     */
    public static String[] getThemesList() {
        return themesList;
    }

    /**
     * This method returns an integer which represents the current theme in use.
     * @return int An integer which represents the current theme in use.
     */
    public static int getCurrentTheme() {
        return currentTheme;
    }
}