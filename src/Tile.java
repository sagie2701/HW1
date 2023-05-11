public class Tile {

    private final int value;
    private int row;
    private int column;
    private int[] manhettenDistance = new int[2];


    public Tile(String tileNum, int row, int column){
        if (tileNum.equals("_"))
            this.value = 0;
        else
            this.value = Integer.parseInt(tileNum);
        this.row = row;
        this.column = column;
    }

    public Tile(Tile tile){
        this.value = tile.getValue();
        this.row = tile.getRow();
        this.column = tile.getColumn();
    }

    public int getValue() {
        return value;
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

    public void switchPlace(Tile tile){
        int tempRow = this.row, tempColumn = this.column;
        this.row = tile.getRow();
        this.column = tile.getColumn();
        tile.setRow(tempRow);
        tile.setColumn(tempColumn);
    }

    public void switchPlaceByPlace(int row, int column){
        int tempRow = this.row, tempColumn = this.column;
        this.row = row;
        this.column = column;
    }

    public int[] getManhettenDistance() {
        return manhettenDistance;
    }

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
    }

    public void setManhettenDistance(int[] manhettenDistance){
        this.manhettenDistance[0] = manhettenDistance[0];
        this.manhettenDistance[1] = manhettenDistance[1];
    }

    public int getManhettenDistanceSum(){
        return this.manhettenDistance[0] + this.manhettenDistance[1];
    }

    public boolean isInTarget(){
        return this.manhettenDistance[0] == 0 && this.manhettenDistance[1] == 0;
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