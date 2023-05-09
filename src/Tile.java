public class Tile {

    private final int tileNum;
    private int row;
    private int column;


    public Tile(String tileNum, int row, int column){
        if (tileNum.equals("_"))
            this.tileNum = 0;
        else
            this.tileNum = Integer.parseInt(tileNum);
        this.row = row;
        this.column = column;
    }

    public int getTileNum() {
        return tileNum;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

/*
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

     */
}