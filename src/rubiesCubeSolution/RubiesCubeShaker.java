package rubiesCubeSolution;

import rubiesCube.FaceType;
import rubiesCube.RubiesCubeImpl;
import rubiesCube.SpeedRubiesCube3x3x3;

public class RubiesCubeShaker {
    private static final int ONE_R0TATE = 90;

    public static void shake(RubiesCubeImpl cube) {
        int side = cube.getSide();

        for (int i = 0; i < 50; i++) {
            int randomFaceIndex = (int) (Math.random() * FaceType.values().length);
            boolean isRow = Math.random() < 0.5;
            int trajectory = Math.random() < 0.5 ? 1 : -1;
            int countRotate = (int) (Math.random() * 2);

            FaceType faceType = FaceType.values()[randomFaceIndex];
            int index = (int) (Math.random() * side);
            int degrees = ONE_R0TATE * trajectory * countRotate;

            if (isRow) {
                cube.moveRow(faceType, index, degrees);
            } else {
                cube.moveColumn(faceType, index, degrees);
            }
        }
    }

    public static void chess(SpeedRubiesCube3x3x3 cube) {
        cube.mr().mr().e().e().yr().mr().mr();
    }

    public static void cross(SpeedRubiesCube3x3x3 cube) {
        cube.lr().lr().r().b().b().f().f().d().d().b().b()
                .f().f().lr().lr().rr().rr().ur().ur().rr();
    }

    public static void eye(SpeedRubiesCube3x3x3 cube) {
        cube.er().mr().e().m();
    }

}
