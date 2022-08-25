package chess_project;
import chess_project.pieces.*;

import java.lang.reflect.Array;
import java.util.*;

public class Game {

    private Board chess_board;
    private List<List<Integer>> color_board = new ArrayList<>(8);
    private int in_check;
    private int player_turn;
    private String game_state;
    private MovesList moves_list;
    private BoardStates board_states;

    public Game() {
        this.chess_board = new Board();
        for (int x = 0; x < 8; x++) {
            int bottom_color = (x % 2 == 0) ? 1 : 0;
            List<Integer> new_row = new ArrayList<>(8);
            for (int y = 0; y < 8; y++ ) {
                new_row.add(bottom_color % 2);
                bottom_color++;
            }
            this.color_board.add(new_row);
        }
        this.in_check = -99;
        this.player_turn = 0;
        this.game_state = "active";
        this.moves_list = new MovesList();
        this.board_states = new BoardStates();
    }


    public void displayColorBoard() {
        for (int y = 7; y > -1; y--) {
            for (int x = 0; x < 8; x++) {
                System.out.printf("---" + this.color_board.get(x).get(y) + "---");
            }
            System.out.printf("%n");
        }
    }


    public Board cloneBoardState() {
        return null;
    }


    public int deadPositions() {
        return 0;
    }

/*

    public void makeGameMove() {

        if (this.game_state != "active") {
            System.out.println("Game has ended!");
        }

        // check for threefold repetition

        // prompt user for start / dest positions
        List<Integer> user_move = getMoveFromUser();
        System.out.println(user_move);

    }


    private List<Integer> getMoveFromUser() {

        Scanner get_input = new Scanner(System.in);
        System.out.print("Please pick a starting position (ie \"c3\"): ");
        String start_pos = get_input.nextLine();
        char x_char = start_pos.charAt(0);
        int x_axis = ((int) x_char) - 97;
        String y_char = start_pos.substring(1,2);
        int y_axis = Integer.parseInt(y_char);
        //System.out.println(x_axis + "," + y_axis);
        List<Integer> int_start_pos = new ArrayList<>(2);
        int_start_pos.add(x_axis);
        int_start_pos.add(y_axis);
        return int_start_pos;


    }


 */

    private List<List<Integer>> fetchChosenMove() {

        List<List<Integer>> chosen_move;
        if (this.player_turn == 0) { // if player (white)
            chosen_move = this.getUserMove();
        }
        else { // if AI (black)
            System.out.println("Black is coming up with a move, please wait!");
            long start = System.currentTimeMillis();
            chosen_move = AI.getAIMoveMT(this.chess_board);
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println("time elapse: " + timeElapsed);
            //chosen_move = AI.getAIMoveMT(this.chess_board);
        }

        return chosen_move;

    }

    /*
    - CHANGE BACK TO PRIVATE
     */
    public List<List<Integer>> getUserMove() {

        List<List<Integer>> user_input = new ArrayList<>();
        List<List<Integer>> fetched_legal_move = new ArrayList<>();

        do {
            user_input = getUserInput();
            fetched_legal_move = validateUserMoveLegal(user_input);
        }
        while (fetched_legal_move == null);

        return fetched_legal_move;

    }


    /*
    - returns legal move corresponding to user's entered start & dest positions
    - precond:
      - within bounds
      - start piece belongs to player
      - dest pos doesn't contain player's own piece
     */
    private List<List<Integer>> validateUserMoveLegal(List<List<Integer>> input_indexes) {
        //System.out.println(input_indexes);
        //System.out.println(this.chess_board.getAllLegalMoves(0));
        for (List<List<Integer>> legal_move : this.chess_board.getAllLegalMoves(0)) {
            if (legal_move.get(0).equals(input_indexes.get(0)) && legal_move.get(1).equals(input_indexes.get(1))) {
                return legal_move;
            }
        }
        System.out.println("This is not a legal move!");
        return null;

    }


