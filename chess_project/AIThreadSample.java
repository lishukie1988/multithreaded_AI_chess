package chess_project;
import chess_project.pieces.*;

import java.nio.file.LinkPermission;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class AIThreadSample implements Runnable {

    Thread t;

    public AIThreadSample() {
        t = new Thread(this);
        System.out.println("New thread: " + t);
        t.start(); // Starting the thread
    }

    // execution of thread starts from run() method
    public void run() {
        int finished = 0;
        while (!Thread.interrupted() || finished != 1) {
            //System.out.println("Thread is running");
            //finished = Main.testGetAIMoveNewBoard();

            if (Main.testGetAIMoveNewBoard() == 1) {
                break;
            }


            //Main.testGetAIMoveNewBoard();



            //finished = 1;
            //System.out.println("finished = " + finished);
            /*
            int x = 0;
            while(x < 9999999) {
                System.out.println(x);
                x++;
            }

             */
        }
        System.out.println("Thread has stopped.");
    }




}
