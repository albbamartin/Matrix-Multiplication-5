package operations.utils;

import builders.DenseMatrixBuilder;
import matrixes.DenseMatrix;

import java.util.ArrayList;
import java.util.List;

public class ReduceTest {
    int nMosaicsPerRow;
    public ReduceTest(int nMosaicsPerRow){
        this.nMosaicsPerRow = nMosaicsPerRow;
    }

    public DenseMatrix reduce(List<DenseMatrix> mosaicList) {
        DenseMatrix result = placeTiles(addingUpTiles(mosaicList));
        EliminateZerosTest eliminateZeros = new EliminateZerosTest();
        result = eliminateZeros.eliminateRowsOfZeros(result);
        return eliminateZeros.eliminateColumnsOfZeros(result);
    }

    public List<DenseMatrix> addingUpTiles(List<DenseMatrix> mosaicList) {
        List<DenseMatrixBuilder> resultList = new ArrayList<>();
        for (int nMatrixes = 0; nMatrixes < mosaicList.size() / nMosaicsPerRow; nMatrixes++) {
            DenseMatrixBuilder builder = new DenseMatrixBuilder(mosaicList.get(0).getnRows(), mosaicList.get(0).getnColumns());
            resultList.add(builder);
        }
        List<DenseMatrix> finalResult = new ArrayList<>();
        int matrixIndex = 0;
        for (int a = 0; a < mosaicList.size() / nMosaicsPerRow; a += nMosaicsPerRow)
            for (int b = 0; b < nMosaicsPerRow; b++)
                for (int i = 0; i < nMosaicsPerRow; i++) {
                    for (int row = 0; row < mosaicList.get(0).getnRows(); row++)
                        for (int col = 0; col < mosaicList.get(0).getnColumns(); col++)
                            resultList.get(a + i).add(row, col, mosaicList.get(matrixIndex).get(row, col));
                    matrixIndex++;
                }
        for (int m=0; m<resultList.size(); m++)
            finalResult.add(resultList.get(m).toMatrix());

        return finalResult;
    }

    public DenseMatrix placeTiles(List<DenseMatrix> resultList){
        int sizeMosaic = resultList.get(0).size();
        int resultSize = nMosaicsPerRow * sizeMosaic;
        DenseMatrixBuilder result = new DenseMatrixBuilder(resultSize,resultSize);
        int rowCounter=0, colCounter=0;
        for (int matrixIndex = 0; matrixIndex < resultList.size(); matrixIndex++) {
            for (int row = 0; row < sizeMosaic; row++)
                for (int col = 0; col < sizeMosaic; col++)
                    result.set(row + rowCounter,col + colCounter,resultList.get(matrixIndex).get(row,col));
            if ((matrixIndex+1)%nMosaicsPerRow==0) rowCounter+=sizeMosaic;
            colCounter+=sizeMosaic;
            if (colCounter >= resultSize) colCounter=0;
        }
        return result.toMatrix();
    }
}
