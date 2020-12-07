package ChessGame;

/**
 * 2020-11-29: Debugged bishop, rook, and queen. They should now function properly
 * 2020-11-28: Bishop should be done
 * Rooks are done too
 * begins to write queen hopefully I can get it done today
 * 2020-11-27: Knights & Pawns Done
 * prior notes
 * how do I remove the piece taken from another player?
 * need to create a new object and give positions by giving the targeted cell a value
 * we can find whether the cell is empty using isEmpty method   Done!
 * the rules for each piece and corresponding change in it's position
 * reflect on board needs function from tiles to check if tile is empty, and change
 */
class Pawn extends Pieces {

    final String name;
    final String side;
    final Player thisPlayer;
    final Player enemy;
    int move;   // counts amount of move, if it is the first move, it is allowed to move twice
    int[] currentPos;

    Pawn(int[] position, String name, Player thisPlayer, Player enemyPlayer, int move) {
        super(position, name, thisPlayer, enemyPlayer);
        this.side = thisPlayer.side;
        this.name = name;
        this.thisPlayer = thisPlayer;
        this.enemy = enemyPlayer;
        this.move = move;
        this.currentPos = position;
    }


    boolean isLegal(int[] currentPos, int[] targetPos) {
            /*
            this methods checks whether the move is legal for pawn
            pawns are special because it can take pieces in the squares to its left up and right up if they are occupied
            need to check the side of the piece
            maybe give each string a name and see if there is the opposite color?
             */
        boolean legal = false;
        int currentX = currentPos[0];
        int currentY = currentPos[1];
        int targetX = targetPos[0];
        int targetY = targetPos[1];

        /*
        three conditions: left top, right top, top
         */
        //determine what the user want to do first
        //The two should come together think how

        if (side.equals("white")) {
            boolean cond1 = currentX + 1 == targetX && currentY - 1 == targetY; // right top
            boolean cond2 = currentX - 1 == targetX && currentY - 1 == targetY; // left top
            boolean cond3 = currentX == targetX && currentY - 1 == targetY; //top for whites, the y can only decrease
            boolean cond4 = move <= 1 && currentX == targetX && currentY - 2 == targetY;
            if (cond1) { // if not out of bound
                    if (Board.Occupied(targetX, targetY)) { //if occupied
                        String targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                        legal = !side.equals(targetSide);
                    }
            }
            if (cond2) {
                    if (Board.Occupied(targetX, targetY)) {
                        String targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                        legal = !side.equals(targetSide);
                    }
            }
            if (cond3) {
            /*
            cond3, if space is occupied, return false
            if target is not occupied
             */
                    //if not occupied
                    legal = !Board.Occupied(targetX, targetY);
            }
            if (cond4) {
                    //if is occupied then you can't take that space
                    //if not occupied, then it is allowed
                    legal = !Board.Occupied(targetX, targetY);
            }
        }
        if (side.equals("black")) {
            boolean cond1 = currentX + 1 == targetX && currentY + 1 == targetY; // right top
            boolean cond2 = currentX - 1 == targetX && currentY + 1 == targetY; // left top
            boolean cond3 = currentX == targetX && currentY + 1 == targetY; //top for whites, the y can only decrease
            boolean cond4 = move <= 1 && currentX == targetX && currentY + 2 == targetY;
            if (cond1) {  // if not out of bound
                    if (Board.Occupied(targetX, targetY)) { //if occupied
                        String targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                        legal = !side.equals(targetSide);
                    }

            }
            if (cond2) {
                    if (Board.Occupied(targetX, targetY)) {
                        String targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                        legal = !side.equals(targetSide);
                    }
            }
            if (cond3) {
            /*
            cond3, if space is occupied, return false
            if target is not occupied
             */
                    //if not occupied
                    legal = !Board.Occupied(targetX, targetY);
            }
            if (cond4) {
                    //if is occupied then you can't take that space
                    //if not occupied, then it is allowed
                    legal = !Board.Occupied(targetX, targetY);
                }
        }
        if (legal) {
            move++;
        }
        return legal;
    }
}   //Done

