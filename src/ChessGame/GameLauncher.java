package ChessGame;

/*
This is the launcher of the game
 */

import java.io.IOException;

public class GameLauncher{

    GameLauncher() throws IOException { //constructor
        new Application();  //creates a new application
    }

    public static void main(String[] args) throws IOException{
        new GameLauncher();
    }
}
