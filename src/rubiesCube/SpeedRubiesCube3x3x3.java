package rubiesCube;

public class SpeedRubiesCube3x3x3 extends SpeedRubiesCube {
    private final int MID = 1;// mid index

    public SpeedRubiesCube3x3x3() {
        super(3);
    }

    public SpeedRubiesCube3x3x3(CubePiece[][][] data) {
        super(3, data);
    }

    public SpeedRubiesCube3x3x3 s() {
        moveColumn(FaceType.LEFT, MID, ONE_TURNOVER);
        return this;
    }

    public SpeedRubiesCube3x3x3 sr() {
        moveColumn(FaceType.LEFT, MID, -ONE_TURNOVER);
        return this;
    }

    public SpeedRubiesCube3x3x3 m() {
        moveColumn(FaceType.FRONT, MID, ONE_TURNOVER);
        return this;
    }

    public SpeedRubiesCube3x3x3 mr() {
        moveColumn(FaceType.FRONT, MID, -ONE_TURNOVER);
        return this;
    }

    public SpeedRubiesCube3x3x3 e() {
        moveRow(FaceType.FRONT, MID, ONE_TURNOVER);
        return this;
    }

    public SpeedRubiesCube3x3x3 er() {
        moveRow(FaceType.FRONT, MID, -ONE_TURNOVER);
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 f() {
        super.f();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 fr() {
        super.fr();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 b() {
        super.b();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 br() {
        super.br();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 l() {
        super.l();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 lr() {
        super.lr();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 r() {
        super.r();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 rr() {
        super.rr();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 u() {
        super.u();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 ur() {
        super.ur();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 d() {
        super.d();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 dr() {
        super.dr();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 x() {
        super.x();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 xr() {
        super.xr();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 y() {
        super.y();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 yr() {
        super.yr();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 z() {
        super.z();
        return this;
    }

    @Override
    public SpeedRubiesCube3x3x3 zr() {
        super.zr();
        return this;
    }

    public SpeedRubiesCube3x3x3 raiseDiagonallyToTheRight() {
        dr().rr().d().r().d().f().dr().fr();
        return this;
    }

    public SpeedRubiesCube3x3x3 raiseDiagonallyToTheLeft() {
        d().lr().dr().l().dr().fr().d().f();
        return this;
    }

    public SpeedRubiesCube3x3x3 flipOver() {
        f().e().f().e().f().e().f().e();
        return this;
    }

    public SpeedRubiesCube3x3x3 rotateThreeCubeClockwise() {
        rr().fr().l().f().r().fr().lr().f();
        return this;
    }

    public SpeedRubiesCube3x3x3 rotateThreeCubeCounterClockwise() {
        fr().l().br().lr().f().l().b().lr();
        return this;
    }

    public SpeedRubiesCube3x3x3 rotateCubeClockwise() {
        r().fr().rr().f().r().fr().rr().f();
        return this;
    }

    public SpeedRubiesCube3x3x3 rotateCubeCounterClockwise() {
        fr().r().f().rr().fr().r().f().rr();
        return this;
    }

    public SpeedRubiesCube3x3x3 swapDiagonallyRightOnTop() {
        u().fr().l().u().lr().ur().f();
        return this;
    }

    public SpeedRubiesCube3x3x3 swapDiagonallyLeftOnTop() {
        ur().f().r().ur().rr().u().fr();
        return this;
    }
}