class Knight extends Pieces {
    /**
     * 2020-11-27: begin writing knights
     */
    final String name;
    final Player thisPlayer;
    final Player enemyPlayer;
    final String side;

    Knight(int[] position, String name, Player thisPlayer, Player enemeyPlayer) {
        super(position, name, thisPlayer, enemeyPlayer);
        this.name = name;
        this.thisPlayer = thisPlayer;
        this.enemyPlayer = enemeyPlayer;
        this.side = thisPlayer.side;
    }

    @Override
    boolean isLegal(int[] currentPos, int[] targetPos) {
        int currentX = currentPos[0];
        int currentY = currentPos[1];
        int targetX = targetPos[0];
        int targetY = targetPos[1];
        boolean legal = false;
            /*first define conditions for knights
             then check if out of bound
             then check if it is a legal move
             knights have 8 moves it can move to, so 8 conditions
             */
        boolean cond1 = currentX + 1 == targetX && currentY + 2 == targetY;
        boolean cond2 = currentX - 1 == targetX && currentY + 2 == targetY;
        boolean cond3 = currentX + 2 == targetX && currentY + 1 == targetY;
        boolean cond4 = currentX + 2 == targetX && currentY - 1 == targetY;
        boolean cond5 = currentX + 1 == targetX && currentY - 2 == targetY;
        boolean cond6 = currentX - 1 == targetX && currentY - 2 == targetY;
        boolean cond7 = currentX - 2 == targetX && currentY + 1 == targetY;
        boolean cond8 = currentX - 2 == targetX && currentY - 1 == targetY;
        if (cond1 || cond2 || cond3 || cond4 || cond5 || cond6 || cond7 || cond8) {    //if any of these conditions are true
         //if not ouf bound\
                if (!Board.Occupied(targetX, targetY)) {
                    legal = true;
                } else if (Board.Occupied(targetX, targetY)) {
                    String targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                    if (!side.equals(targetSide)) {
                        legal = true;
                    } else {
                        System.out.println("You cannot take your own piece! ");
                        legal = false;
                    }
                }
            }
        return legal;
    }

}   //Done

class Bishop extends Pieces {
    /*
    Bishop is by far the hardest..
     */
    final String name;
    final Player thisPlayer;
    final Player enemyPlayer;

    Bishop(int[] position, String name, Player thisPlayer, Player enemeyPlayer) {
        super(position, name, thisPlayer, enemeyPlayer);
        this.name = name;
        this.thisPlayer = thisPlayer;
        this.enemyPlayer = enemeyPlayer;
    }

