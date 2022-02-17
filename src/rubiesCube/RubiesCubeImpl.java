package rubiesCube;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;
import static rubiesCube.RubiesCubeImpl.RubiesCubeUtil.rotateSquareMatrix;

public class RubiesCubeImpl implements IRubiesCube {
    protected static final int ONE_TURNOVER = 90;

    private static final List<FaceType> faceTypesAxisX =
            Arrays.asList(FaceType.FRONT, FaceType.RIGHT, FaceType.BACK, FaceType.LEFT);

    private static final List<FaceType> faceTypesAxisY =
            Arrays.asList(FaceType.FRONT, FaceType.TOP, FaceType.BACK, FaceType.BOTTOM);

    private static final List<FaceType> faceTypesAxisZ =
            Arrays.asList(FaceType.LEFT, FaceType.TOP, FaceType.RIGHT, FaceType.BOTTOM);

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
            this.data = createDefault(side);
            fillColor(side);
        }
    }

    private static void validateDegrees(int degrees) {
        if (degrees % ONE_TURNOVER != 0) {
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
        //TODO Проверка маленьких кубов 8 угловых, f(side) двойных, остальные центральные
        for (int x = 0; x < side; x++) {
            if (data[x].length != side)
                illegalData();

            for (int y = 0; y < side; y++) {
                if (data[x][y].length != side)
                    illegalData();
            }
        }
    }

    private void illegalData() {
        throw new IllegalArgumentException("Illegal data");
    }

    private CubePiece[][][] createDefault(int side) {
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

    @Override
    public int getSide() {
        return side;
    }

    @Override
    public CubePiece[][][] getData() {
        return data;
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
        boolean needFlip = needFlip(type);
        index = needFlip ? side - 1 - index : index;
        degrees = needFlip ? -degrees : degrees;
        CubePiece[][] layer;

        for (int i = 0; i < abs(degrees / ONE_TURNOVER); i++) {
            switch (type.coordinate) {
                case X, Y -> {
                    layer = rotateSquareMatrix(getXLayer(index), degrees);
                    setXLayer(index, layer);
                    moveAxlesForEachPiece(layer, Coordinate.X);
                }
                default /*Z*/ -> {
                    layer = rotateSquareMatrix(getZLayer(index), degrees);
                    setZLayer(index, layer);
                    moveAxlesForEachPiece(layer, Coordinate.Z);
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
                for (int i = 0; i < abs(degrees / ONE_TURNOVER); i++) {
                    CubePiece[][] layer = rotateSquareMatrix(getYLayer(index), degrees);
                    setYLayer(index, layer);
                    moveAxlesForEachPiece(layer, Coordinate.Y);
                }
            }
        }
    }

    private void moveAxlesForEachPiece(CubePiece[][] layer, Coordinate coordinate) {
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

    private boolean needFlip(FaceType faceType) {
        return switch (faceType) {
            case /*TOP,*/ RIGHT, BACK -> true;
            default -> false;
        };
    }

    @Override
    public void rotateFace(FaceType type, int degrees) {
        switch (abs(type.supportIndex)) {
            case 3 -> moveRow(FaceType.LEFT,
                    type == FaceType.TOP ? 0 : side - 1,
                    type == FaceType.TOP ? -degrees : degrees);
            case 2 -> moveColumn(FaceType.LEFT,
                    type == FaceType.FRONT ? side - 1 : 0,
                    type == FaceType.FRONT ? degrees : -degrees);
            case 1 -> moveColumn(FaceType.FRONT,
                    type == FaceType.LEFT ? 0 : side - 1,
                    type == FaceType.LEFT ? -degrees : degrees);
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
        if (o == null || getClass() != o.getClass()) return false;
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

    protected static class RubiesCubeUtil {

        protected static CubePiece[][] rotateSquareMatrix(CubePiece[][] data, int degrees) {
            return (degrees > 0) ?
                    RubiesCubeUtil.rotateSquareMatrixRight(data)
                    : RubiesCubeUtil.rotateSquareMatrixLeft(data);
        }

        protected static CubePiece[][] rotateSquareMatrixLeft(CubePiece[][] data) {
            int side = data.length;
            CubePiece[][] result = new CubePiece[side][side];

            for (int row = 0; row < side; row++) {
                for (int column = 0; column < side; column++) {
                    result[side - column - 1][row] = data[row][column];
                }
            }
            return result;
        }

        protected static CubePiece[][] rotateSquareMatrixRight(CubePiece[][] data) {
            int side = data.length;
            CubePiece[][] result = new CubePiece[side][side];

            for (int row = 0; row < side; row++) {
                for (int column = 0; column < side; column++) {
                    result[column][side - row - 1] = data[row][column];
                }
            }
            return result;
        }
    }
}
