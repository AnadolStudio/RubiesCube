import org.junit.Test;
import rubiescube.imlementation.RubiesCubeImpl;
import rubiescube.imlementation.SpeedRubiesCube3x3x3;
import rubiescubesolution.RubiesCube3x3x3Solution;
import rubiescubesolution.RubiesCubeShaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RubiesCubeSolutionTest {

    @Test
    public void solution3x3x3() {
        SpeedRubiesCube3x3x3 defaultCube = new SpeedRubiesCube3x3x3();
        RubiesCube3x3x3Solution solution = new RubiesCube3x3x3Solution();

        for (int i = 0; i < 2_000; i++) {
            SpeedRubiesCube3x3x3 cubeTest = new SpeedRubiesCube3x3x3();
            RubiesCubeShaker.shake(cubeTest);
            assertNotEquals(defaultCube, cubeTest);

            solution.run(cubeTest);
            assertEquals(defaultCube, cubeTest);
        }

        defaultCube = new SpeedRubiesCube3x3x3();
        SpeedRubiesCube3x3x3 cubeTest = new SpeedRubiesCube3x3x3();
        solution.run(cubeTest);
        assertEquals(defaultCube, cubeTest);
    }

    @Test
    public void shakeTest() {
        for (int i = 0; i < 2_000; i++) {
            RubiesCubeImpl defaultCube = new RubiesCubeImpl(3);
            RubiesCubeImpl cubeTest = new RubiesCubeImpl(3);
            assertEquals(defaultCube, cubeTest);

            RubiesCubeShaker.shake(cubeTest);

            assertNotEquals(defaultCube, cubeTest);

            defaultCube = new RubiesCubeImpl(4);
            cubeTest = new RubiesCubeImpl(4);
            RubiesCubeShaker.shake(cubeTest);

            assertNotEquals(defaultCube, cubeTest);

            defaultCube = new RubiesCubeImpl(2);
            cubeTest = new RubiesCubeImpl(2);
            RubiesCubeShaker.shake(cubeTest);

            assertNotEquals(defaultCube, cubeTest);
        }
    }
}