    @Override
    boolean isLegal(int[] currentPos, int[] targetPos) {
        int currentX = currentPos[0];
        int currentY = currentPos[1];
        int targetX = targetPos[0];
        int targetY = targetPos[1];
        boolean legal = false;
            /*
            Bishops can move diagonally as long as there are no pieces blocking it.
            I can use a for loop to iterate through every targeted tile and return whether if it's empty
            if it's not empty, return the nearest and ask whether the user wants to move there
            Only need one condition basically the change in x must equal the change in y
             */
        //here it gets a bit complicated
        //The problem is if x and y is positive we need to increase x and y from 0 to iterate through every tile
        //If it's negative we need to decrease x and y from 0
        int changeInX = targetX - currentX; //we need to find the change in X and Y
        int changeInY = targetY - currentY; //this change is important as later in the for loop when we scan for any other pieces in the way
        int absChangeInX = Math.abs(changeInX); //we then need to find its absolute value to make sure they are the same despite + or -
        //this is for later comparing if it meets cond1
        int absChangeInY = Math.abs(changeInY);
        int absChange = Math.abs(changeInX);
        boolean cond1 = absChangeInX == absChangeInY;
        int iX = 0;
        int iY = 0;
        String targetSide;
        if (cond1) {  //iterate through every tile and check from the current tile whether it is full
                if (changeInY < 0) {    //moving upwards
                    if (changeInX < 0) {  //moving to the left
                        for (int change = 0; change < absChange; change++) {
                            iX--;
                            iY--;
                            if (Board.Occupied(currentX + iX, currentY + iY)) {
                                break;  // if the tile is occupied it will break the loop and go and determine
                            }
                            //and then check if side matches
                            //if the target is further than the iteration, the move is illegal
                            //otherwise it's fine
                        }
                        if (targetX == currentX + iX || targetY == currentY + iY) {
                            if (Board.Occupied(currentX + iX, currentY + iY)) {
                                targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                                if (!thisPlayer.side.equals(targetSide)) {
                                    legal = true;
                                }
                            } else {      // if the target tile is empty
                                legal = true;
                            }
                        }
                    } else if (changeInX > 0) {//moving to the right
                        for (int change = 0; change < absChange; change++) {
                            iX++;
                            iY--;
                            if (Board.Occupied(currentX + iX, currentY + iY)) {
                                break;
                            }
                        }
                        if (targetX == currentX + iX || targetY == currentY + iY) { // missed what happens if that tile is empty
                            if (Board.Occupied(currentX + iX, currentY + iY)) {
                                targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                                if (!thisPlayer.side.equals(targetSide)) {
                                    legal = true;
                                }
                            } else {      // if the target tile is empty
                                legal = true;
                            }
                        }
                    }
                } else if (changeInY > 0) {   //moving downwards
                    if (changeInX < 0) {    //moving to the left
                        for (int change = 0; change < absChange; change++) {
                            iX--;
                            iY++;
                            if (Board.Occupied(currentX + iX, currentY + iY)) {
                                break;
                            }
                        }
                        if (targetX == currentX + iX || targetY == currentY + iY) {
                            if (Board.Occupied(currentX + iX, currentY + iY)) {
                                targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                                if (!thisPlayer.side.equals(targetSide)) {
                                    legal = true;
                                }
                            } else {      // if the target tile is empty
                                legal = true;
                            }
                        }
                    } else if (changeInX > 0) { //moving to the right
                        for (int change = 0; change < absChange; change++) {
                            iX++;
                            iY++;
                            if (Board.Occupied(currentX + iX, currentY + iY)) {
                                break;
                            }
                        }
                        if (targetX == currentX + iX || targetY == currentY + iY) {
                            if (Board.Occupied(currentX + iX, currentY + iY)) {
                                targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                                if (!thisPlayer.side.equals(targetSide)) {
                                    legal = true;
                                }
                            } else {      // if the target tile is empty
                                legal = true;
                            }
                        }
                    }
                }
            }
        return legal;
    }
}   //Done

class Rook extends Pieces {
    /*
    Rooks can share a similar approach as Bishops but should be easier since only x or y change
     */
    final String name;
    final Player thisPlayer;
    final Player enemyPlayer;

    Rook(int[] position, String name, Player thisPlayer, Player enemeyPlayer) {
        super(position, name, thisPlayer, enemeyPlayer);
        this.thisPlayer = thisPlayer;
        this.enemyPlayer = enemeyPlayer;
        this.name = name;
    }

