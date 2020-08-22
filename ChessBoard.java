import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ChessBoard extends Application{
    Button[][] chessButton;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Webale Chess Assignment Beta");
        GridPane gridPane = new GridPane();
        chessButton = new Button[8][7];
        int btnCount = 0;
        for (int row = 0; row < 8 ; row++){
            for (int col = 0; col < 7; col++){
                chessButton[row][col] = new Button();
                chessButton[row][col].setId(String.valueOf(btnCount));      //set btn id as 0 ~55
                chessButton[row][col].setMinHeight(100.0);
                chessButton[row][col].setMinWidth(100.0);
                chessButton[row][col].setStyle("-fx-background-color: #FFFFFF;-fx-border-color: rgb(50,87,138);-fx-border-width: 2;");
                gridPane.add(chessButton[row][col], col, row);
                System.out.println(chessButton[row][col].getId());
                btnCount++;
            }
        }

        Scene scene = new Scene(gridPane, 700,800);
        primaryStage.setScene(scene);
        primaryStage.show();
        setChessIconImage();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setChessIconImage(){
        ChessGame game = new ChessGame();
        GenericChessPiece[][] chessPieces = game.getChessPieces();
        for (int row = 0; row < 8 ; row++) {
            for (int col = 0; col < 7; col++) {
                if (chessPieces[row][col] != null) {
                        Image image = new Image(getClass().getResourceAsStream(chessPieces[row][col].getIconImage()[chessPieces[row][col].getTeam()-1]));
                        ImageView imageView = new ImageView(image);
                        chessButton[row][col].setGraphic(imageView);
                }
            }
        }
    }
}
