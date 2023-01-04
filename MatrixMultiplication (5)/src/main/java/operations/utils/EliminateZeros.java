package operations.utils;

public class EliminateZeros {

    public double[][] eliminateRowsOfZeros(double[][] matrix) {
        int lastRow = matrix.length - 1;
        while (lastRow >= 0) {
            boolean isZero = true;
            for (int j = 0; j < matrix[lastRow].length; j++) {
                if (matrix[lastRow][j] != 0) {
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
        double[][] newMatrix = new double[lastRow + 1][];
        System.arraycopy(matrix, 0, newMatrix, 0, lastRow + 1);
        return newMatrix;
    }

    public double[][] eliminateColumnsOfZeros(double[][] matrix) {
        int lastColumn = matrix[0].length - 1;
        while (lastColumn >= 0) {
            boolean isZero = true;
            for (double[] doubles : matrix) {
                if (doubles[lastColumn] != 0) {
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
        double[][] newMatrix = new double[matrix.length][lastColumn + 1];
        for (int i = 0; i < matrix.length; i++)
            if (lastColumn + 1 >= 0) System.arraycopy(matrix[i], 0, newMatrix[i], 0, lastColumn + 1);
        return newMatrix;
    }
}
