package chess_project.pieces;
import chess_project.*;

import java.util.*;

public class King extends Piece{

    private int moved = 0;

    public King(int player, int x, int y) {
        this.player = player;
        this.character = "ki";
        this.position.add(x);
        this.position.add(y);
    }

    /*
    public void testPrint() {
        Main.testCircular(this);
        System.out.println(this.position + this.character + this.player);
    }
     */

    public int getMoved() {
        return this.moved;
    }

    public void setMoved() {
        this.moved = 1;
    }

    @Override
    public List<List<List<Integer>>> getTheoreticMoves(Board input_board) {
        return null;
    }

    @Override
    public List<List<List<Integer>>> getLegalMoves(Board input_board) {
        return null;
    }
}
