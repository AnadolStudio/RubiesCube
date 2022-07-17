package rubiescube.imlementation;

import rubiescube.CubePiece;
import rubiescube.enumeration.FaceType;

public class SpeedRubiesCube extends RubiesCubeImpl {
    protected final int firstIndex;
    protected final int lastIndex;

    public SpeedRubiesCube(int side) {
        this(side, null);
    }

    public SpeedRubiesCube(int side, CubePiece[][][] data) {
        super(side, data);
        firstIndex = 0;
        lastIndex = side - 1;
    }

    // Это стандартная нотация движений кубика Рубика. Тут "r" начит reverse.
    public SpeedRubiesCube f() {//Front
        moveColumn(FaceType.LEFT, lastIndex, ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube fr() {// Front reverse
        moveColumn(FaceType.LEFT, lastIndex, -ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube b() {
        moveColumn(FaceType.LEFT, firstIndex, -ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube br() {
        moveColumn(FaceType.LEFT, firstIndex, ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube l() {
        moveColumn(FaceType.FRONT, firstIndex, ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube lr() {
        moveColumn(FaceType.FRONT, firstIndex, -ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube r() {
        moveColumn(FaceType.FRONT, lastIndex, ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube rr() {
        moveColumn(FaceType.FRONT, lastIndex, -ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube u() {
        moveRow(FaceType.FRONT, firstIndex, ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube ur() {
        moveRow(FaceType.FRONT, firstIndex, -ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube d() {
        moveRow(FaceType.FRONT, lastIndex, ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube dr() {
        moveRow(FaceType.FRONT, lastIndex, -ONE_ROTATE);
        return this;
    }


    public SpeedRubiesCube x() {
        rotateCubeOnXAxis(ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube xr() {
        rotateCubeOnXAxis(-ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube y() {
        rotateCubeOnYAxis(ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube yr() {
        rotateCubeOnYAxis(-ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube z() {
        rotateCubeOnZAxis(ONE_ROTATE);
        return this;
    }

    public SpeedRubiesCube zr() {
        rotateCubeOnZAxis(-ONE_ROTATE);
        return this;
    }
}
