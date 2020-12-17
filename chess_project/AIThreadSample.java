package chess_project;
import chess_project.pieces.*;

import java.nio.file.LinkPermission;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class AIThreadSample implements Runnable {

    Thread t;
    private int test_int = 999;
    private List<Integer> input_list;

    public AIThreadSample(List<Integer> list) {
        t = new Thread(this);
        System.out.println("New thread: " + t);
        this.input_list = list;
        t.start(); // Starting the thread
        //return test_int;
    }

    // execution of thread starts from run() method
    public void run() {
        int finished = 0;
        while (!Thread.interrupted()) {
            System.out.println("Thread is running");
            //finished = Main.testGetAIMoveNewBoard();

            // interrupt either terminates thread BEFORE / AFTER calling Main.testGetAIMoveNewBoard()
            // - not in the middle of executing the call

            input_list.add(test_int);
            System.out.println("Thread: added test_int into input_list");

            int main_test_return_value = Main.testGetAIMoveNewBoard();

            if (main_test_return_value == 1) {
                input_list.add(main_test_return_value);
                break;
            }

        }
        System.out.println("Thread has stopped.");
    }




}
