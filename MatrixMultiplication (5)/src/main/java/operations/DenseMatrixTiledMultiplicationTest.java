package operations;

import matrixes.DenseMatrix;
import operations.utils.*;

import java.util.List;

public class DenseMatrixTiledMultiplicationTest implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix>{
    int sizeMosaic;

    public DenseMatrixTiledMultiplicationTest(int sizeMosaic) {
        this.sizeMosaic=sizeMosaic;
    }

    public DenseMatrix multiply(DenseMatrix A, DenseMatrix B) {

        DivideIntoMosaicsTest divideIntoMosaics = new DivideIntoMosaicsTest();
        List<DenseMatrix> mosaicListA = divideIntoMosaics.divideIntoMosaics(A, this.sizeMosaic);
        List<DenseMatrix> mosaicListB = divideIntoMosaics.divideIntoMosaics(B, this.sizeMosaic);

        int nMosaicsPerRow = (int) Math.ceil((double) A.size()/sizeMosaic);

        MapTest map = new MapTest();
        List<DenseMatrix> resultList = map.map(mosaicListA, mosaicListB, nMosaicsPerRow);

        ReduceTest reduce = new ReduceTest(nMosaicsPerRow);
        return reduce.reduce(resultList);
    }
}
