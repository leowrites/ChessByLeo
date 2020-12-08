package ChessGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    /*
     * Chess project
     * STARTED 2020-11-24
     * COMPLETED 2020-
     * AUTHOR Leo
     * Write a functional chess engine
     * <p>
     * 2020-12-08
     * TODO:
     *      Begin check and checkmate mechanism - Done
     *      Implement the check and debug the methods
     * 2020-12-06
     * TODO:
     *      Rewrite code for turns, specifically in setPosition() - done
     *      Possibly delete all the check mechanism for now - there is back up for that. Make sure all the piece can move first
     *      Pawn, knight and bishop debug complete
     *      Finish King today or tmr
     * 2020-12-03
     * TODO:
     * JFrame
     * <p>
     * 2020-12-01 update:
     * check mechanism debug complete, begin checkmate
     * 2020-11-29 update:
     * Fixed bugs in the game phase, now it can correctly identify whether it needs to ask for uesr input again
     * or continue to black
     * User input now works with the rest of the game.
     * TODO:
     * Debug check mechanism, for some reason its not doing anything
     * begin working on a reference and a method where the user can say the tile and convert that into coordinates  Done
     * Bishop isn't fully working look into it and test bishop and queen.
     * Still have to test/debug check() method
     * Finish writing King
     * <p>
     * 2020-11-28 update:
     * began writing check and checkmate mechanism
     * began writing king
     * went back and optimised code for pawns
     * round mechanism finished - see gamePhase()
     * bishops are done!
     * rooks and queens are done to though still need to be tested
     * began writing user input as well as "game phase"
     * TODO:
     * Start writing the King movement and start brainstorming how to bring in check-mate and conditions that ends the game
     * Forgot that the first move for pawn can move up two tiles
     * Learn hashmap
     * Begin writing the function to take in user input
     * I can have a 2D array that stores the name for each tile (for example, d4), and user input can simply be a piece
     * and that tile coordinate
     * <p>
     * 2020-11-27 update:
     * Finished knights and pawns
     * IMPORTANT:  1.Learned that you can pass an object to a method or constructor and use all the attributes of that instance.
     * 2.Made setPosition method available for all pieces without repeating the code in each Piece
     * this means the only difference between all the pieces is now the isLegal method
     * Do it by (Object type(or class name), object name(can be whatever))
     * <p>
     * Deleted White and Black subclasses for players - It makes the whole thing way harder to call variables
     * There is no reason to make a inheritance class of Players at all...
     * Finished the rules for pawns - Now it can identify correctly whether the move is legal or not.
     * Looks like we need a method to add the pieces to the ArrayList for players
     * TODO:
     * **What if you can store pieces objects into an 2D ArrayList? Then you can have a board of the actual objects.
     * Start bishops
     * Start learning 2D graphics
     * I can start initializing the pawns and the knights pieces.
     * Try generate multi-dimensional ArrayLists to generate the board.
     */
   /*
   This class is where games will happen
   A chess game will have:
   rules (check, checkmate, castle)
   two players
   8*8 board
   16 pieces(an abstract class) on each side
   turns where players can move their pieces
    */
    static String whitePlayerName;
    static String blackPlayerName;
    static Player white = new Player(whitePlayerName, "white");
    static Player black = new Player(blackPlayerName, "black");
    static int[] whitePawn1Pos = {0, 6};
    static int[] whitePawn2Pos = {1, 6};
    static int[] whitePawn3Pos = {2, 6};
    static int[] whitePawn4Pos = {3, 6};
    static int[] whitePawn5Pos = {4, 6};
    static int[] whitePawn6Pos = {5, 6};
    static int[] whitePawn7Pos = {6, 6};
    static int[] whitePawn8Pos = {7, 6};
    static Pieces whitePawn1 = new Pawn(whitePawn1Pos, "[whitePawna2]", white, black, 0);
    static Pieces whitePawn2 = new Pawn(whitePawn2Pos, "[whitePawnb2]", white, black, 0);
    static Pieces whitePawn3 = new Pawn(whitePawn3Pos, "[whitePawnc2]", white, black, 0);
    static Pieces whitePawn4 = new Pawn(whitePawn4Pos, "[whitePawnd2]", white, black, 0);
    static Pieces whitePawn5 = new Pawn(whitePawn5Pos, "[whitePawne2]", white, black, 0);
    static Pieces whitePawn6 = new Pawn(whitePawn6Pos, "[whitePawnf2]", white, black, 0);
    static Pieces whitePawn7 = new Pawn(whitePawn7Pos, "[whitePawng2]", white, black, 0);
    static Pieces whitePawn8 = new Pawn(whitePawn8Pos, "[whitePawnh2]", white, black, 0);
    //black pawn
    static int[] blackPawn1Pos = {0, 1};
    static int[] blackPawn2Pos = {1, 1};
    static int[] blackPawn3Pos = {2, 1};
    static int[] blackPawn4Pos = {3, 1};
    static int[] blackPawn5Pos = {4, 1};
    static int[] blackPawn6Pos = {5, 1};
    static int[] blackPawn7Pos = {6, 1};
    static int[] blackPawn8Pos = {7, 1};
    static Pieces blackPawn1 = new Pawn(blackPawn1Pos, "[blackPawna7]", black, white, 0);
    static Pieces blackPawn2 = new Pawn(blackPawn2Pos, "[blackPawnb7]", black, white, 0);
    static Pieces blackPawn3 = new Pawn(blackPawn3Pos, "[blackPawnc7]", black, white, 0);
    static Pieces blackPawn4 = new Pawn(blackPawn4Pos, "[blackPawnd7]", black, white, 0);
    static Pieces blackPawn5 = new Pawn(blackPawn5Pos, "[blackPawne7]", black, white, 0);
    static Pieces blackPawn6 = new Pawn(blackPawn6Pos, "[blackPawnf7]", black, white, 0);
    static Pieces blackPawn7 = new Pawn(blackPawn7Pos, "[blackPawng7]", black, white, 0);
    static Pieces blackPawn8 = new Pawn(blackPawn8Pos, "[blackPawnh7]", black, white, 0);
    //white knight
    static int[] knightb1Pos = {1, 7};
    static int[] knightg1Pos = {6, 7};
    static Pieces whiteKnightb1 = new Knight(knightb1Pos, "[whiteKnightb1]", white, black);
    static Pieces whiteKnightg1 = new Knight(knightg1Pos, "[whiteKnightg1]", white, black);
    //black knight
    static int[] knightb8Pos = {1, 0};
    static int[] knightg8Pos = {6, 0};
    static Pieces blackKnightb8 = new Knight(knightb8Pos, "[blackKnightb8]", black, white);
    static Pieces blackKnightg8 = new Knight(knightg8Pos, "[blackKnightg8]", black, white);
    //white bishop
    static int[] bishopc1Pos = {2, 7};
    static int[] bishopf1Pos = {5, 7};
    static Pieces whiteBishopc1 = new Bishop(bishopc1Pos, "[whiteBishopc1]", white, black);
    static Pieces whiteBishopf1 = new Bishop(bishopf1Pos, "[whiteBishopf1]", white, black);
    //black bishop
    static int[] bishopc8Pos = {2, 0};
    static int[] bishopf8Pos = {5, 0};
    static Pieces blackBishopc8 = new Bishop(bishopc8Pos, "[blackBishopc8]", black, white);
    static Pieces blackBishopf8 = new Bishop(bishopf8Pos, "[blackBishopc8]", black, white);
    //white rook
    static int[] rooka1Pos = {0, 7};
    static int[] rookh1Pos = {7, 7};
    static Pieces whiteRooka1 = new Rook(rooka1Pos, "[whiteRooka1]", white, black);
    static Pieces whiteRookh1 = new Rook(rookh1Pos, "[whiteRookh1]", white, black);
    //black rook
    static int[] rooka8Pos = {0, 0};
    static int[] rookh8Pos = {7, 0};
    static Pieces blackRooka8 = new Rook(rooka8Pos, "[blackRooka8]", black, white);
    static Pieces blackRookh8 = new Rook(rookh8Pos, "[blackRookh8]", black, white);
    //white queen
    static int[] whiteQueenPos = {3, 7};
    static Pieces whiteQueen = new Queen(whiteQueenPos, "[whiteQueen]", white, black);
    //black queen
    static int[] blackQueenPos = {3, 0};
    static Pieces blackQueen = new Queen(blackQueenPos, "[blackQueen]", black, white);
    //white king
    static int[] whiteKingPos = {4, 7};
    static King whiteKing = new King(whiteKingPos, "[whiteKing]", white, black);
    //black king
    static int[] blackKingPos = {4, 0};
    static King blackKing = new King(blackKingPos, "[blackKing]", black, white);
    static ArrayList<Pieces> blackPiecesObjects = new ArrayList<>();
    static ArrayList<Pieces> whitePiecesObjects = new ArrayList<>();

    ArrayList<int[]> whitePossibleMoves = new ArrayList<>();
    ArrayList<int[]> blackPossibleMoves = new ArrayList<>();
    int whitePossibility = 0;
    int blackPossibility = 0;
    ArrayList<int[]> whiteKingPossibleMoves = new ArrayList<>();
    ArrayList<int[]> blackKingPossibleMoves = new ArrayList<>();
    int AmountOfWhiteKingPossibleMoves = 0;
    int AmountOfBlackKingPossibleMoves = 0;
    ArrayList<int[]> whiteMoveStopCheck = new ArrayList<>();
    ArrayList<int[]> blackMoveStopCheck = new ArrayList<>();
    int amountOfWhiteStopCheck = 0;
    int amountOfBlackStopCheck = 0;
    int blackTotalPossibility = 0;
    int whiteTotalPossibility = 0;


    public void initialization() {
       /*
       initialization stage, give all necessary values
        */
        //initializing players
        blackPiecesObjects.addAll(Arrays.asList(blackRooka8, blackKnightb8, blackBishopc8, blackQueen, blackKing, blackBishopf8, blackKnightg8, blackRookh8,
                blackPawn1, blackPawn2, blackPawn3, blackPawn4, blackPawn5, blackPawn6, blackPawn7, blackPawn8));
        whitePiecesObjects.addAll(Arrays.asList(whiteRooka1, whiteKnightb1, whiteBishopc1, whiteQueen, whiteKing, whiteBishopf1, whiteKnightg1, whiteRookh1,
                whitePawn1, whitePawn2, whitePawn3, whitePawn4, whitePawn5, whitePawn6, whitePawn7, whitePawn8));
        white.setPieces(whitePiecesObjects);
        black.setPieces(blackPiecesObjects);
        white.setPiecesAlive(whitePiecesObjects);
        black.setPiecesAlive(blackPiecesObjects);

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        } *///Delay 5s to allow the players to read
        //initializing pieces - name must match piece as corresponding boardString
        //It's a bit confusing because the array goes from top to bottom, but the naming for each tile on a real chess board
        //for example d1, counts from bottom up. So be careful with the coordinates as white always starts at the bottom,
        //and black at top.
        //white pawn
    }

    public void check(Player thisPlayer) {

        if (Application.turn.equals("white")) {

            for (Pieces piece : thisPlayer.getPiecesAlive()) {

                for (int y = 0; y < 8; y++) {

                    for (int x = 0; x < 8; x++) {

                        int[] currentPos = {piece.x, piece.y};

                        int[] targetPos = {x, y};

                        if (piece.isLegal(currentPos, targetPos)) {

                            System.out.println("Checking " + piece.name + "possible position:" + Arrays.toString(targetPos));

                            whitePossibleMoves.add(targetPos);

                        }

                    }
                }

            }
            for (int[] possibility : whitePossibleMoves) {

                System.out.println("Checking position " + Arrays.toString(possibility));

                if (Game.blackKing.getCurrentPos() == possibility) {

                    System.out.println("Position " + Arrays.toString(possibility) + " puts white King in check");

                    whitePossibility++;

                    blackKing.checkStatus = true;


                }
                if (whitePossibility == 0) {

                    System.out.println("white King is not in check");

                    blackKing.checkStatus = false;

                }
            }

        }

        if (Application.turn.equals("black")) {

            for (Pieces piece : thisPlayer.getPiecesAlive()) {

                for (int y = 0; y < 8; y++) {

                    for (int x = 0; x < 8; x++) {

                        int[] currentPos = {piece.x, piece.y};

                        int[] targetPos = {x, y};

                        if (piece.isLegal(currentPos, targetPos)) {

                            System.out.println("Checking " + piece.name + " possible position: " + Arrays.toString(targetPos));

                            blackPossibleMoves.add(targetPos);

                        }

                    }
                }

            }
            for (int[] possibility : blackPossibleMoves) {

                System.out.println("Checking position " + Arrays.toString(possibility));

                if (blackKing.getCurrentPos() == possibility) {

                    System.out.println("Position " + Arrays.toString(possibility) + " puts black King in check");

                    blackPossibility++;

                    blackKing.checkStatus = true;


                }
                if (blackPossibility == 0) {

                    System.out.println("black King is not in check");

                    blackKing.checkStatus = false;

                }
            }

        }

    }

    public boolean response() {

        boolean gameOver = false;

        if (Application.turn.equals("white")) {

            while (whiteKing.checkStatus) {

                for (int y = 0; y < 8; y++) {

                    for (int x = 0; x < 8; x++) {

                        int[] targetPos = {x, y};

                        if (whiteKing.isLegal(whiteKing.getCurrentPos(), targetPos)) {

                            whiteKingPossibleMoves.add(targetPos);

                        }
                    }
                }

                System.out.println("All the white king's possible positions are: " + whiteKingPossibleMoves);

                for (int[] possibility : whiteKingPossibleMoves) {   //every king possible move

                    for (int[] blackPossibleMoves : blackPossibleMoves) {

                        if (possibility == blackPossibleMoves) {

                            System.out.println("");

                            whitePossibleMoves.remove(possibility); //remove if a white piece can get to that position

                            AmountOfWhiteKingPossibleMoves ++;

                        }

                    }

                }

                System.out.println("White king can only move to " + whitePossibleMoves);

                for (int[] possibility : whitePossibleMoves) {   //every possible move for black piece
                    //save the board first, also every possible move from before
                    Board.setSavedBoard(Board.board);
                    Board.setSavedBoardString(Board.boardString);
                    ArrayList<int[]> savedWhitePossibleMoves = whitePossibleMoves;  //so we can reverse back
                    int savedWhitePossibility = whitePossibility;
                    ArrayList<int[]> savedBlackPossibleMoves = blackPossibleMoves;  //so we can reverse back
                    int savedBlackPossibilities = blackPossibility;
                    System.out.println("Board saved: " + Arrays.deepToString(Board.savedBoardString));
                    System.out.println("White move saved: " + savedBlackPossibleMoves);
                    System.out.println("White have: " + savedBlackPossibilities + " before theoretical positions check");

                    for (Pieces piece : white.piecesAlive) { //for each black piece alive

                        if (piece.isLegal(piece.getCurrentPos(), possibility)) { // if the piece can move to that possibility

                            piece.setPosition(possibility); //move the piece
                            //call check
                            Application.turn = "black";
                            check(white);
                            Application.turn = "white";
                            //reset the board

                            if (!whiteKing.checkStatus) { //if white is no longer checking black

                                whiteMoveStopCheck.add(possibility);    //add that possibility to the inventory

                                amountOfWhiteStopCheck++;   //increase the possibilities black has to stop the check

                                System.out.println("Moving " + piece.name + "to position " + Arrays.toString(possibility) +
                                        " will stop black from checking white");
                                System.out.println("Current black possible moves are: " + blackPossibleMoves);
                                System.out.println("Current amount of black possibilities are: " + blackPossibility);
                            }

                            System.out.println("White has " + amountOfWhiteStopCheck + " moves");
                            whiteKing.checkStatus = true;
                            Board.board = Board.savedBoard;
                            Board.boardString = Board.savedBoardString;
                            blackPossibleMoves = savedBlackPossibleMoves;
                            blackPossibility = savedBlackPossibilities;
                            System.out.println("Board reversed to " + Arrays.deepToString(Board.boardString));
                            System.out.println("Black possible moves reversed to " + blackPossibleMoves);
                            System.out.println("Amount of black possibility checking the king reversed to " + blackPossibility);

                        }
                    }
                }
            }
        }

        if (Application.turn.equals("black")) {

            while (blackKing.checkStatus) {

                for (int y = 0; y < 8; y++) {

                    for (int x = 0; x < 8; x++) {

                        int[] targetPos = {x, y};

                        if (blackKing.isLegal(blackKing.getCurrentPos(), targetPos)) {

                            blackKingPossibleMoves.add(targetPos);

                        }
                    }
                }

                System.out.println("All the black king's possible positions are: " + blackKingPossibleMoves);

                for (int[] possibility : blackKingPossibleMoves) {   //every king possible move

                    for (int[] whitePossibleMove : whitePossibleMoves) {

                        if (possibility == whitePossibleMove) {

                            System.out.println("");

                            blackKingPossibleMoves.remove(possibility); //remove if a white piece can get to that position

                            AmountOfBlackKingPossibleMoves ++;

                        }

                    }

                }

                System.out.println("Black king can only move to " + blackKingPossibleMoves);

                for (int[] possibility : blackPossibleMoves) {   //every possible move for black piece
                    //save the board first, also every possible move from before
                    Board.setSavedBoard(Board.board);
                    Board.setSavedBoardString(Board.boardString);
                    ArrayList<int[]> savedWhitePossibleMoves = whitePossibleMoves;  //so we can reverse back
                    int savedWhitePossibility = whitePossibility;
                    System.out.println("Board saved: " + Arrays.deepToString(Board.savedBoardString));
                    System.out.println("White move saved: " + savedWhitePossibleMoves);
                    System.out.println("White have: " + savedWhitePossibility + " before theoretical positions check");

                    for (Pieces piece : black.piecesAlive) { //for each black piece alive

                        if (piece.isLegal(piece.getCurrentPos(), possibility)) { // if the piece can move to that possibility

                            piece.setPosition(possibility); //move the piece
                            //call check
                            Application.turn = "white";
                            check(white);
                            Application.turn = "black";
                            //reset the board

                            if (!blackKing.checkStatus) { //if white is no longer checking black

                                blackMoveStopCheck.add(possibility);    //add that possibility to the inventory

                                amountOfBlackStopCheck++;   //increase the possibilities black has to stop the check

                                System.out.println("Moving " + piece.name + "to position " + Arrays.toString(possibility) +
                                        " will stop white from checking black");
                                System.out.println("Current white possible moves are: " + whitePossibleMoves);
                                System.out.println("Current amount of white possibilities are: " + whitePossibility);
                            }

                            System.out.println("Black has " + amountOfBlackStopCheck + " moves");
                            blackKing.checkStatus = true;
                            Board.board = Board.savedBoard;
                            Board.boardString = Board.savedBoardString;
                            whitePossibleMoves = savedWhitePossibleMoves;
                            whitePossibility = savedWhitePossibility;
                            System.out.println("Board reversed to " + Arrays.deepToString(Board.boardString));
                            System.out.println("White possible moves reversed to " + whitePossibleMoves);
                            System.out.println("Amount of white possibility checking the king  reversed to " + whitePossibility);

                        }
                    }
                }
            }
        }

        blackTotalPossibility = AmountOfBlackKingPossibleMoves + amountOfBlackStopCheck;
        whiteTotalPossibility = AmountOfWhiteKingPossibleMoves + amountOfWhiteStopCheck;

        if (blackTotalPossibility == 0 ){

            gameOver = true;
            //game over here

        }else if (whiteTotalPossibility == 0){

             gameOver = true;

        }

        return gameOver;

    }

}


