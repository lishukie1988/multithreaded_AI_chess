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

    public static int ai_identical_boards(Board a, Board b) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int identical = ai_identical_pieces(a, a.getBoard().get(row).get(col), b, b.getBoard().get(row).get(col));
                if (identical == 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    private static int ai_identical_pieces(Board a, Piece piece_a, Board b, Piece piece_b) {

        if (piece_a == null || piece_b == null) {
            //System.out.println(11);
            return piece_a == piece_b ? 1 : 0;
        }
//        if (piece_a.getPosition() != piece_b.getPosition()) {
//            System.out.println(12);
//            System.out.println(piece_a.getPosition());
//            System.out.println(piece_b.getPosition());
//
//            return 0;
//        }
        if (!piece_a.getCharacter().equals(piece_b.getCharacter())) {
            //System.out.println(13);
            return 0;
        }
        if (piece_a.getPlayer() != piece_b.getPlayer()) {
            //System.out.println(14);
            return 0;
        }
        if (piece_a.getCharacter().equals("ro") || piece_a.getCharacter().equals("ki") || piece_a.getCharacter().equals("pa")) {
//            Class characte = piece_a.getClass();
//            int identical = ((characte) piece_a).getMoved() == ((characte) piece_b).getMoved() ? 1 : 0;
//            if (identical == 0) {
//                return 0;
//            }
            if (piece_a.getCharacter().equals("ro")) {
                int identical = ((Rook) piece_a).getMoved() == ((Rook) piece_b).getMoved() ? 1 : 0;
                if (identical == 0) {
                    //System.out.println(15);
                    return 0;
                }
            }
            if (piece_a.getCharacter().equals("ki")) {
                int identical = ((King) piece_a).getMoved() == ((King) piece_b).getMoved() ? 1 : 0;
                if (identical == 0) {
                    //System.out.println(16);
                    return 0;
                }
            }
            if (piece_a.getCharacter().equals("pa")) {
                int identical = ((Pawn) piece_a).getMoved() == ((Pawn) piece_b).getMoved() ? 1 : 0;
                if (identical == 0) {
                    //System.out.println(17);
                    return 0;
                }
            }
        }

        if (piece_a.getCharacter().equals("pa")) {

            int a_turn = a.getTurnNumber();
            int b_turn = b.getTurnNumber();
            int a_spec = ((Pawn) piece_a).getSpecialTurnNumber();
            int b_spec = ((Pawn) piece_b).getSpecialTurnNumber();

            if (a_spec == -99 && b_spec == -99) {
                //System.out.println(18);
                return 1;
            }
            else if (a_spec == -99 || b_spec == -99) {
                if (a_spec == -99) {
                    //System.out.println(19);
                    return (b_turn - b_spec == 1 ? 0 : 1);
                }
                else {
                    //System.out.println(20);
                    return (a_turn - a_spec == 1 ? 0 : 1);
                }
            }
            else {
                int a_allowed = a_turn - a_spec == 1 ? 1 : 0;
                int b_allowed = b_turn - b_spec == 1 ? 1 : 0;
                //System.out.println(21);
                return a_allowed == b_allowed ? 1 : 0;
            }
        }

        //System.out.println(22);
        return 1;

    }

}