    /*
    - keep prompting user for start pos & dest pos in format "a1" "b2"
    - until a pair of indexes:
      - within bounds
      - start_pos contains a piece belonging to player (0)
      - dest_pos does not contain a piece belonging to player (0)
     */

    // ****** TEMPORARILY PUBLIC, set back to PRIVATE LATER
    public List<List<Integer>> getUserInput() {

        List<List<Integer>> fetched_user_input = new ArrayList<>(2);

        Scanner get_input = new Scanner(System.in);
        String start_pos;
        String dest_pos;
        do {
            System.out.print("Please pick a starting position (ie \"d2\"): ");
            start_pos = get_input.nextLine();
            System.out.print("Please pick a destination position (ie \"d3\"): ");
            dest_pos = get_input.nextLine();
        }
        while (this.validateInputFormat(start_pos) == 0 || this.validatePlayersPiece(start_pos) == 0 ||
                this.validateInputFormat(dest_pos) == 0 || this.validateNotPlayersPiece(dest_pos) == 0);

        List<Integer> int_start_pos = convertToIndexes(start_pos);
        fetched_user_input.add(int_start_pos);

        List<Integer> int_dest_pos = convertToIndexes(dest_pos);
        fetched_user_input.add(int_dest_pos);

        return fetched_user_input;

    }


    private int validateInputFormat(String user_input) {

        if(user_input.length() < 2) {
            System.out.println("Input start/destination indexes are in the wrong format or out of bounds!");
            return 0;
        }

        char x_char = user_input.charAt(0);
        int x_axis = ((int) x_char) - 97;
        Boolean is_digit = Character.isDigit(user_input.charAt(1));
        if (is_digit == false || x_axis < 0 || x_axis > 7) {
            System.out.println("Input start/destination indexes are in the wrong format or out of bounds!");
            return 0;
        }
        else if (is_digit == true) {
            String y_char = user_input.substring(1,2);
            int y_axis = Integer.parseInt(y_char);
            if (y_axis < 1 || y_axis > 8) {
                System.out.println("Input start/destination indexes are in the wrong format or out of bounds!");
                return 0;
            }
        }

        return 1;


    }


    /*
    - validates that input points to a piece belonging to player
    - precondition:
     - indexes within bounds
     */
    private int validatePlayersPiece(String user_input) {
        List<Integer> index_pair = convertToIndexes(user_input);
        Piece fetched_piece = this.chess_board.getBoard().get(index_pair.get(0)).get(index_pair.get(1));
        if (fetched_piece == null || fetched_piece.getPlayer() != 0) {
            System.out.println("Indexes do not point to a piece belonging to player (white)!");
            return 0;
        }
        return 1;
    }


    /*
- validates that input points to a piece that doesn't belong to player
- precondition:
 - indexes within bounds
 */
    private int validateNotPlayersPiece(String user_input) {
        List<Integer> index_pair = convertToIndexes(user_input);
        Piece fetched_piece = this.chess_board.getBoard().get(index_pair.get(0)).get(index_pair.get(1));
        if (fetched_piece != null && fetched_piece.getPlayer() == 0) {
            System.out.println("Indexes point to a piece belonging to player (white)!");
            return 0;
        }
        return 1;
    }

    /*
    - converts string input into index pair
    - precondition:
     - indexes within bounds
     */
    private List<Integer> convertToIndexes(String user_input) {

        char x_char = user_input.charAt(0);
        int x_axis = ((int) x_char) - 97;
        String y_char = user_input.substring(1,2);
        int y_axis = Integer.parseInt(y_char) - 1;
        //System.out.println(x_axis + "," + y_axis);
        //List<List<Integer>> fetched_user_input = new ArrayList<>(2);
        List<Integer> index_pair = new ArrayList<>(2);
        index_pair.add(x_axis);
        index_pair.add(y_axis);

        return index_pair;
    }

    // change back to PRIVTE
    // param: List<Integer> index
    public String indexToString(List<Integer> index) {
        String result = "aa";
        char[] result_chars = result.toCharArray();
        int x_cord = index.get(0);
        x_cord += 97;
        result_chars[0] = (char) x_cord;

        int y_cord = index.get(1);
        y_cord += 49;
        result_chars[1] = (char) y_cord;

        String result_string = String.valueOf(result_chars);
        return result_string;
    }

