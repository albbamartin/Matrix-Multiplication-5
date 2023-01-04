package operations;

import operations.utils.DivideIntoMosaics;
import operations.utils.Map;
import operations.utils.Reduce;

import java.util.List;

public class DenseMatrixTiledMultiplication {

    int sizeMosaic;

    public DenseMatrixTiledMultiplication(int sizeMosaic) {
        this.sizeMosaic=sizeMosaic;
    }

    public double[][] multiply(double[][] A, double[][] B) {

        DivideIntoMosaics divideIntoMosaics = new DivideIntoMosaics();
        List<double[][]> mosaicListA = divideIntoMosaics.divideIntoMosaics(A, this.sizeMosaic);
        List<double[][]> mosaicListB = divideIntoMosaics.divideIntoMosaics(B, this.sizeMosaic);

        int nMosaicsPerRow = (int) Math.ceil((double) A.length/sizeMosaic);

        Map map = new Map();
        List<double[][]> resultList = map.map(mosaicListA, mosaicListB, nMosaicsPerRow);

        Reduce reduce = new Reduce(nMosaicsPerRow);
        return reduce.reduce(resultList);
    }
}
