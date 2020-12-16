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
        //testKingLegal();
        //testCastlingMove();
        //testPassantMove();
        //testPromotionMove();
        //testTwoSquareMove();
        //testMakeMoveTwoSquare();
        //testMakeMovePromotion();
        //testMakeMoveCastling();
        //testMakeMovePassant();
        //testGetAllLegalMoves();
        //testGetAllLegalMoves2();
        //testGetAIMove1To2Recursions();
        //testGetAIMoveNewBoard();
        //testGetAIMoveNoKnight();
        //testGetAIMoveNoKnightNoQueen();
        //testGetAIMoveOnlyPawn();
        //testGetAIMoveOnePawn();
        //testGetAIMoveOnlyKing();
        //testGetUserInput();
        testPlayGame();
        //testUnderThreat();

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
        //setupRemove(new_board,0,0);
        //setupRemove(new_board,0,7);
        //setupRemove(new_board,7,7);
        setupRemove(new_board,3,6);
        setupRemove(new_board,4,6);
        setupRemove(new_board,5,6);

        //setupMove(new_board,5,1,5,6);
        setupMove(new_board,5,1,4,5);
        setupRemove(new_board,5,0);
        setupRemove(new_board,4,1);
        setupMove(new_board, 6,6,4,2);


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

    public static void testCastlingMove() {

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
        setupMove(new_board, 6,6,4,2);


        //((Rook)new_board.getBoard().get(0).get(0)).setMoved(1);
        //((King)new_board.getBoard().get(4).get(0)).setMoved(1);

        new_board.displayBoard();
        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());


        System.out.println("King @ (4,7): ");
        List<List<List<Integer>>> legal_moves = new_board.getBoard().get(4).get(7).getLegalMoves(new_board);
        System.out.println(legal_moves + "len: " + legal_moves.size());

        System.out.println("King @ (4,0): ");
        List<List<List<Integer>>> legal_moves1 = new_board.getBoard().get(4).get(0).getLegalMoves(new_board);
        System.out.println(legal_moves1 + "len: " + legal_moves1.size());

        new_board.castlingMove(legal_moves1.get(3));
        new_board.displayBoard();

        System.out.println(((King) new_board.getBoard().get(6).get(0)).getPosition());
        System.out.println(((King) new_board.getBoard().get(6).get(0)).getMoved());
        System.out.println(new_board.getPlayerPieces(2).get(0).getPosition());
        System.out.println(((Rook) new_board.getBoard().get(5).get(0)).getPosition());
        System.out.println(((Rook) new_board.getBoard().get(5).get(0)).getMoved());


        System.out.println(((King) new_board.getBoard().get(4).get(7)).getPosition());
        System.out.println(((King) new_board.getBoard().get(4).get(7)).getMoved());

        System.out.println(((Rook) new_board.getBoard().get(7).get(7)).getPosition());
        System.out.println(((Rook) new_board.getBoard().get(7).get(7)).getMoved());

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        //System.out.println("King @ (0,3): ");
        //List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(0).get(3).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

        //System.out.println("King @ (7,2): ");
        //List<List<List<Integer>>> theoretic_moves1 = new_board.getBoard().get(7).get(2).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves1 + "len: " + theoretic_moves1.size());


    }

    public static void testMakeMoveCastling() {


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
        setupMove(new_board, 6,6,4,2);


        //((Rook)new_board.getBoard().get(0).get(0)).setMoved(1);
        //((King)new_board.getBoard().get(4).get(0)).setMoved(1);

        new_board.displayBoard();
        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());


        System.out.println("King @ (4,7): ");
        List<List<List<Integer>>> legal_moves = new_board.getBoard().get(4).get(7).getLegalMoves(new_board);
        System.out.println(legal_moves + "len: " + legal_moves.size());

        System.out.println("King @ (4,0): ");
        List<List<List<Integer>>> legal_moves1 = new_board.getBoard().get(4).get(0).getLegalMoves(new_board);
        System.out.println(legal_moves1 + "len: " + legal_moves1.size());

        new_board.makeMove(legal_moves1.get(3));
        new_board.displayBoard();

        System.out.println(((King) new_board.getBoard().get(6).get(0)).getPosition());
        System.out.println(((King) new_board.getBoard().get(6).get(0)).getMoved());
        System.out.println(new_board.getPlayerPieces(2).get(0).getPosition());
        System.out.println(((Rook) new_board.getBoard().get(5).get(0)).getPosition());
        System.out.println(((Rook) new_board.getBoard().get(5).get(0)).getMoved());


        System.out.println(((King) new_board.getBoard().get(4).get(7)).getPosition());
        System.out.println(((King) new_board.getBoard().get(4).get(7)).getMoved());

        System.out.println(((Rook) new_board.getBoard().get(7).get(7)).getPosition());
        System.out.println(((Rook) new_board.getBoard().get(7).get(7)).getMoved());

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        //System.out.println("King @ (0,3): ");
        //List<List<List<Integer>>> theoretic_moves = new_board.getBoard().get(0).get(3).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves + "len: " + theoretic_moves.size());

        //System.out.println("King @ (7,2): ");
        //List<List<List<Integer>>> theoretic_moves1 = new_board.getBoard().get(7).get(2).getTheoreticMoves(new_board);
        //System.out.println(theoretic_moves1 + "len: " + theoretic_moves1.size());


    }

    public static void testPassantMove() {

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

        setupMove(new_board, 4,1,5,5);
        ((Pawn)new_board.getBoard().get(5).get(5)).setMoved(1);

        setupMove(new_board, 7,1,7,2);
        ((Pawn)new_board.getBoard().get(7).get(2)).setMoved(1);
        setupMove(new_board, 1,1,1,3);
        ((Pawn)new_board.getBoard().get(1).get(3)).setSpecialTurnNumber(0);
        ((Pawn)new_board.getBoard().get(1).get(3)).setMoved(1);
        setupMove(new_board, 0,6,0,3);
        ((Pawn)new_board.getBoard().get(0).get(3)).setMoved(1);;

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

        // ********* GETLEGALMOVES instead of GETTHEORETICMOVES
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

        new_board.enPassantMove(theoretic_moves8.get(1));
        new_board.displayBoard();
        System.out.println(new_board.getBoard().get(1).get(2).getPosition());
        System.out.println(((Pawn) new_board.getBoard().get(1).get(2)).getMoved());
        System.out.println(((Pawn) new_board.getBoard().get(1).get(2)).getSpecialTurnNumber());

        System.out.println("Pawn @ (2,4) special: " + ((Pawn) new_board.getBoard().get(2).get(4)).getSpecialTurnNumber());

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

    }

    public static void testMakeMovePassant() {

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

        setupMove(new_board, 4,1,5,5);
        ((Pawn)new_board.getBoard().get(5).get(5)).setMoved(1);

        setupMove(new_board, 7,1,7,2);
        ((Pawn)new_board.getBoard().get(7).get(2)).setMoved(1);
        setupMove(new_board, 1,1,1,3);
        ((Pawn)new_board.getBoard().get(1).get(3)).setSpecialTurnNumber(0);
        ((Pawn)new_board.getBoard().get(1).get(3)).setMoved(1);
        setupMove(new_board, 0,6,0,3);
        ((Pawn)new_board.getBoard().get(0).get(3)).setMoved(1);;

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

        // ********* GETLEGALMOVES instead of GETTHEORETICMOVES
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

        new_board.makeMove(theoretic_moves8.get(1));
        new_board.displayBoard();
        System.out.println(new_board.getBoard().get(1).get(2).getPosition());
        System.out.println(((Pawn) new_board.getBoard().get(1).get(2)).getMoved());
        System.out.println(((Pawn) new_board.getBoard().get(1).get(2)).getSpecialTurnNumber());

        System.out.println("Pawn @ (2,4) special: " + ((Pawn) new_board.getBoard().get(2).get(4)).getSpecialTurnNumber());

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

    }

    public static void testPromotionMove() {

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

        System.out.println("Pawn @ (5,6): ");
        List<List<List<Integer>>> theoretic_moves11 = new_board.getBoard().get(5).get(6).getLegalMoves(new_board);
        System.out.println(theoretic_moves11 + "len: " + theoretic_moves11.size());


        new_board.promotionMove(theoretic_moves11.get(1));

        new_board.displayBoard();
        for (Piece piece : new_board.getPlayerPieces(0)) {
            if (piece.getCharacter() == "ro") {
                System.out.println(piece.getCharacter() + piece.getPosition() + "original: " + ((Rook) piece).getOriginal());
            }
        }
        System.out.println(new_board.getPlayerPieces(0).size());

    }

    public static void testMakeMovePromotion() {

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

        System.out.println("Pawn @ (5,6): ");
        List<List<List<Integer>>> theoretic_moves11 = new_board.getBoard().get(5).get(6).getLegalMoves(new_board);
        System.out.println(theoretic_moves11 + "len: " + theoretic_moves11.size());


        new_board.makeMove(theoretic_moves11.get(1));

        new_board.displayBoard();
        for (Piece piece : new_board.getPlayerPieces(0)) {
            if (piece.getCharacter() == "ro") {
                System.out.println(piece.getCharacter() + piece.getPosition() + "original: " + ((Rook) piece).getOriginal());
            }
        }
        System.out.println(new_board.getPlayerPieces(0).size());

    }

    public static void testTwoSquareMove() {

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

        System.out.println("Pawn @ (5,1): ");
        List<List<List<Integer>>> theoretic_moves11 = new_board.getBoard().get(5).get(1).getLegalMoves(new_board);
        System.out.println(theoretic_moves11 + "len: " + theoretic_moves11.size());

        new_board.twoSquarePawnMove(theoretic_moves11.get(1));
        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        System.out.println(new_board.getBoard().get(5).get(3).getPosition());
        System.out.println(((Pawn) new_board.getBoard().get(5).get(3)).getSpecialTurnNumber());
        System.out.println(((Pawn) new_board.getBoard().get(5).get(3)).getMoved());

        System.out.println(new_board.getBoard().get(4).get(3).getPosition());
        System.out.println(((Pawn) new_board.getBoard().get(4).get(3)).getSpecialTurnNumber());
        System.out.println(((Pawn) new_board.getBoard().get(4).get(3)).getMoved());
    }

    public static void testMakeMoveTwoSquare() {



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

        System.out.println("Pawn @ (5,1): ");
        List<List<List<Integer>>> theoretic_moves11 = new_board.getBoard().get(5).get(1).getLegalMoves(new_board);
        System.out.println(theoretic_moves11 + "len: " + theoretic_moves11.size());

        System.out.println(theoretic_moves11.get(1));

        new_board.makeMove(theoretic_moves11.get(1));
        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());

        System.out.println(new_board.getBoard().get(5).get(3).getPosition());
        System.out.println(((Pawn) new_board.getBoard().get(5).get(3)).getSpecialTurnNumber());
        System.out.println(((Pawn) new_board.getBoard().get(5).get(3)).getMoved());

        System.out.println(new_board.getBoard().get(4).get(3).getPosition());
        System.out.println(((Pawn) new_board.getBoard().get(4).get(3)).getSpecialTurnNumber());
        System.out.println(((Pawn) new_board.getBoard().get(4).get(3)).getMoved());
    }

    public static void testGetAllLegalMoves() {

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



        System.out.println("All legal moves of player white: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(0);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());


    }

    public static void testGetAllLegalMoves2() {

        Board new_board = new Board();
        new_board.displayBoard();
        System.out.println("All legal moves of player white: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(0);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

    }

    public static void testGetAIMove1To2Recursions() {

        Board new_board = new Board();

        setupRemove(new_board, 4, 6);
        setupRemove(new_board, 5,1);
        //new_board.displayBoard();
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


        setupMove(new_board, 5,6,5,3);
        //((Pawn)new_board.getBoard().get(4).get(3)).setSpecialTurnNumber(0);
        ((Pawn)new_board.getBoard().get(5).get(3)).setMoved(1);

        setupMove(new_board, 4,1,5,5);
        ((Pawn)new_board.getBoard().get(5).get(5)).setMoved(1);

        setupMove(new_board, 7,1,7,2);
        ((Pawn)new_board.getBoard().get(7).get(2)).setMoved(1);
        setupMove(new_board, 1,1,1,3);
        ((Pawn)new_board.getBoard().get(1).get(3)).setSpecialTurnNumber(0);
        ((Pawn)new_board.getBoard().get(1).get(3)).setMoved(1);
        setupMove(new_board, 0,6,0,3);
        ((Pawn)new_board.getBoard().get(0).get(3)).setMoved(1);
        setupMove(new_board, 3,1,4,1);
        ((Pawn)new_board.getBoard().get(4).get(1)).setMoved(1);


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());



        /*
        System.out.println("All legal moves of player white: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(0);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

         */

        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

        /*
        List<List<Integer>> fetched_ai_move = AI.getAIMoveMaxRecursion(null, 0, 0, new_board);

        System.out.println("fetched ai move with max recursion depth = 0: ");
        System.out.println(fetched_ai_move);

         */

        /*
        List<List<Integer>> fetched_ai_move = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1: ");
        System.out.println(fetched_ai_move);


         */

        List<List<Integer>> fetched_ai_move = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move);


        // repeat of command before ai move call

        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());


        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves2 = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves2 + "len: " + all_legal_moves2.size());

    }

    public static void testGetAIMoveNewBoard() {

        Board new_board = new Board();


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());



        /*
        System.out.println("All legal moves of player white: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(0);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

         */

        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

        List<List<List<Integer>>> safe_dest_moves = new ArrayList<>();
        List<List<Integer>> fetched_ai_move = AI.getAIMoveMaxRecursion(null, 0, 0, new_board, safe_dest_moves);

        System.out.println("fetched ai move with max recursion depth = 0: ");
        System.out.println(fetched_ai_move);


        /*

        List<List<Integer>> fetched_ai_move2 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1: ");
        System.out.println(fetched_ai_move2);


        List<List<Integer>> fetched_ai_move4 = AI.getAIMoveMaxRecursion(null, 0, 2, new_board);

        System.out.println("fetched ai move with max recursion depth = 2 (just enough to derive in check kn move): ");
        System.out.println(fetched_ai_move4);


        List<List<Integer>> fetched_ai_move5 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1 (just under depth needed to derive in check kn move, ie = null): ");
        System.out.println(fetched_ai_move5);

         */

        List<List<Integer>> fetched_ai_move3 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move3);




        // repeat of command before ai move call

        /*
        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());


        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves2 = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves2 + "len: " + all_legal_moves2.size());

         */

    }


    public static void testGetAIMoveNoKnight() {

        Board new_board = new Board();


        setupRemove(new_board,1,7);
        setupRemove(new_board,6,7);

        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());



        /*
        System.out.println("All legal moves of player white: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(0);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

         */

        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

        List<List<List<Integer>>> safe_dest_moves = new ArrayList<>();
        List<List<Integer>> fetched_ai_move = AI.getAIMoveMaxRecursion(null, 0, 0, new_board, safe_dest_moves);

        System.out.println("fetched ai move with max recursion depth = 0: ");
        System.out.println(fetched_ai_move);


        /*

        List<List<Integer>> fetched_ai_move2 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1: ");
        System.out.println(fetched_ai_move2);


        List<List<Integer>> fetched_ai_move4 = AI.getAIMoveMaxRecursion(null, 0, 2, new_board);

        System.out.println("fetched ai move with max recursion depth = 2 (just enough to derive in check kn move): ");
        System.out.println(fetched_ai_move4);


        List<List<Integer>> fetched_ai_move5 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1 (just under depth needed to derive in check kn move, ie = null): ");
        System.out.println(fetched_ai_move5);

         */

        List<List<Integer>> fetched_ai_move3 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move3);




        // repeat of command before ai move call


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());


        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves2 = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves2 + "len: " + all_legal_moves2.size());

        List<List<Integer>> fetched_ai_move4 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move4);


    }

    public static void testGetAIMoveNoKnightNoQueen() {

        Board new_board = new Board();


        setupRemove(new_board,1,7);
        setupRemove(new_board,6,7);
        setupRemove(new_board,3,7);


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());



        /*
        System.out.println("All legal moves of player white: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(0);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

         */


        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());


        List<List<List<Integer>>> safe_dest_moves = new ArrayList<>();
        List<List<Integer>> fetched_ai_move = AI.getAIMoveMaxRecursion(null, 0, 0, new_board, safe_dest_moves);

        System.out.println("fetched ai move with max recursion depth = 0: ");
        System.out.println(fetched_ai_move);


        /*

        List<List<Integer>> fetched_ai_move2 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1: ");
        System.out.println(fetched_ai_move2);


        List<List<Integer>> fetched_ai_move4 = AI.getAIMoveMaxRecursion(null, 0, 2, new_board);

        System.out.println("fetched ai move with max recursion depth = 2 (just enough to derive in check kn move): ");
        System.out.println(fetched_ai_move4);


        List<List<Integer>> fetched_ai_move5 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1 (just under depth needed to derive in check kn move, ie = null): ");
        System.out.println(fetched_ai_move5);

         */

        List<List<Integer>> fetched_ai_move3 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move3);




        // repeat of command before ai move call


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());


        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves2 = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves2 + "len: " + all_legal_moves2.size());

        List<List<Integer>> fetched_ai_move4 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move4);


    }

    public static void testGetAIMoveOnlyPawn() {

        Board new_board = new Board();


        setupRemove(new_board,1,7);
        setupRemove(new_board,2,7);
        setupRemove(new_board,3,7);
        setupRemove(new_board,5,7);
        setupRemove(new_board,6,7);
        setupRemove(new_board,7,7);
        setupRemove(new_board,0,7);

        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());



        /*
        System.out.println("All legal moves of player white: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(0);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

         */

        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());


        List<List<List<Integer>>> safe_dest_moves = new ArrayList<>();
        List<List<Integer>> fetched_ai_move = AI.getAIMoveMaxRecursion(null, 0, 0, new_board, safe_dest_moves);

        System.out.println("fetched ai move with max recursion depth = 0: ");
        System.out.println(fetched_ai_move);


        /*

        List<List<Integer>> fetched_ai_move2 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1: ");
        System.out.println(fetched_ai_move2);


        List<List<Integer>> fetched_ai_move4 = AI.getAIMoveMaxRecursion(null, 0, 2, new_board);

        System.out.println("fetched ai move with max recursion depth = 2 (just enough to derive in check kn move): ");
        System.out.println(fetched_ai_move4);


        List<List<Integer>> fetched_ai_move5 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1 (just under depth needed to derive in check kn move, ie = null): ");
        System.out.println(fetched_ai_move5);

         */

        List<List<Integer>> fetched_ai_move3 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move3);




        // repeat of command before ai move call


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());


        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves2 = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves2 + "len: " + all_legal_moves2.size());

        List<List<Integer>> fetched_ai_move4 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move4);


    }

    public static void testGetAIMoveOnePawn() {

        Board new_board = new Board();


        setupRemove(new_board,1,7);
        setupRemove(new_board,2,7);
        setupRemove(new_board,3,7);
        setupRemove(new_board,5,7);
        setupRemove(new_board,6,7);
        setupRemove(new_board,7,7);
        setupRemove(new_board,0,7);

        //setupRemove(new_board,1,6);
        setupRemove(new_board,2,6);
        setupRemove(new_board,3,6);
        setupRemove(new_board,5,6);
        setupRemove(new_board,6,6);
        setupRemove(new_board,7,6);
        setupRemove(new_board,0,6);
        setupRemove(new_board,4,6);

        //setupMove(new_board,3,7,2,3);
        //((King)new_board.getBoard().get(2).get(3)).setMoved(1);


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());



        /*
        System.out.println("All legal moves of player white: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(0);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

         */

        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

        List<List<List<Integer>>> safe_dest_moves = new ArrayList<>();
        List<List<Integer>> fetched_ai_move = AI.getAIMoveMaxRecursion(null, 0, 0, new_board, safe_dest_moves);

        System.out.println("fetched ai move with max recursion depth = 0: ");
        System.out.println(fetched_ai_move);


        /*

        List<List<Integer>> fetched_ai_move2 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1: ");
        System.out.println(fetched_ai_move2);


        List<List<Integer>> fetched_ai_move4 = AI.getAIMoveMaxRecursion(null, 0, 2, new_board);

        System.out.println("fetched ai move with max recursion depth = 2 (just enough to derive in check kn move): ");
        System.out.println(fetched_ai_move4);


        List<List<Integer>> fetched_ai_move5 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1 (just under depth needed to derive in check kn move, ie = null): ");
        System.out.println(fetched_ai_move5);

         */

        List<List<Integer>> fetched_ai_move3 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move3);




        // repeat of command before ai move call


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());


        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves2 = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves2 + "len: " + all_legal_moves2.size());

        List<List<Integer>> fetched_ai_move4 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move4);


    }

    public static void testGetAIMoveOnlyKing() {

        Board new_board = new Board();


        setupRemove(new_board,1,7);
        setupRemove(new_board,2,7);
        setupRemove(new_board,3,7);
        setupRemove(new_board,5,7);
        setupRemove(new_board,6,7);
        setupRemove(new_board,7,7);
        setupRemove(new_board,0,7);

        setupRemove(new_board,1,6);
        setupRemove(new_board,2,6);
        setupRemove(new_board,3,6);
        setupRemove(new_board,5,6);
        setupRemove(new_board,6,6);
        setupRemove(new_board,7,6);
        setupRemove(new_board,0,6);
        setupRemove(new_board,4,6);

        setupMove(new_board,4,7,2,3);
        ((King)new_board.getBoard().get(2).get(3)).setMoved(1);


        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());



        /*
        System.out.println("All legal moves of player white: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(0);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());

         */

        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves + "len: " + all_legal_moves.size());


        /*
        List<List<Integer>> fetched_ai_move = AI.getAIMoveMaxRecursion(null, 0, 0, new_board);

        System.out.println("fetched ai move with max recursion depth = 0: ");
        System.out.println(fetched_ai_move);

         */


        /*

        List<List<Integer>> fetched_ai_move2 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1: ");
        System.out.println(fetched_ai_move2);


        List<List<Integer>> fetched_ai_move4 = AI.getAIMoveMaxRecursion(null, 0, 2, new_board);

        System.out.println("fetched ai move with max recursion depth = 2 (just enough to derive in check kn move): ");
        System.out.println(fetched_ai_move4);


        List<List<Integer>> fetched_ai_move5 = AI.getAIMoveMaxRecursion(null, 0, 1, new_board);

        System.out.println("fetched ai move with max recursion depth = 1 (just under depth needed to derive in check kn move, ie = null): ");
        System.out.println(fetched_ai_move5);

         */


        // king not iterating through basic legal moves properly
        // getAIMoveMaxRecursion not making mock move for succeeding recursion depth
        //  - ie staying @ root_move[0] (original position) for all recursion depths
        // test other pieces to see if root move advances for each succeeding recursion call
        List<List<Integer>> fetched_ai_move3 = AI.getAIMove(new_board);
        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move3);




        // repeat of command before ai move call


        /*
        new_board.displayBoard();

        System.out.println("white: " + new_board.getPlayerPieces(0).size());
        System.out.println("black: " + new_board.getPlayerPieces(1).size());


        System.out.println("All legal moves of player black: ");
        List<List<List<Integer>>> all_legal_moves2 = new_board.getAllLegalMoves(1);
        System.out.println(all_legal_moves2 + "len: " + all_legal_moves2.size());

        List<List<Integer>> fetched_ai_move4 = AI.getAIMove(new_board);

        System.out.println("fetched ai move with no max recursion: ");
        System.out.println(fetched_ai_move4);

         */


    }

    public static void testGetUserInput() {

        Game new_game = new Game();
        System.out.println(new_game.getUserInput());

    }

    public static void testPlayGame() {

        Game new_game = new Game();
        //System.out.println(new_game.getUserMove());
        new_game.playGame();

    }

    public static void testUnderThreat() {

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


        System.out.println("Under threat (A4):");
        System.out.println(new_board.underThreat(0, 3));

        System.out.println("Under threat (A1):");
        System.out.println(new_board.underThreat(0, 0));

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
