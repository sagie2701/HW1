import java.util.Arrays;

public class Board {
    private Tile[][] boardGame;

    public Board(String boardString) {
        String[] boardStringArray = boardString.split("\\|");
        int boardRows = boardStringArray.length;
        int boardColumns = boardStringArray[0].length() / 2 + 1;
        this.boardGame = new Tile[boardRows][boardColumns];
        for (int i = 0 ; i < boardRows ; i++){
            String[] numbersInRow = boardStringArray[i].split(" ");
            for (int j = 0 ; j < boardColumns ; j++){
                this.boardGame[i][j] = new Tile(numbersInRow[j], i, j);
            }
        }
    }

    public Board(Tile[][] boardGame){
        this.boardGame = boardGame;
    }

    public int getRowsNumber(){
        if (this.boardGame != null) {
            return this.boardGame.length;
        }
        return 0;
    }

    public int getcolumnsNumber(){
        if (this.boardGame != null) {
            return this.boardGame[0].length;
        }
        return 0;
    }

    public int getValueByPostion(int row, int column){
        return this.boardGame[row][column].getTileNum();
    }

    public Tile getEmptyTile(){
        for (int i = 0 ; i < this.boardGame.length; i++){
            for (int j = 0 ; j < this.boardGame[0].length ; j++){
                if (this.boardGame[i][j].getTileNum() == 0){
                    return this.boardGame[i][j];
                }
            }
        }
        return null;
    }

    public Tile getTile(int row, int column){
        return this.boardGame[row][column];
    }

    public Board moveTile(Action action){
        int[] emptyTileLocation = action.getEmptyTileLocation();
        int emptyTileRow = emptyTileLocation[0], emptyTileCol = emptyTileLocation[1];
        Tile tileToMove = action.getTileToMove();
        Tile[][] newBoard = this.cloneBoardGame();
        //update board game
        newBoard[emptyTileRow][emptyTileCol] = tileToMove;
        newBoard[tileToMove.getRow()][tileToMove.getColumn()] = this.boardGame[emptyTileRow][emptyTileCol];
        //update positions of tiles
        tileToMove.switchPlace(this.boardGame[emptyTileRow][emptyTileCol]);
        return new Board(newBoard);
    }

    private Tile[][] cloneBoardGame(){
        Tile[][] cloneBoard = new Tile[this.boardGame.length][this.boardGame[0].length];
        for (int i = 0 ; i < this.boardGame.length; i++){
            for (int j = 0 ; j < this.boardGame[0].length ; j++){
                cloneBoard[i][j] = new Tile(this.boardGame[i][j]);
            }
        }
        return cloneBoard;
    }

    /*
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

     */
}
