package chess_project;
import chess_project.pieces.*;

import java.lang.reflect.Array;
import java.util.*;

public class Board {
    private ArrayList<ArrayList<Piece>> board = new ArrayList<>(8);
    private Map<Integer, ArrayList<Piece>> player_pieces = new HashMap<Integer, ArrayList<Piece>>(4); // black = 1, white = 0, black_king = 3, white_king = 2
    private int turn_number = 1;
    private List<Integer> black_move = new ArrayList<>(2);
    private List<Integer> white_move = new ArrayList<>(2);

    public Board () {

        ArrayList<Piece> black_pieces = new ArrayList<Piece>(15);
        ArrayList<Piece> white_pieces = new ArrayList<Piece>(15);
        ArrayList<Piece> black_king = new ArrayList<>(1);
        ArrayList<Piece> white_king = new ArrayList<>(1);
        this.player_pieces.put(0, white_pieces);
        this.player_pieces.put(1, black_pieces);
        this.player_pieces.put(2, white_king);
        this.player_pieces.put(3, black_king);

        for (int x = 0; x < 2; x++) {
            this.black_move.add(-99);
        }

        for (int x = 0; x < 2; x++) {
            this.white_move.add(-99);
        }

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
                if (x_axis == 4) {
                    this.player_pieces.get(y + 2).add(new_piece);
                }
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

    public Board (int empty) {

        ArrayList<Piece> black_pieces = new ArrayList<Piece>(15);
        ArrayList<Piece> white_pieces = new ArrayList<Piece>(15);
        ArrayList<Piece> black_king = new ArrayList<>(1);
        ArrayList<Piece> white_king = new ArrayList<>(1);
        this.player_pieces.put(0, white_pieces);
        this.player_pieces.put(1, black_pieces);
        this.player_pieces.put(2, white_king);
        this.player_pieces.put(3, black_king);

        for (int x = 0; x < 8; x++) {
            ArrayList<Piece> new_row = new ArrayList<Piece>(8);
            for (int y = 0; y < 8; y++ ) {
                new_row.add(null);
            }
            this.board.add(new_row);
        }

    }

    public Board cloneBoard() {

        Board cloned_board = new Board(0);
        cloned_board.setTurnNumber(this.getTurnNumber());
        for (int player = 0; player < 2; player++) {
            for (Piece piece : this.player_pieces.get(player)) {
                String character = piece.getCharacter();
                int this_player = piece.getPlayer();
                int x_axis = piece.getPosition().get(0);
                int y_axis = piece.getPosition().get(1);

                switch (character) {
                    case "ro":
                        Piece new_rook = new Rook(this_player, x_axis, y_axis);
                        ((Rook) new_rook).setMoved(((Rook) piece).getMoved());
                        ((Rook) new_rook).setOriginal(((Rook) piece).getOriginal());
                        cloned_board.place(new_rook.getPosition(), new_rook);
                        break;
                    case "ki":
                        Piece new_king = new King(this_player, x_axis, y_axis);
                        ((King) new_king).setMoved(((King) piece).getMoved());
                        cloned_board.place(new_king.getPosition(), new_king);
                        cloned_board.getPlayerPieces(player + 2).add(new_king);
                        break;
                    case "pa":
                        Piece new_pawn = new Pawn(this_player, x_axis, y_axis);
                        ((Pawn) new_pawn).setMoved(((Pawn) piece).getMoved());
                        ((Pawn) new_pawn).setSpecialTurnNumber(((Pawn) piece).getSpecialTurnNumber());
                        cloned_board.place(new_pawn.getPosition(), new_pawn);
                        break;
                    case "qu":
                        Piece new_queen = new Queen(this_player, x_axis, y_axis);
                        cloned_board.place(new_queen.getPosition(), new_queen);
                        break;
                    case "kn":
                        Piece new_knight = new Knight(this_player, x_axis, y_axis);
                        cloned_board.place(new_knight.getPosition(), new_knight);
                        break;
                    case "bi":
                        Piece new_bishop = new Bishop(this_player, x_axis, y_axis);
                        cloned_board.place(new_bishop.getPosition(), new_bishop);
                        break;
                }
            }
        }
        return cloned_board;
    }

    public void displayBoard() {
        ArrayList<String> colors = new ArrayList<String>(2);
        String black_color = "||||||||";
        String white_color = "        ";
        colors.add(black_color);
        colors.add(white_color);

        for (int x = 0; x < 30; x++) {
            System.out.printf("%n");
        }

        for (int y = 7; y > -1; y--) {


            int start = (y % 2 == 1) ? 1 : 0;

            System.out.printf("  ");

            for (int x = 0; x < 8; x++) {
                int color = ((x + start) % 2 == 0) ? 0 : 1;
                System.out.printf(colors.get(color));
            }
            System.out.printf("%n");


            System.out.printf(y + 1 + " ");



            for (int x = 0; x < 8; x++) {
                int color = ( (x + start) % 2 == 0) ? 0 : 1;


                //System.out.printf(colors.get(color));

                Piece current_piece = this.board.get(x).get(y);
                if (current_piece != null) {
                    if (current_piece.getPosition().equals(black_move) || current_piece.getPosition().equals(white_move)) {
                        String current_player = (current_piece.getPlayer() == 1) ? "B" : "W";
                        System.out.printf("*(" + current_player + " " + current_piece.getCharacter() + ")*");

                    }
                    else {
                        String current_player = (current_piece.getPlayer() == 1) ? "B" : "W";
                        System.out.printf(colors.get(color).substring(0, 1) + "(" + current_player + " " + current_piece.getCharacter() + ")" + colors.get(color).substring(0, 1));
                    }
                }
                else {
                    System.out.printf(colors.get(color) );
                }
                //System.out.printf(colors.get(color));


            }
            System.out.printf("%n");

            System.out.printf("  ");

            for (int x = 0; x < 8; x++) {
                int color = ((x + start) % 2 == 0) ? 0 : 1;
                System.out.printf(colors.get(color));
            }
            System.out.printf("%n");

        }

        int ascii = 65;
        System.out.printf("  ");
        for (int x = 0; x < 8; x++) {
            System.out.printf("   " + (char)ascii + (char)ascii + "   ");
            ascii++;
        }
        System.out.println("");
        System.out.printf("%n");
    }

    public void setMovedPiece(int player, int x, int y) {
        List<Integer> fetched_moved_square = (player == 0) ? this.white_move : this.black_move;
        fetched_moved_square.set(0, x);
        fetched_moved_square.set(1, y);


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

    public void decreaseTurnNumber() {
        this.turn_number--;
    }

    public void setTurnNumber(int value) { this.turn_number = value;}

    public List<List<List<Integer>>> getAllLegalMoves(int player) {
        List<List<List<Integer>>> all_legal_moves = new ArrayList<>();
        for (Piece piece : this.getPlayerPieces(player)) {
            //System.out.println(piece.getCharacter());
            List<List<List<Integer>>> this_piece_moves = piece.getLegalMoves(this);
            //System.out.println(this_piece_moves);
            //System.out.println(this_piece_moves.size());
            for (List<List<Integer>> move : this_piece_moves) {
                all_legal_moves.add(move);
            }
        }

        return all_legal_moves;

    }


    /*
    - returns 0 if opponent piece has no chance of capturing player's king
     */
    public int fastCheck(Piece opponent_piece, int player) {

        List<Integer> king_position = this.player_pieces.get(player + 2).get(0).getPosition();
        int king_x = king_position.get(0);
        int king_y = king_position.get(1);
        int opp_x = opponent_piece.getPosition().get(0);
        int opp_y = opponent_piece.getPosition().get(1);


        String character = opponent_piece.getCharacter();
        switch (character) {
            case "ki":
            case "pa":
                if (Math.abs(king_x - opp_x) >= 2 || Math.abs(king_y - opp_y) >= 2) {
                    return 0;
                }
                break;
            case "ro":
                if (king_x != opp_x && king_y != opp_y) {
                    return 0;
                }
                break;
            case "bi":
                if (king_x == opp_x || king_y == opp_y) {
                    return 0;
                }
                break;
            case "kn":
                if (Math.abs(king_x - opp_x) >= 3 || (Math.abs(king_y - opp_y)) >= 3) {
                    return 0;
                }
                break;
        }

        return 1;

    }


    public int inCheckNormal(int player) {

        int opposite_player = (player == 0) ? 1 : 0;
        //List<Integer> king_position = new ArrayList<>(2);
        //king_position.add(this.player_pieces.get(player + 2).get(0).getPosition().get(0));
        //king_position.add(this.player_pieces.get(player + 2).get(0).getPosition().get(1));
        List<Integer> king_position = this.player_pieces.get(player + 2).get(0).getPosition();

        for (Piece piece : this.player_pieces.get(opposite_player)) {

            int fast_check = this.fastCheck(piece, player);
            if (fast_check == 0) {
                continue;
            }

            List<List<List<Integer>>> theoretic_moves = piece.getTheoreticMoves(this);
            for (List<List<Integer>> theoretic_move : theoretic_moves) {
                if (theoretic_move.get(1).equals(king_position)) {
                    //System.out.println(theoretic_move);
                    return 1;
                }
            }
        }
        return 0;
    }

    /*
    - checks to see if player is in check by seeing if any of opposite player's pieces contain theo move which == player's king's position
    - assumes both player's king is on its original rank
    - checks to see if opposite player's king is also on its original rank
      - if so, skips opposite player's king
     */
    public int inCheckCastling(int player) {

        int opposite_player = (player == 0) ? 1 : 0;
        //int opposite_starting_rank = (player == 0) ? 0 : 7;
        //List<Integer> king_position = new ArrayList<>(2);
        //king_position.add(this.player_pieces.get(player + 2).get(0).getPosition().get(0));
        //king_position.add(this.player_pieces.get(player + 2).get(0).getPosition().get(1));
        List<Integer> king_position = this.player_pieces.get(player + 2).get(0).getPosition();

        for (Piece piece : this.player_pieces.get(opposite_player)) {
            if (piece.getCharacter() == "ki") {
                continue;
            }

            int fast_check = this.fastCheck(piece, player);
            if (fast_check == 0) {
                continue;
            }

            List<List<List<Integer>>> theoretic_moves = piece.getTheoreticMoves(this);
            for (List<List<Integer>> theoretic_move : theoretic_moves) {
                if (theoretic_move.get(1).equals(king_position)) {
                    return 1;
                }
            }
        }
        return 0;
    }


    public int inCheck(int player) {

        int opposite_player = (player == 0) ? 1 : 0;
        int player_king_start_rank = (player == 0) ? 0 : 7;
        int opposite_player_king_start_rank = (player == 0) ? 7 : 0;
        // if both player's kings on their original ranks:
            // call inCheckNormal
        if (this.getPlayerPieces(player + 2).get(0).getPosition().get(1) == player_king_start_rank &&
                this.getPlayerPieces(opposite_player + 2).get(0).getPosition().get(1) == opposite_player_king_start_rank) {
            return this.inCheckCastling(player);
        }

        // if not both player's kings on their original ranks:
            // call inCheckCastling
        else {
            return this.inCheckNormal(player);
        }
    }

    /*
    - returns 1:
      - if input_piece @ [x,y] is on a square that matches the dest pos of a legal move of the opponent
    - returns 0: otherwise

     */
    public int underThreat(int x, int y) {

        Piece input_piece = this.board.get(x).get(y);
        int opposite_player = (input_piece.getPlayer() == 0) ? 1 : 0;
        for (List<List<Integer>> legal_opponent_move : this.getAllLegalMoves(opposite_player)) {
            if (legal_opponent_move.get(1).equals(input_piece.getPosition())) {
            //if (legal_opponent_move.get(1).get(0) == input_piece.getPosition().get(0) &&
            //        legal_opponent_move.get(1).get(1) == input_piece.getPosition().get(1)) {
                //System.out.println(legal_opponent_move.get(1));
                return 1;
            }
        }
        return 0;
    }

    public int nonPawnUnderThreat(int x, int y) {
        Piece input_piece = this.board.get(x).get(y);
        String character = input_piece.getCharacter();
        if (character.equals("pa")) {
            return 0;
        }
        int opposite_player = (input_piece.getPlayer() == 0) ? 1 : 0;
        for (List<List<Integer>> legal_opponent_move : this.getAllLegalMoves(opposite_player)) {
            if (legal_opponent_move.get(1).equals(input_piece.getPosition())) {
                //if (legal_opponent_move.get(1).get(0) == input_piece.getPosition().get(0) &&
                //        legal_opponent_move.get(1).get(1) == input_piece.getPosition().get(1)) {
                //System.out.println(legal_opponent_move.get(1));
                return 1;
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
        if (piece != null) {
            piece.setPosition(position.get(0), position.get(1));
        };
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
            if (player_array.get(x).getPosition().equals(position)) {
                //System.out.println("target piece located");
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
        if (piece != null) {
            this.playerAppend(piece.getPlayer(), piece);
        }
    }

    // *** VALIDATED
    public Piece move(List<Integer> start_pos, List<Integer> dest_pos) {
        //System.out.println("reached move");
        //System.out.println("start pos = " + start_pos);
        Piece retrieved = this.boardRemove(start_pos);
        //System.out.println(retrieved.getPosition());
        //System.out.println("start pos = " + start_pos);
        //System.out.println(retrieved);
        // following line mutates start_pos
        this.boardPlace(dest_pos, retrieved);
        //System.out.println("start pos = " + start_pos);
        //System.out.println("dest pos = " + dest_pos);
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

    public void castlingMove(List<List<Integer>> move_positions_type) {

        /*
        int rook_x_axis = (move_positions_type.get(2).get(0) == 0) ? 0 : 7;
        int y_axis = move_positions_type.get(0).get(1);
        int rook_shift = (move_positions_type.get(2).get(0) == 0) ? 1 : -1;
        int rook_dest_x = move_positions_type.get(1).get(0) + rook_shift;
        List<Integer> rook_start_pos = new ArrayList<>(2);
        rook_start_pos.add(rook_x_axis);
        rook_start_pos.add(y_axis);
        List<Integer> rook_dest_pos = new ArrayList<>(2);
        rook_dest_pos.add(rook_dest_x);
        rook_dest_pos.add(y_axis);

         */

        Piece king = this.move(move_positions_type.get(0), move_positions_type.get(1));
        //Piece rook = this.move(rook_start_pos, rook_dest_pos);
        Piece rook = this.move(move_positions_type.get(3), move_positions_type.get(4));

        ((King) king).setMoved(1);
        ((Rook) rook).setMoved(1);

    }


    public void enPassantMove(List<List<Integer>> move_positions_type) {

        this.remove(move_positions_type.get(3));
        this.move(move_positions_type.get(0), move_positions_type.get(1));

    }


    public void promotionMove(List<List<Integer>> move_positions_type) {

        int dest_x = move_positions_type.get(1).get(0);
        int dest_y = move_positions_type.get(1).get(1);
        int player = (dest_y == 0) ? 1 : 0;

        this.remove(move_positions_type.get(1));
        this.remove(move_positions_type.get(0));

        Scanner get_choice = new Scanner(System.in);
        System.out.print("Please pick a piece to promote Pawn to (0 = Queen, 1 = Bishop, 2 = Knight, 3 = Rook): ");
        int user_choice = get_choice.nextInt();
        Piece new_piece;
        switch(user_choice) {
            case 0:
                new_piece = new Queen(player, dest_x, dest_y);
                this.place(move_positions_type.get(1), new_piece);
                break;
            case 1:
                new_piece = new Bishop(player, dest_x, dest_y);
                this.place(move_positions_type.get(1), new_piece);
                break;
            case 2:
                new_piece = new Knight(player, dest_x, dest_y);
                this.place(move_positions_type.get(1), new_piece);
                break;
            case 3:
                new_piece = new Rook(player, dest_x, dest_y);
                ((Rook) new_piece).setOriginal(0);
                this.place(move_positions_type.get(1), new_piece);
                break;
        }
    }


    public void promotionMoveAI(List<List<Integer>> move_positions_type) {

        int dest_x = move_positions_type.get(1).get(0);
        int dest_y = move_positions_type.get(1).get(1);

        this.remove(move_positions_type.get(1));
        this.remove(move_positions_type.get(0));

        Piece new_piece;
        new_piece = new Queen(1, dest_x, dest_y);
        this.place(move_positions_type.get(1), new_piece);

    }


    public void twoSquarePawnMove(List<List<Integer>> move_positions_type) {

        Piece pawn = this.move(move_positions_type.get(0), move_positions_type.get(1));
        ((Pawn) pawn).setMoved(1);
        ((Pawn) pawn).setSpecialTurnNumber(this.turn_number);

    }


    public void makeMove(List<List<Integer>> move_positions_type) {

        // normal move
        if (move_positions_type.size() == 2) {
            this.normalMove(move_positions_type);
        }

        // special move
        else {

            // castling move
            if (move_positions_type.get(2).get(0) == 0 || move_positions_type.get(2).get(0) == 1) {
                this.castlingMove(move_positions_type);
            }
            // en passant move
            else if (move_positions_type.get(2).get(0) == 3) {
                this.enPassantMove(move_positions_type);
            }
            // promotion move
            else if (move_positions_type.get(2).get(0) == 2) {
                this.promotionMove(move_positions_type);
            }
            // two square move
            else if (move_positions_type.get(2).get(0) == 4) {
                this.twoSquarePawnMove(move_positions_type);
            }

        }

    }


}

