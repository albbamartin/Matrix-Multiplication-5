package operations.utils;

import builders.DenseMatrixBuilder;
import matrixes.DenseMatrix;

import java.util.ArrayList;
import java.util.List;

public class Partitioner {
    public List<DenseMatrix> divide(DenseMatrix matrix, int mosaicSize) {
        List<DenseMatrix> mosaicList = new ArrayList<>();
        int rows = matrix.getnRows();
        int cols = matrix.getnColumns();
        for (int i = 0; i < rows; i += mosaicSize) {
            for (int j = 0; j < cols; j += mosaicSize) {
                DenseMatrixBuilder mosaic = new DenseMatrixBuilder(mosaicSize,mosaicSize);
                for (int k = 0; k < mosaicSize; k++) {
                    for (int l = 0; l < mosaicSize; l++)
                        if (i + k < rows && j + l < cols) mosaic.set(k,l,matrix.get(i + k,j + l));
                }
                mosaicList.add(mosaic.toMatrix());
            }
        }
        return mosaicList;
    }
}
