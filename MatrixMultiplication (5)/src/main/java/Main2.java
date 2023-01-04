import builders.DenseMatrixBuilder;
import matrixes.DenseMatrix;
import operations.DenseMatrixTiledMultiplicationTest;
import operations.utils.*;

import java.util.List;

public class Main2 {
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

        DivideIntoMosaicsTest divideIntoMosaics = new DivideIntoMosaicsTest();
        List<DenseMatrix> mosaicListA = divideIntoMosaics.divideIntoMosaics(newA, sizeMosaic);
        List<DenseMatrix> mosaicListB = divideIntoMosaics.divideIntoMosaics(newA, sizeMosaic);

        int nMosaicsPerRow = (int) Math.ceil((double) A.length/sizeMosaic);

        MapTest map = new MapTest();
        List<DenseMatrix> resultList = map.map(mosaicListA, mosaicListB, nMosaicsPerRow);

        ReduceTest reduce = new ReduceTest(nMosaicsPerRow);
        DenseMatrix denseMatrix = reduce.reduce(resultList);

        System.out.println("Resultado de reduce");
        denseMatrix.printear();
        System.out.println("----");

        DenseMatrixTiledMultiplicationTest denseMatrixTiledMultiplicationTest = new DenseMatrixTiledMultiplicationTest(sizeMosaic);
        DenseMatrix denseMatrix2 = denseMatrixTiledMultiplicationTest.multiply(newA,newA);
        denseMatrix2.printear();
    }
}
