package rubiesCube;

public class SpeedRubiesCube3x3x3 extends SpeedRubiesCube {
    private final int MID = 1;// mid index

    public SpeedRubiesCube3x3x3() {
        super(3);
    }

    public SpeedRubiesCube3x3x3(CubePiece[][][] data) {
        super(3, data);
    }

    public void s() {
        moveColumn(FaceType.LEFT, MID, ONE_TURNOVER);
    }

    public void sr() {
        moveColumn(FaceType.LEFT, MID, -ONE_TURNOVER);
    }

    public void m() {
        moveColumn(FaceType.FRONT, MID, ONE_TURNOVER);
    }

    public void mr() {
        moveColumn(FaceType.FRONT, MID, -ONE_TURNOVER);
    }

    public void e() {
        moveRow(FaceType.FRONT, MID, ONE_TURNOVER);
    }

    public void er() {
        moveRow(FaceType.FRONT, MID, -ONE_TURNOVER);
    }

}
