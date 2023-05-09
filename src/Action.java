public class Action {
    private Tile tileToMove;
    private String directionToMove;

    public Action(Tile tileToMove, String directionToMove) {
        this.tileToMove = tileToMove;
        this.directionToMove = directionToMove;
    }

    public Action(String directionToMove) {
        this.directionToMove = directionToMove;
    }

    public boolean isLegal(){
        return this.tileToMove == null;
    }

    public Tile getTileToMove() {
        return tileToMove;
    }

    public String toString(){
        return "Move " + this.tileToMove.getTileNum() + " " + this.directionToMove;
    }
}


