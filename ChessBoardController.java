import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * This application utilises an MVC approach for managing the model and updating the user's view. This class is used as the controller class which manipulates the ChessBoard class (model), and updates the ChessBoardView class (view).
 */
public class ChessBoardController  {
    /**
     * This button will store the reference of the valid first click of button object.
     */
    private static Button btnSelectedFrom;
    /**
     * This will store the chess object in corresponding to the btnSelectedFrom position.
     */
    private static GenericChessPiece chessSelectedFrom;
    /**
     * Integer variables that will store the coordinates for the chess piece movement including the source and destination.
     */
    private static int btnFromCol, btnFromRow, btnToCol, btnToRow;
    /**
     * MediaPlayer object used for playing sound effects in the game.
     */
    private static MediaPlayer mediaPlayer;
    /**
     * Boolean to keep track of whether to mute or unmute sound effects. By default, this variable is set to true, and sound effects are muted.
     */
    private static boolean muted = true; 
    /**
     * File Chooser extension filter used for only allowing creating and loading files with the .webale extension.
     */
    private static final FileChooser.ExtensionFilter EXTENSION_FILTER = new FileChooser.ExtensionFilter("Webale files (*.webale)", "*.webale");

    /**
     * This method is used to move the chess piece
     * @param actionEvent This is the first paramter to addNum method
     */
    public static void moveChessPiece(ActionEvent actionEvent) {
        int btnRow = Integer.parseInt(((Button) actionEvent.getSource()).getId()) / 10;
        int btnCol = Integer.parseInt(((Button) actionEvent.getSource()).getId()) % 10;
        if(btnSelectedFrom == null){
            // This indicates first click
            if (ChessBoard.getInstance().hasChessPiece(btnRow, btnCol)) {
                // If this button have chess piece
                if(ChessBoard.getPlayerTurn() == ChessBoard.getChessPieces()[btnRow][btnCol].getTeam()){
                    // If this chess piece belongs to current player
                    Lighting lightingEffect = new Lighting();
                    Light.Distant distantLightSource = new Light.Distant();
                    distantLightSource.setAzimuth(-85.5);
                    lightingEffect.setLight(distantLightSource);
                    lightingEffect.setSurfaceScale(3.2);
                    btnSelectedFrom = ((Button)actionEvent.getSource());
                    btnSelectedFrom.setEffect(lightingEffect);
                    chessSelectedFrom =  ChessBoard.getChessPieces()[btnRow][btnCol];
                    btnFromRow = btnRow;
                    btnFromCol = btnCol;
                    chessSelectedFrom.showValidPossibleMoves(ChessBoard.getInstance()).forEach(e -> ChessBoardView.getChessButton()[e.getY()][e.getX()].setEffect(lightingEffect));
                }
                else{
                    // If this chess piece does not belong to current player
                    playSound("./Assets/error_1.mp3", 1.00);
                    ChessBoardView.setLabelTexts("Not your turn");
                }
            }
            else{
                playSound("./Assets/error_1.mp3", 1.00);
                ChessBoardView.setLabelTexts("No chess piece is selected");
            }
        }
        else{
            // This indicates second click (after a chess piece pickup)
            if (btnSelectedFrom == actionEvent.getSource()) {
                // This indicates that the second click is same as first click, means chess piece pickup cancellation
                ChessBoardView.setLabelTexts("Cancelled Pickup");
                chessSelectedFrom.showValidPossibleMoves(ChessBoard.getInstance()).forEach(e -> ChessBoardView.getChessButton()[e.getY()][e.getX()].setEffect(null));
                ChessBoardView.getChessButton()[btnFromRow][btnFromCol].setEffect(null);
                btnSelectedFrom = null;
                chessSelectedFrom = null;
            }
            else{
                // If the second click by the user is a valid click
                ChessBoardView.setLabelTexts("");
                if(chessSelectedFrom.showValidPossibleMoves(ChessBoard.getInstance()).contains(new Point(btnCol,btnRow))){   // Assumes that the button row and col is in showValidPossibleMove arraylist?
                    btnToRow = Integer.parseInt(((Button) actionEvent.getSource()).getId()) / 10;
                    btnToCol = Integer.parseInt(((Button) actionEvent.getSource()).getId()) % 10;
                    chessSelectedFrom.showValidPossibleMoves(ChessBoard.getInstance()).forEach(e -> ChessBoardView.getChessButton()[e.getY()][e.getX()].setEffect(null));
                    ChessBoard.getInstance().moveChessPieces(btnFromRow, btnFromCol, btnToRow, btnToCol);
                    playSound("./Assets/move_1.mp3", 1.00);

                    // If the chess piece is an arrow that has touched the opposite edge, turn the arrow over to move to opposite direction
                    if (chessSelectedFrom instanceof Arrow) {
                        int y = chessSelectedFrom.getCoordinate().getY();
                        int direction = ((Arrow) chessSelectedFrom).getDirection();
                        if ((direction == 0 && y == 0) || (direction == 1 && y == 7)) {
                            ((Arrow)chessSelectedFrom).toggleTurnOver();
                        }
                    }

                    btnSelectedFrom = null;
                    chessSelectedFrom = null;
                    ChessBoard.getInstance().trianglePlusTransform();  // Check for  round condition first
                    ChessBoard.getInstance().incrementTurnCount();  // Increment round number at end of each turn
                    ChessBoard.flipBoard();  // Flip board after each successful move
                    ChessBoardView.getChessButton()[btnFromRow][btnFromCol].setEffect(null);
                    updateChessButtonIconImage();
                    ChessBoardView.setLabelTexts(ChessBoard.getRoundCount(), ChessBoard.getPlayerTurnName());

                    // Check for game over condition first
                    if (ChessBoard.getInstance().getWinner() != 0) {
                        // end game, and announce winner
                        playSound("./Assets/win.mp3", 0.2);  // Play game win sound effect
                        
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Game Over");
                        alert.setHeaderText("Player " + (ChessBoard.getInstance().getWinner() == 1 ? "Blue" : "Red") + " wins!");
                        alert.setContentText("Choose your option.");
                        // The following line sets the icon of alert box window

                        ImageView alertIcon = new ImageView("./Assets/webale-icon.png");

                        alertIcon.setFitHeight(30); alertIcon.setFitWidth(30);

                        alert.getDialogPane().setGraphic(alertIcon);
                        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:./Assets/webale-icon.png"));

                        ButtonType buttonTypeReset = new ButtonType("Reset");
                        ButtonType buttonTypeLoad = new ButtonType("Load");
                        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                        alert.getButtonTypes().setAll(buttonTypeReset, buttonTypeLoad, buttonTypeCancel);

                        Optional<ButtonType> fileLines = alert.showAndWait();

                        if (fileLines.get() == buttonTypeReset) {
                            resetGame();
                        } else if (fileLines.get() == buttonTypeLoad) {
                            if (!loadGameFile(ChessBoardView.getStage())) {
                                resetGame();
                            }
                        } else {
                            resetGame();
                        }

                        if (!muted) {
                            // If sound effect is enabled, stop sound effect
                            mediaPlayer.stop();
                        }
                    }
                }
                else{
                    // Assumes that the button row and col is not in showValidPossibleMove arraylist or arraylist is empty?
                    ChessBoardView.setLabelTexts("Invalid movement");
                    chessSelectedFrom.showValidPossibleMoves(ChessBoard.getInstance()).forEach(e -> ChessBoardView.getChessButton()[e.getY()][e.getX()].setEffect(null));
                    ChessBoardView.getChessButton()[btnFromRow][btnFromCol].setEffect(null);
                    btnSelectedFrom = null;         //reset clicks
                    chessSelectedFrom = null;
                    playSound("./Assets/error_1.mp3", 1.00);
                }
            }
        }
    }

