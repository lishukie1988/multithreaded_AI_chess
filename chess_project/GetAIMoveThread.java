package chess_project;

import chess_project.pieces.*;

import java.nio.file.LinkPermission;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class GetAIMoveThread implements Runnable {

    //private int test_int = 0;
    private Board test_board;
    private List<List<List<Integer>>> fetched_moves_list;
    private List<List<List<Integer>>> safe_dest_moves;
    private int max_recursion;


    public GetAIMoveThread(int input_max_recursion, Board input_board, List<List<List<Integer>>> fetched_moves_list, List<List<List<Integer>>> safe_dest_moves) {
        this.test_board = input_board;
        this.fetched_moves_list = fetched_moves_list;
        this.max_recursion = input_max_recursion;
        this.safe_dest_moves = safe_dest_moves;

    }

    /*
    public void run() {
        System.out.println("This code is running in a thread");

        List<List<Integer>> fetched_move = AI.getAIMoveMaxRecursion(null, 0, this.test_int, this.test_board, this.safe_dest_moves);
        this.test_list.add(fetched_move);

    }


     */

    public void run() {
        while (!Thread.interrupted()) {
            //System.out.println("This code is running in a thread");

            List<List<Integer>> fetched_move = AI.getAIMoveMaxRecursion(null, 0, this.max_recursion, this.test_board, this.safe_dest_moves);
            if (fetched_move != null) {
                //System.out.println("Inside thread, finishedcalling getAIMoveRecursion, fetched move: ");
                //System.out.println(fetched_move);

                // ****** commented out for now to test mutex
                //test_list.add(fetched_move);
                // ****** mutex protected (synchronized) list appending
                AI.addToFetchedMovesList(this.fetched_moves_list, fetched_move);

            }

            //test_list.add(AI.getAIMoveMaxRecursion(null, 0, this.test_int, this.test_board, this.safe_dest_moves));
            //System.out.println("thread finished, exiting");
            break;
            //List<List<Integer>> fetched_move = AI.getAIMoveMaxRecursion(null, 0, this.test_int, this.test_board, this.safe_dest_moves);
            //this.test_list.add(fetched_move);
        }

    }


}