public class State {

    private Board board;

    /**
     * crate a new State
     * @param boardString -- a string that describe a game board
     */
    public State(String boardString){
        this.board = new Board(boardString);
    }

    /**
     * crate a new State
     * @param board -- a board game
     */
    public State(Board board) {
        this.board = board;
    }

    /**
     * check if the current game board is the goal
     * @return -- true if is and false if not
     */
    public boolean isGoal(){
        return this.board.isGoal();
    }

    public Action[] actions(){
        int rows = this.board.getRowsNumber();
        int columns = this.board.getcolumnsNumber();
        Tile emptyTile = this.board.getEmptyTile();
        int emptyTileRow = emptyTile.getRow(), emptyTileCol = emptyTile.getColumn();
        Action[] actions = new Action[4];
        int counter = 0;
        for (EnumDirection direction : EnumDirection.values()) {
            switch (direction){
                case UP:
                    if (emptyTileRow != 0) {
                        actions[0] = new Action(this.board.getTile(emptyTileRow - 1, emptyTileCol),
                                "up", emptyTileRow, emptyTileCol);
                        counter++;
                    }
                    break;
                case DOWN:
                    if (emptyTileRow != rows - 1) {
                        actions[1] = new Action(this.board.getTile(emptyTileRow + 1, emptyTileCol),
                                "down", emptyTileRow, emptyTileCol);
                        counter++;
                    }
                    break;
                case RIGHT:
                    if (emptyTileCol != 0) {
                        actions[2] = new Action(this.board.getTile(emptyTileRow, emptyTileCol - 1),
                                "right", emptyTileRow, emptyTileCol);
                        counter++;
                    }
                    break;
                case LEFT:
                    if (emptyTileCol != columns - 1) {
                        actions[3] = new Action(this.board.getTile(emptyTileRow, emptyTileCol + 1),
                                "left", emptyTileRow, emptyTileCol);
                        counter++;
                    }
            }
        }
        Action[] actionsToDo = new Action[counter];
        int i = 0, numberOfActions = 0;
        while (counter != 0){
            if (actions[i++] != null){
                counter--;
                actionsToDo[numberOfActions++] = actions[i-1];
            }
        }
        return actionsToDo;
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