     /**
     * This method is used to update the view with chess piece icon images.
     */
    public static void updateChessButtonIconImage(){
        GenericChessPiece[][] chessPieces = ChessBoard.getChessPieces();
        for (int row = 0; row < 8 ; row++) {
            for (int col = 0; col < 7; col++) {
                ChessBoardView.getChessButton()[row][col].setGraphic(null);
                if (chessPieces[row][col] != null) {
                    Image image = new Image(ChessBoardController.class.getResourceAsStream(chessPieces[row][col].getIconImage()[chessPieces[row][col].getTeam() - 1]));  // get
                    ImageView imageView = new ImageView(image);

                    // Check whether the arrow has touched
                    if (chessPieces[row][col] instanceof Arrow && ((Arrow)chessPieces[row][col]).getTurnOver()) {
                        imageView.setRotate(ChessBoard.getTurnCount() * 180 + 180);
                    }
                    else{
                        imageView.setRotate(ChessBoard.getTurnCount() * 180);  // This function call rotates an ImageIcon
                        // Only rotate images when it's Blue's turn
                        // Because, initially the images loaded are in another direction
                    }

                    ColorAdjust chessPieceFilter = new ColorAdjust();
                    double[] hueSet = Theme.getHueSet();

                    if (chessPieces[row][col].getTeam() == 1) {
                        chessPieceFilter.setHue(hueSet[0]);
                    } else {
                        chessPieceFilter.setHue(hueSet[1]);
                    }

                    imageView.setEffect(chessPieceFilter);
                    ChessBoardView.getChessButton()[row][col].setGraphic(imageView);
                }
            }
        }
    }

