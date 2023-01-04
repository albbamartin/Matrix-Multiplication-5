package operations;

import matrixes.Matrix;

public interface MatrixMultiplication <matrix1 extends Matrix, matrix2 extends Matrix, matrix3 extends  Matrix>{
    matrix1 multiply(matrix2 a, matrix3 b);
}