package rubiesCube;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RubiesCubeTest {

    @Test
    public void simpleTest() {

        RubiesCubeImpl cubeTest = new RubiesCubeImpl(3);
        RubiesCubeImpl cubeResult = new RubiesCubeImpl(3);
        assertEquals(cubeResult, cubeTest);
    }

    @Test
    public void cubeRotate() {
        RubiesCubeImpl cubeTest = new RubiesCubeImpl(3);
        RubiesCubeImpl cubeResult = new RubiesCubeImpl(3);

        int degrees = 360;
        cubeTest.rotateCubeOnXAxis(degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.rotateCubeOnYAxis(degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.rotateCubeOnZAxis(degrees);
        assertEquals(cubeResult, cubeTest);

        degrees = -degrees;
        cubeTest.rotateCubeOnXAxis(degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.rotateCubeOnYAxis(degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.rotateCubeOnZAxis(degrees);
        assertEquals(cubeResult, cubeTest);
    }


    @Test
    public void columnMoveTest() {
        RubiesCubeImpl cubeTest = new RubiesCubeImpl(3);
        RubiesCubeImpl cubeResult = new RubiesCubeImpl(3);

        int degrees = 360;
        cubeTest.moveColumn(FaceType.FRONT, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.FRONT, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.FRONT, 2, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.moveColumn(FaceType.LEFT, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.LEFT, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.LEFT, 2, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.moveColumn(FaceType.BOTTOM, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.BOTTOM, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.BOTTOM, 2, degrees);
        assertEquals(cubeResult, cubeTest);


        degrees = -360;
        cubeTest.moveColumn(FaceType.BACK, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.BACK, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.BACK, 2, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.moveColumn(FaceType.RIGHT, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.RIGHT, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.RIGHT, 2, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.moveColumn(FaceType.TOP, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.TOP, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveColumn(FaceType.TOP, 2, degrees);
        assertEquals(cubeResult, cubeTest);
    }

    @Test
    public void rowMoveTest() {
        RubiesCubeImpl cubeTest = new RubiesCubeImpl(3);
        RubiesCubeImpl cubeResult = new RubiesCubeImpl(3);

        int degrees = 360;
        cubeTest.moveRow(FaceType.FRONT, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.FRONT, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.FRONT, 2, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.moveRow(FaceType.LEFT, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.LEFT, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.LEFT, 2, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.moveRow(FaceType.BOTTOM, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.BOTTOM, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.BOTTOM, 2, degrees);
        assertEquals(cubeResult, cubeTest);


        degrees = -360;
        cubeTest.moveRow(FaceType.BACK, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.BACK, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.BACK, 2, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.moveRow(FaceType.RIGHT, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.RIGHT, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.RIGHT, 2, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.moveRow(FaceType.TOP, 0, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.TOP, 1, degrees);
        assertEquals(cubeResult, cubeTest);
        cubeTest.moveRow(FaceType.TOP, 2, degrees);
        assertEquals(cubeResult, cubeTest);

//______________________________________

        cubeTest.moveRow(FaceType.TOP, 2, 90);
        cubeResult.moveRow(FaceType.BOTTOM, 0, -90);
        assertEquals(cubeResult, cubeTest);


        cubeTest.moveRow(FaceType.TOP, 2, 90);
        cubeResult.moveRow(FaceType.TOP, 2, -270);
        assertEquals(cubeResult, cubeTest);
    }
}
