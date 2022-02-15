package rubiesCube;

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

    public void f() {//Front
        moveColumn(FaceType.LEFT, lastIndex, ONE_TURNOVER);
    }

    public void fr() {// Front reverse
        moveColumn(FaceType.LEFT, lastIndex, -ONE_TURNOVER);
    }

    public void b() {
        moveColumn(FaceType.LEFT, firstIndex, ONE_TURNOVER);
    }

    public void br() {
        moveColumn(FaceType.LEFT, firstIndex, -ONE_TURNOVER);
    }

    public void l() {
        moveColumn(FaceType.FRONT, firstIndex, ONE_TURNOVER);
    }

    public void lr() {
        moveColumn(FaceType.FRONT, firstIndex, -ONE_TURNOVER);

    }

    public void r() {
        moveColumn(FaceType.FRONT, lastIndex, ONE_TURNOVER);
    }

    public void rr() {
        moveColumn(FaceType.FRONT, lastIndex, -ONE_TURNOVER);
    }

    public void u() {
        moveRow(FaceType.FRONT, firstIndex, ONE_TURNOVER);
    }

    public void ur() {
        moveRow(FaceType.FRONT, firstIndex, -ONE_TURNOVER);
    }

    public void d() {
        moveRow(FaceType.FRONT, lastIndex, ONE_TURNOVER);
    }

    public void dr() {
        moveRow(FaceType.FRONT, lastIndex, -ONE_TURNOVER);
    }


    public void x() {
        rotateCubeOnXAxis(ONE_TURNOVER);
    }

    public void xr() {
        rotateCubeOnXAxis(-ONE_TURNOVER);
    }

    public void y() {
        rotateCubeOnYAxis(ONE_TURNOVER);
    }

    public void yr() {
        rotateCubeOnYAxis(-ONE_TURNOVER);
    }

    public void z() {
        rotateCubeOnZAxis(ONE_TURNOVER);
    }

    public void zr() {
        rotateCubeOnZAxis(-ONE_TURNOVER);
    }
}
