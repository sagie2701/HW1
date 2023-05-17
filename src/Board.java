import java.util.Arrays;

public class Board {

    /**
     * board class -- a board of the puzzle build from tiles
     * @param tiles -- 2d array of tiles that represent the board
     * @param tilesNotInPlace -- array of tiles that not in their goal position
     * @param tilesNotInPlaceCounter -- number of tiles that not in their goal position
     */
    private Tile[][] tiles;
    private Tile[] tilesNotInPlace;
    private int tilesNotInPlaceCounter;

    /**
     * create new Board
     * @param boardString -- boardGame by string
     */
    public Board(String boardString) {
        String[] boardStringArray = boardString.split("\\|");
        int boardRows = boardStringArray.length;
        String[] boardColumns = boardStringArray[0].split(" ");
        //set attribute
        this.tiles = new Tile[boardRows][boardColumns.length];
        this.tilesNotInPlace = new Tile[this.tiles.length * this.tiles[0].length];
        this.tilesNotInPlaceCounter = 0;
        //crate board game and tiles not in place array
        for (int i = 0 ; i < boardRows ; i++){
            String[] numbersInRow = boardStringArray[i].split(" ");
            for (int j = 0 ; j < boardColumns.length ; j++){
                this.tiles[i][j] = new Tile(numbersInRow[j], i, j);
                int value = this.tiles[i][j].getValue();
                //crate tiles not in place array
                if ( /*(value == 0 && (i != boardRows-1 || j != boardColumns.length-1)) || */(value != 0 && value != boardColumns.length * i + j + 1)) {
                    this.tilesNotInPlace[this.tilesNotInPlaceCounter] = this.tiles[i][j];
                    this.tilesNotInPlaceCounter++;
                }
                this.tiles[i][j].manhattanDistance(boardColumns.length);
            }
        }

    }


    /**
     * create new Board
     * @param boardGame -- boardGame
     * @param tilesNotInPlace -- tiles that not in their goal place array
     * @param tilesNotInPlaceCounter -- number of tiles that not in their goal place
     */
    public Board(Tile[][] boardGame, Tile[] tilesNotInPlace, int tilesNotInPlaceCounter){
        this.tiles = boardGame;
        this.tilesNotInPlace = tilesNotInPlace;
        this.tilesNotInPlaceCounter = tilesNotInPlaceCounter;
    }


    /**
     * returns the number of rows in board
     * @return the number of rows in board
     */
    public int getRowsNumber(){
        if (this.tiles != null) {
            return this.tiles.length;
        }
        return 0;
    }


    /**
     * returns the number of columns in board
     * @return the number of columns in board
     */
    public int getColumnsNumber(){
        if (this.tiles != null) {
            return this.tiles[0].length;
        }
        return 0;
    }


    /**
     * gets the location of the empty tile
     * @return array of the location of the empty tile, [0] - row, [1] - column
     */
    public Tile getEmptyTile(){
        for (int i = 0; i < this.tiles.length; i++){
            for (int j = 0; j < this.tiles[0].length ; j++){
                if (this.tiles[i][j].getValue() == 0){
                    return this.tiles[i][j];
                }
            }
        }
        return null;
    }


    /**
     * gets the tile in the provided location
     * @param row -- row location of the wanted tile
     * @param column -- column location of the wanted tile
     * @return a wanted tile by location
     */
    public Tile getTile(int row, int column){
        return this.tiles[row][column];
    }


    /**
     * makes a provided action on the board
     * @param action -- an action to preform on the board
     * @return new Board type after the action was made
     */
    public Board moveTile(Action action){
        int[] emptyTileLocation = action.getEmptyTileLocation();
        int emptyTileRow = emptyTileLocation[0], emptyTileCol = emptyTileLocation[1];
        Tile tileToMove = action.getTileToMove();
        int tileToMoveRow = tileToMove.getRow(), tileToMoveCol = tileToMove.getColumn();
        //clone the board game before making changes
        Tile[][] newBoard = this.cloneBoardGame();
        //update board game
        newBoard[emptyTileRow][emptyTileCol] = new Tile(tileToMove);
        newBoard[tileToMoveRow][tileToMoveCol] = new Tile(this.tiles[emptyTileRow][emptyTileCol]);
        //check if we move a tile fromm its goal location
        if (tileToMove.getManhattanDistance() == 0) {
            newBoard[emptyTileRow][emptyTileCol].switchPlace(newBoard[tileToMoveRow][tileToMoveCol]);
            newBoard[emptyTileRow][emptyTileCol].manhattanDistance(newBoard[0].length);
            newBoard[tileToMoveRow][tileToMoveCol].manhattanDistance(newBoard[0].length);
            return new Board(newBoard, crateTilesNotInPlace(newBoard), this.tilesNotInPlaceCounter+1);
        }
        //update positions of tiles and manhattan distance
        newBoard[emptyTileRow][emptyTileCol].switchPlace(newBoard[tileToMoveRow][tileToMoveCol]);
        newBoard[emptyTileRow][emptyTileCol].manhattanDistance(newBoard[0].length);
        newBoard[tileToMoveRow][tileToMoveCol].manhattanDistance(newBoard[0].length);
        //check if tile moved to his goal location
        if (newBoard[emptyTileRow][emptyTileCol].getManhattanDistance() == 0) {
            return new Board(newBoard, crateTilesNotInPlace(newBoard) ,this.tilesNotInPlaceCounter-1);
        }
        return new Board(newBoard, crateTilesNotInPlace(newBoard), this.tilesNotInPlaceCounter);
    }


    /**
     * sum all manhattan distance tiles
     * @return int - sum all manhattan distance tiles
     */
    public int getEmptyTilesDistance(){
        int sumDistance = 0;
        for (int i = 0 ; i < tilesNotInPlaceCounter ; i++){
            sumDistance += tilesNotInPlace[i].getManhattanDistance();
        }
        return sumDistance;
    }


    /**
     * crate a new tiles not in place array
     * @param tiles -- current board game
     * @return a new tiles not in place array
     */
    private Tile[] crateTilesNotInPlace(Tile[][] tiles){
        Tile[] newTilesNotInPlace = new Tile[this.tilesNotInPlace.length];
        int value, index = 0;
        for (int i = 0 ; i < tiles.length ; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                value = tiles[i][j].getValue();
                if ((value != 0 && value != tiles[0].length * i + j + 1)) {
                    newTilesNotInPlace[index++] = tiles[i][j];
                }
            }
        }
        return newTilesNotInPlace;
    }


    /**
     * return a copy of the board
     * @return 2d array of tiles
     */
    private Tile[][] cloneBoardGame(){
        int rows = this.tiles.length;
        int columns = this.tiles[0].length;
        Tile[][] cloneBoard = new Tile[rows][columns];
        for (int i = 0 ; i < rows; i++){
            for (int j = 0 ; j < columns ; j++){
                cloneBoard[i][j] = new Tile(this.tiles[i][j]);
                cloneBoard[i][j].setMinkowskiDistance(this.tiles[i][j].getManhattanDistance());
            }
        }
        return cloneBoard;
    }

    /**
     * check if the current game board is the goal
     * @return true if is goal and false if not goal
     */
    public boolean isGoal(){
        int rows = this.tiles.length;
        int columns = this.tiles[0].length;
        for (int i = 0 ; i < rows; i++){
            for (int j = 0 ; j < columns ; j++){
                int value = this.tiles[i][j].getValue();
                if ( (value == 0 && (i != rows-1 || j != columns-1)) || (value != 0 && value != columns * i + j + 1))
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
