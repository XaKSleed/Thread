import java.util.Random;

public class UsualMatrix {

    private int rows;
    private int columns;
    private int [][] matrix;

    public UsualMatrix(int newRows, int newColumns){

        rows = newRows;
        columns = newColumns;
        matrix = new int [rows][columns];
    }
    public UsualMatrix MatrixSum(UsualMatrix n){
        UsualMatrix sumtr = new UsualMatrix(n.rows, n.columns);
        int res = 0;
        for(int i=0; i<n.getRows(); i++){
            for(int j=0; j<n.getColumns(); j++){
                res = this.getElement(i, j) + n.getElement(i,j);
                sumtr.setElement(i,j,res);
            }
        }
        return(sumtr);
    }

    public UsualMatrix MatrixProduct(UsualMatrix n){
        UsualMatrix multmtr = new UsualMatrix(this.getRows(), n.getColumns());
        int counter = 0;
        for(int i = 0; i<this.getRows(); i++){
            for(int j = 0; j<n.getColumns(); j++){
                for(int k = 0; k<this.getColumns(); k++){
                    counter = counter+this.getElement(i, k)*n.getElement(k, j);
                }
                multmtr.setElement(i, j, counter);
                counter = 0;
            }
        }
        return(multmtr);

    }

    public UsualMatrix randMatrix( int seed) {
        Random randomizer = new Random(seed);
        for(int i=0; i < this.getRows(); i++) {
            for(int j=0; j < this.getColumns(); j++) {
                this.matrix[i][j] = 1 + randomizer.nextInt(5-1);
            }
        }
        return null;
    }


    public void setElement(int row, int column, int value){
        matrix[row][column] = value;
    }
    public int getElement(int row, int column){
        return(matrix[row][column]);
    }
    public int getRows(){
        return(rows);
    }
    public int getColumns(){
        return(columns);
    }


    public String toString(){
        StringBuilder buffer = new StringBuilder();
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.columns; j++){
                buffer.append(matrix[i][j]).append(" ");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

}