    @Override
    boolean isLegal(int[] currentPos, int[] targetPos) {
        int currentX = currentPos[0];
        int currentY = currentPos[1];
        int targetX = targetPos[0];
        int targetY = targetPos[1];
        int changeInX = targetX - currentX;
        int changeInY = targetY - currentY;
        int absChangeInX = Math.abs(changeInX);
        int absChangeInY = Math.abs(changeInY);
        int iX = 0;
        int iY = 0;
        boolean legal = false;  // default false because to make sure it only does the move if it matches all the requirements
        // condition: only x change or only y change
        boolean cond1 = absChangeInX > 0 && absChangeInY == 0 || absChangeInY > 0 && absChangeInX == 0;
        String targetSide;
        if (cond1) {
                        /*
                        decide if the change occurs in X or Y
                        the change for the other variable should be 0
                        then decide the direction of iteration
                         */
                if (absChangeInX > 0) { // if the change occurs in X
                    if (changeInX > 0) { //moving towards right
                        for (int change = 0; change < absChangeInX; change++) {
                            iX++;
                            if (Board.Occupied(currentX + iX, currentY + iY)) {  //if it encounters a piece
                                break;
                            }// it exits the loop naturally if does not encounter any piece
                        }
                        if (currentX + iX == targetX && currentY == targetY) {  //if it is the destination
                            if (Board.Occupied(currentX + iX, currentY + iY)) {  //if the tile is occupied
                                targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                                if (!thisPlayer.side.equals(targetSide)) {
                                    legal = true;
                                }
                            } else {    //if it is not occupied
                                legal = true;
                            }
                        }
                    } else if (changeInX < 0) {//moving towards left
                        for (int change = 0; change < absChangeInX; change++) {
                            iX--;
                            if (Board.Occupied(currentX + iX, currentY + iY)) {  //if it encounters a piece
                                break;
                            }// it exits the loop naturally if does not encounter any piece
                        }
                        if (currentX + iX == targetX && currentY == targetY) {  //if it is the destination
                            if (Board.Occupied(currentX + iX, currentY + iY)) {  //if the tile is occupied
                                targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                                if (!thisPlayer.side.equals(targetSide)) {
                                    legal = true;
                                }
                            } else {
                                legal = true;
                            }
                        }
                    }
                } else if (absChangeInY > 0) { //if change occurs in Y
                    if (changeInY > 0) {    //if it moves down
                        for (int change = 0; change < absChangeInY; change ++) {
                            iY ++;
                            if (Board.Occupied(currentX + iX, currentY + iY)) {  //if it encounters a piece
                                break;
                            }// it exits the loop naturally if does not encounter any piece
                        }
                        if (currentX == targetX && currentY + iY == targetY) {  //if it is the destination
                            if (Board.Occupied(currentX + iX, currentY + iY)) {  //if the tile is occupied
                                targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                                if (!thisPlayer.side.equals(targetSide)) {
                                    legal = true;
                                }
                            } else {
                                legal = true;
                            }
                        }
                    } else if (changeInY < 0) {   //if it moves up
                        for (int change = 0; change < absChangeInY; change++) {
                            iY --;
                            if (Board.Occupied(currentX + iX, currentY + iY)) {  //if it encounters a piece
                                break;
                            }// it exits the loop naturally if does not encounter any piece
                        }
                        if (currentX == targetX && currentY + iY == targetY) {  //if it is the destination
                            if (Board.Occupied(currentX + iX, currentY + iY)) {  //if the tile is occupied
                                targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                                if (!thisPlayer.side.equals(targetSide)) {
                                    legal = true;
                                }
                            } else {
                                legal = true;
                            }
                        }   // if not,defualt false
                    }
                }
        }
        return legal;
    }
}   //Done

class Queen extends Pieces {
    /*
    can I just use everything from bishop and rook?
     */
    String name;
    Player thisPlayer;
    Player enemyPlayer;

    Queen(int[] position, String name, Player thisPlayer, Player enemeyPlayer) {
        super(position, name, thisPlayer, enemeyPlayer);
        this.name = name;
        this.thisPlayer = thisPlayer;
        this.enemyPlayer = enemeyPlayer;
    }

