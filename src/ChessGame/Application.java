package ChessGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.*;

/*
This is the panel where the game will take place

TODO 2020-12-07: implement game loop and new game option
                 Rethink check mechanism in Game class.
                 implement turn mechanism - done
TODO 2020-12-06: debug bishop and rook - done
                 Implement some method that shows what the user is selecting - have the selectedIcons added to the buttons - done
TODO 2020-12-05: Integration of the graphics with the backend rules
                 Included all images
                 Integration complete
TODO 2020-12-04:
                The difference between images and bufferedimages
                Understand how to draw an image on to the screen
                how to scale the image

 */

public class Application extends JFrame {
    //Runnable allows thread
    //You can do this by both implementing Runnable or extending the Thread class, but when you extend you can't extend
    //another class. So here it has to be implementing

    //Thread newThread = new Thread();  creates an object of the class, Declaration = Instantiation Initialization
    // But you can create an object which has unknown values. This is a reference variable
    /*allows multiple tasks to run at once when implementing runnable, you can pass an instance of of the class
    and run that*/

    private int[] currentPos;   //****Very important

    private int[] targetPos;   //I declared these positions in the ChessSquareButton class before and it could never get the value
    //because they belong to that one specific button, I need to take these two out side of the class so they are not instance specific

    private boolean hasPiece = true;

    private String turn = "white";

    private final ChessSquareButton[][] squares = new ChessSquareButton[8][8];

    private ImageIcon whitePawnIcon;

    private ImageIcon blackPawnIcon;

    private ImageIcon whitePawnSelected;
    
    private ImageIcon blackPawnSelected;
    
    ArrayList<ImageIcon> whiteIcons = new ArrayList<>();
    ArrayList<ImageIcon> blackIcons = new ArrayList<>();
    ArrayList<ImageIcon> whiteSelected = new ArrayList<>();
    ArrayList<ImageIcon> blackSelected = new ArrayList<>();

    Application() throws IOException {  //Application constructor


        super("Chess By Leo");

        init();

        //replace with currentY and currentX when integrating with backend

        Dimension frameDimension = new Dimension(700, 700);
        setSize(frameDimension);

        requestFocus();

        setResizable(false);

        setLocationRelativeTo(null);

        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    //Need two things, one has the background and the other has the grid layout

    public void init() throws IOException {
        //drawing on to the buffer image

        Game game1 = new Game();
        game1.initialization();

        loadImage();

        Container contents = getContentPane();
        GridLayout layout = new GridLayout(8, 8, 0, 0);
        contents.setLayout(layout);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {//sets the button
                if (y == 0) {
                    if ((x + y) % 2 != 0) {
                        squares[y][x] = new ChessSquareButton(Game.blackPiecesObjects.get(x), blackIcons.get(x), blackSelected.get(x));
                        squares[y][x].setBackground(new Color(103, 101, 47));
                    } else {
                        squares[y][x] = new ChessSquareButton(Game.blackPiecesObjects.get(x), blackIcons.get(x), blackSelected.get(x));
                        squares[y][x].setBackground(new Color(241, 245, 233));
                    }
                }
                if (y == 1) {
                    if ((x + y) % 2 != 0) {
                        squares[y][x] = new ChessSquareButton(Game.blackPiecesObjects.get(x + 8), blackPawnIcon, blackPawnSelected);
                        squares[y][x].setBackground(new Color(103, 101, 47));
                    } else {
                        squares[y][x] = new ChessSquareButton(Game.blackPiecesObjects.get(x + 8), blackPawnIcon, blackPawnSelected);
                        squares[y][x].setBackground(new Color(241, 245, 233));
                    }
                } else if (y >= 2 && y <= 5) {
                    if ((x + y) % 2 != 0) {
                        squares[y][x] = new ChessSquareButton();
                        squares[y][x].setBackground(new Color(103, 101, 47));
                    } else {
                        squares[y][x] = new ChessSquareButton();
                        squares[y][x].setBackground(new Color(241, 245, 233));
                    }
                }
                if (y == 6) {
                    if ((x + y) % 2 != 0) {
                        squares[y][x] = new ChessSquareButton(Game.whitePiecesObjects.get(x + 8), whitePawnIcon, whitePawnSelected);
                        squares[y][x].setBackground(new Color(103, 101, 47));
                    } else {
                        squares[y][x] = new ChessSquareButton(Game.whitePiecesObjects.get(x + 8), whitePawnIcon, whitePawnSelected);
                        squares[y][x].setBackground(new Color(241, 245, 233));
                    }
                }
                if (y == 7) {
                    if ((x + y) % 2 != 0) {
                        squares[y][x] = new ChessSquareButton(Game.whitePiecesObjects.get(x), whiteIcons.get(x), whiteSelected.get(x));
                        squares[y][x].setBackground(new Color(103, 101, 47));
                    } else {
                        squares[y][x] = new ChessSquareButton(Game.whitePiecesObjects.get(x), whiteIcons.get(x), whiteSelected.get(x));
                        squares[y][x].setBackground(new Color(241, 245, 233));
                    }
                }
                squares[y][x].setOpaque(true);
                squares[y][x].setBorderPainted(false);
                contents.add(squares[y][x]);
            }
        }
    }

