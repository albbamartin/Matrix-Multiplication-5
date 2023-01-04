package operations.utils;

import java.util.ArrayList;
import java.util.List;

public class Reduce {

    int nMosaicsPerRow;
    public Reduce(int nMosaicsPerRow){
        this.nMosaicsPerRow = nMosaicsPerRow;
    }

    public double[][] reduce(List<double[][]> mosaicList) {
        double[][] result = placeTiles(addingUpTiles(mosaicList));
        EliminateZeros eliminateZeros = new EliminateZeros();
        result = eliminateZeros.eliminateRowsOfZeros(result);
        return eliminateZeros.eliminateColumnsOfZeros(result);
    }

    public List<double[][]> addingUpTiles(List<double[][]> mosaicList) {

        List<double[][]> resultList = new ArrayList<>();

        for (int nMatrixes = 0; nMatrixes < mosaicList.size()/nMosaicsPerRow; nMatrixes++) {
            double[][] matriz = new double[mosaicList.get(0).length][mosaicList.get(0).length];
            resultList.add(matriz);
        }

        int matrixIndex = 0;
        for (int a=0; a<mosaicList.size()/nMosaicsPerRow; a+=nMosaicsPerRow)
            for (int b=0; b<nMosaicsPerRow; b++)
                for (int i=0; i<nMosaicsPerRow; i++) {
                    for (int row=0; row < resultList.get(a).length; row++)
                        for (int col=0; col < resultList.get(a)[0].length; col++)
                            resultList.get(a+i)[row][col] += mosaicList.get(matrixIndex)[row][col];
                    matrixIndex++;
                }

        System.out.println(resultList.size());

        return resultList;
    }

    public double[][] placeTiles(List<double[][]> resultList){
        int sizeMosaic = resultList.get(0).length;
        int resultSize = nMosaicsPerRow * sizeMosaic;
        double [][] result = new double[resultSize][resultSize];
        int rowCounter=0, colCounter=0;
        for (int matrixIndex = 0; matrixIndex < resultList.size(); matrixIndex++) {
            for (int row = 0; row < sizeMosaic; row++)
                for (int col = 0; col < sizeMosaic; col++)
                    result[row + rowCounter][col + colCounter] = resultList.get(matrixIndex)[row][col];
            if ((matrixIndex+1)%nMosaicsPerRow==0) rowCounter+=sizeMosaic;
            colCounter+=sizeMosaic;
            if (colCounter >= result.length) colCounter=0;
        }
        return result;
    }
}