abstract class Pieces {
    // the first value will be x and the second y
    /**
     * Here is going to be a template for every piece to inherit
     * Each piece is going to have
     * the player it belongs to
     * a name
     * a position on the board
     * the places it can move to
     * the places it cannot move to
     * One piece could exist more than once (There are 8 pawns, for example)
     * Probably give each piece a constructor to initialize their locations and the side it belongs to
     * The movement for the piece and its position needs to be stored and updated (Stored directly on the board)
     * Same
     * 2020-11-27
     * Remove son classes of players, and create objects from player
     */

    //Constructor to initialize a chess piece
    protected String name;  //name of the piece
    protected int x;    //this need to be done when a piece is initialized
    // the first value will be x and the second y
    final Player thisPlayer;
    final Player enemyPlayer;
    protected int y;

    int[] getCurrentPos() {

        return new int[]{x, y};

    }

    //Constructor to initialize a chess piece
    Pieces(int[] position, String name, Player thisPlayer, Player enemyPlayer) { //essentially the position will be an array too, with x and y.

        //When initializing a piece object, determine its position and the owner of the piece.
        this.name = name;
        this.x = position[0];
        this.y = position[1];
        this.thisPlayer = thisPlayer;
        this.enemyPlayer = enemyPlayer;
        //the corresponding board cell needs to be replaced with 1 to represent occupied
        Board.board[y][x] = 1;
    }