    public void loadImage() throws IOException {

        whitePawnIcon = new ImageIcon(resizeImage(ImageIO.read(new File("whitePawnIcon.png"))));
        ImageIcon whiteBishopIcon = new ImageIcon(resizeImage(ImageIO.read(new File("whiteBishopIcon.png"))));
        ImageIcon whiteKnightIcon = new ImageIcon(resizeImage(ImageIO.read(new File("whiteKnightIcon.png"))));
        ImageIcon whiteRookIcon = new ImageIcon(resizeImage(ImageIO.read(new File("whiteRookIcon.png"))));
        ImageIcon whiteKingIcon = new ImageIcon(resizeImage(ImageIO.read(new File("whiteKingIcon.png"))));
        ImageIcon whiteQueenIcon = new ImageIcon(resizeImage(ImageIO.read(new File("whiteQueenIcon.png"))));

        //selected affect
        whitePawnSelected = new ImageIcon(resizeImage(ImageIO.read(new File("whitePawnSelected.png"))));
        ImageIcon whiteBishopSelected = new ImageIcon(resizeImage(ImageIO.read(new File("whiteBishopSelected.png"))));
        ImageIcon whiteKnightSelected = new ImageIcon(resizeImage(ImageIO.read(new File("whiteKnightSelected.png"))));
        ImageIcon whiteRookSelected = new ImageIcon(resizeImage(ImageIO.read(new File("whiteRookSelected.png"))));
        ImageIcon whiteKingSelected = new ImageIcon(resizeImage(ImageIO.read(new File("whiteKingSelected.png"))));
        ImageIcon whiteQueenSelected = new ImageIcon(resizeImage(ImageIO.read(new File("whiteQueenSelected.png"))));

        blackPawnIcon = new ImageIcon(resizeImage(ImageIO.read(new File("blackPawnIcon.png"))));
        ImageIcon blackBishopIcon = new ImageIcon(resizeImage(ImageIO.read(new File("blackBishopIcon.png"))));
        ImageIcon blackKnightIcon = new ImageIcon(resizeImage(ImageIO.read(new File("blackKnightIcon.png"))));
        ImageIcon blackRookIcon = new ImageIcon(resizeImage(ImageIO.read(new File("blackRookIcon.png"))));
        ImageIcon blackKingIcon = new ImageIcon(resizeImage(ImageIO.read(new File("blackKingIcon.png"))));
        ImageIcon blackQueenIcon = new ImageIcon(resizeImage(ImageIO.read(new File("blackQueenIcon.png"))));
        
        blackPawnSelected = new ImageIcon(resizeImage(ImageIO.read(new File("blackPawnSelected.png"))));
        ImageIcon blackBishopSelected = new ImageIcon(resizeImage(ImageIO.read(new File("blackBishopSelected.png"))));
        ImageIcon blackKnightSelected = new ImageIcon(resizeImage(ImageIO.read(new File("blackKnightSelected.png"))));
        ImageIcon blackRookSelected = new ImageIcon(resizeImage(ImageIO.read(new File("blackRookSelected.png"))));
        ImageIcon blackKingSelected = new ImageIcon(resizeImage(ImageIO.read(new File("blackKingSelected.png"))));
        ImageIcon blackQueenSelected = new ImageIcon(resizeImage(ImageIO.read(new File("blackQueenSelected.png"))));

        whiteIcons.addAll(Arrays.asList(whiteRookIcon, whiteKnightIcon, whiteBishopIcon, whiteQueenIcon, whiteKingIcon, whiteBishopIcon, whiteKnightIcon, whiteRookIcon));
        
        whiteSelected.addAll(Arrays.asList(whiteRookSelected, whiteKnightSelected, whiteBishopSelected, whiteQueenSelected, whiteKingSelected, whiteBishopSelected, whiteKnightSelected, whiteRookSelected));
        
        blackIcons.addAll(Arrays.asList(blackRookIcon, blackKnightIcon, blackBishopIcon, blackQueenIcon, blackKingIcon, blackBishopIcon, blackKnightIcon, blackRookIcon));

        blackSelected.addAll(Arrays.asList(blackRookSelected, blackKnightSelected, blackBishopSelected, blackQueenSelected, blackKingSelected, blackBishopSelected, blackKnightSelected, blackRookSelected));
    }

