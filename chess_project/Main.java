package chess_project;
import chess_project.pieces.*;

import java.nio.file.LinkPermission;
import java.util.*;

public class Main {

    public static void main(String[] arg) {
        //testBishopTheoretic();
        //testKnightTheoretic();
        //testQueenTheoretic();
        //testRookTheoretic();
        //testPawnTheoretic();
        //testBishopLegal();
        //testKnightLegal();
        //testQueenLegal();
        //testRookLegal();
        //testPawnLegal();
        //testKingTheoretic();
        testKingLegal();

    }

    public static void testBishopTheoretic() {
        Board new_board = new Board();

        List<List<Integer>> move_positions = new ArrayList<>(2);
        List<Integer> start_pos = new ArrayList<>(2);
        start_pos.add(2);
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

    public static void testKnightTheoretic() {
        Board new_board = new Board();

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

        System.out.println("Knigt @ (3,3): ");
        List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(3).get(3).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

        System.out.println("Knigt @ (6,7): ");
        List<List<List<Integer>>> theoretic_moves2 = new_board.getBoard().get(6).get(7).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves2 + "len: " + theoretic_moves2.size());
    }

    public static void testQueenTheoretic() {
        Board new_board = new Board();

        List<List<Integer>> move_positions = new ArrayList<>(2);
        List<Integer> start_pos = new ArrayList<>(2);
        start_pos.add(3);
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

    public static void testRookTheoretic() {
        Board new_board = new Board();

        List<List<Integer>> move_positions = new ArrayList<>(2);
        List<Integer> start_pos = new ArrayList<>(2);
        start_pos.add(0);
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
        int rook_moved = ((Rook) new_board.getBoard().get(3).get(3)).getMoved();
        System.out.println("moved rook.moved = " + rook_moved);
    }

    public static void testPawnTheoretic() {

        Board new_board = new Board();

        setupMove(new_board, 0,1,3,3);
        ((Pawn)new_board.getBoard().get(3).get(3)).setMoved(1);
        setupMove(new_board, 2,6,2,4);
        ((Pawn)new_board.getBoard().get(2).get(4)).setMoved(1);
        ((Pawn)new_board.getBoard().get(2).get(4)).setSpecialTurnNumber(0);
        setupMove(new_board, 6,1,6,4);
        ((Pawn)new_board.getBoard().get(6).get(4)).setMoved(1);
        setupMove(new_board, 5,6,5,4);
        ((Pawn)new_board.getBoard().get(5).get(4)).setSpecialTurnNumber(0);
        ((Pawn)new_board.getBoard().get(5).get(4)).setMoved(1);
        setupMove(new_board, 7,1,7,2);
        ((Pawn)new_board.getBoard().get(7).get(2)).setMoved(1);
        setupMove(new_board, 1,1,1,3);
        ((Pawn)new_board.getBoard().get(1).get(3)).setSpecialTurnNumber(0);
        ((Pawn)new_board.getBoard().get(1).get(3)).setMoved(1);
        setupMove(new_board, 0,6,0,3);
        ((Pawn)new_board.getBoard().get(0).get(3)).setMoved(1);
        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        //System.out.println("Pawn @ (1,1): ");
        //List<List<List<Integer>>> theoretic_moves3 = new_board.getBoard().get(1).get(1).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves3 + "len: " + theoretic_moves3.size());

        System.out.println("Pawn @ (3,3): ");
        List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(3).get(3).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

        System.out.println("Pawn @ (4,1): ");
        List<List<List<Integer>>> theoretic_moves1 = new_board.getBoard().get(4).get(1).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves1 + "len: " + theoretic_moves1.size());

        System.out.println("Pawn @ (3,1): ");
        List<List<List<Integer>>> theoretic_moves2 = new_board.getBoard().get(3).get(1).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves2 + "len: " + theoretic_moves2.size());

