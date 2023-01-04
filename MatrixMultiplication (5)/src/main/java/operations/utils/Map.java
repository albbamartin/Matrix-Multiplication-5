package operations.utils;

import java.util.ArrayList;
import java.util.List;

public class Map {

    public List<double[][]> map(List<double[][]> mosaicListA, List<double[][]> mosaicListB, int nMosaicsPerRow) {
        int initialRowCounter = 0, finalRowCounter;
        List<double[][]> resultList = new ArrayList<>();
        for (int i = 0; i < mosaicListA.size(); i++) {
            double[][] mosaicA = mosaicListA.get(i);
            finalRowCounter = initialRowCounter + nMosaicsPerRow;
            for (int j = initialRowCounter; j < finalRowCounter; j++) {
                double[][] mosaicB = mosaicListB.get(j);
                resultList.add(multiply(mosaicA, mosaicB));
            }
            initialRowCounter += nMosaicsPerRow;
            if (initialRowCounter == mosaicListA.size()) initialRowCounter = 0;
        }
        return resultList;
    }

    public double[][] multiply(double[][] mosaicA, double[][] mosaicB) {
        double[][] result = new double[mosaicA.length][mosaicA[0].length];
        for (int i = 0; i < mosaicA.length; i++)
            for (int j = 0; j < mosaicB[0].length; j++)
                for (int k = 0; k < mosaicA[0].length; k++)
                    result[i][j] += mosaicA[i][k] * mosaicB[k][j];
        return result;
    }
}
