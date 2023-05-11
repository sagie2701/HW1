import java.util.Arrays;

public class Board {
    private Tile[][] tiles;
    private int tilesNotInPlace;

    public Board(String boardString) {
        this.tilesNotInPlace = 0;
        String[] boardStringArray = boardString.split("\\|");
        int boardRows = boardStringArray.length;
        String[] boardColumns = boardStringArray[0].split(" ");
        this.tiles = new Tile[boardRows][boardColumns.length];
        for (int i = 0 ; i < boardRows ; i++){
            String[] numbersInRow = boardStringArray[i].split(" ");
            for (int j = 0 ; j < boardColumns.length ; j++){
                this.tiles[i][j] = new Tile(numbersInRow[j], i, j);
                this.tiles[i][j].manhettenDistance(boardColumns.length);
                if (!this.tiles[i][j].isInTarget())
                    this.tilesNotInPlace++;
            }
        }
    }

    public Board(Tile[][] boardGame, int tilesNotInPlace){
        this.tiles = boardGame;
        this.tilesNotInPlace = tilesNotInPlace;
    }

    public int getRowsNumber(){
        if (this.tiles != null) {
            return this.tiles.length;
        }
        return 0;
    }

    public int getcolumnsNumber(){
        if (this.tiles != null) {
            return this.tiles[0].length;
        }
        return 0;
    }

    public int getValueByPostion(int row, int column){
        return this.tiles[row][column].getValue();
    }

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

    public Tile getTile(int row, int column){
        return this.tiles[row][column];
    }

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
        if (newBoard[emptyTileRow][emptyTileCol].isInTarget())
            return new Board(newBoard, this.tilesNotInPlace--);
        return new Board(newBoard, this.tilesNotInPlace);
    }

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

    public int getTilesNotInPlace() {
        return this.tilesNotInPlace;
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
