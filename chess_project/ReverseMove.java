package chess_project;
import chess_project.pieces.*;

import java.lang.reflect.Array;
import java.util.*;

public class ReverseMove {

    List<List<Integer>> mock_move = null;
    Piece mover_piece = null;
    Piece captured_piece = null;
    int mover_moved = -99;
    int mover_special_turn_number = -99;

    // constructor for normal type ReverseMove object
    public ReverseMove(List<List<Integer>> normal_move, int backup_mover_moved, Piece dest_piece) {
        this.mock_move = normal_move;
        this.captured_piece = dest_piece;
        this.mover_moved = backup_mover_moved;
    }

    // constructor for:
    // - castling type ReverseMove object
    // - two_square type ReverseMove object
    public ReverseMove(List<List<Integer>> move_array) {
        this.mock_move = move_array;
    }

    // constructor for en passant type ReverseMove object
    public ReverseMove(List<List<Integer>> en_passant_move, Piece captured_pawn) {
        this.mock_move = en_passant_move;
        this.captured_piece = captured_pawn;
    }

    // constructor for promotion type ReverseMove object
    public ReverseMove(List<List<Integer>> promotion_move, Piece player_pawn, Piece captured_piece) {
        this.mock_move = promotion_move;
        this.captured_piece = captured_piece;
        this.mover_piece = player_pawn;
    }

}
