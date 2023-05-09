public class State {

    private Board board;

    public State(String boardString){
        this.board = new Board(boardString);
    }

    public State(Board board) {
        this.board = board;
    }

    public boolean isGoal(){
        int rows = this.board.getRowsNumber();
        int columns = this.board.getcolumnsNumber();
        for (int i = 0 ; i < rows; i++){
            for (int j = 0 ; j < columns ; j++){
                int value = this.board.getValueByPostion(i, j);
                if (value != columns * i + j + 1 || (value == 0 && i != rows-1 && j != columns-1))
                    return false;
            }
        }
        return true;
    }

    public Action[] action(){
        int rows = this.board.getRowsNumber();
        int columns = this.board.getcolumnsNumber();
        Tile emptyTile = this.board.getEmptyTile();
        int emptyTileRow = emptyTile.getRow(), emptyTileCol = emptyTile.getColumn();
        Action[] actions = new Action[4];
        for (enumDirections direction : enumDirections.values()) {
            switch (direction){
                case UP:
                    if (emptyTileRow != rows - 1)
                        actions[0] = new Action(this.board.getTile(emptyTileRow - 1, emptyTileCol), "up");
                    else
                        actions[0] = new Action("up");
                    break;
                case DOWN:
                    if (emptyTileRow != 0)
                        actions[0] = new Action(this.board.getTile(emptyTileRow + 1, emptyTileCol), "down");
                    else
                        actions[0] = new Action("down");
                    break;
                case RIGHT:
                    if (emptyTileCol != 0)
                        actions[0] = new Action(this.board.getTile(emptyTileRow, emptyTileCol - 1), "right");
                    else
                        actions[0] = new Action("right");
                    break;
                case LEFT:
                    if (emptyTileCol != columns - 1)
                        actions[0] = new Action(this.board.getTile(emptyTileRow, emptyTileCol + 1), "left");
                    else
                        actions[0] = new Action("left");

            }
        }
        return actions;
    }

    public State result(Action action){
        return new State(this.board.moveTile(action));
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }


    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
