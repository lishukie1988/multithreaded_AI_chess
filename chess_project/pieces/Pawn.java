package chess_project.pieces;
import chess_project.*;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public void setMoved(int status) {
        this.moved = status;
    }

    public int getSpecialTurnNumber() {
        return this.special_turn_number;
    }

    public void setSpecialTurnNumber(int new_status) {
        this.special_turn_number = new_status;
    }

    @Override
    public List<List<List<Integer>>> getTheoreticMoves(Board input_board) {
        List<List<List<Integer>>> return_list = new ArrayList<>();
        int y_shift = (this.player == 0) ? 1 : -1;
        int player_x = this.position.get(0);
        int player_y = this.position.get(1);

        // check move/advancement, ie square ahead is not occupied by any piece & within bounds
        // modularize
        if (0 <= player_y + y_shift && player_y + y_shift <= 7 &&
                input_board.getBoard().get(player_x).get(player_y + y_shift) == null) {
            List<List<Integer>> move = newMovePositions(player_x, player_y + y_shift, 3);
            // check if dest pos = 8th rank of player
            // if so, add extra type element into theoretic move
            if (player_y + y_shift == 0 || player_y + y_shift == 7) {
                addToMovePositions(move, 2, -99);
            }
            return_list.add(move);

            // check 2 square move
            // prereq: 1st advanced square to be vacant, validated above
            if (this.moved == 0 && input_board.getBoard().get(player_x).get(player_y + 2*y_shift) == null) {
                // initialize new theoretic move array since arrays are mutable
                // & 1st array filled with 1st square info & appended to return_list
                List<List<Integer>> second_square_move = newMovePositions(player_x, player_y + 2*y_shift, 3);
                addToMovePositions(second_square_move, 4, -99);
                return_list.add(second_square_move);
            }
        }

        // check capture
        // modularize
        // for each direction (left/right)
        for (int x = 0; x < 2; x++) {
            int x_shift = (x == 0) ? -1 : 1;
            // check to see if dest_square is within bounds & contains an opponent piece
            if (0 <= player_y + y_shift && player_y + y_shift <= 7 &&
                    0 <= player_x + x_shift && player_x + x_shift <= 7) {
                Piece capture = input_board.getBoard().get(player_x + x_shift).get(player_y + y_shift);
                if (capture != null && capture.getPlayer() != this.player) {

                    List<List<Integer>> capture_move = newMovePositions(player_x + x_shift, player_y + y_shift, 3);

                    // check if dest pos = 8th rank of player
                    // if so, add extra type element into theoretic move
                    // modularize
                    if (player_y + y_shift == 0 || player_y + y_shift == 7) {
                        addToMovePositions(capture_move, 2, -99);
                    }

                    return_list.add(capture_move);
                }
            }
        }

        // check en passant:
        // for each adjacent side
        for (int x = 0; x < 2; x++) {
            int x_shift = (x == 0) ? -1 : 1;
            // if within bounds
            if (0 <= player_x + x_shift && player_x + x_shift <= 7) {
                Piece adjacent = input_board.getBoard().get(player_x + x_shift).get(player_y);
                // if contains pawn piece belonging to opponent with special_turn_move == last move
                if (adjacent != null && adjacent.getCharacter() == "pa" &&
                        adjacent.getPlayer() != this.player &&
                        ((Pawn) adjacent).getSpecialTurnNumber() == input_board.getTurnNumber() - 1) {

                    List<List<Integer>> passant_move = newMovePositions(player_x + x_shift, player_y + y_shift, 4);
                    addToMovePositions(passant_move, 3, -99);
                    addToMovePositions(passant_move, player_x + x_shift, player_y);
                    return_list.add(passant_move);
                }
            }
        }

        return return_list;
    }

    private List<List<Integer>> newMovePositions(int dest_x, int dest_y, int capacity) {

        List<Integer> dest_pos = new ArrayList<>(2);
        dest_pos.add(dest_x);
        dest_pos.add(dest_y);
        List<List<Integer>> move = new ArrayList<>(capacity);
        List<Integer> start = new ArrayList<>(2);
        start.add(this.position.get(0));
        start.add(this.position.get(1));
        move.add(start);
        move.add(dest_pos);
        return move;
    }

    private void addToMovePositions(List<List<Integer>> input_list, int index_one, int index_two) {

        List<Integer> new_element = new ArrayList<>(2);
        new_element.add(index_one);
        if (index_two != -99) {
            new_element.add(index_two);
        }
        input_list.add(new_element);
    }


    @Override
    public List<List<List<Integer>>> getLegalMoves(Board input_board) {
        List<List<List<Integer>>> return_list = new ArrayList<>();
        // check basic moves
        for (List<List<Integer>> theoretic_move : this.getTheoreticMoves(input_board)) {
            // if normal / promotion_move
            if (theoretic_move.size() == 2 || theoretic_move.get(2).get(0) == 2) {
                Piece captured = input_board.remove(theoretic_move.get(1));
                Piece player_pawn = input_board.move(theoretic_move.get(0), theoretic_move.get(1));
                int player_pawn_moved = this.moved;
                this.moved = 1;
                if (input_board.inCheck(this.player) != 1) {
                    return_list.add(theoretic_move);
                }
                input_board.move(theoretic_move.get(1), theoretic_move.get(0));
                input_board.place(theoretic_move.get(1), captured);
                this.moved = player_pawn_moved;
            }

            // if two_square_move
            // no need to backup potentially captured piece, since 2 square moves requires empty dest pos
            if (theoretic_move.size() > 2 && theoretic_move.get(2).get(0) == 4) {
                Piece player_pawn = input_board.move(theoretic_move.get(0), theoretic_move.get(1));
                int player_pawn_moved = this.getMoved();
                int player_pawn_special = this.getSpecialTurnNumber();
                this.moved = 1;
                this.special_turn_number = input_board.getTurnNumber() - 1;
                if (input_board.inCheck(this.player) != 1) {
                    return_list.add(theoretic_move);
                }
                input_board.move(theoretic_move.get(1), theoretic_move.get(0));
                this.moved = player_pawn_moved;
                this.special_turn_number = player_pawn_special;
            }

            // if en passant
            if (theoretic_move.size() > 2 && theoretic_move.get(2).get(0) == 3) {
                // no need to change/backup moved attribute of player's pawn
                // since moved == 1 before & after bc of en passant requirements
                Piece captured = input_board.remove(theoretic_move.get(3));
                Piece player_pawn = input_board.move(theoretic_move.get(0), theoretic_move.get(1));
                if (input_board.inCheck(this.player) != 1) {
                    return_list.add(theoretic_move);
                }
                input_board.move(theoretic_move.get(1), theoretic_move.get(0));
                input_board.place(theoretic_move.get(3), captured);
            }
        }

        return return_list;
    }

}
