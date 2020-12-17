package chess_project;
import chess_project.pieces.*;

import java.nio.file.LinkPermission;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class SampleJoinedThread implements Runnable {

    private int test_int = 0;
    private List<Integer> test_list;


    public SampleJoinedThread(int input_int, List<Integer> input_list) {
        this.test_int = input_int;
        this.test_list = input_list;

    }

    public void run() {
        System.out.println("This code is running in a thread");
        int x = 0;
        //fetched_move = AI.getAIMoveMaxRecursion(null, 0, max_recursion, input_board, safe_dest_moves);

    }
}