    /**
     * This method is used to create game saves for Webale chess application. This method is triggered when the user clicks on the "Save Game" button in the view class.
     * @param primaryStage Primary stage of the JavaFX application.
     */
    public static void saveGameFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.getExtensionFilters().add(EXTENSION_FILTER);
        fileChooser.setInitialFileName("Webale Game");
        File destinationFile = fileChooser.showSaveDialog(primaryStage);
        if (destinationFile != null) {
            try {
                PrintWriter saveFile = new PrintWriter(new BufferedWriter(new FileWriter(destinationFile)));  // Buffered writer object to write to output file
                saveFile.println(ChessBoard.getTurnCount());  // Write turn count to file first
                saveFile.println(Theme.getCurrentTheme());  // Secondly, write current theme to file
                for (int row = 0; row < 8 ; row++)
                    for (int col = 0; col < 7; col++)
                        if (ChessBoard.getChessPieces()[row][col] != null)
                            saveFile.println(ChessBoard.getChessPieces()[row][col].toString());
                saveFile.close();
            } catch (Exception ex) {
                ChessBoardView.setLabelTexts("Error in saving");
                ex.printStackTrace();
                System.exit(1);
            }
        } else {
            ChessBoardView.setLabelTexts("Cancel file selection.");
        }
    }

    /**
     * This method is used to load game saves for Webale chess application. This method is triggered when the user clicks on the "Load Game" button in the view class.
     * @param primaryStage Primary stage of the JavaFX application.
     * @return boolean Indicates the status whether a game save file chosen by the user has been successfully loaded.
     */
    public static boolean loadGameFile(Stage primaryStage) {
        // Done by Cornelius Pang Sh'ng How
        FileChooser f = new FileChooser();
        f.setTitle("Please select a webale game file.");
        f.getExtensionFilters().add(EXTENSION_FILTER);
        f.setSelectedExtensionFilter(EXTENSION_FILTER);
        File selectedFile = f.showOpenDialog(primaryStage);

        List<String> fileLines = null;
        if (selectedFile != null) {
            try {
                fileLines = Files.readAllLines(Paths.get(String.valueOf(selectedFile.toPath())));
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.exit(1);
            }
            ChessPieceFactory factory = new ChessPieceFactory();
            ChessBoard.clearBoard();
            ChessBoard.getInstance().setTurnCount(Integer.parseInt(fileLines.get(0)));  // Set turn count to turn count in file
            Theme.setTheme(Integer.parseInt(fileLines.get(1)));  // Set theme to saved theme in file
            
            for (int i = 2; i < fileLines.size(); i++){
                String[] currentLine = fileLines.get(i).split(";");  // 4 - other pieces; 6 - arrow piece; other than 4 or 6 is invalid
                if (currentLine.length == 4)
                    ChessBoard.getChessPieces()[Integer.parseInt(currentLine[2])][Integer.parseInt(currentLine[1])] = factory.makeChessPiece(currentLine[0], Integer.parseInt(currentLine[1]), Integer.parseInt(currentLine[2]), Integer.parseInt(currentLine[3]));
                else if (currentLine.length == 6)
                    ChessBoard.getChessPieces()[Integer.parseInt(currentLine[2])][Integer.parseInt(currentLine[1])] = factory.makeChessPiece(currentLine[0], Integer.parseInt(currentLine[1]), Integer.parseInt(currentLine[2]), Integer.parseInt(currentLine[3]), Boolean.parseBoolean(currentLine[4]), Integer.parseInt(currentLine[5]));
            }
            updateChessButtonIconImage();
            ChessBoardView.setLabelTexts(ChessBoard.getRoundCount(), ChessBoard.getPlayerTurnName());
            return true;
        }
        return false;
    }

    /**
     * This method is used to reset the game to initial layout. In other words, when this method is called, it restarts the Webale chess game.
     */
    public static void resetGame() {
        ChessBoard.resetBoard();
        updateChessButtonIconImage();
        ChessBoardView.setLabelTexts("");
        ChessBoardView.setLabelTexts(ChessBoard.getRoundCount(), ChessBoard.getPlayerTurnName());
    }

    /**
     * This method is used to toggle in game sound effects (SFX).
     */
    public static void toggleSoundEffect() {
        muted = !muted;
    }

    /**
     * This method is used to play audio files within the Webale chess game
     * @param musicFile Name of the music file to be played
     * @param volume Volume for playing the audio file
     */
    public static void playSound(String musicFile, double volume){
        if (!muted) {
            Media sound = new Media(ChessBoardController.class.getResource(musicFile).toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }
    }
}