    BufferedImage resizeImage(Image originalImage) {

        Image afterScaling = originalImage.getScaledInstance(87, 87, Image.SCALE_SMOOTH); //scale image
        BufferedImage outputImage = new BufferedImage(87, 87, BufferedImage.TYPE_INT_ARGB);   //create output BufferedImage
        outputImage.createGraphics().drawImage(afterScaling, 0, 0, null);   //draw that image onto the output
        return outputImage;

    }

    class ChessSquareButton extends JButton implements ActionListener {

        private Pieces thisPiece;

        ImageIcon notSelected;

        ImageIcon selected;

        ChessSquareButton() {

            addActionListener(this);

        }

        ChessSquareButton(Pieces pieces, ImageIcon notSelected, ImageIcon selected) {

            addActionListener(this);

            this.thisPiece = pieces;

            setIcon(notSelected);

            this.selected = selected;

            this.notSelected = notSelected;

        }

        @Override
        public void actionPerformed(ActionEvent e) {    //First action listener selects the piece to move

            Object source = e.getSource();

            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    if (squares[y][x] == source) {
                        if (currentPos == null) {   //first Click
                            currentPos = new int[]{x, y};
                            if (thisPiece == null) {
                                currentPos = null;

                            }
                            else if (thisPiece.thisPlayer.side.equals(turn)) {

                                setIcon(selected);

                            }

                        }else {    //second click

                            targetPos = new int[]{x, y};

                            if (turn.equals("white")){
                                if (squares[currentPos[1]][currentPos[0]].thisPiece.thisPlayer.side.equals("white")){
                                    //if it clicks itself
                                    if (squares[currentPos[1]][currentPos[0]] == squares[targetPos[1]][targetPos[0]] ){ //if it's the same square, it means to cancel the selecting

                                        squares[currentPos[1]][currentPos[0]].setIcon(squares[currentPos[1]][currentPos[0]].notSelected);

                                        currentPos = null;

                                    }else if (!squares[currentPos[1]][currentPos[0]].thisPiece.isLegal(currentPos, targetPos)) {  // if not a legal move

                                        squares[currentPos[1]][currentPos[0]].setIcon(squares[currentPos[1]][currentPos[0]].notSelected);

                                        currentPos = null;

                                    }
                                    else {
                                        process();
                                    }
                                }
                                else{
                                    squares[currentPos[1]][currentPos[0]].setIcon(squares[currentPos[1]][currentPos[0]].notSelected);
                                    currentPos = null;
                                }
                            }
                            else if (turn.equals("black")){

                                if (squares[currentPos[1]][currentPos[0]].thisPiece.thisPlayer.side.equals("black")) {

                                    if (squares[currentPos[1]][currentPos[0]] == squares[targetPos[1]][targetPos[0]]) { //if it's the same square, it means to cancel the selecting

                                        squares[currentPos[1]][currentPos[0]].setIcon(squares[currentPos[1]][currentPos[0]].notSelected);

                                        currentPos = null;

                                    } else if (!squares[currentPos[1]][currentPos[0]].thisPiece.isLegal(currentPos, targetPos)) {  // if not a legal move

                                        squares[currentPos[1]][currentPos[0]].setIcon(squares[currentPos[1]][currentPos[0]].notSelected);

                                        currentPos = null;

                                    }else {
                                        process();
                                        turn = "white";
                                    }
                                }else{
                                        squares[currentPos[1]][currentPos[0]].setIcon(squares[currentPos[1]][currentPos[0]].notSelected);
                                        currentPos = null;
                                    }
                            }
                        }
                    }
                }
            }
        }

        public void process() {
    //if the original square has a piece

                //if it's a legal move
                this.notSelected = squares[currentPos[1]][currentPos[0]].notSelected;   //give the notSelected icon to the new square
                this.selected = squares[currentPos[1]][currentPos[0]].selected;     //give the selected icon to the new square
                this.setIcon(notSelected); //set the new square icon as not selected
                this.thisPiece = squares[currentPos[1]][currentPos[0]].thisPiece;   //give the piece information to the new square
                squares[currentPos[1]][currentPos[0]].selected = null;  //erase the old icons
                squares[currentPos[1]][currentPos[0]].notSelected = null;
                squares[currentPos[1]][currentPos[0]].setIcon(null);    //set the icon to null
                squares[currentPos[1]][currentPos[0]].thisPiece = null; //erase the old piece information
                thisPiece.setPosition(targetPos);

                currentPos = null;  //erase currentPos
                hasPiece = true;
                turn = "black";
            }
    }
}
