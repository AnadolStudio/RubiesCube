import org.junit.Test;
import rubiescube.enumeration.FaceType;
import rubiescube.imlementation.RubiesCubeImpl;
import rubiescube.imlementation.SpeedRubiesCube3x3x3;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        cubeTest = new RubiesCubeImpl(3);
        cubeTest.rotateCubeOnZAxis(90);
        assertTrue(cubeTest.getCubePiece(2, 1, 1).hasAllThisColors(Color.WHITE));

        cubeTest = new RubiesCubeImpl(3);
        cubeTest.rotateCubeOnXAxis(90);
        assertTrue(cubeTest.getCubePiece(1, 1, 0).hasAllThisColors(Color.YELLOW));

        cubeTest = new RubiesCubeImpl(3);
        cubeTest.rotateCubeOnYAxis(90);
        assertTrue(cubeTest.getCubePiece(1, 1, 0).hasAllThisColors(Color.ORANGE));
        SpeedRubiesCube3x3x3 speedCubeTest = new SpeedRubiesCube3x3x3();
        speedCubeTest.s();
        assertTrue(speedCubeTest.getCubePiece(2, 1, 1).hasAllThisColors(Color.WHITE));

        speedCubeTest = new SpeedRubiesCube3x3x3();
        speedCubeTest.e();
        assertTrue(speedCubeTest.getCubePiece(1, 1, 0).hasAllThisColors(Color.ORANGE));

        speedCubeTest = new SpeedRubiesCube3x3x3();
        speedCubeTest.moveColumn(FaceType.LEFT, 2, 90);
        assertTrue(speedCubeTest.getCubePiece(2, 1, 0).hasColor(Color.WHITE));

    }

    @Test
    public void columnMoveTest() {
        RubiesCubeImpl cubeTest = new RubiesCubeImpl(3);
        RubiesCubeImpl cubeResult = new RubiesCubeImpl(3);

        //FRONT
        cubeTest.moveColumn(FaceType.FRONT, 2, 90);
        assertTrue(cubeTest.getCubePiece(2, 0, 2).hasColor(Color.GREEN));
        cubeTest.moveColumn(FaceType.FRONT, 1, 90);
        assertTrue(cubeTest.getCubePiece(1, 0, 2).hasColor(Color.GREEN));
        cubeTest.moveColumn(FaceType.FRONT, 0, 90);
        assertTrue(cubeTest.getCubePiece(0, 0, 2).hasColor(Color.GREEN));

        //reboot
        cubeTest.rotateCubeOnXAxis(-90);
        assertEquals(cubeResult, cubeTest);

        //LEFT
        cubeTest.moveColumn(FaceType.LEFT, 0, 90);
        assertTrue(cubeTest.getCubePiece(2, 0, 2).hasAllThisColors(Color.ORANGE, Color.WHITE, Color.BLUE));
        cubeTest.moveColumn(FaceType.LEFT, 1, 90);
        assertTrue(cubeTest.getCubePiece(2, 0, 1).hasAllThisColors(Color.ORANGE, Color.WHITE));
        cubeTest.moveColumn(FaceType.LEFT, 2, 90);
        assertTrue(cubeTest.getCubePiece(2, 0, 0).hasAllThisColors(Color.ORANGE, Color.WHITE, Color.GREEN));

        //reboot
        cubeTest.rotateCubeOnZAxis(-90);
        assertEquals(cubeResult, cubeTest);

        //TOP
        cubeTest.moveColumn(FaceType.TOP, 2, 90);
        assertTrue(cubeTest.getCubePiece(2, 0, 2).hasColor(Color.GREEN));
        cubeTest.moveColumn(FaceType.TOP, 1, 90);
        assertTrue(cubeTest.getCubePiece(1, 0, 2).hasColor(Color.GREEN));
        cubeTest.moveColumn(FaceType.TOP, 0, 90);
        assertTrue(cubeTest.getCubePiece(0, 0, 2).hasColor(Color.GREEN));

        //reboot
        cubeTest.rotateCubeOnXAxis(-90);
        assertEquals(cubeResult, cubeTest);

        //Right
        cubeTest.moveColumn(FaceType.RIGHT, 0, 90);
        assertTrue(cubeTest.getCubePiece(0, 0, 0).hasAllThisColors(Color.RED, Color.WHITE, Color.GREEN));
        cubeTest.moveColumn(FaceType.RIGHT, 1, 90);
        assertTrue(cubeTest.getCubePiece(0, 0, 1).hasAllThisColors(Color.RED, Color.WHITE));
        cubeTest.moveColumn(FaceType.RIGHT, 2, 90);
        assertTrue(cubeTest.getCubePiece(0, 0, 2).hasAllThisColors(Color.RED, Color.WHITE, Color.BLUE));

        //reboot
        cubeTest.rotateCubeOnZAxis(90);
        assertEquals(cubeResult, cubeTest);

        //Back
        cubeTest.moveColumn(FaceType.BACK, 0, 90);
        assertTrue(cubeTest.getCubePiece(2, 0, 2).hasAllThisColors(Color.BLUE, Color.YELLOW, Color.RED));
        cubeTest.moveColumn(FaceType.BACK, 1, 90);
        assertTrue(cubeTest.getCubePiece(1, 0, 2).hasAllThisColors(Color.BLUE, Color.YELLOW));
        cubeTest.moveColumn(FaceType.BACK, 2, 90);
        assertTrue(cubeTest.getCubePiece(0, 0, 2).hasAllThisColors(Color.BLUE, Color.YELLOW, Color.ORANGE));

        //reboot
        cubeTest.rotateCubeOnXAxis(90);
        assertEquals(cubeResult, cubeTest);

        //Bottom
        cubeTest.moveColumn(FaceType.BOTTOM, 2, -90);
        assertTrue(cubeTest.getCubePiece(2, 0, 2).hasAllThisColors(Color.BLUE, Color.YELLOW, Color.RED));
        cubeTest.moveColumn(FaceType.BOTTOM, 1, -90);
        assertTrue(cubeTest.getCubePiece(1, 0, 2).hasAllThisColors(Color.BLUE, Color.YELLOW));
        cubeTest.moveColumn(FaceType.BOTTOM, 0, -90);
        assertTrue(cubeTest.getCubePiece(0, 0, 2).hasAllThisColors(Color.BLUE, Color.YELLOW, Color.ORANGE));

        //reboot
        cubeTest.rotateCubeOnXAxis(90);
        assertEquals(cubeResult, cubeTest);
    }

    @Test
    public void rotateFaceTest() {
        RubiesCubeImpl cubeTest = new RubiesCubeImpl(3);
        RubiesCubeImpl cubeResult = new RubiesCubeImpl(3);

        int degrees = 360;
        cubeTest.rotateFace(FaceType.FRONT, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.rotateFace(FaceType.LEFT, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.rotateFace(FaceType.BOTTOM, degrees);
        assertEquals(cubeResult, cubeTest);

        degrees = -360;
        cubeTest.rotateFace(FaceType.BACK, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.rotateFace(FaceType.RIGHT, degrees);
        assertEquals(cubeResult, cubeTest);

        cubeTest.rotateFace(FaceType.TOP, degrees);
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
