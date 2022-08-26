package chess_project;
import chess_project.pieces.*;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class AI {

    public static List<List<Integer>> getAIMove(Board input_board) {

        List<List<Integer>> fetched_move = null;
        List<List<List<Integer>>> safe_dest_moves = new ArrayList<>();
        int max_recursion = 0;
        do {

            //System.out.println("calling getAIMoveMaxRecursion with max_recursion = " + max_recursion);
            fetched_move = getAIMoveMaxRecursion(null, 0, max_recursion, input_board, safe_dest_moves);
            max_recursion++;
        }
        while (max_recursion < 8 && fetched_move == null);

        if (fetched_move == null) {
            if (safe_dest_moves.size() != 0) {
                fetched_move = safe_dest_moves.get(0);
            }
            else {
                fetched_move = input_board.getAllLegalMoves(1).get(0);
            }
        }

        //System.out.println("safe dest moves: " );
        //System.out.println(safe_dest_moves);

        return fetched_move;

    }


    public static List<List<Integer>> getAIMoveMT(Board input_board) {


        List<Thread> started_threads = new ArrayList<>();

        List<List<Integer>> fetched_move = null;
        List<List<List<Integer>>> fetched_moves_list = new ArrayList<>();
        List<List<List<Integer>>> safe_dest_moves = new ArrayList<>();
        //int max_recursion = 0;

        //ReentrantLock lock_fetched_moves_list = new ReentrantLock();


        for (int max_recursion = 0; max_recursion < 6; max_recursion++) {

            Board cloned = input_board.cloneBoard();
            GetAIMoveThread obj = new GetAIMoveThread(max_recursion, cloned, fetched_moves_list, safe_dest_moves);
            Thread thread_x = new Thread(obj);
            thread_x.start();
            started_threads.add(thread_x);

        }

        int current_unjoined_thread = 0;

        for (int x = 0; x < 6; x++) {
            System.out.println("AI joining thread " + x + "/5");
            try {
                started_threads.get(x).join();
                current_unjoined_thread = x + 1;
            }
            catch (Exception ex) {
            }
            if (fetched_moves_list.size() != 0) {
                break;
            }
        }

        try
        {
            for (int x = current_unjoined_thread; x < 6; x++) {
                System.out.println("AI interrupting thread " + x + "/5");
                started_threads.get(x).interrupt();
            }
        }
        catch(Exception ex)
        {
        }


        if (fetched_moves_list.size() != 0) {
            fetched_move = fetched_moves_list.get(0);
        }

        else {
            if (safe_dest_moves.size() != 0) {
                fetched_move = safe_dest_moves.get(0);
            }
            else {
                fetched_move = input_board.getAllLegalMoves(1).get(0);
            }
        }

        //System.out.println("Reached return fetched_move");


        //System.out.println("safe dest moves: " );
        //System.out.println(safe_dest_moves);

        return fetched_move;

    }

    public static synchronized void addToFetchedMovesList (List<List<List<Integer>>> input_list, List<List<Integer>> fetched_move) {
        input_list.add(fetched_move);
    }

    public static synchronized  void addToSafeDestMoves (List<List<List<Integer>>> input_list, List<List<Integer>> safe_dest_move) {
        input_list.add(safe_dest_move);
    }



    // TEMPORARILY PUBLIC, set back to PRIVATE LATER
    public static List<List<Integer>> getAIMoveMaxRecursion(List<List<Integer>> root_move, int current_recursion, int max_recursion, Board input_board, List<List<List<Integer>>> safe_dest_moves) {
        //System.out.println("current recursion depth: " + current_recursion);

        // retrieve all legal moves for black (AI) based on current board state
        List<List<List<Integer>>> legal_moves = input_board.getAllLegalMoves(1);

        // loop over each of the retrieved legal moves
        for (List<List<Integer>> move : legal_moves) {
            // make mock move with static ai method in ai class
            // returns a ReverseMove object

            // *** CHANGED TO UNDERTHREAT FROM NONPAWNUNDERTHREAT

            // check whether the start pos of the piece assoc with this move is already under threat
            int start_under_threat = input_board.underThreat(move.get(0).get(0), move.get(0).get(1));
            // *
            //int is_pawn = (input_board.getBoard().get(move.get(0).get(0)).get(move.get(0).get(1)).getCharacter() == "pa") ? 1 : 0;
            //int is_first_ten = (input_board.getTurnNumber() < 11) ? 1 : 0;
            // *
            //int first_ten_start_pawn = (is_pawn == 1 && is_first_ten == 1) ? 1 : 0;

            // make hypothetical move while storing the reciprocating move to reverse the board to it's original state later on
            ReverseMove reverse_move = aIMockMove(move, input_board);

            // *** CHANGED TO UNDERTHREAT FROM NONPAWNUNDERTHREAT

            // check whether the dest pos of the piece assoc with this move will become under threat
            int dest_under_threat = input_board.underThreat(move.get(1).get(0), move.get(1).get(1));

            // *** added mutex
            // if this hyp move is the 1st of the chain of hyp moves && dest of piece of this hyp move is safe
            // && there has so far been no moves where dest of piece is safe
            if (root_move == null && dest_under_threat == 0 && safe_dest_moves.size() == 0) {
                //safe_dest_moves.add(move);
                // add this hyp move to the list of possible moves where dest of piece is safe
                // safe_dest_moves:
                // - list (used bc of mutability) of 1 root move that doesn't put assoc piece into a dest pos that's under threat
                // - mutable, accessed by multiple threads at the same time each running this fct with a different input max recursion depth, hench the use of MUTEX
                // - could be used as fallback root move by getAIMoveMT if no base case hyp move is discovered to be reachable by any root moves within input recursion limit carried out by the multiple fcts run by multiple threads
                addToSafeDestMoves(safe_dest_moves, move);
            }

            // * base case 1: hyp move which leads to 1 of the following situations
            // if start pos of piece of current hyp move is under threat & dest pos of piece of current hyp move doesn't put piece under threat @ dest pos
            // or
            // if dest pos of piece of current hyp move doesn't put piece under threat & puts opponent (human) in check
            if ( ( start_under_threat == 1 && dest_under_threat == 0) || (dest_under_threat == 0 && input_board.inCheck(0) == 1)) {
                //System.out.println(move.get(1));
                //System.out.println(input_board.getAllLegalMoves(0));
                aiReverseMockMove(reverse_move, input_board);
                //System.out.println("move number: " + (current_recursion + 1) + " !in check!");
                //System.out.println(move);
                // return the root move of the chain of hyp moves this hyp move belongs to
                if (root_move == null) {
                    return move;
                } else {
                    return root_move;
                }
            }

            // * recursive case
            // else if dest pos of piece of current move doesn't put piece under threat & max length of hyp move chain not reached

            else if (dest_under_threat == 0 && current_recursion < max_recursion) {
                //input_board.underThreat(move.get(1).get(0), move.get(1).get(1));
                //System.out.println("before recursing current move to the next level: ");
                //System.out.println(move.get(1));
                //System.out.println("piece @ move[1] = ");
                //System.out.println(input_board.getBoard().get(move.get(1).get(0)).get(move.get(1).get(1)));

                //System.out.println(move);

                // repeat this function call with this current move

                List<List<Integer>> fetched_move;
                if (root_move == null) {
                    fetched_move = getAIMoveMaxRecursion(move, current_recursion + 1, max_recursion, input_board, safe_dest_moves);
                } else {
                    fetched_move = getAIMoveMaxRecursion(root_move, current_recursion + 1, max_recursion, input_board, safe_dest_moves);
                }

                aiReverseMockMove(reverse_move, input_board);

                // if recursive fct call with this move results in a non-null base case move
                if (fetched_move != null) {
                    //System.out.println("move number: " + (current_recursion + 1));
                    //System.out.println(move);
                    return fetched_move; // == root_move
                }

                //} else if (current_recursion == max_recursion) {

                // else if dest pos of this hyp move puts piece under threat
            } else {
                // restore board state & loop to next legal move in list of retrieved legal moves
                aiReverseMockMove(reverse_move, input_board);
            }
        }

        // * base case 2: if no in-check resulting root move is found within provided max recursion level
        return null;

    }


    private static ReverseMove aIMockMove(List<List<Integer>> mock_move, Board input_board) {

        ReverseMove reverse_move = null;
        // normal mock move
        if (mock_move.size() == 2) {
            reverse_move = aiMockNormalMove(mock_move, input_board);
        }
        // castling mock move
        else if (mock_move.get(2).get(0) == 0 || mock_move.get(2).get(0) == 1) {
            reverse_move = aiMockCastlingMove(mock_move, input_board);
        }
        // en passant mock move
        else if (mock_move.get(2).get(0) == 3) {
            reverse_move = aiMockEnPassantMove(mock_move, input_board);
        }
        // two square mock move
        else if (mock_move.get(2).get(0) == 4) {
            reverse_move = aiMockTwoSquareMove(mock_move, input_board);
        }
        // promotion mock move
        else if (mock_move.get(2).get(0) == 2) {
            reverse_move = aiMockPromotionMove(mock_move, input_board);
        }

        return reverse_move;
    }


    private static void aiReverseMockMove(ReverseMove reverse_move, Board input_board) {

        // normal mock move
        if (reverse_move.getMockMove().size() == 2) {
            aiReverseMockNormalMove(reverse_move, input_board);
        }
        // castling mock move
        else if (reverse_move.getMockMove().get(2).get(0) == 0 || reverse_move.getMockMove().get(2).get(0) == 1) {
            aiReverseMockCastlingMove(reverse_move, input_board);
        }
        // en passant mock move
        else if (reverse_move.getMockMove().get(2).get(0) == 3) {
            aiReverseMockEnPassantMove(reverse_move, input_board);
        }
        // two square mock move
        else if (reverse_move.getMockMove().get(2).get(0) == 4) {
            aiReverseMockTwoSquareMove(reverse_move, input_board);
        }
        // promotion mock move
        else if (reverse_move.getMockMove().get(2).get(0) == 2) {
            aiReverseMockPromotionMove(reverse_move, input_board);
        }
    }


    private static ReverseMove aiMockNormalMove(List<List<Integer>> normal_move, Board input_board) {

        int backup_mover_moved = -99;
        Piece mover_piece = input_board.getBoard().get(normal_move.get(0).get(0)).get(normal_move.get(0).get(1));
        String character = mover_piece.getCharacter();
        switch(character)
        {
            case "ro":
                backup_mover_moved = ((Rook) mover_piece).getMoved();
                break;
            case "ki":
                backup_mover_moved = ((King) mover_piece).getMoved();
                break;
            case "pa":
                backup_mover_moved = ((Pawn) mover_piece).getMoved();
                break;
        }

        Piece dest_piece = input_board.getBoard().get(normal_move.get(1).get(0)).get(normal_move.get(1).get(1));
        ReverseMove reverse_move = new ReverseMove(normal_move, backup_mover_moved, dest_piece);
        input_board.normalMove(normal_move);
        input_board.increaseTurnNumber();
        return reverse_move;
    }


    private static void aiReverseMockNormalMove(ReverseMove reverse_move, Board input_board) {

        Piece mover_piece = input_board.move(reverse_move.getMockMove().get(1), reverse_move.getMockMove().get(0));
        input_board.place(reverse_move.getMockMove().get(1), reverse_move.getCapturedPiece());
        String character = mover_piece.getCharacter();
        switch(character)
        {
            case "ro":
                ((Rook) mover_piece).setMoved(reverse_move.getMoverMoved());
                break;
            case "ki":
                ((King) mover_piece).setMoved(reverse_move.getMoverMoved());
                break;
            case "pa":
                ((Pawn) mover_piece).setMoved(reverse_move.getMoverMoved());
                break;
        }
        input_board.decreaseTurnNumber();
    }


    private static ReverseMove aiMockCastlingMove(List<List<Integer>> castling_move, Board input_board) {

        ReverseMove reverse_move = new ReverseMove(castling_move);
        input_board.castlingMove(castling_move);
        input_board.increaseTurnNumber();
        return reverse_move;
    }


    private static void aiReverseMockCastlingMove(ReverseMove reverse_move, Board input_board) {

        Piece king = input_board.move(reverse_move.getMockMove().get(1), reverse_move.getMockMove().get(0));
        Piece rook = input_board.move(reverse_move.getMockMove().get(4), reverse_move.getMockMove().get(3));
        ((King) king).setMoved(0);
        ((Rook) rook).setMoved(0);
        input_board.decreaseTurnNumber();
    }


    private static ReverseMove aiMockEnPassantMove(List<List<Integer>> en_passant_move, Board input_board) {

        Piece captured_pawn = input_board.getBoard().get(en_passant_move.get(3).get(0)).get(en_passant_move.get(3).get(1));
        ReverseMove reverse_move = new ReverseMove(en_passant_move, captured_pawn);
        input_board.enPassantMove(en_passant_move);
        input_board.increaseTurnNumber();
        return reverse_move;
    }


    private static void aiReverseMockEnPassantMove(ReverseMove reverse_move, Board input_board) {

        Piece player_pawn = input_board.move(reverse_move.getMockMove().get(1), reverse_move.getMockMove().get(0));
        input_board.place(reverse_move.getMockMove().get(3), reverse_move.getCapturedPiece());
        input_board.decreaseTurnNumber();

    }


    private static ReverseMove aiMockPromotionMove(List<List<Integer>> promotion_move, Board input_board) {

        Piece player_pawn = input_board.getBoard().get(promotion_move.get(0).get(0)).get(promotion_move.get(0).get(1));
        Piece dest_piece = input_board.getBoard().get(promotion_move.get(1).get(0)).get(promotion_move.get(1).get(1));
        ReverseMove reverse_move = new ReverseMove(promotion_move, player_pawn, dest_piece);
        input_board.promotionMoveAI(promotion_move);
        input_board.increaseTurnNumber();
        return reverse_move;

    }


    private static void aiReverseMockPromotionMove(ReverseMove reverse_move, Board input_board) {

        input_board.remove(reverse_move.getMockMove().get(1));
        input_board.place(reverse_move.getMockMove().get(1), reverse_move.getCapturedPiece());
        input_board.place(reverse_move.getMockMove().get(0), reverse_move.getMoverPiece());
        input_board.decreaseTurnNumber();

    }


    private static ReverseMove aiMockTwoSquareMove(List<List<Integer>> two_square_move, Board input_board) {

        ReverseMove reverse_move = new ReverseMove(two_square_move);
        input_board.twoSquarePawnMove(two_square_move);
        input_board.increaseTurnNumber();
        return reverse_move;
    }


    private static void aiReverseMockTwoSquareMove(ReverseMove reverse_move, Board input_board) {

        Piece pawn = input_board.move(reverse_move.getMockMove().get(1), reverse_move.getMockMove().get(0));
        ((Pawn) pawn).setMoved(0);
        ((Pawn) pawn).setSpecialTurnNumber(-99);
        input_board.decreaseTurnNumber();
    }


}