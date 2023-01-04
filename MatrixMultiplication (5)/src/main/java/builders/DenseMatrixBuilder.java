package builders;
import matrixes.DenseMatrix;

public class DenseMatrixBuilder implements Builder {

    private final double [][] matrix;

    private int nRows;
    private int nColumns;

    public DenseMatrixBuilder(int nRows, int nColumns) {
        this.matrix = emptyMatrixGenerator(nRows, nColumns);
        this.nRows = nRows;
        this.nColumns = nColumns;
    }

    double[][] emptyMatrixGenerator(int nRows, int nColumns) {
        double[][] matrix = new double[nRows][nColumns];
        for (int row = 0; row < nRows; row++)
            for (int col = 0; col < nColumns; col++)
                matrix[row][col] = 0;
        return matrix;
    }

    public void set(int row, int col, double value) {
        this.matrix[row][col] = value;
    }

    public void add(int row, int col, double value) {
        this.matrix[row][col] += value;
    }


    @Override
    public DenseMatrix toMatrix() {
        return new DenseMatrix(this.matrix)
                .setnRows(nRows)
                .setnColumns(nColumns);
    }
}
