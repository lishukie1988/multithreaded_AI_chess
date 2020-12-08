package chess_project.pieces;
import chess_project.*;

import java.util.*;

public class Knight extends Piece{

    public Knight(int player, int x, int y) {
        this.player = player;
        this.character = "kn";
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