        System.out.println("Pawn @ (6,4): ");
        List<List<List<Integer>>> theoretic_moves4 = new_board.getBoard().get(6).get(4).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves4 + "len: " + theoretic_moves4.size());

        System.out.println("Pawn @ (7,2): ");
        List<List<List<Integer>>> theoretic_moves5 = new_board.getBoard().get(7).get(2).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves5 + "len: " + theoretic_moves5.size());

        //System.out.println("Pawn @ (0,6): ");
        //List<List<List<Integer>>> theoretic_moves6 = new_board.getBoard().get(0).get(6).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves6 + "len: " + theoretic_moves6.size());

        System.out.println("Pawn @ (2,4): ");
        List<List<List<Integer>>> theoretic_moves7 = new_board.getBoard().get(2).get(4).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves7 + "len: " + theoretic_moves7.size());

        System.out.println("Pawn @ (0,3): ");
        List<List<List<Integer>>> theoretic_moves8 = new_board.getBoard().get(0).get(3).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves8 + "len: " + theoretic_moves8.size());

        System.out.println("Pawn @ (1,3): ");
        List<List<List<Integer>>> theoretic_moves9 = new_board.getBoard().get(1).get(3).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves9 + "len: " + theoretic_moves9.size());

        System.out.println("Pawn @ (3,6): ");
        List<List<List<Integer>>> theoretic_moves10 = new_board.getBoard().get(3).get(6).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves10 + "len: " + theoretic_moves10.size());

        System.out.println("Pawn @ (6,6): ");
        List<List<List<Integer>>> theoretic_moves11 = new_board.getBoard().get(6).get(6).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves11 + "len: " + theoretic_moves11.size());

    }

    public static void testKingTheoretic() {

        Board new_board = new Board();
        //setupMove(new_board,4,0,7,2);
        //setupMove(new_board,4,7,0,3);

        setupRemove(new_board,1,0);
        setupRemove(new_board,2,0);
        setupRemove(new_board,3,0);
        setupRemove(new_board,1,7);
        setupRemove(new_board,2,7);
        setupRemove(new_board,3,7);
        //setupRemove(new_board,5,0);
        //setupRemove(new_board,6,0);
        setupRemove(new_board,5,7);
        setupRemove(new_board,6,7);
        setupRemove(new_board,6,0);
        setupRemove(new_board,0,0);
        setupRemove(new_board,0,7);
        //setupRemove(new_board,7,7);
        setupRemove(new_board,3,6);
        setupRemove(new_board,4,6);
        setupRemove(new_board,5,6);
        //((Rook)new_board.getBoard().get(0).get(0)).setMoved(1);
        //((King)new_board.getBoard().get(4).get(0)).setMoved(1);
        new_board.displayBoard();
        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        System.out.println("King @ (4,7): ");
        List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(4).get(7).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

        System.out.println("King @ (4,0): ");
        List<List<List<Integer>>> theoretic_moves1 = new_board.getBoard().get(4).get(0).getTheoreticMoves(new_board);
        System.out.println(theoretic_moves1 + "len: " + theoretic_moves1.size());

        //System.out.println("King @ (0,3): ");
        //List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(0).get(3).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

        //System.out.println("King @ (7,2): ");
        //List<List<List<Integer>>> theoretic_moves1 = new_board.getBoard().get(7).get(2).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves1 + "len: " + theoretic_moves1.size());
    }

    public static void testBishopLegal() {
        Board new_board = new Board();

        setupMove(new_board, 2, 0, 1, 4);
        setupRemove(new_board, 3, 6);
        new_board.displayBoard();
        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        System.out.println("Bishop @ (1,4): ");
        List<List<List<Integer>>> legal_moves = new_board.getBoard().get(1).get(4).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves + "len: " + legal_moves.size());
        System.out.println("Bishop @ (5,7): ");
        List<List<List<Integer>>> legal_moves1 = new_board.getBoard().get(5).get(7).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves1 + "len: " + legal_moves1.size());

        System.out.println("Queen @ (3,7): ");
        List<List<List<Integer>>> legal_moves2 = new_board.getBoard().get(3).get(7).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves2 + "len: " + legal_moves2.size());

        System.out.println("Pawn @ (4,6): ");
        List<List<List<Integer>>> legal_moves3 = new_board.getBoard().get(4).get(6).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves3 + "len: " + legal_moves3.size());

    }

    public static void testKnightLegal() {
        Board new_board = new Board();

        setupMove(new_board, 1, 0, 3, 5);
        //setupRemove(new_board, 3, 6);
        new_board.displayBoard();
        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        System.out.println("Knight @ (3,5): ");
        List<List<List<Integer>>> legal_moves = new_board.getBoard().get(3).get(5).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves + "len: " + legal_moves.size());
        System.out.println("Knight @ (6,7): ");
        List<List<List<Integer>>> legal_moves1 = new_board.getBoard().get(6).get(7).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves1 + "len: " + legal_moves1.size());
        System.out.println("Knight @ (6,0): ");
        List<List<List<Integer>>> legal_moves2 = new_board.getBoard().get(6).get(0).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves2 + "len: " + legal_moves2.size());
        System.out.println("Knight @ (1,7): ");
        List<List<List<Integer>>> legal_moves3 = new_board.getBoard().get(1).get(7).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves3 + "len: " + legal_moves3.size());

    }

    public static void testQueenLegal() {

        Board new_board = new Board();

        setupMove(new_board, 3, 0, 0, 5);
        setupRemove(new_board, 3, 6);
        new_board.displayBoard();
        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        System.out.println("Queen @ (0,5): ");
        List<List<List<Integer>>> legal_moves = new_board.getBoard().get(0).get(5).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves + "len: " + legal_moves.size());

        System.out.println("Queen @ (3,7): ");
        List<List<List<Integer>>> legal_moves1 = new_board.getBoard().get(3).get(7).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves1 + "len: " + legal_moves1.size());

        System.out.println("Pawn @ (5,6): ");
        List<List<List<Integer>>> legal_moves2 = new_board.getBoard().get(5).get(6).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves2 + "len: " + legal_moves2.size());

    }

    public static void testRookLegal() {

        Board new_board = new Board();

        setupMove(new_board, 0, 0, 0, 3);
        setupMove(new_board, 7, 0, 4, 3);
        setupRemove(new_board, 4, 6);
        new_board.displayBoard();
        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        System.out.println("Rook @ (0,3): ");
        List<List<List<Integer>>> legal_moves = new_board.getBoard().get(0).get(3).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves + "len: " + legal_moves.size());

        System.out.println("Rook @ (4,3): ");
        List<List<List<Integer>>> legal_moves1 = new_board.getBoard().get(4).get(3).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves1 + "len: " + legal_moves1.size());

        System.out.println("Rook @ (7,7): ");
        List<List<List<Integer>>> legal_moves2 = new_board.getBoard().get(7).get(7).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves2 + "len: " + legal_moves2.size());

        System.out.println("King @ (4,7): ");
        List<List<List<Integer>>> legal_moves3 = new_board.getBoard().get(4).get(7).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves3 + "len: " + legal_moves3.size());

        System.out.println("Knight @ (1,0): ");
        List<List<List<Integer>>> legal_moves4 = new_board.getBoard().get(1).get(0).getLegalMoves(new_board);
        System.out.println("legal moves: " + legal_moves4 + "len: " + legal_moves4.size());

    }


    public static void testPawnLegal() {

        Board new_board = new Board();

        setupRemove(new_board, 4, 6);
        // when white pawn moves from (5,6) to (5,<6) instead of (5,6), removes black pawn @ (5,<6) in the process
        // (5,6) is empty ONLY AFTER the MOVE BELOW, it is STILL OCCUPIED @ THIS POINT of EXECUTION
        //new_board.displayBoard();



        setupMove(new_board, 0,1,3,3);
        ((Pawn)new_board.getBoard().get(3).get(3)).setMoved(1);
        setupMove(new_board, 2,6,2,4);
        ((Pawn)new_board.getBoard().get(2).get(4)).setMoved(1);
        ((Pawn)new_board.getBoard().get(2).get(4)).setSpecialTurnNumber(0);
        setupMove(new_board, 6,1,6,4);
        ((Pawn)new_board.getBoard().get(6).get(4)).setMoved(1);


        setupMove(new_board, 5,6,4,3);
        //((Pawn)new_board.getBoard().get(4).get(3)).setSpecialTurnNumber(0);
        ((Pawn)new_board.getBoard().get(4).get(3)).setMoved(1);

        setupMove(new_board, 4,1,5,6);
        ((Pawn)new_board.getBoard().get(5).get(6)).setMoved(1);

        setupMove(new_board, 7,1,7,2);
        ((Pawn)new_board.getBoard().get(7).get(2)).setMoved(1);
        setupMove(new_board, 1,1,1,3);
        ((Pawn)new_board.getBoard().get(1).get(3)).setSpecialTurnNumber(0);
        ((Pawn)new_board.getBoard().get(1).get(3)).setMoved(1);
        setupMove(new_board, 0,6,0,3);
        ((Pawn)new_board.getBoard().get(0).get(3)).setMoved(1);


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());



        //System.out.println("Pawn @ (1,1): ");
        //List<List<List<Integer>>> theoretic_moves3 = new_board.getBoard().get(1).get(1).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves3 + "len: " + theoretic_moves3.size());

        System.out.println("Pawn @ (3,3): ");
        List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(3).get(3).getLegalMoves(new_board);
        System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

        //System.out.println("Pawn @ (4,6): ");
        //List<List<List<Integer>>> theoretic_moves1 = new_board.getBoard().get(4).get(6).getLegalMoves(new_board);
        //System.out.println(theoretic_moves1 + "len: " + theoretic_moves1.size());

        System.out.println("Pawn @ (3,1): ");
        List<List<List<Integer>>> theoretic_moves2 = new_board.getBoard().get(3).get(1).getLegalMoves(new_board);
        System.out.println(theoretic_moves2 + "len: " + theoretic_moves2.size());

        System.out.println("Pawn @ (6,4): ");
        List<List<List<Integer>>> theoretic_moves4 = new_board.getBoard().get(6).get(4).getLegalMoves(new_board);
        System.out.println(theoretic_moves4 + "len: " + theoretic_moves4.size());

        System.out.println("Pawn @ (7,2): ");
        List<List<List<Integer>>> theoretic_moves5 = new_board.getBoard().get(7).get(2).getLegalMoves(new_board);
        System.out.println(theoretic_moves5 + "len: " + theoretic_moves5.size());

        //System.out.println("Pawn @ (0,6): ");
        //List<List<List<Integer>>> theoretic_moves6 = new_board.getBoard().get(0).get(6).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves6 + "len: " + theoretic_moves6.size());

        System.out.println("Pawn @ (2,4): ");
        List<List<List<Integer>>> theoretic_moves7 = new_board.getBoard().get(2).get(4).getLegalMoves(new_board);
        System.out.println(theoretic_moves7 + "len: " + theoretic_moves7.size());

        System.out.println("Pawn @ (0,3): ");
        List<List<List<Integer>>> theoretic_moves8 = new_board.getBoard().get(0).get(3).getLegalMoves(new_board);
        System.out.println(theoretic_moves8 + "len: " + theoretic_moves8.size());

        System.out.println("Pawn @ (1,3): ");
        List<List<List<Integer>>> theoretic_moves9 = new_board.getBoard().get(1).get(3).getLegalMoves(new_board);
        System.out.println(theoretic_moves9 + "len: " + theoretic_moves9.size());

        System.out.println("Pawn @ (3,6): ");
        List<List<List<Integer>>> theoretic_moves10 = new_board.getBoard().get(3).get(6).getLegalMoves(new_board);
        System.out.println(theoretic_moves10 + "len: " + theoretic_moves10.size());

        System.out.println("Pawn @ (6,6): ");
        List<List<List<Integer>>> theoretic_moves11 = new_board.getBoard().get(6).get(6).getLegalMoves(new_board);
        System.out.println(theoretic_moves11 + "len: " + theoretic_moves11.size());


    }


    public static void testKingLegal() {


        Board new_board = new Board();
        //setupMove(new_board,4,0,7,2);
        //setupMove(new_board,4,7,0,3);
        //new_board.displayBoard();


        setupRemove(new_board,1,0);
        setupRemove(new_board,2,0);
        setupRemove(new_board,3,0);
        setupRemove(new_board,1,7);
        setupRemove(new_board,2,7);
        setupRemove(new_board,3,7);
        //setupRemove(new_board,5,0);
        //setupRemove(new_board,6,0);
        setupRemove(new_board,5,7);
        setupRemove(new_board,6,7);
        setupRemove(new_board,6,0);
        setupRemove(new_board,0,0);
        setupRemove(new_board,0,7);
        //setupRemove(new_board,7,7);
        setupRemove(new_board,3,6);
        setupRemove(new_board,4,6);
        setupRemove(new_board,5,6);

        //setupMove(new_board,5,1,5,6);
        setupMove(new_board,5,1,4,6);
        setupRemove(new_board,5,0);
        setupRemove(new_board,4,1);
        setupMove(new_board, 6,6,4,1);


        //((Rook)new_board.getBoard().get(0).get(0)).setMoved(1);
        //((King)new_board.getBoard().get(4).get(0)).setMoved(1);
        new_board.displayBoard();
        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        System.out.println("King @ (4,7): ");
        List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(4).get(7).getLegalMoves(new_board);
        System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

        System.out.println("King @ (4,0): ");
        List<List<List<Integer>>> theoretic_moves1 = new_board.getBoard().get(4).get(0).getLegalMoves(new_board);
        System.out.println(theoretic_moves1 + "len: " + theoretic_moves1.size());

        //System.out.println("King @ (0,3): ");
        //List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(0).get(3).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

        //System.out.println("King @ (7,2): ");
        //List<List<List<Integer>>> theoretic_moves1 = new_board.getBoard().get(7).get(2).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves1 + "len: " + theoretic_moves1.size());


    }


    public static void setupMove(Board input_board, int start_x, int start_y, int dest_x, int dest_y) {
        List<List<Integer>> move_positions = new ArrayList<>(2);
        List<Integer> start_pos = new ArrayList<>(2);
        start_pos.add(start_x);
        start_pos.add(start_y);
        List<Integer> dest_pos = new ArrayList<>(2);
        dest_pos.add(dest_x);
        dest_pos.add(dest_y);
        move_positions.add(start_pos);
        move_positions.add(dest_pos);
        input_board.normalMove(move_positions);
    }


    public static void setupRemove(Board input_board, int x, int y) {
        List<Integer> remove_pos = new ArrayList<>(2);
        remove_pos.add(x);
        remove_pos.add(y);
        input_board.remove(remove_pos);
    }

}
