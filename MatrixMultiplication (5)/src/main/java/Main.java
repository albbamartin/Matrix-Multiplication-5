import builders.DenseMatrixBuilder;
import matrixes.DenseMatrix;
import operations.DenseMatrixTiledMultiplication;

public class Main {
    public static void main(String[] args) {

        int sizeMosaic = 2;

        double[][] A = {{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};

        DenseMatrixBuilder builder = new DenseMatrixBuilder(A.length, A.length);

        for (int row=0; row < A.length; row++)
            for (int col=0; col < A.length; col++)
                builder.set(row, col, A[row][col]);

        DenseMatrix newA = builder.toMatrix();

        DenseMatrixTiledMultiplication denseMatrixTiledMultiplication = new DenseMatrixTiledMultiplication(sizeMosaic);
        DenseMatrix denseMatrix = denseMatrixTiledMultiplication.multiply(newA,newA);
        denseMatrix.printear();
    }
}
