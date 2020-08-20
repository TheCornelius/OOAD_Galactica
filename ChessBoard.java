import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class ChessBoard extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Webale Chess Assignment Beta");
        GridPane gridPane = new GridPane();
        for (int row = 0; row < 8 ; row++){
            for (int col = 0; col < 7; col++){
                Button button = new Button();
                button.setMinHeight(100.0);
                button.setMinWidth(100.0);
                gridPane.add(button, col, row);
            }
        }
        Scene scene = new Scene(gridPane, 700,800);
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println("Hello World");
        System.out.println("Hello 123");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
