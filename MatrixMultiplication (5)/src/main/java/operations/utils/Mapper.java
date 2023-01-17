package operations.utils;

import builders.DenseMatrixBuilder;
import matrixes.DenseMatrix;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public List<DenseMatrix> map(List<DenseMatrix> mosaicListA, List<DenseMatrix> mosaicListB, int nMosaicsPerRow) {
        int initialRowCounter = 0, finalRowCounter;
        List<DenseMatrix> resultList = new ArrayList<>();
        for (int i = 0; i < mosaicListA.size(); i++) {
            DenseMatrix mosaicA = mosaicListA.get(i);
            finalRowCounter = initialRowCounter + nMosaicsPerRow;
            for (int j = initialRowCounter; j < finalRowCounter; j++) {
                DenseMatrix mosaicB = mosaicListB.get(j);
                resultList.add(multiply(mosaicA, mosaicB));
            }
            initialRowCounter += nMosaicsPerRow;
            if (initialRowCounter == mosaicListA.size()) initialRowCounter = 0;
        }
        return resultList;
    }

    public DenseMatrix multiply(DenseMatrix mosaicA, DenseMatrix mosaicB) {
        DenseMatrixBuilder result = new DenseMatrixBuilder(mosaicA.getnRows(),mosaicA.getnColumns());
        for (int i = 0; i < mosaicA.getnRows(); i++)
            for (int j = 0; j < mosaicB.getnColumns(); j++) {
                double sum = 0;
                for (int k = 0; k < mosaicA.getnColumns(); k++)
                    sum += mosaicA.get(i,k) * mosaicB.get(k,j);
                result.set(i,j,sum);
            }
        return result.toMatrix();
    }
}
