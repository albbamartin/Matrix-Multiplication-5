package operations.utils;

import java.util.ArrayList;
import java.util.List;

public class DivideIntoMosaics {

    public List<double[][]> divideIntoMosaics(double[][] matrix, int mosaicSize) {
        List<double[][]> mosaicList = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i += mosaicSize) {
            for (int j = 0; j < cols; j += mosaicSize) {
                double[][] mosaic = new double[mosaicSize][mosaicSize];
                for (int k = 0; k < mosaicSize; k++) {
                    for (int l = 0; l < mosaicSize; l++)
                        if (i + k < rows && j + l < cols) mosaic[k][l] = matrix[i + k][j + l];
                }
                mosaicList.add(mosaic);
            }
        }
        return mosaicList;
    }
}