    void setPosition(int[] targetPos) {
    /*
       here I need something that goes back to check what kind of piece it is, and whether it can take.
       loop through all the piece locations?
      specify what is allowed for this piece
      need a function to check if it is within the 8*8
      Also the piece needs to be eliminated
      */
        int targetX = targetPos[0];
        int targetY = targetPos[1];
        int[] currentPos = {x, y};
        //Maybe insert here the check scan
        if (thisPlayer.side.equals("white")) {
            if (isLegal(currentPos, targetPos)) {   //if move is legal
                Board.board[y][x] = 0;
                Board.board[targetY][targetX] = 1;
                Board.boardString[y][x] = "[  ]";
                Board.boardString[targetY][targetX] = name;
                this.x = targetX;   //update the location of the piece
                this.y = targetY;
                if (Board.Occupied(targetX, targetY)) {  //if the target is not empty
                    enemyPlayer.piecesDead.add(Board.boardString[targetY][targetX]);
                    enemyPlayer.piecesAlive.remove(Board.board[targetY][targetX]);
                    //if no longer being checked, break out of the loop
                }
            }
        } else if (thisPlayer.side.equals("black")) {
            if (isLegal(currentPos, targetPos)) {   //if move is legal  //while being checked
                //see if moving this piece will stop the "checked" status before actually replacing the tile
                //maybe test the tile, if it cancels, make the change, if it does not, go back and ask for another input
                //Test the tile
                Board.board[y][x] = 0;
                Board.board[targetY][targetX] = 1;
                Board.boardString[y][x] = "[  ]";
                Board.boardString[targetY][targetX] = name;
                //check status again
                this.x = targetX;   //update the location of the piece
                this.y = targetY;
                if (Board.Occupied(targetX, targetY)) {
                    enemyPlayer.piecesDead.add(Board.boardString[targetY][targetX]);
                    enemyPlayer.piecesAlive.remove(Board.board[targetY][targetX]);
                }
            }
        }
    }

