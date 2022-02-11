package rubiesCube;

import helpers.Pair;

import java.util.*;

import static java.lang.Math.abs;

public abstract class AbstractRubiesCube implements RubiesCube {
    protected static final int ONE_TURNOVER = 90;

    private static final List<FaceType> faceTypesAxisX =
            Arrays.asList(FaceType.FRONT, FaceType.RIGHT, FaceType.BACK, FaceType.LEFT);

    private static final List<FaceType> faceTypesAxisY =
            Arrays.asList(FaceType.FRONT, FaceType.TOP, FaceType.BACK, FaceType.BOTTOM);

    private static final List<FaceType> faceTypesAxisZ =
            Arrays.asList(FaceType.LEFT, FaceType.TOP, FaceType.RIGHT, FaceType.BOTTOM);

    private final HashMap<FaceType, Face> facesMap;
    private final int side;

    public AbstractRubiesCube(int side) {
        this(side, null);
    }

    public AbstractRubiesCube(int side, Face[] faces) {
        if (side < 2) {
            throw new IllegalArgumentException("Side cube must be >= 2");
        }
        if (faces != null && faces.length != 6) {
            throw new IllegalArgumentException("Faces length must be == 6");
        }
        this.side = side;
        facesMap = new HashMap<>();

        if (faces == null) faces = createDefaultFaces(side);

        for (Face face : faces) {
            if (facesMap.put(face.type, face) != null) {
                throw new IllegalArgumentException("Duplicate face");
            }
        }
    }

    private static void validateDegrees(int degrees) {
        if (degrees % ONE_TURNOVER != 0) {
            throw new IllegalArgumentException("Degrees must be multiple of 90");
        }
    }

    private Face[] createDefaultFaces(int side) {
        Face[] faces = new Face[6];
        for (int i = 0; i < FaceType.values().length; i++) {
            faces[i] = new Face(FaceType.values()[i], side);
        }
        return faces;
    }

    public int getSide() {
        return side;
    }

    @Override
    public void rotateCubeOnXAxis(int degrees) {
        validateDegrees(degrees);

        rotateNormalFaces(degrees, FaceType.LEFT, FaceType.RIGHT);
        offsetFaces(degrees, faceTypesAxisZ);
    }

    @Override
    public void rotateCubeOnYAxis(int degrees) {
        validateDegrees(degrees);

        rotateNormalFaces(degrees, FaceType.TOP, FaceType.BOTTOM);
        offsetFaces(degrees, faceTypesAxisX);
    }

    @Override
    public void rotateCubeOnZAxis(int degrees) {
        validateDegrees(degrees);

        rotateNormalFaces(degrees, FaceType.BACK, FaceType.FRONT);
        offsetFaces(degrees, faceTypesAxisY);
    }

    private void rotateNormalFaces(int degrees, FaceType one, FaceType two) {
        facesMap.get(one).rotate(-degrees);
        facesMap.get(two).rotate(degrees);
    }

    private void offsetFaces(int degrees, List<FaceType> faceTypesAxisZ) {
        int length = faceTypesAxisZ.size();
        int offset = degrees / ONE_TURNOVER;

        ArrayList<Face> facesList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            Face face = facesMap.get(faceTypesAxisZ.get(i));
            face.type = faceTypesAxisZ.get(abs((i + offset) % length));
            facesList.add(face);
        }

