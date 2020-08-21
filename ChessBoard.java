import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ChessBoard extends Application{
    private ArrayList<Button> buttons;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Webale Chess Assignment Beta");
        GridPane gridPane = new GridPane();
        buttons = new ArrayList<Button>();
        int btnCount = 0;
        for (int row = 0; row < 8 ; row++){
            for (int col = 0; col < 7; col++){
                buttons.add(new Button());
                buttons.get(btnCount).setId(String.valueOf(btnCount));
                buttons.get(btnCount).setMinHeight(100.0);
                buttons.get(btnCount).setMinWidth(100.0);
                buttons.get(btnCount).setStyle("-fx-background-color: #FFFFFF;-fx-border-color: rgb(50,87,138);-fx-border-width: 2;");
                gridPane.add(buttons.get(btnCount), col, row);
                System.out.println(buttons.get(btnCount).getId());
                btnCount++;
            }
        }
        /*
        Image image = new Image(getClass().getResourceAsStream("red_arrow.png"));
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        */

        Scene scene = new Scene(gridPane, 700,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
