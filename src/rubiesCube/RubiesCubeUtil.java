package rubiesCube;

public class RubiesCubeUtil {

    public static int[][] rotateSquareMatrixLeft(int[][] data) {
        int side = data.length;
        int[][] result = new int[side][side];

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                result[side - column - 1][row] = data[row][column];
            }
        }
        return result;
    }

    public static int[][] rotateSquareMatrixRight(int[][] data) {
        int side = data.length;
        int[][] result = new int[side][side];

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                result[column][side - row - 1] = data[row][column];
            }
        }
        return result;
    }
}
