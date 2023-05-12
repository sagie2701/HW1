import java.util.Arrays;

public class Board {
    private Tile[][] tiles;

    public Board(String boardString) {
        String[] boardStringArray = boardString.split("\\|");
        int boardRows = boardStringArray.length;
        String[] boardColumns = boardStringArray[0].split(" ");
        this.tiles = new Tile[boardRows][boardColumns.length];
        for (int i = 0 ; i < boardRows ; i++){
            String[] numbersInRow = boardStringArray[i].split(" ");
            for (int j = 0 ; j < boardColumns.length ; j++){
                this.tiles[i][j] = new Tile(numbersInRow[j], i, j);
                this.tiles[i][j].manhettenDistance(boardColumns.length);
            }
        }
    }

    /**
     *
     * @param boardGame
     */
    public Board(Tile[][] boardGame){
        this.tiles = boardGame;
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
     *
     * @return
     */
    public int getcolumnsNumber(){
        if (this.tiles != null) {
            return this.tiles[0].length;
        }
        return 0;
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public int getValueByPostion(int row, int column){
        return this.tiles[row][column].getValue();
    }

    /**
     *
     * @return
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
     *
     * @param row
     * @param column
     * @return
     */
    public Tile getTile(int row, int column){
        return this.tiles[row][column];
    }

    /**
     *
     * @param action
     * @return
     */
    public Board moveTile(Action action){
        int[] emptyTileLocation = action.getEmptyTileLocation();
        int emptyTileRow = emptyTileLocation[0], emptyTileCol = emptyTileLocation[1];
        Tile tileToMove = action.getTileToMove();
        int tileToMoveRow = tileToMove.getRow(), tileToMoveCol = tileToMove.getColumn();
        Tile[][] newBoard = this.cloneBoardGame();
        //update board game
        newBoard[emptyTileRow][emptyTileCol] = new Tile(tileToMove);
        newBoard[tileToMoveRow][tileToMoveCol] = new Tile(this.tiles[emptyTileRow][emptyTileCol]);
        //update positions of tiles
        newBoard[emptyTileRow][emptyTileCol].switchPlace(newBoard[tileToMoveRow][tileToMoveCol]);
        newBoard[emptyTileRow][emptyTileCol].manhettenDistance(newBoard[0].length);
        return new Board(newBoard);
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
                cloneBoard[i][j].setManhettenDistance(this.tiles[i][j].getManhettenDistance());
            }
        }
        return cloneBoard;
    }

    /**
     * check if the current game board is the goal
     * @return -- true if is and false if not
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
