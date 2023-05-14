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
    private int minkowskiDistance;

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
     * returns the Minkowski Distance of the tile
     * @return the Minkowski Distance of the tile
     */
    public int getMinkowskiDistance() {
        return this.minkowskiDistance;
    }

    /**
     * calculate the Manhetten Distance of the tile
     * @param columns -- the number of columns in the board
     */
    public void minkowskiDistance(int columns){
        int rowDistance = this.value / columns, colDistance = (this.value % columns) - 1;
        if (colDistance == -1) {
            rowDistance--;
            colDistance = columns - 1;
        }
        rowDistance = rowDistance - this.row;
        colDistance = colDistance - this.column;
        if (rowDistance < 0)
            rowDistance *= -1;
        if (colDistance < 0)
            colDistance *= -1;
        //minkowski distance
        this.minkowskiDistance = (int)Math.round(Math.pow((Math.pow(rowDistance, 5) + Math.pow(colDistance, 5)), 0.2));
        /*
        for (int i = 0 ; i < 5 ; i++){
            rowDistance *= rowDistance;
            colDistance *= colDistance;
        }
        this.minkowskiDistance = (int) (fifthRoot((double) (rowDistance + colDistance)) + 0.5);

         */
    }

    private static double fifthRoot(double number) {
        double precision = 0.00001; // the desired precision of the result
        double x = number / 5.0; // initial guess

        while (true) {
            double nextX = (1.0 / 5.0) * ((4.0 * x) + (number / (x * x * x * x))); // calculate the next guess
            double diff = nextX - x; // calculate the difference between the next guess and the current guess
            if (diff < 0) { // check if the difference is negative
                diff = -diff; // take the absolute value of the difference
            }
            if (diff < precision) { // check if the desired precision has been reached
                x = nextX;
                break;
            }
            x = nextX;
        }
        return x;
    }

    /**
     * set the Manhetten Distance to a given value
     * @param minkowskiDistance -- array that contain the Manhetten Distance, [0]-moves in x, [1]-moves in y
     */
    public void setMinkowskiDistance(int minkowskiDistance){
        this.minkowskiDistance = minkowskiDistance;
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