package chess_project.pieces;
import chess_project.*;

import java.util.*;

public class Queen extends Piece{

    public Queen(int player, int x, int y) {
        this.player = player;
        this.character = "qu";
        this.position.add(x);
        this.position.add(y);
    }

    /*
    public void testPrint() {
        Main.testCircular(this);
        System.out.println(this.position + this.character + this.player);
    }
     */

    @Override
    public List<List<List<Integer>>> getTheoreticMoves(Board input_board) {
        return null;
    }

    @Override
    public List<List<List<Integer>>> getLegalMoves(Board input_board) {
        return null;
    }
}
