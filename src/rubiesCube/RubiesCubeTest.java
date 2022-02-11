package rubiesCube;

import org.junit.Test;
import rubiesCube.AbstractRubiesCube.Face;

import java.util.Arrays;

import static helpers.Colors.*;
import static org.junit.Assert.*;

public class RubiesCubeTest {

    @Test
    public void matrixRotate() {
        int[][] matrixTest = createMatrixTest();
        int[][] matrixResult = new int[][]{
                {3, 6, 9},
                {2, 5, 8},
                {1, 4, 7}
        };
        assertArrayEquals(matrixResult, RubiesCubeUtil.rotateSquareMatrixLeft(matrixTest));

        matrixTest = createMatrixTest();
        matrixResult = new int[][]{
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        assertArrayEquals(matrixResult, RubiesCubeUtil.rotateSquareMatrixRight(matrixTest));
    }

    private int[][] createMatrixTest() {
        return new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
    }

    @Test
    public void cubeRotate() {
        SimpleRubiesCube cubeTest = new SimpleRubiesCube(3);
        SimpleRubiesCube cubeResult;

        try {
            cubeTest.rotateCubeOnXAxis(45);
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        cubeTest.rotateCubeOnXAxis(180);

        Face faceLeft = new Face(FaceType.LEFT, 3, FaceType.RIGHT.defaultValue);
        Face faceTop = new Face(FaceType.TOP, 3, FaceType.BOTTOM.defaultValue);
        Face faceRight = new Face(FaceType.RIGHT, 3, FaceType.LEFT.defaultValue);
        Face faceBottom = new Face(FaceType.BOTTOM, 3, FaceType.TOP.defaultValue);
        Face faceFront = new Face(FaceType.FRONT, 3); // В данном случае данные не изменятся, так как вся грань одного цвета
        Face faceBack = new Face(FaceType.BACK, 3);

        cubeResult = new SimpleRubiesCube(3, new Face[]{
                faceLeft, faceTop,
                faceRight, faceBottom,
                faceFront, faceBack});
        assertEquals(cubeTest, cubeResult);

        cubeTest.rotateCubeOnXAxis(180);
        assertEquals(cubeTest, new SimpleRubiesCube(3));

        cubeTest.rotateCubeOnYAxis(360);
        assertEquals(cubeTest, new SimpleRubiesCube(3));

        cubeTest.rotateCubeOnZAxis(360);
        assertEquals(cubeTest, new SimpleRubiesCube(3));
    }


    @Test
    public void simpleColumnMove() {
        SimpleRubiesCube cubeResult;
        SimpleRubiesCube cubeTest = new SimpleRubiesCube(3);

        int[] values = new int[3];
        int degrees = 90;
        Face faceLeft = new Face(FaceType.LEFT, 3);
        faceLeft.rotate(-degrees);
        Face faceRight = new Face(FaceType.RIGHT, 3);

        Face faceTop = new Face(FaceType.TOP, 3);
        Arrays.fill(values, FaceType.FRONT.defaultValue);
        faceTop.setColumn(0, values);

        Face faceBottom = new Face(FaceType.BOTTOM, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.BACK.defaultValue);
        faceBottom.setColumn(0, values);

        Face faceFront = new Face(FaceType.FRONT, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.BOTTOM.defaultValue);
        faceFront.setColumn(0, values);

        Face faceBack = new Face(FaceType.BACK, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.TOP.defaultValue);
        faceBack.setColumn(2, values);

        cubeResult = new SimpleRubiesCube(3, new Face[]{
                faceLeft, faceTop,
                faceRight, faceBottom,
                faceFront, faceBack});

        cubeTest.moveColumn(FaceType.FRONT, 0, degrees);
        assertEquals(cubeTest, cubeResult);


        cubeTest = new SimpleRubiesCube(3);
        degrees = 90;
        faceFront = new Face(FaceType.FRONT, 3);
        faceFront.rotate(-degrees);
        faceBack = new Face(FaceType.BACK, 3);

        faceLeft = new Face(FaceType.LEFT, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.BOTTOM.defaultValue);
        faceLeft.setColumn(0, values);

        faceTop = new Face(FaceType.TOP, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.LEFT.defaultValue);
        faceTop.setRow(0, values);

        faceRight = new Face(FaceType.RIGHT, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.TOP.defaultValue);
        faceRight.setColumn(2, values);

        faceBottom = new Face(FaceType.BOTTOM, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.RIGHT.defaultValue);
        faceBottom.setRow(2, values);

        cubeResult = new SimpleRubiesCube(3, new Face[]{
                faceLeft, faceTop,
                faceRight, faceBottom,
                faceFront, faceBack});

        cubeTest.moveColumn(FaceType.LEFT, 0, degrees);
        assertEquals(cubeTest, cubeResult);


        cubeTest = new SimpleRubiesCube(3);

        cubeResult = new SimpleRubiesCube(3, new Face[]{
                faceLeft, faceTop,
                faceRight, faceBottom,
                faceFront, faceBack});

        cubeTest.moveRow(FaceType.TOP, 0, degrees);
        assertEquals(cubeTest, cubeResult);
    }

    @Test
    public void simpleRowMove() {
        SimpleRubiesCube cubeResult;
        SimpleRubiesCube cubeTest = new SimpleRubiesCube(3);

        int[] values = new int[3];
        int degrees = 90;
        Face faceTop = new Face(FaceType.TOP, 3);
        faceTop.rotate(-degrees);
        Face faceBottom = new Face(FaceType.BOTTOM, 3);

        Face faceLeft = new Face(FaceType.LEFT, 3);
        Arrays.fill(values, FaceType.BACK.defaultValue);
        faceLeft.setRow(0, values);

        Face faceRight = new Face(FaceType.RIGHT, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.FRONT.defaultValue);
        faceRight.setRow(0, values);

        Face faceFront = new Face(FaceType.FRONT, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.LEFT.defaultValue);
        faceFront.setRow(0, values);

        Face faceBack = new Face(FaceType.BACK, 3);
        values = new int[3];
        Arrays.fill(values, FaceType.RIGHT.defaultValue);
        faceBack.setRow(0, values);

        cubeResult = new SimpleRubiesCube(3, new Face[]{
                faceLeft, faceTop,
                faceRight, faceBottom,
                faceFront, faceBack});

        cubeTest.moveRow(FaceType.FRONT, 0, degrees);
        assertEquals(cubeTest, cubeResult);


        cubeTest = new SimpleRubiesCube(3);
        cubeTest.moveRow(FaceType.FRONT, 0, 360);
        assertEquals(cubeTest, new SimpleRubiesCube(3));
    }

    @Test
    public void hardColumnMove() {
        SimpleRubiesCube cubeResult = createRealCube();
        SimpleRubiesCube cubeTest = createRealCube();

        cubeTest.moveColumn(FaceType.LEFT, 2, 360);
        assertEquals(cubeTest, cubeResult);
        cubeTest.moveColumn(FaceType.FRONT, 2, -360);
        assertEquals(cubeTest, cubeResult);
        cubeTest.moveColumn(FaceType.RIGHT, 0, 360);
        assertEquals(cubeTest, cubeResult);
        cubeTest.moveColumn(FaceType.BACK, 2, -360);
        assertEquals(cubeTest, cubeResult);
    }

    @Test
    public void hardRowMove() {
        SimpleRubiesCube cubeResult = createRealCube();
        SimpleRubiesCube cubeTest = createRealCube();

        cubeTest.moveRow(FaceType.LEFT, 2, 360);
        assertEquals(cubeTest, cubeResult);
        cubeTest.moveRow(FaceType.RIGHT, 0, -360);
        assertEquals(cubeTest, cubeResult);
        cubeTest.moveRow(FaceType.TOP, 2, -360);
        assertEquals(cubeTest, cubeResult);
        cubeTest.moveRow(FaceType.BOTTOM, 1, -360);
        assertEquals(cubeTest, cubeResult);
    }

    private SimpleRubiesCube createRealCube() {
        Face faceLeft = new Face(FaceType.LEFT, 3);
        faceLeft.setRow(0, new int[]{BLUE, ORANGE, GREEN});
        faceLeft.setRow(1, new int[]{ORANGE, GREEN, BLUE});
        faceLeft.setRow(2, new int[]{BLUE, YELLOW, YELLOW});

        Face faceRight = new Face(FaceType.RIGHT, 3);
        faceRight.setRow(0, new int[]{RED, RED, WHITE});
        faceRight.setRow(1, new int[]{YELLOW, BLUE, BLUE});
        faceRight.setRow(2, new int[]{BLUE, RED, YELLOW});

        Face faceTop = new Face(FaceType.TOP, 3);
        faceTop.setRow(0, new int[]{RED, WHITE, ORANGE});
        faceTop.setRow(1, new int[]{WHITE, WHITE, WHITE});
        faceTop.setRow(2, new int[]{ORANGE, WHITE, WHITE});

        Face faceBottom = new Face(FaceType.BOTTOM, 3);
        faceBottom.setRow(0, new int[]{RED, BLUE, ORANGE});
        faceBottom.setRow(1, new int[]{ORANGE, YELLOW, GREEN});
        faceBottom.setRow(2, new int[]{RED, GREEN, GREEN});

        Face faceFront = new Face(FaceType.FRONT, 3);
        faceFront.setRow(0, new int[]{WHITE, GREEN, GREEN});
        faceFront.setRow(1, new int[]{ORANGE, RED, RED});
        faceFront.setRow(2, new int[]{GREEN, RED, YELLOW});

        Face faceBack = new Face(FaceType.BACK, 3);
        faceBack.setRow(0, new int[]{BLUE, BLUE, YELLOW});
        faceBack.setRow(1, new int[]{YELLOW, ORANGE, WHITE});
        faceBack.setRow(2, new int[]{ORANGE, YELLOW, WHITE});

        return new SimpleRubiesCube(3, new Face[]{
                faceLeft, faceTop,
                faceRight, faceBottom,
                faceFront, faceBack});
    }
}