        for (Face face : facesList) {
            facesMap.put(face.type, face);
        }
    }

    @Override
    public void moveColumn(FaceType type, int index, int degrees) {
        validateDegrees(degrees);
        boolean currentTypeIsLeft = abs(type.supportIndex) == 1;
        List<FaceType> facesTypeList = currentTypeIsLeft ? faceTypesAxisZ : faceTypesAxisY;

        rotateExtremeFaces(index, degrees,
                currentTypeIsLeft ? FaceType.BACK : FaceType.LEFT,
                currentTypeIsLeft ? FaceType.FRONT : FaceType.RIGHT);

        for (int count = 0; count < abs(degrees / ONE_TURNOVER); count++) {
            int currentFaceIndex = facesTypeList.indexOf(type);
            Pair<Integer, int[]> pair = new Pair<>(index, null);
            FaceType currentFaceType = type;

            for (int i = 1; i <= facesTypeList.size(); i++) {
                Face one = facesMap.get(currentFaceType);
                currentFaceType = facesTypeList.get((currentFaceIndex + i) % facesTypeList.size());
                Face two = facesMap.get(currentFaceType);
                pair = offsetVerticalLine(one, two, pair.getK(), pair.getV());
            }
        }
    }

    private void rotateExtremeFaces(int index, int degrees, FaceType negativeFace, FaceType positiveFace) {
        // Вращения боковых граней, если индекс является крайним
        if (index == 0) {
            facesMap.get(negativeFace).rotate(-degrees);
        }
        if (index == side - 1) {
            facesMap.get(positiveFace).rotate(degrees);
        }
    }

    private Pair<Integer, int[]> offsetVerticalLine(Face one, Face two, int oneIndex, int[] values) {
        int oneSI = one.type.supportIndex;
        int twoSI = two.type.supportIndex;

        boolean isNormal = abs(abs(oneSI) - abs(twoSI)) > 1;
        int twoIndex = oneIndex;
        boolean reverseData = false;
        boolean oppositeSign = oneSI * twoSI < 0; // Если знаки противоположные

        if (isNormal) {
            if (!oppositeSign) reverseData = true;
            else twoIndex = side - 1 - twoIndex;

        } else {
            if (oneSI > 0 ^ (abs(twoSI) == 3)) {
                reverseData = true;
                twoIndex = side - 1 - twoIndex;
            }
        }

        if (values == null || values.length == 0) {
            values = isNormal && abs(oneSI) == 3 ? one.getRow(oneIndex) : one.getColumn(oneIndex);
        }

        if (reverseData) reverseArray(values);

        int[] changedValues;
        if (isNormal && abs(twoSI) == 3) {
            changedValues = two.getRow(twoIndex);
            two.setRow(twoIndex, values);
        } else {
            changedValues = two.getColumn(twoIndex);
            two.setColumn(twoIndex, values);
        }

        return new Pair<>(twoIndex, changedValues);
    }

    private void reverseArray(int[] values) {
        Collections.reverse(Arrays.asList(values));
    }

    @Override
    public void moveRow(FaceType type, int index, int degrees) {
        validateDegrees(degrees);
        if (type == FaceType.TOP || type == FaceType.BOTTOM) {// Для этих граней сдвиг ряда равносилен сдвигу колонки у следующих граней
            moveColumn(type == FaceType.TOP ? FaceType.LEFT : FaceType.RIGHT,
                    index, degrees);
            return;
        }

        rotateExtremeFaces(index, degrees, FaceType.TOP, FaceType.BOTTOM);
        for (int count = 0; count < abs(degrees / ONE_TURNOVER); count++) {
            int currentFaceIndex = faceTypesAxisX.indexOf(type);
            Pair<Integer, int[]> pair = new Pair<>(index, null);
            FaceType currentFaceType = type;

            for (int i = 1; i <= faceTypesAxisX.size(); i++) {
                Face one = facesMap.get(currentFaceType);
                currentFaceType = faceTypesAxisX.get((currentFaceIndex + i) % faceTypesAxisX.size());
                Face two = facesMap.get(currentFaceType);
                pair = offsetHorizontalLine(one, two, pair.getK(), pair.getV());
            }
        }
    }

    private Pair<Integer, int[]> offsetHorizontalLine(Face one, Face two, int oneIndex, int[] values) {
        if (values == null || values.length == 0) {
            values = one.getRow(oneIndex);
        }
        int[] changedValues = two.getRow(oneIndex);
        two.setRow(oneIndex, values);

        return new Pair<>(oneIndex, changedValues);
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
        //TODO должно выглядеть как развертка куба
        return "rubiesCube.AbstractRubiesCube{" +
                "facesMap=" + facesMap +
                ", side=" + side +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractRubiesCube that = (AbstractRubiesCube) o;
        if (side != that.side || facesMap.size() != that.facesMap.size()) return false;

        for (Face face :
                facesMap.values()) {
            Face thatFace = that.facesMap.get(face.type);
            if (!face.equals(thatFace)) return false;
        }

        return true;
    }

    static class Face { // Сделал временно public-private static только для тестов
        private FaceType type;
        private int[][] data;

        public Face(FaceType faceType, int side) {
            this(faceType, side, faceType.defaultValue);
        }

        public Face(FaceType faceType, int side, int defaultValue) {
            type = faceType;
            data = new int[side][side];
            for (int[] row : data) {
                Arrays.fill(row, defaultValue);
            }
        }

        int[] getColumn(int index) {
            int[] result = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                int[] row = data[i];
                result[i] = row[index];
            }
            return result;
        }

        void setColumn(int index, int[] column) {
            for (int i = 0; i < data.length; i++) {
                data[i][index] = column[i];
            }
        }

        int[] getRow(int index) {
            return data[index];
        }

        void setRow(int index, int[] row) {
            data[index] = row;
        }

        void rotate(int degrees) {
            validateDegrees(degrees);

            boolean clockwise = degrees > 0;

            for (int count = 0; count < abs(degrees / ONE_TURNOVER); count++) {
                data = clockwise ?
                        RubiesCubeUtil.rotateSquareMatrixRight(data)
                        : RubiesCubeUtil.rotateSquareMatrixLeft(data);
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int[] row : data) {
                builder.append(Arrays.toString(row));
            }
            return "Face{" +
                    "type=" + type +
                    ", data=" + builder +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Face face = (Face) o;
            if (type != face.type) return false;
            for (int i = 0; i < data.length; i++) {
                int[] row = data[i];
                int[] thatRow = face.data[i];
                for (int j = 0; j < row.length; j++) {
                    if (row[j] != thatRow[j]) return false;
                }
            }
            return true;
        }
    }
}
