package chess_project;
import chess_project.pieces.*;

import java.lang.reflect.Array;
import java.util.*;

public class Board {
    private ArrayList<ArrayList<Piece>> board = new ArrayList<>(8);
    private Map<Integer, ArrayList<Piece>> player_pieces = new HashMap<Integer, ArrayList<Piece>>(2); // black = 1, white = 0
    private int turn_number = 1;

    public Board () {

        ArrayList<Piece> black_pieces = new ArrayList<Piece>(8);
        ArrayList<Piece> white_pieces = new ArrayList<Piece>(8);
        this.player_pieces.put(0, white_pieces);
        this.player_pieces.put(1, black_pieces);

        for (int x = 0; x < 8; x++) {
            ArrayList<Piece> new_row = new ArrayList<Piece>(8);
            for (int y = 0; y < 8; y++ ) {
                new_row.add(null);
            }
            this.board.add(new_row);
        }
        for (int x = 0; x < 2; x++) {
            int x_axis = (x == 0) ? 0 : 7;
            for (int y = 0; y < 2; y++) {
                int y_axis = (y == 0) ? 0 : 7;
                Piece new_rook = new Rook(y, x_axis, y_axis);
                this.player_pieces.get(y).add(new_rook);
                this.board.get(x_axis).set(y_axis, new_rook);
            }
        }
        for (int x = 0; x < 2; x++) {
            int x_axis = (x == 0) ? 1 : 6;
            for (int y = 0; y < 2; y++) {
                int y_axis = (y == 0) ? 0 : 7;
                Piece new_knight = new Knight(y, x_axis, y_axis);
                this.player_pieces.get(y).add(new_knight);
                this.board.get(x_axis).set(y_axis, new_knight);
            }
        }
        for (int x = 0; x < 2; x++) {
            int x_axis = (x == 0) ? 2 : 5;
            for (int y = 0; y < 2; y++) {
                int y_axis = (y == 0) ? 0 : 7;
                Piece new_bishop = new Bishop(y, x_axis, y_axis);
                this.player_pieces.get(y).add(new_bishop);
                this.board.get(x_axis).set(y_axis, new_bishop);
            }
        }
        for (int x = 0; x < 2; x++) {
            int x_axis = (x == 0) ? 3 : 4;
            for (int y = 0; y < 2; y++) {
                int y_axis = (y == 0) ? 0 : 7;
                Piece new_piece = (x_axis == 3) ? new Queen(y, x_axis, y_axis) : new King(y, x_axis, y_axis);
                this.player_pieces.get(y).add(new_piece);
                this.board.get(x_axis).set(y_axis, new_piece);
            }
        }
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 2; y++) {
                int y_axis = (y == 0) ? 1 : 6;
                Piece new_pawn = new Pawn(y, x, y_axis);
                this.player_pieces.get(y).add(new_pawn);
                this.board.get(x).set(y_axis, new_pawn);
            }
        }
    }

    public Board (String debug) {
        this();
        if (debug == "test_bishop") {
            Piece bishop = this.board.get(2).get(0);
            this.board.get(3).set(3, bishop);
        }
    }


    public void displayBoard() {
        for (int y = 7; y > -1; y--) {
            for (int x = 0; x < 8; x++) {
                Piece current_piece = this.board.get(x).get(y);
                if (current_piece != null) {
                    String current_player = (current_piece.getPlayer() == 1) ? "B" : "W";
                    System.out.printf("-(" + current_player + " " + current_piece.getCharacter() + ")-");
                }
                else {
                    System.out.printf("---**---");
                }
            }
            System.out.printf("%n");
        }
    }

    public ArrayList<ArrayList<Piece>> getBoard() {
        return this.board;
    }

    public ArrayList<Piece> getPlayerPieces(int player) {

        return this.player_pieces.get(player);
    }

    public int getTurnNumber() {
        return turn_number;
    }

    public void increaseTurnNumber() {
        this.turn_number++;
    }


    public int inCheck(int player) {

        int opposite_player = (player == 0) ? 1 : 0;
        List<Integer> king_position = new ArrayList<>(2);
        for (Piece piece : this.player_pieces.get(player)) {
            if (piece.getCharacter() == "ki") {
                king_position = piece.getPosition();
                break;
            }
        }
        for (Piece piece : this.player_pieces.get(opposite_player)) {
            //System.out.println("inCheck");
            //System.out.println(piece);
            // *** TODO: NEED TO IMPLEMENT getTheoreticMoves for ALL PIECES for this to not crash
            List<List<List<Integer>>> theoretic_moves = piece.getTheoreticMoves(this);
            for (List<List<Integer>> theoretic_move : piece.getTheoreticMoves(this)) {
                if (theoretic_move.get(2) == king_position) {
                    return 1;
                }
            }
        }
        return 0;
    }


    // *** VALIDATED
    private void boardPlace(List<Integer> position, Piece piece) {
        //System.out.println("reached boardPlace");
        //System.out.println(position);
        //System.out.println(piece);
        this.board.get(position.get(0)).set(position.get(1), piece);
        piece.setPosition(position.get(0), position.get(1));
        //System.out.println(piece.getPosition());
    }

    // *** VALIDATED
    private Piece boardRemove(List<Integer> position) {
        Piece retrieved = this.board.get(position.get(0)).get(position.get(1));
        this.board.get(position.get(0)).set(position.get(1), null);
        return retrieved;
    }

    private void playerAppend(int player, Piece piece) {
        this.player_pieces.get(player).add(piece);
    }

    // *** VALIDATED
    private void playerPop(int player, List<Integer> position) {
        //System.out.println("reached playerPop");
        ArrayList<Piece> player_array = this.player_pieces.get(player);
        for (int x = 0; x < player_array.size(); x++) {
            //System.out.println(player_array.get(x).getPosition() + " vs "  + position);
            if (player_array.get(x).position.equals(position)) {
                System.out.println("target piece located");
                player_array.remove(x);
                break;
            }
        }
    }

    // *** VALIDATED
    public Piece remove(List<Integer> position) {
        //System.out.println("reached remove");
        Piece retrieved = this.boardRemove(position);
        if (retrieved != null) {
            this.playerPop(retrieved.getPlayer(), position);
        }
        return retrieved;
    }

    public void place(List<Integer> position, Piece piece) {
        this.boardPlace(position, piece);
        this.playerAppend(piece.getPlayer(), piece);
    }

    // *** VALIDATED
    public Piece move(List<Integer> start_pos, List<Integer> dest_pos) {
        //System.out.println("reached move");
        //System.out.println("start pos = " + start_pos);
        Piece retrieved = this.boardRemove(start_pos);
        //System.out.println(retrieved);
        this.boardPlace(dest_pos, retrieved);
        return retrieved;
    }

    // *** VALIDATED
    public void normalMove(List<List<Integer>> move_positions) {
        this.remove(move_positions.get(1));
        Piece mover = this.move(move_positions.get(0), move_positions.get(1));
        if (mover instanceof Rook) {
            Rook rook  = (Rook) mover;
            ((Rook) mover).setMoved(1);
        }
        if (mover instanceof Pawn) {
            Pawn pawn  = (Pawn) mover;
            ((Pawn) mover).setMoved(1);
            //System.out.println("pawn.moved: " + ((Pawn) mover).getMoved());
        }
        if (mover instanceof King) {
            King king  = (King) mover;
            ((King) mover).setMoved(1);

        }

    }

}

