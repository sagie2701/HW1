import java.lang.Math;
public class Tile {

    /**
     * Tile class -- a tile in the board of the puzzle
     * @param value -- the value of the tile
     * @param row -- the row the tile is in
     * @param column -- the column the tile is in
     * @param manhettenDistance -- the manhetten Distance of the tile
     */
    private final int value;
    private int row;
    private int column;
    private int[] manhettenDistance = new int[2];

    /**
     * creats new tile
     * @param tileNum -- tile value
     * @param row -- tile row location
     * @param column -- tile column location
     */
    public Tile(String tileNum, int row, int column){
        if (tileNum.equals("_"))
            this.value = 0;
        else
            this.value = Integer.parseInt(tileNum);
        this.row = row;
        this.column = column;
    }

    /**
     *create new tile
     * @param tile -- existing tile
     */
    public Tile(Tile tile){
        this.value = tile.getValue();
        this.row = tile.getRow();
        this.column = tile.getColumn();
    }

    /**
     * returns the value of the tile
     * @return the value of the tile
     */
    public int getValue() {
        return value;
    }

    /**
     * returns the row the tile is on
     * @return the row the tile is on
     */
    public int getRow() {
        return row;
    }

    /**
     * change the row the tile is on to a given row
     * @param row -- the new row of the tile
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * returns the row the tile is on
     * @return the row the tile is on
     */
    public int getColumn() {
        return column;
    }

    /**
     * change the column the tile is on to a given column
     * @param column -- the new column of the tile
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * switch between two tiles
     * @param tile -- a tile object to switch the current one with
     */
    public void switchPlace(Tile tile){
        int tempRow = this.row, tempColumn = this.column;
        this.row = tile.getRow();
        this.column = tile.getColumn();
        tile.setRow(tempRow);
        tile.setColumn(tempColumn);
    }

    /**
     * move tile to new location by row and column
     * @param row -- new row location for tile
     * @param column -- new column location for tile
     */
    public void switchPlaceByPlace(int row, int column){
        this.row = row;
        this.column = column;
    }

    /**
     * returns the Manhetten Distance of the tile
     * @return the Manhetten Distance of the tile
     */
    public int getManhettenDistance() {
        return manhettenDistance[0];
    }

    /**
     * calculate the Manhetten Distance of the tile
     * @param columns -- the number of columns in the board
     */
    public void manhettenDistance(int columns){
        this.manhettenDistance[0] = this.value / columns;
        this.manhettenDistance[1] = (this.value % columns) - 1;
        if (this.manhettenDistance[1] == -1) {
            this.manhettenDistance[0]--;
            this.manhettenDistance[1] = columns - 1;
        }
        this.manhettenDistance[0] = this.manhettenDistance[0] - this.row;
        this.manhettenDistance[1] = this.manhettenDistance[1] - this.column;
        if (this.manhettenDistance[0] < 0)
            this.manhettenDistance[0] *= -1;
        if (this.manhettenDistance[1] < 0)
            this.manhettenDistance[1] *= -1;
        //oclides distance
        this.manhettenDistance[0] = (int)Math.round(Math.pow((Math.pow(this.manhettenDistance[0], 5) + Math.pow(this.manhettenDistance[1], 5)), 0.2));
    }

    /**
     * set the Manhetten Distance to a given value
     * @param manhettenDistance -- array that contain the Manhetten Distance, [0]-moves in x, [1]-moves in y
     */
    public void setManhettenDistance(int manhettenDistance){
        this.manhettenDistance[0] = manhettenDistance;
    }


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
}