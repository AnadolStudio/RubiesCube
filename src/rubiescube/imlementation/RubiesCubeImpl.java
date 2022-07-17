package rubiescube.imlementation;

import rubiescube.CubePiece;
import rubiescube.RubiesCube;
import rubiescube.enumeration.Coordinates;
import rubiescube.enumeration.FaceType;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;

import static java.lang.Math.abs;
import static rubiescube.imlementation.RubiesCubeImpl.RubiesCubeUtil.rotateSquareMatrix;

public class RubiesCubeImpl implements RubiesCube {

    protected final CubePiece[][][] data;
    protected final int side;

    public RubiesCubeImpl(int side) {
        this(side, null);
    }

    public RubiesCubeImpl(int side, CubePiece[][][] data) {
        if (side < 2) {
            throw new IllegalArgumentException("Side cube must be >= 2");
        }

        this.side = side;
        if (data != null) {
            validateData(side, data);
            this.data = data;
        } else {
            this.data = createDefaultCube(side);
            fillColor(side);
        }
    }

    private static void validateDegrees(int degrees) {
        if (degrees % ONE_ROTATE != 0) {
            throw new IllegalArgumentException("Degrees must be multiple of 90");
        }
    }

    private void fillColor(int side) {
        fillLayer(FaceType.BOTTOM, getYLayer(side - 1));
        fillLayer(FaceType.TOP, getYLayer(0));
        fillLayer(FaceType.RIGHT, getXLayer(side - 1));
        fillLayer(FaceType.LEFT, getXLayer(0));
        fillLayer(FaceType.BACK, getZLayer(side - 1));
        fillLayer(FaceType.FRONT, getZLayer(0));
    }

    private void validateData(int side, CubePiece[][][] data) {
        // Сторона по X
        if (data.length != side)
            illegalData();

        // Сторона по Y
        for (int x = 0; x < side; x++) {
            if (data[x].length != side)
                illegalData();

            // Сторона по Z
            for (int y = 0; y < side; y++) {
                if (data[x][y].length != side)
                    illegalData();
            }
        }
    }

    private void illegalData() {
        throw new IllegalArgumentException("Illegal data");
    }

