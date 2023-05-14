public class State {
    /**
     * Stata class -- hold a Board game and creates and performs actions
     * @param board -- a Board object with the board game
     */
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

    /**
     * crate all possible actions in current state
     * @return -- an array[4] with all possible actions by order (up, down, right, left)
     */
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

    /**
     * crate a new State
     * @param action -- action to commit in order to crate the new state
     * @return -- a new State
     */
    public State result(Action action){
        return new State(this.board.moveTile(action));
    }

    public int getEmptyTileDistance(){
        return this.board.getEmptyTile().getManhettenDistance();
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
