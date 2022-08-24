package chess_project;
import chess_project.*;
import chess_project.pieces.*;

import java.lang.reflect.Array;
import java.util.*;


public class BoardStates {

    private ArrayList<Board> states;


    public BoardStates() {

        this.states = new ArrayList<>();

    }


    public ArrayList<Board> getStates() {

        return this.states;
    }


    public int threeFold() {

        return 0;
    }

    public int ai_identical_boards(Board a, Board b) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int identical = this.ai_identical_pieces(a, a.getBoard().get(row).get(col), b, b.getBoard().get(row).get(col));
                if (identical == 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public int ai_identical_pieces(Board a, Piece piece_a, Board b, Piece piece_b) {
        if (piece_a == null || piece_b == null) {
            return piece_a == piece_b ? 1 : 0;
        }
        if (piece_a.getPosition() != piece_b.getPosition()) {
            return 0;
        }
        if (piece_a.getCharacter() != piece_b.getCharacter()) {
            return 0;
        }
        if (piece_a.getPlayer() != piece_b.getPlayer()) {
            return 0;
        }
        if (piece_a.getCharacter() == "ro" || piece_a.getCharacter() == "ki" || piece_a.getCharacter() == "pa") {
//            Class characte = piece_a.getClass();
//            int identical = ((characte) piece_a).getMoved() == ((characte) piece_b).getMoved() ? 1 : 0;
//            if (identical == 0) {
//                return 0;
//            }
            if (piece_a.getCharacter() == "ro") {
                int identical = ((Rook) piece_a).getMoved() == ((Rook) piece_b).getMoved() ? 1 : 0;
                if (identical == 0) {
                    return 0;
                }
            }
            if (piece_a.getCharacter() == "ki") {
                int identical = ((King) piece_a).getMoved() == ((King) piece_b).getMoved() ? 1 : 0;
                if (identical == 0) {
                    return 0;
                }
            }
            if (piece_a.getCharacter() == "pa") {
                int identical = ((Pawn) piece_a).getMoved() == ((Pawn) piece_b).getMoved() ? 1 : 0;
                if (identical == 0) {
                    return 0;
                }
            }

        }

    }

}
