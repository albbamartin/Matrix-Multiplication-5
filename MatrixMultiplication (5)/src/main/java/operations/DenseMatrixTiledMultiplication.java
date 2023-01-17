package operations;

import matrixes.DenseMatrix;
import operations.utils.*;

import java.util.List;

public class DenseMatrixTiledMultiplication implements MatrixMultiplication<DenseMatrix, DenseMatrix, DenseMatrix>{
    int sizeMosaic;

    public DenseMatrixTiledMultiplication(int sizeMosaic) {
        this.sizeMosaic=sizeMosaic;
    }

    public DenseMatrix multiply(DenseMatrix A, DenseMatrix B) {

        Partitioner divideIntoMosaics = new Partitioner();
        List<DenseMatrix> mosaicListA = divideIntoMosaics.divide(A, this.sizeMosaic);
        List<DenseMatrix> mosaicListB = divideIntoMosaics.divide(B, this.sizeMosaic);

        int nMosaicsPerRow = (int) Math.ceil((double) A.size()/sizeMosaic);

        Mapper map = new Mapper();
        List<DenseMatrix> resultList = map.map(mosaicListA, mosaicListB, nMosaicsPerRow);

        Reducer reduce = new Reducer(nMosaicsPerRow);
        return reduce.reduce(resultList);
    }
}
