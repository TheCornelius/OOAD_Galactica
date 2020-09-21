import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * This class demonstrates the main user interface (the View in the MVC pattern) of the whole program.
 *
 */
public class ChessBoardView extends Application{
    /**
     * chessButton(s) in 7x8 array
     */
    private static Button[][] chessButton;
    /**
     * The main stage (window) of the program
     */
    private static Stage stage;
    /**
     * 3 labels which allocate at the bottom of the program
     */
    private static Label roundCount, playerTurn, errMsg;

    /**
     * The main entry point of the javaFX program
     * This method will initialize all the UI elements and the relative layouts
     * @param primaryStage The main stage (window) of the program
     */
    public void start(Stage primaryStage){
        stage = primaryStage;
        Button btnSave = new Button("Save Game");
        btnSave.setOnAction(e -> ChessBoardController.saveGameFile(primaryStage));
        Button btnLoad = new Button("Load Game");
        btnLoad.setOnAction(e -> ChessBoardController.loadGameFile(primaryStage));
        Button btnReset = new Button("Reset Game");
        btnReset.setOnAction(e -> ChessBoardController.resetGame());
        Button btnSoundEffect = new Button("Toggle SFX");
        btnSoundEffect.setOnAction(e -> ChessBoardController.toggleSoundEffect());

        ArrayList<String> themeChoices = new ArrayList<>(Arrays.asList(Theme.getThemesList()));
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText("Choose Theme");
        themeChoices.forEach(e-> comboBox.getItems().add(e));
        comboBox.setOnAction(e -> Theme.setTheme(themeChoices.indexOf(comboBox.getValue())));

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(btnSave, btnLoad, btnReset, btnSoundEffect, comboBox);

        BorderPane borderPane = new BorderPane();
        roundCount = new Label("Round: 1");
        playerTurn = new Label("Player BLUE turn");
        errMsg = new Label("");
        borderPane.setLeft(errMsg);
        borderPane.setCenter(playerTurn);
        borderPane.setRight(roundCount);
        VBox vbox = new VBox(hBox);

        // Chess grids creation
        GridPane gridPane = new GridPane();
        chessButton = new Button[8][7];

        for (int row = 0; row < 8 ; row++){
            for (int col = 0; col < 7; col++){
                chessButton[row][col] = new Button();
                chessButton[row][col].prefHeightProperty().bind(gridPane.heightProperty());
                chessButton[row][col].prefWidthProperty().bind(gridPane.widthProperty());
                chessButton[row][col].setMinHeight(80.0);
                chessButton[row][col].setMinWidth(80.0);
                chessButton[row][col].setId(String.valueOf(row * 10 + col));
                chessButton[row][col].setOnAction(ChessBoardController::moveChessPiece);
                gridPane.add(chessButton[row][col], col, row);
            }
        }

        // Set default theme color
        Theme.setTheme(0);
        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(borderPane);
        Scene scene = new Scene(vbox, 650, 750);
        primaryStage.getIcons().add(new Image("file:./Assets/webale-icon.png"));
        primaryStage.setTitle("Webale Chess");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(760);
        primaryStage.setMinWidth(750);
        primaryStage.show();
        ChessBoardController.updateChessButtonIconImage();
    }
    /**
     * Main function which calls the start() method above.
     * @param args Arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Return chessButton array
     * @return ChessButton[][] array
     */
    public static Button[][] getChessButton() {
        return chessButton;
    }
    /**
     * Return the main primary stage of the program
     * @return stage
     */
    public static Stage getStage() {
        return stage;
    }
    /**
     * Set the label of the error message which allocates at the left bottom of the program.
     * @param errorMessage Pass in the errorMessage
     */
    public static void setLabelTexts(String errorMessage) {
        errMsg.setText(errorMessage);
    }
    /**
     * Set the labels of the turn count (right bottom) and the turn Message (bottom center)
     * @param round Pass in the round count
     * @param turnMessage Pass in the turnMessage
     */
    public static void setLabelTexts(int round, String turnMessage) {
        roundCount.setText("Round: " + round);
        playerTurn.setText(turnMessage.toUpperCase() + "'s turn");
    }
}