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

    public String toString(){
        return "Move " + this.tileToMove.getValue() + " " + this.directionToMove;
    }
}


