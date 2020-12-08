package chess_project.pieces;
import chess_project.*;

import java.util.*;

public class Pawn extends Piece{

    private int moved = 0;
    private int special_turn_number = -99;

    public Pawn(int player, int x, int y) {
        this.player = player;
        this.character = "pa";
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

    public int getSpecialTurnNumber() {
        return this.special_turn_number;
    }

    public void setSpecialTurnNumber(int new_status) {
        this.special_turn_number = new_status;
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