    @Override
    boolean isLegal(int[] currentPos, int[] targetPos) {
        int currentX = currentPos[0];
        int currentY = currentPos[1];
        int targetX = targetPos[0];
        int targetY = targetPos[1];
        int changeInX = targetX - currentX;
        int changeInY = targetY - currentY;
        int absChangeInX = Math.abs(changeInX);
        int absChangeInY = Math.abs(changeInY);
        int absChange = Math.abs(changeInX);
        int iX = 0;
        int iY = 0;
        boolean legal = false;
        boolean cond1 = absChangeInX > 0 && absChangeInY == 0 || absChangeInY > 0 && absChangeInX == 0; //cond1 same as rook movement
        boolean cond2 = absChangeInX == absChangeInY; //cond2 same as bishop movement
        String targetSide;
        if (cond1) {
                        /*
                        decide if the change occurs in X or Y
                        the change for the other variable should be 0
                        then decide the direction of iteration
                         */
            if (absChangeInX > 0) { // if the change occurs in X
                if (changeInX > 0) { //moving towards right
                    for (int change = 0; change < absChangeInX; change++) {
                        iX++;
                        if (Board.Occupied(currentX + iX, currentY + iY)) {  //if it encounters a piece
                            break;
                        }// it exits the loop naturally if does not encounter any piece
                    }
                    if (currentX + iX == targetX && currentY == targetY) {  //if it is the destination
                        if (Board.Occupied(currentX + iX, currentY + iY)) {  //if the tile is occupied
                            targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                            if (!thisPlayer.side.equals(targetSide)) {
                                legal = true;
                            }
                        } else {    //if it is not occupied
                            legal = true;
                        }
                    }
                } else if (changeInX < 0) {//moving towards left
                    for (int change = 0; change < absChangeInX; change++) {
                        iX--;
                        if (Board.Occupied(currentX + iX, currentY + iY)) {  //if it encounters a piece
                            break;
                        }// it exits the loop naturally if does not encounter any piece
                    }
                    if (currentX + iX == targetX && currentY == targetY) {  //if it is the destination
                        if (Board.Occupied(currentX + iX, currentY + iY)) {  //if the tile is occupied
                            targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                            if (!thisPlayer.side.equals(targetSide)) {
                                legal = true;
                            }
                        } else {
                            legal = true;
                        }
                    }
                }
            } else if (absChangeInY > 0) { //if change occurs in Y
                if (changeInY > 0) {    //if it moves down
                    for (int change = 0; change < absChangeInY; change ++) {
                        iY ++;
                        if (Board.Occupied(currentX + iX, currentY + iY)) {  //if it encounters a piece
                            break;
                        }// it exits the loop naturally if does not encounter any piece
                    }
                    if (currentX == targetX && currentY + iY == targetY) {  //if it is the destination
                        if (Board.Occupied(currentX + iX, currentY + iY)) {  //if the tile is occupied
                            targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                            if (!thisPlayer.side.equals(targetSide)) {
                                legal = true;
                            }
                        } else {
                            legal = true;
                        }
                    }
                } else if (changeInY < 0) {   //if it moves up
                    for (int change = 0; change < absChangeInY; change++) {
                        iY --;
                        if (Board.Occupied(currentX + iX, currentY + iY)) {  //if it encounters a piece
                            break;
                        }// it exits the loop naturally if does not encounter any piece
                    }
                    if (currentX == targetX && currentY + iY == targetY) {  //if it is the destination
                        if (Board.Occupied(currentX + iX, currentY + iY)) {  //if the tile is occupied
                            targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                            if (!thisPlayer.side.equals(targetSide)) {
                                legal = true;
                            }
                        } else {
                            legal = true;
                        }
                    }   // if not,defualt false
                }
            }
        }   //copied from rook movement
        if (cond2) {  //iterate through every tile and check from the current tile whether it is full
            if (changeInY < 0) {    //moving upwards
                if (changeInX < 0) {  //moving to the left
                    for (int change = 0; change < absChange; change++) {
                        iX--;
                        iY--;
                        if (Board.Occupied(currentX + iX, currentY + iY)) {
                            break;  // if the tile is occupied it will break the loop and go and determine
                        }
                        //and then check if side matches
                        //if the target is further than the iteration, the move is illegal
                        //otherwise it's fine
                    }
                    if (targetX == currentX + iX || targetY == currentY + iY) {
                        if (Board.Occupied(currentX + iX, currentY + iY)) {
                            targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                            if (!thisPlayer.side.equals(targetSide)) {
                                legal = true;
                            }
                        } else {      // if the target tile is empty
                            legal = true;
                        }
                    }
                } else if (changeInX > 0) {//moving to the right
                    for (int change = 0; change < absChange; change++) {
                        iX++;
                        iY--;
                        if (Board.Occupied(currentX + iX, currentY + iY)) {
                            break;
                        }
                    }
                    if (targetX == currentX + iX || targetY == currentY + iY) { // missed what happens if that tile is empty
                        if (Board.Occupied(currentX + iX, currentY + iY)) {
                            targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                            if (!thisPlayer.side.equals(targetSide)) {
                                legal = true;
                            }
                        } else {      // if the target tile is empty
                            legal = true;
                        }
                    }
                }
            } else if (changeInY > 0) {   //moving downwards
                if (changeInX < 0) {    //moving to the left
                    for (int change = 0; change < absChange; change++) {
                        iX--;
                        iY++;
                        if (Board.Occupied(currentX + iX, currentY + iY)) {
                            break;
                        }
                    }
                    if (targetX == currentX + iX || targetY == currentY + iY) {
                        if (Board.Occupied(currentX + iX, currentY + iY)) {
                            targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                            if (!thisPlayer.side.equals(targetSide)) {
                                legal = true;
                            }
                        } else {      // if the target tile is empty
                            legal = true;
                        }
                    }
                } else if (changeInX > 0) { //moving to the right
                    for (int change = 0; change < absChange; change++) {
                        iX++;
                        iY++;
                        if (Board.Occupied(currentX + iX, currentY + iY)) {
                            break;
                        }
                    }
                    if (targetX == currentX + iX || targetY == currentY + iY) {
                        if (Board.Occupied(currentX + iX, currentY + iY)) {
                            targetSide = Board.boardString[targetY][targetX].substring(1, 6);
                            if (!thisPlayer.side.equals(targetSide)) {
                                legal = true;
                            }
                        } else {      // if the target tile is empty
                            legal = true;
                        }
                    }
                }
            }
        }   //copied from bishop movement
        return legal;
    }
}   //Done

