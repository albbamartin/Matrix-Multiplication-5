package benchmarking;


import builders.DenseMatrixBuilder;
import matrixes.DenseMatrix;
import operations.DenseMatrixStandardMultiplication;
import operations.DenseMatrixTiledMultiplicationTest;
import operations.MatrixMultiplication;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@BenchmarkMode(Mode.AverageTime)
@Fork(value = 2)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 3, time = 2)
public class DenseBenchMark {
    private static final int SIZE = 1024;

    @Benchmark
    public void standardMatrixMultiplication() {
        executeWith(new DenseMatrixStandardMultiplication());
    }

    @Benchmark
    public void tiledMatrixMultiplicationTest() {
        executeWith(new DenseMatrixTiledMultiplicationTest(3));
    }


    private void executeWith(MatrixMultiplication matrixMultiplication) {
        matrixMultiplication.multiply(createRandomMatrix(SIZE), createRandomMatrix(SIZE));
    }

    private DenseMatrix createRandomMatrix(int size) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(size, size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.set(i, j, random.nextDouble());
            }
        }
        return builder.toMatrix();
    }
}
