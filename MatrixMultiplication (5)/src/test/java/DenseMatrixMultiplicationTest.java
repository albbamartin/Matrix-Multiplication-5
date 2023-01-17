import builders.DenseMatrixBuilder;
import matrixes.DenseMatrix;
import operations.DenseMatrixTiledMultiplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

@RunWith(Parameterized.class)
public class DenseMatrixMultiplicationTest {

    public int size;

    public DenseMatrixMultiplicationTest(int size) {
        this.size = size;
    }

    @Test
    public void multiply_two_random_dense_matrix_with_tiles() {
        DenseMatrix denseMatrixA = createRandomMatrix(this.size);
        DenseMatrix denseMatrixB = createRandomMatrix(this.size);
        DenseMatrixTiledMultiplication denseTiledMatrixMultiplication = new DenseMatrixTiledMultiplication(3);
        DenseMatrix c = denseTiledMatrixMultiplication.multiply(denseMatrixA,denseMatrixB);
        Vector vector = new Vector(this.size);
        assertThat(vector.multiply(c)).isEqualTo(vector.multiply(denseMatrixA).multiply(denseMatrixA));
    }

    private DenseMatrix createRandomMatrix(int size) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(this.size, this.size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                builder.set(i, j, random.nextDouble());
            }
        }
        return builder.toMatrix();
    }

    @Parameterized.Parameters
    public static List<Integer> parameters(){
        return Arrays.asList(5,10,100);
    }
}