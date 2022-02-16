import org.junit.Test;
import rubiesCube.SpeedRubiesCube3x3x3;
import rubiesCubeSolution.RubiesCubeShaker;

public class RubiesCubeSolutionTest {

    @Test
    public void solution3x3x3() {
        SpeedRubiesCube3x3x3 cubeResult = new SpeedRubiesCube3x3x3();
        SpeedRubiesCube3x3x3 cubeTest = new SpeedRubiesCube3x3x3();
        RubiesCubeShaker.shake(cubeTest);

    }
}
