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
        Tile temp = this.getEmptyTile();
        int emptyTileRow = temp.getRow(), emptyTileCol = temp.getColumn();
        Tile tileToMove = action.getTileToMove();
        Tile[][] newBoard = this.boardGame.clone();
        //update board game
        newBoard[temp.getRow()][temp.getColumn()] = tileToMove;
        newBoard[tileToMove.getRow()][tileToMove.getColumn()] = temp;
        //update positions of tiles
        temp.setRow(tileToMove.getRow());
        temp.setColumn(tileToMove.getColumn());
        tileToMove.setRow(emptyTileRow);
        tileToMove.setColumn(emptyTileCol);
        return new Board(newBoard);
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