    boolean isLegal(int[] currentPos, int[] targetPos) {
        return false;
    }
}

class Player {
    protected final String playerName;    //Sets the player name
    String side;

    ArrayList<Pieces> pieces = new ArrayList<>();
    ArrayList<Pieces> piecesAlive = new ArrayList<>();
    ArrayList<String> piecesDead = new ArrayList<>();

    public ArrayList<Pieces> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Pieces> pieces) {
        this.pieces = pieces;
    }

    public ArrayList<Pieces> getPiecesAlive() {
        return piecesAlive;
    }

    public void setPiecesAlive(ArrayList<Pieces> piecesAlive) {
        this.piecesAlive = piecesAlive;
    }

    /*
            write pieces alive, dead and taken with arraylist so that it can be used by pieces
             */


    Player(String playerName, String side) {
        this.playerName = playerName;
        this.side = side;

        //Decides white or black
    }



    /*
    Turns
    Name to identify
    Players can have a method that moves the piece, which must match the
    squares the pieces can move to.
    Each player will have 16 pieces to begin with.
     */
}

class Board {
    /**
     * board is made up of tiles
     * A board once initialized is going to have occupied pieces at it's initial position
     * board is going to be changed
     * - How does one object exist in an array?
     */
    static int[][] savedBoard;
    static String[][] savedBoardString;