    /*
    - makes move to this.chess_board
    - updates this.player_turn
     */
    private void makeGameMove(List<List<Integer>> move) {

        this.chess_board.makeMove(move);

        int x_axis = move.get(1).get(0);
        int y_axis = move.get(1).get(1);
        int player = this.chess_board.getBoard().get(x_axis).get(y_axis).getPlayer();

        this.chess_board.setMovedPiece(player, x_axis, y_axis);
        if (player_turn == 1) {
            String start = this.indexToString(move.get(0));
            String dest = this.indexToString(move.get(1));
            this.chess_board.displayBoard();
            System.out.println("Black moved from " + start + " to " + dest + "!");
        }
        this.player_turn = (this.player_turn == 0) ? 1: 0;

    }


    /*
    - checks to see if player_x is in check right after player_y makes a move
    - if so, updates this.in_check to reflect it
     */
    private void updateInCheck() {
        if (this.chess_board.inCheck(this.player_turn) == 1) {
            this.in_check = this.player_turn;
            String player = (this.in_check == 0) ? "White" : "Black";
            System.out.println(player + " is in check!");
        }
    }

    private int checkCheckmateStalemate() {

        List<List<List<Integer>>> all_legal_moves = this.chess_board.getAllLegalMoves(this.player_turn);
        if (all_legal_moves.size() == 0) {
            if (this.in_check == this.player_turn) {
                if (this.player_turn == 0) {
                    this.game_state = "black_won";
                    System.out.println("Black won!");
                }
                else {
                    this.game_state = "white_won";
                    System.out.println("White won!");
                }
            }
            else {
                this.game_state = "stalemate";
                System.out.println("Stalemate!");
            }
            return 1;
        }

        return 0;

    }

    private void updateMovesList(List<List<Integer>> chosen_legal_move) {

        // if pawn piece is moved (including en passant)
        Piece start_piece = this.chess_board.getBoard().get(chosen_legal_move.get(0).get(0)).get(chosen_legal_move.get(0).get(1));
        Piece dest_piece = this.chess_board.getBoard().get(chosen_legal_move.get(1).get(0)).get(chosen_legal_move.get(1).get(1));

        if (start_piece.getCharacter() == "pa" || dest_piece != null) {
            this.moves_list.addMove(1);
        }
        else {
            this.moves_list.addMove(0);
        }

        //System.out.println("this.moves_list: ");
        //System.out.println(this.moves_list.getMoves());

    }


    private int checkFiftyMoveDraw() {

        if (this.moves_list.isDraw() == 1) {
            this.game_state = "fifty_move_draw";
            System.out.println("Game is drawn because none of the past 50 moves contain a pawn move or capture!");
            return 1;
        }

        return 0;

    }


    private int nextTurn() {

        /*
        if (this.checkThreeFold() == 1) {
            return 1;
        }

        if (this.checkFiftyMove() == 1) {
            return 1;
        }
        */

        List<List<Integer>> chosen_move = this.fetchChosenMove();

        this.updateMovesList(chosen_move);

        this.makeGameMove(chosen_move);

        this.chess_board.increaseTurnNumber();

        this.updateInCheck();


        if (checkFiftyMoveDraw() == 1) {
            return 1;
        }

        if (this.checkCheckmateStalemate() == 1) {
            return 1;
        }

        if (this.deadPositions() == 1) {
            return 1;
        }

        //Board cloned_board_state = this.cloneBoardState();
        //this.board_states.addState(cloned_board_state);

        return 0;

    }

    public void playGame() {

        this.chess_board.displayBoard();
        while (this.game_state.equals("active")) {
            this.nextTurn();
            try {
                Thread.sleep(10);
            }
            catch (Exception e) {

            }
            //System.out.printf("%n");

            //System.out.println(this.chess_board.getAllLegalMoves(this.player_turn));
        }

    }



}
