import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ChessBoard extends Application{
    protected ChessGame game;
    Button[][] chessButton;
    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new ChessGame();                                               //instantiate game model
        primaryStage.setTitle("Webale Chess Assignment Beta");
        GridPane gridPane = new GridPane();
        chessButton = new Button[8][7];
        for (int row = 0; row < 8 ; row++){
            for (int col = 0; col < 7; col++){
                chessButton[row][col] = new Button();
                chessButton[row][col].setId(String.valueOf(row*10+col));      //set btn id as |row|col| digit combination
                chessButton[row][col].setMinHeight(100.0);
                chessButton[row][col].setMinWidth(100.0);
                chessButton[row][col].setOnAction(new GameController(this));
                chessButton[row][col].setStyle("-fx-background-color: #FFFFFF;-fx-border-color: rgb(50,87,138);-fx-border-width: 2;");
                gridPane.add(chessButton[row][col], col, row);
                System.out.println(chessButton[row][col].getId());
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
        GenericChessPiece[][] chessPieces = game.getChessPieces();
        for (int row = 0; row < 8 ; row++) {
            for (int col = 0; col < 7; col++) {
                chessButton[row][col].setGraphic(null);
                if (game.getChessPieces()[row][col] != null) {
                        Image image = new Image(getClass().getResourceAsStream(chessPieces[row][col].getIconImage()[chessPieces[row][col].getTeam()-1]));
                        ImageView imageView = new ImageView(image);
                        chessButton[row][col].setGraphic(imageView);
                }
            }
        }
    }

    public Button[][] getChessButton() {
        return chessButton;
    }

    public ChessGame getGame() {
        return game;
    }
}
