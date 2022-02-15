package rubiesCube;

public class RubiesCubeUtil {


    public static RubiesCubeImpl.CubePiece[][] rotateSquareMatrix(RubiesCubeImpl.CubePiece[][] data, int degrees) {
        return (degrees > 0) ?
                RubiesCubeUtil.rotateSquareMatrixRight(data)
                : RubiesCubeUtil.rotateSquareMatrixLeft(data);
    }

    public static RubiesCubeImpl.CubePiece[][] rotateSquareMatrixLeft(RubiesCubeImpl.CubePiece[][] data) {
        int side = data.length;
        RubiesCubeImpl.CubePiece[][] result = new RubiesCubeImpl.CubePiece[side][side];

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                result[side - column - 1][row] = data[row][column];
            }
        }
        return result;
    }

    public static RubiesCubeImpl.CubePiece[][] rotateSquareMatrixRight(RubiesCubeImpl.CubePiece[][] data) {
        int side = data.length;
        RubiesCubeImpl.CubePiece[][] result = new RubiesCubeImpl.CubePiece[side][side];

        for (int row = 0; row < side; row++) {
            for (int column = 0; column < side; column++) {
                result[column][side - row - 1] = data[row][column];
            }
        }
        return result;
    }
}