    static int[][] board = {
            //Chess board
            //int[y][x]
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };
    static String[][] boardString = {
            {"[blackRooka8]", "[blackKnightb8]", "[blackBishopc8]", "[blackQueen]", "[blackKing]", "[blackBishopf8]", "[blackKnightg8]", "[blackRookh8]"},
            {"[blackPawna7]", "[blackPawnb7]", "[blackPawnc7]", "[blackPawnd7]", "[blackPawne7]", "[blackPawnf7]", "[blackPawng7]", "[blackPawnh7]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[whitePawna2]", "[whitePawnb2]", "[whitePawnc2]", "[whitePawnd2]", "[whitePawne2]", "[whitePawnf2]", "[whitePawng2]", "[whitePawnh2]"},
            {"[whiteRooka1]", "[whiteKnightb1]", "[whiteBishopc1]", "[whiteQueen]", "[whiteKing]", "[whiteBishopf1]", "[whiteKnightg1]", "[whiteRookh1]"}
    };

    static void setSavedBoardString(String[][] currentBoard) {

        savedBoardString = currentBoard;

    }

    static void setSavedBoard(int[][] currentBoard) {

        savedBoard = currentBoard;

    }

    static boolean Occupied(int targetX, int targetY) {   //method to check if tile is occupied and if it is on the board
        //need object from board
        boolean result;
        //else within range
        result = board[targetY][targetX] == 1;  //if true, it is occupied, otherwise return false

        return result;
    }
}

