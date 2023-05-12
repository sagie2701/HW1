public class Action {
    /**
     * Action class -- an action that possible to commit
     * @param tileToMove -- a Tile to move in this action
     * @param directionToMove -- direction to move with the tile
     * @param emptyTileRow -- empty tile current location (row)
     * @param emptyTileCol -- empty tile current location (column)
     */
    private Tile tileToMove;
    private String directionToMove;
    private int emptyTileRow;
    private int emptyTileCol;

    /**
     * build a new Action
     * @param tileToMove -- tile to move
     * @param directionToMove -- direction to move
     * @param emptyTileRow -- empty tile current location (row)
     * @param emptyTileCol -- empty tile current location (column)
     */
    public Action(Tile tileToMove, String directionToMove, int emptyTileRow, int emptyTileCol) {
        this.tileToMove = tileToMove;
        this.directionToMove = directionToMove;
        this.emptyTileRow = emptyTileRow;
        this.emptyTileCol = emptyTileCol;
    }

    /**
     * @return -- Tile to move
     */
    public Tile getTileToMove() {
        return tileToMove;
    }

    /**
     * @return -- int array with empty tile location (index 0 row, 1 column)
     */
    public int[] getEmptyTileLocation(){
        int[] arr = new int[]{this.emptyTileRow, this.emptyTileCol};
        return arr;
    }

    /**
     * check if a possible move is good one by manhetten distance of the Tile
     * @return -- 0 for best move, manhetten distance for good move, and manhetten distance*2 for bad move
     */
    public int isGoodMove(){
        int manhettenDistance = this.tileToMove.getManhettenDistanceSum();
        int prevRow = this.tileToMove.getRow(), prevColumn = this.tileToMove.getColumn();
        this.tileToMove.switchPlaceByPlace(this.emptyTileRow, this.emptyTileCol);
        int newManhettenDistance = this.tileToMove.getManhettenDistanceSum();
        this.tileToMove.switchPlaceByPlace(prevRow, prevColumn);
        if (newManhettenDistance == 0)
            return 0;
        else if(newManhettenDistance - manhettenDistance > 0)
            return newManhettenDistance*2;
        return newManhettenDistance;
    }

    /**
     * @return -- a string that describe the move (example: "Move 7 up")
     */
    public String toString(){
        return "Move " + this.tileToMove.getValue() + " " + this.directionToMove;
    }
}


