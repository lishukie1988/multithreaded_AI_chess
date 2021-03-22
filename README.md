- Summary:
    - This is a multithreaded command-line Chess game implemented with an AI player using Java.
    - The following Chess features are implemented:
        - all piece-specific rules including castling, en-passant, promotion
        - checkmate & stalemate detection
        - threefold repetition rule
    

- How to run the game:
    - alternative 1:
      - please have Java installed on your system 
      - in a terminal window, run the "chess.jar" file in the same directory as this README file using the following command: "java -jar chess.jar"
    - alternative 2:
      - using a Java IDE, run the main function inside Main.java
    

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