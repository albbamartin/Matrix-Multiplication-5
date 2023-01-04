import operations.DenseMatrixTiledMultiplication;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int sizeMosaic = 2;
        DenseMatrixTiledMultiplication denseMatrixTiledMultiplication = new DenseMatrixTiledMultiplication(sizeMosaic);
        double[][] A = {{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};

        double[][] finalResult = denseMatrixTiledMultiplication.multiply(A, A);

        for (double[] row : finalResult)
            System.out.println(Arrays.toString(row));

        double[][] B = {{1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29, 30},
                {31, 32, 33, 34, 35, 36}};

        finalResult = denseMatrixTiledMultiplication.multiply(B, B);

        for (double[] row : finalResult)
            System.out.println(Arrays.toString(row));
    }
}
