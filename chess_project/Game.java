package chess_project;
import chess_project.pieces.*;

import java.lang.reflect.Array;
import java.util.*;

public class Game {

    private Board chess_board;
    private List<List<Integer>> color_board = new ArrayList<>(8);
    private String in_check;
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
        this.in_check = "none";
        this.game_state = "active";
    }


    public void displayColorBoard() {
        for (int y = 7; y > -1; y--) {
            for (int x = 0; x < 8; x++) {
                System.out.printf("---" + this.color_board.get(x).get(y) + "---");
            }
            System.out.printf("%n");
        }
    }


    public Board cloneChessboard() {
        return null;
    }


    public int deadPositions() {
        return 0;
    }


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

}
