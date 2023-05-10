public class Action {
    private Tile tileToMove;
    private String directionToMove;
    private int emptyTileRow;
    private int emptyTileCol;

    public Action(Tile tileToMove, String directionToMove, int emptyTileRow, int emptyTileCol) {
        this.tileToMove = tileToMove;
        this.directionToMove = directionToMove;
        this.emptyTileRow = emptyTileRow;
        this.emptyTileCol = emptyTileCol;
    }


    public Tile getTileToMove() {
        return tileToMove;
    }

    public int[] getEmptyTileLocation(){
        int[] arr = new int[]{this.emptyTileRow, this.emptyTileCol};
        return arr;
    }

    public String toString(){
        return "Move " + this.tileToMove.getValue() + " " + this.directionToMove;
    }
}


