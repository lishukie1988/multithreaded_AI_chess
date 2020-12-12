package chess_project;
import chess_project.pieces.*;

import java.lang.reflect.Array;
import java.util.*;


public class MovesList {

    private List<Integer> moves;

    public MovesList() {

        this.moves = new ArrayList<>();
    }


    public List<Integer> getMoves() {

        return this.moves;
    }


    public void addMove(int move) {

        if (this.moves.size() < 50) {
            this.moves.add(move);
        }

        else if (this.moves.size() == 50) {
            this.moves.remove(0);
            this.moves.add(move);
        }

    }


    public int isDraw() {

        if (this.moves.size() < 50) {
            return 0;
        }
        else {
            for (int move : this.moves) {
                if (move == 1) {
                    return 1;
                }
            }
        }

        return 0;
    }

}
