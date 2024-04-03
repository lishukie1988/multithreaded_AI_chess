- Summary:

    - This is a command-line Chess game implemented with a multi-threaded AI player algorithm using Java.
    - The following Chess features are implemented:
        - all piece-specific rules including castling, en-passant, promotion
        - checkmate & stalemate detection
        - threefold repetition rule
    
- Demo Screenshots:
    - Player (white) move 1 selection:
        - ![Screenshot 2024-04-03 at 2 29 33 PM](https://github.com/lishukie6588/multithreaded_AI_chess/assets/59763257/d849606a-2950-4044-9377-f766dc1937c9)
    - AI (black) move 1 selection:
        - ![Screenshot 2024-04-03 at 2 30 01 PM](https://github.com/lishukie6588/multithreaded_AI_chess/assets/59763257/5d09c911-21fe-43d1-bc29-8d02d72473d2)
    - Player (white) move 2 selection:
        - ![Screenshot 2024-04-03 at 2 30 47 PM](https://github.com/lishukie6588/multithreaded_AI_chess/assets/59763257/42b16bef-46a9-4a1c-a2d0-e9f9988ceaf7)
    - AI (black) move 2 selection & Player (white) is in check:
        - ![Screenshot 2024-04-03 at 2 31 50 PM](https://github.com/lishukie6588/multithreaded_AI_chess/assets/59763257/7e165486-2ba2-4aeb-a19e-eb0ea908c6e9)

- How to run the game:

    - alternative 1:
        - please have Java JDK 16.0.2 or newer installed on your system 
        - in a terminal window, run the "chess.jar" file in the same directory as this README file using the following command: "java -jar chess.jar"
    - alternative 2:
        - using a Java IDE configured with Java 16.0.2, run the main function inside Main.java
    

- How to play the game:

    - the user is White and the AI player is Black
    - the user is prompted to make the first move by entering:
        - the start position of the move (a piece belonging to the user)
        - the destination position of the move (a desired destination square)
    - the AI player (Black) then makes its move and the updated state of the chess board is displayed
    - if an opponent is put in check after a move is made by either player, it will be announced in the console 
    - the previous steps are repeated until one of the following situations occur:
        - stalemate is detected, in which case a message will be displayed and the program terminates
        - checkmate is detected, in which case the winner is announced and the program terminates
        - the game draws by the threefold repetition rule: the state of the Chess board remains unchanged after 3 turns


- Time complexity: Please see time_complexity.txt for detailed analysis
