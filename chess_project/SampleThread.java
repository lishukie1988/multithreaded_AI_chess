package chess_project;
import chess_project.pieces.*;

import java.nio.file.LinkPermission;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;



public class SampleThread implements Runnable {

    Thread t;

    public SampleThread() {
        t = new Thread(this);
        System.out.println("New thread: " + t);
        t.start(); // Starting the thread
    }

    // execution of thread starts from run() method
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Thread is running");
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
