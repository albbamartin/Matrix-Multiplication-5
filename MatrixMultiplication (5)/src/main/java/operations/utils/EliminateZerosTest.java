package operations.utils;

import builders.DenseMatrixBuilder;
import matrixes.DenseMatrix;

public class EliminateZerosTest {
    public DenseMatrix eliminateRowsOfZeros(DenseMatrix matrix) {
        int lastRow = matrix.size() - 1;
        while (lastRow >= 0) {
            boolean isZero = true;
            for (int j = 0; j < matrix.getnRows(); j++) {
                if (matrix.get(lastRow,j) != 0) {
                    isZero = false;
                    break;
                }
            }
            if (isZero) {
                lastRow--;
            } else {
                break;
            }
        }

        DenseMatrixBuilder newMatrix = new DenseMatrixBuilder(lastRow + 1, matrix.getnColumns());
        for (int row=0; row<lastRow + 1; row++)
            for (int col=0; col< matrix.getnColumns(); col++)
                newMatrix.set(row, col, matrix.get(row,col));

        return newMatrix.toMatrix();
    }

    public DenseMatrix eliminateColumnsOfZeros(DenseMatrix matrix) {
        int lastColumn = matrix.size() - 1;
        while (lastColumn >= 0) {
            boolean isZero = true;
            for (int row=0; row<matrix.getnRows(); row++) {
                if (matrix.get(row, lastColumn) != 0) {
                    isZero = false;
                    break;
                }
            }
            if (isZero) {
                lastColumn--;
            } else {
                break;
            }
        }

        DenseMatrixBuilder newMatrix = new DenseMatrixBuilder(matrix.getnRows(), lastColumn + 1);
        for (int row = 0; row < matrix.size(); row++)
            for (int col=0; col< lastColumn + 1; col++)
                if (lastColumn + 1 >= 0)
                    newMatrix.set(row, col, matrix.get(row,col));

        return newMatrix.toMatrix();
    }
}
