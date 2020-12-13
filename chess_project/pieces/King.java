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

    public void setMoved(int status) {
        this.moved = status;
    }

    @Override
    public List<List<List<Integer>>> getTheoreticMoves(Board input_board) {
        //System.out.println("reached King.getTheoreticMoves");
        List<List<List<Integer>>> return_list = new ArrayList<>();
        // basic moves
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                basicMoves(x, y, return_list, input_board);
            }
        }

        // castling i = 0 = left, 2 = right
        //int debug_loop_count = 0;

        for (int i = 0; i < 2; i++) {
            //debug_loop_count++;
            //System.out.println("reached check castling loop: " + debug_loop_count);
            int rook_x_axis = (i == 0) ? 0 : 7;
            int y_axis = (this.getPlayer() == 0) ? 0 : 7;
            int validated = 1;
            int shift = (i == 0) ? -1 : 1;
            // king || chosen rook moved
            Piece retrieved_rook = input_board.getBoard().get(rook_x_axis).get(y_axis);
            if (this.moved != 0 || retrieved_rook == null || retrieved_rook.getCharacter() != "ro" ||
                    retrieved_rook.getPlayer() != this.player) {
                validated = 0;
                //System.out.println();
                //System.out.println("player's king moved / retrieved null / retrieved !rook / !player");

            }
            else if (((Rook) retrieved_rook).getMoved() != 0 || ((Rook) retrieved_rook).getOriginal() == 0) {
                validated = 0;
            }

            //System.out.println("validated req 0, 1:" + validated);

            // no pieces between the 2 before move
            if (validated == 1) {
                for (int x = rook_x_axis - shift; x != 4; x -= shift) {
                    //System.out.println("current pos btwn ki & rook: " + "(" + x + ", " + y_axis + ")");
                    if (input_board.getBoard().get(x).get(y_axis) != null) {
                        validated = 0;
                        break;
                    }
                }
            }

            // this player's king not in check before move
            // circular reference loop encountered when both kings have castling theoretic options available
            // - ie BOTH KINGS in their ORIGINAL RANKS
            // when checking if a king is INCHECK BEFORE & DURING (INTERMEDIATE SQUARE) a potential castling move:
            // - verify that opponent doesn't have any piece that lands on player king's pos
            //   - if OPPONENT KING in ORIGINAL RANK:
            //      - SKIP OPPONENT KING PIECE since opponent king piece will GUARANTEE to not have a theoretic move that lands on player's king
            if (validated == 1) {
                //System.out.println("validated that no piece is in btwn ki & ro");
                if (input_board.inCheck(this.player) != 0) {
                    validated = 0;
                }
            }
            // check if intermediate square move puts king in check
            if (validated == 1) {
                //System.out.println("validated that player not in check before move");
                List<Integer> dest_pos = new ArrayList<>(2);
                dest_pos.add(4 + shift);
                dest_pos.add(y_axis);
                List<Integer> theoretic_backup_start_pos = new ArrayList<>(2);
                theoretic_backup_start_pos.add(this.position.get(0));
                theoretic_backup_start_pos.add(this.position.get(1));
                // input_board.move doesn't mutate position argument, safe to pass directly
                Piece retrieved_king = input_board.move(this.position, dest_pos);
                if (input_board.inCheck(this.player) != 0) {
                    validated = 0;
                }
                // reverse test move
                input_board.move(dest_pos, theoretic_backup_start_pos);
            }
            // add current castling move to returned_list
            if (validated == 1) {
                //System.out.println("theoretic castling move " + rook_x_axis + " validated");
                List<List<Integer>> move = new ArrayList<>(2);
                List<Integer> start = new ArrayList<>(2);
                start.add(this.position.get(0));
                start.add(this.position.get(1));
                move.add(start);
                List<Integer> dest = new ArrayList<>(2);
                dest.add(4 + 2*shift);
                dest.add(y_axis);
                move.add(dest); // dest_pos of king piece
                List<Integer> castling_type = new ArrayList<>(1);
                castling_type.add(i);
                move.add(castling_type); // direction indicator
                return_list.add(move);
            }
        }

        return return_list;
    }

    private void basicMoves(int x_increment, int y_increment, List<List<List<Integer>>> return_list, Board input_board) {
        int x_axis = this.position.get(0);
        int y_axis = this.position.get(1);
        int x_dest = x_axis + x_increment;
        int y_dest = y_axis + y_increment;
        //System.out.println("before while");
        //Piece current_piece = input_board.getBoard().get(x_axis + x_increment).get(y_axis + y_increment);
        if (y_dest <= 7 && 0 <= y_dest &&
                x_dest <= 7 && 0 <= x_dest) {

            Piece dest_piece = input_board.getBoard().get(x_dest).get(y_dest);
            // if dest piece == null or belongs to opponent
            if (dest_piece == null || dest_piece.getPlayer() != this.player) {
                //System.out.println("inside while");
                List<List<Integer>> move = new ArrayList<>(2);
                List<Integer> start = new ArrayList<>(2);
                start.add(this.position.get(0));
                start.add(this.position.get(1));
                move.add(start);
                List<Integer> dest = new ArrayList<>(2);
                dest.add(x_dest);
                dest.add(y_dest);
                move.add(dest);
                return_list.add(move);
            }
        }
    }


    @Override
    public List<List<List<Integer>>> getLegalMoves(Board input_board) {
        List<List<List<Integer>>> return_list = new ArrayList<>();
        // check basic moves
        for (List<List<Integer>> theoretic_move : this.getTheoreticMoves(input_board)) {
            // if current theoretic move is a basic move
            if (theoretic_move.size() == 2) {
                Piece captured = input_board.remove(theoretic_move.get(1));
                input_board.move(theoretic_move.get(0), theoretic_move.get(1));
                int backup_moved = this.getMoved();
                this.setMoved(1);
                if (input_board.inCheck(this.player) != 1) {
                    return_list.add(theoretic_move);
                }
                this.setMoved(backup_moved);
                input_board.move(theoretic_move.get(1), theoretic_move.get(0));
                input_board.place(theoretic_move.get(1), captured);
            }
            // if castling theoretic move
            else {
                // validate in check status after move
                int shift = (theoretic_move.get(2).get(0) == 0) ? 1 : -1;
                int y_axis = this.position.get(1);
                int rook_start_x = (theoretic_move.get(2).get(0) == 0) ? 0 : 7;
                int rook_dest_x = theoretic_move.get(1).get(0) + shift;
                List<Integer> rook_start_pos = new ArrayList<>(2);
                rook_start_pos.add(rook_start_x);
                rook_start_pos.add(y_axis);
                List<Integer> rook_dest_pos = new ArrayList<>(2);
                rook_dest_pos.add(rook_dest_x);
                rook_dest_pos.add(y_axis);

                Piece retrieved_king = input_board.move(theoretic_move.get(0), theoretic_move.get(1));
                Piece retrieved_rook = input_board.move(rook_start_pos, rook_dest_pos);
                int king_backup_moved = this.moved;
                this.moved = 1;
                int rook_backup_moved = ((Rook) retrieved_rook).getMoved();
                ((Rook) retrieved_rook).setMoved(1);

                if (input_board.inCheck(this.player) == 0) {
                    theoretic_move.add(rook_start_pos);
                    theoretic_move.add(rook_dest_pos);
                    return_list.add(theoretic_move);
                }

                ((Rook) retrieved_rook).setMoved(rook_backup_moved);
                this.moved = king_backup_moved;
                input_board.move(rook_dest_pos, rook_start_pos);
                input_board.move(theoretic_move.get(1), theoretic_move.get(0));
            }
        }

        return return_list;
    }
}
