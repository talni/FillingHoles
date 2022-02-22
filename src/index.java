public class index {
       private int row;
       private int col;
       private float value;

        public index(int row, int col, float value){
            this.row=row;
            this.col=col;
            this.value=value;
        }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
