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
        List<List<List<Integer>>> return_list = new ArrayList<>();
        basicMoves(2,1, return_list, input_board);
        basicMoves(1,2, return_list, input_board);
        basicMoves(2,-1, return_list, input_board);
        basicMoves(1,-2, return_list, input_board);
        basicMoves(-2,1, return_list, input_board);
        basicMoves(-1,2, return_list, input_board);
        basicMoves(-2,-1, return_list, input_board);
        basicMoves(-1,-2, return_list, input_board);
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
                move.add(this.position);
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
        for (List<List<Integer>> theoretic_move : this.getTheoreticMoves(input_board)) {
            Piece captured = input_board.remove(theoretic_move.get(1));
            Piece mover = input_board.move(theoretic_move.get(0), theoretic_move.get(1));
            if (input_board.inCheck(this.player) != 1) {
                return_list.add(theoretic_move);
            }
            input_board.move(theoretic_move.get(1), theoretic_move.get(0));
            input_board.place(theoretic_move.get(1), captured);
        }
        return return_list;
    }


}