class King extends Pieces { //Began 2020-11-28
    final String name;
    final Player thisPlayer;
    final Player enemyPlayer;
    String side;

    King(int[] position, String name, Player thisPlayer, Player enemeyPlayer) {
        super(position, name, thisPlayer, enemeyPlayer);
        this.name = name;
        this.thisPlayer = thisPlayer;
        this.enemyPlayer = enemeyPlayer;
        this.side = thisPlayer.side;
    }

    @Override
    boolean isLegal(int[] currentPos, int[] targetPos) {
        int currentX = currentPos[0];
        int currentY = currentPos[1];
        int targetX = targetPos[0];
        int targetY = targetPos[1];
        boolean legal = false;
        //Queen can move anywhere 1 tile
        boolean cond1 = currentX + 1 == targetX && currentY + 1 == targetY;
        boolean cond2 = currentX + 1 == targetX && currentY - 1 == targetY;
        boolean cond3 = currentX - 1 == targetX && currentY + 1 == targetY;
        boolean cond4 = currentX - 1 == targetX && currentY - 1 == targetY;
        boolean cond5 = currentX == targetX && currentY + 1 == targetY;
        boolean cond6 = currentX == targetX && currentY - 1 == targetY;
        boolean cond7 = currentX + 1 == targetX && currentY == targetY;
        boolean cond8 = currentX - 1 == targetX && currentY == targetY;
        if (cond1 || cond2 || cond3 || cond4 | cond5 || cond6 | cond7 || cond8) {
            //King movement
                if (Board.Occupied(targetX,targetY)){
                    String targetSide = (Board.boardString[targetY][targetX].substring(1,6));
                    if (!thisPlayer.side.equals(targetSide)){
                        legal = true;
                    }
             }else{
                    legal = true;
                }
        }
        return legal;
    }
}
