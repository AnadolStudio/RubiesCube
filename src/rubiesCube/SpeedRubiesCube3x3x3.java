package rubiesCube;

public class SpeedRubiesCube3x3x3 extends SpeedRubiesCube {
    private final int mid;

    public SpeedRubiesCube3x3x3() {
        super(3);
        mid = 1;
    }

    public void s() {
        moveColumn(FaceType.LEFT, mid, ONE_TURNOVER);
    }

    public void sr() {
        moveColumn(FaceType.LEFT, mid, -ONE_TURNOVER);
    }

    public void m() {
        moveColumn(FaceType.FRONT, mid, ONE_TURNOVER);
    }

    public void mr() {
        moveColumn(FaceType.FRONT, mid, -ONE_TURNOVER);
    }

    public void e() {
        moveRow(FaceType.FRONT, mid, ONE_TURNOVER);
    }

    public void er() {
        moveRow(FaceType.FRONT, mid, -ONE_TURNOVER);
    }

}
