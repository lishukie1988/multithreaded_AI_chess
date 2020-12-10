package chess_project;
import chess_project.pieces.*;
import java.util.*;

public class Main {

    public static void main(String[] arg) {

        //Bishop new_bishop = new Bishop(0, 3, 3);

        Board new_board = new Board("test_bishop");
        new_board.displayBoard();
        //new_bishop.getLegalMoves(new_board);
        //List<List<List<Integer>>> theoretic_moves = new_bishop.getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves + "len: " + theoretic_moves.size());


        List<List<Integer>> move_positions = new ArrayList<>(2);
        List<Integer> start_pos = new ArrayList<>(2);
        start_pos.add(1);
        start_pos.add(0);
        List<Integer> dest_pos = new ArrayList<>(2);
        dest_pos.add(3);
        dest_pos.add(3);
        move_positions.add(start_pos);
        move_positions.add(dest_pos);
        new_board.normalMove(move_positions);
        new_board.displayBoard();
        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(3).get(3).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

    }

    /*
    public static void testCircular(Piece piece) {
        piece.setPosition(8, 8);
    }
     */

    /*
    public static void testPassSelf(Board input_board) {
        System.out.println(input_board.testSetVar());
        System.out.println(input_board.getPlayerPieces(1));
    }
     */
}
