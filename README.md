- Summary:

    - This is a command-line Chess game implemented with a multi-threaded AI player algorithm using Java.
    - The following Chess features are implemented:
        - all piece-specific rules including castling, en-passant, promotion
        - checkmate & stalemate detection
        - threefold repetition rule
    

- How to run the game:

    - alternative 1:
        - please have Java 16.0.2 or newer installed on your system 
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


- Time complexity:

    - The main algorthim responsible for calculating a move for the AI (black) is encompassed in the function AI.getAIMoveMaxRecursion.
    - It is a decrease & conquer recursive algorthim with a time complexity of O(m * max(m,n)) where: 
      - n = # of pieces remaining which belong to AI 
      - m = # of pieces remaining which belong to player
    - Below is the calculation of the time complexity of the function AI.getAIMoveMaxRecursion using aggregate analysis:

    - Time analysis for contained helper functions: 
      - T(AI.aiReverseMockMove) = T(AI.aiMockNormalMove) = T(AI.aiMockMove) = O(1)
      - T(Board.getAllLegalMoves) = O(n)
      - T(Board.underThreat) = O(m) 

    - Inputs n, m size progression: 
      - m strictly decreases like DC algorithms, but at a variable rate of either 1 or 0
      - n stays constant

    - Overall time complexity calculation with the above sub-analysis and properties taken into account:

      - T(n,m) = T(Board.getAllLegalMoves) + T(Board.underThreat) +
                 T(AI.aiReverseMockMove) + T(AI.aiMockNormalMove) + 
                 T(AI.aiMockMove) + T(n,m - 1)

      - when m > 0:
        - T(n,m) = O(n) + O(m) + O(1) + O(1) + O(1) + T(n,m - 1) 
	             = n + m + T(n,m - 1) 

      - when m = 0: (base case: return move that "captures" player's king, the sole piece in this hypothetical base case situation)
        - T(n,m) = c = O(1)

	  - Calculation of closed form time complexity:
        T(n,m) = n + m + T(n,m - 1)               
			   = n + m + (n + m-1 + T(n,m - 2))
			   = 2n + 2m - 1 + T(n, m - 2)			  // h = 2 (h = recursion level)
			   = 2n + 2m - 1 + n + m - 2 + T(n, m - 3)
			   = 3n + 3m - 3 + T(n, m - 3)            // h = 3
			   = 3n + 3m - 3 + n + m - 3 + T(n, m - 4)
			   = 4n + 4m - 6 + T(n, m - 4)            // h = 4
			   = hn + hm - (h-1)h/2 + T(n, m - h)     // h = h
			   = m(n+m) - (m-1)m/2 + T(n, 0) 		  // h = m 
			   = (2mn + 2m^2 - (m^2 - m))/2           // T(n,0) = c = O(1)
			   = (2mn + m + m^2)/2
			   = O(mn + m^2)
			   = O(m * max(m,n))