    private CubePiece[][][] createDefaultCube(int side) {
        CubePiece[][][] data = new CubePiece[side][side][side];
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                for (int z = 0; z < side; z++) {
                    data[x][y][z] = new CubePiece();
                }
            }
        }

        return data;
    }

    private void fillLayer(FaceType type, CubePiece[][] layer) {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                layer[i][j].addColor(type.defaultValue, type.coordinate);
            }
        }
    }

    public CubePiece[][] getXLayer(int x) {
        return data[x];
    }

    public CubePiece[][] getYLayer(int y) {
        CubePiece[][] layer = new CubePiece[side][side];
        for (int x = 0; x < side; x++) {
            for (int z = 0; z < side; z++) layer[x][z] = data[x][y][z];
        }

        return layer;
    }

    public CubePiece[][] getZLayer(int z) {
        CubePiece[][] layer = new CubePiece[side][side];
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) layer[x][y] = data[x][y][z];
        }

        return layer;
    }

    public void setXLayer(int x, CubePiece[][] layer) {
        data[x] = layer;
    }

    public void setYLayer(int y, CubePiece[][] layer) {
        for (int x = 0; x < side; x++) {
            for (int z = 0; z < side; z++) data[x][y][z] = layer[x][z];
        }
    }

    public void setZLayer(int z, CubePiece[][] layer) {
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) data[x][y][z] = layer[x][y];
        }
    }

    public int[] findCubePiece(Color... colors) {
        if (colors.length < 1 || colors.length > 3) {
            throw new IllegalArgumentException("Count colors must be > 0 and < 3");
        }

        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                for (int z = 0; z < side; z++) {
                    CubePiece piece = data[x][y][z];
                    if (piece.getColorCount() == colors.length && piece.hasAllThisColors(colors)) {
                        return new int[]{x, y, z};
                    }
                }
            }
        }

        return new int[]{};
    }

    @Override
    public int getSide() {
        return side;
    }

    @Override
    public CubePiece[][][] getData() {
        return data;
    }

    public CubePiece getCubePiece(int x, int y, int z) {
        return data[x][y][z];
    }

    @Override
    public void rotateCubeOnXAxis(int degrees) {
        for (int i = 0; i < side; i++) {
            moveColumn(FaceType.FRONT, i, degrees);
        }
    }

    @Override
    public void rotateCubeOnYAxis(int degrees) {
        for (int i = 0; i < side; i++) {
            moveRow(FaceType.LEFT, i, degrees);
        }
    }

    @Override
    public void rotateCubeOnZAxis(int degrees) {
        for (int i = 0; i < side; i++) {
            moveColumn(FaceType.LEFT, i, degrees);
        }
    }

    @Override
    public void moveColumn(FaceType type, int index, int degrees) {
        validateDegrees(degrees);

        index = needFlipIndex(type) ? side - 1 - index : index;
        degrees = needFlipDegrees(type) ? -degrees : degrees;
        CubePiece[][] layer;

        for (int i = 0; i < abs(degrees / ONE_ROTATE); i++) {
            switch (type.coordinate) {
                default /*Z*/ -> {
                    layer = rotateSquareMatrix(getZLayer(index), -degrees);
                    setZLayer(index, layer);
                    moveAxlesForEachPiece(layer, Coordinates.Z);
                }
                case X, Y -> {
                    layer = rotateSquareMatrix(getXLayer(index), degrees);
                    setXLayer(index, layer);
                    moveAxlesForEachPiece(layer, Coordinates.X);
                }
            }
        }
    }

    @Override
    public void moveRow(FaceType type, int index, int degrees) {
        validateDegrees(degrees);

        switch (type.coordinate) {
            case Y ->// Для этих граней сдвиг ряда равносилен сдвигу колонки у следующих граней
                    moveColumn(type == FaceType.TOP ? FaceType.LEFT : FaceType.RIGHT,
                            index,
                            degrees);
            case X, Z -> {
                for (int i = 0; i < abs(degrees / ONE_ROTATE); i++) {
                    CubePiece[][] layer = rotateSquareMatrix(getYLayer(index), -degrees);
                    setYLayer(index, layer);
                    moveAxlesForEachPiece(layer, Coordinates.Y);
                }
            }
        }
    }

    private void moveAxlesForEachPiece(CubePiece[][] layer, Coordinates coordinate) {
        for (CubePiece[] i : layer) {
            for (CubePiece j : i) {
                switch (coordinate) {
                    case X -> j.moveOnX();
                    case Y -> j.moveOnY();
                    case Z -> j.moveOnZ();
                }
            }
        }
    }

    private boolean needFlipIndex(FaceType faceType) {
        return switch (faceType) {
            case LEFT, BACK -> true;
            default -> false;
        };
    }

    private boolean needFlipDegrees(FaceType faceType) {
        return switch (faceType) {
            case RIGHT, BACK -> true;
            default -> false;
        };
    }

    @Override
    public void rotateFace(FaceType type, int degrees) {
        switch (type) {
            case TOP -> moveRow(FaceType.LEFT, 0, -degrees);
            case FRONT -> moveColumn(FaceType.LEFT, side - 1, degrees);
            case LEFT -> moveColumn(FaceType.FRONT, 0, -degrees);
            case BOTTOM -> moveRow(FaceType.LEFT, side - 1, degrees);
            case BACK -> moveColumn(FaceType.LEFT, 0, -degrees);
            case RIGHT -> moveColumn(FaceType.FRONT, side - 1, degrees);
        }
    }

    @Override
    public String toString() {
        return "RubiesCube " +
                "data=" + Arrays.deepToString(data) +
                ", side=" + side +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RubiesCubeImpl)) return false;
        RubiesCubeImpl that = (RubiesCubeImpl) o;
        if (side != that.side) return false;

        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                for (int z = 0; z < side; z++) {
                    if (!data[x][y][z].equals(that.data[x][y][z])) return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(side) + Arrays.deepHashCode(data);
    }

    public static class RubiesCubeUtil {

        protected static CubePiece[][] rotateSquareMatrix(CubePiece[][] data, int degrees) {
            return rotateSquareMatrix(
                    data.length,
                    (degrees > 0)
                            ? (row, column) -> data[data.length - column - 1][row]
                            : (row, column) -> data[column][data.length - row - 1]
            );
        }

        protected static CubePiece[][] rotateSquareMatrix(int size, BiFunction<Integer, Integer, CubePiece> function) {
            CubePiece[][] result = new CubePiece[size][size];

            for (int row = 0; row < size; row++)
                for (int column = 0; column < size; column++)
                    result[row][column] = function.apply(row, column);

            return result;
        }
    }
}
