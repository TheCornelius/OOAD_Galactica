import java.util.ArrayList;

public class ChessGame {                     //main game model sequence should be here
    private GenericChessPiece[][] chessPieces;    //store current chessPieces objects

    public ChessGame() {
        initializeChess();
    }

    public void initializeChess(){
        chessPieces = new GenericChessPiece[8][7];
        chessPieces[0][0] = new Plus (1,true);
        chessPieces[0][1] = new Triangle(1,true);
        chessPieces[0][2] = new Chevron(1,true);
        chessPieces[0][3] = new Sun(1,true);
        chessPieces[0][4] = new Chevron(1, true);
        chessPieces[0][5] = new Triangle(1, true);
        chessPieces[0][6] = new Plus(1, true);
        chessPieces[1][0] = new Arrow(1,true);
        chessPieces[1][2] = new Arrow(1,true);
        chessPieces[1][4] = new Arrow(1,true);
        chessPieces[1][6] = new Arrow(1,true);

        chessPieces[7][0] = new Plus (2,true);
        chessPieces[7][1] = new Triangle(2,true);
        chessPieces[7][2] = new Chevron(2,true);
        chessPieces[7][3] = new Sun(2,true);
        chessPieces[7][4] = new Chevron(2, true);
        chessPieces[7][5] = new Triangle(2, true);
        chessPieces[7][6] = new Plus(2, true);
        chessPieces[6][0] = new Arrow(2,true);
        chessPieces[6][2] = new Arrow(2,true);
        chessPieces[6][4] = new Arrow(2,true);
        chessPieces[6][6] = new Arrow(2,true);

    }
    public void swapTrianglePlus(){

    }

    public GenericChessPiece[][] getChessPieces() {
        return chessPieces;
    }
}
