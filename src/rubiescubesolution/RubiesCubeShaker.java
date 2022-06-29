package rubiescubesolution;

import rubiescube.enumeration.FaceType;
import rubiescube.imlementation.RubiesCubeImpl;
import rubiescube.imlementation.SpeedRubiesCube3x3x3;

import java.util.Random;

public class RubiesCubeShaker {
    private static final int ONE_R0TATE = 90;

    private enum ShakeType {
        CHESS,
        CROSS,
        EYE,
        RANDOM
    }

    public static class Manager {

        public static void shake(SpeedRubiesCube3x3x3 cube) {
            Random randomUtil = new Random();
            int i = randomUtil.nextInt(ShakeType.values().length);

            switch (ShakeType.values()[i]) {
                case CHESS -> chess(cube);
                case CROSS -> cross(cube);
                case EYE -> eye(cube);
                default -> random(cube);
            }
        }
    }

    public static void random(RubiesCubeImpl cube) {
        int side = cube.getSide();
        RubiesCubeImpl defaultCube = new RubiesCubeImpl(side);
        int count = side == 2 ? 10 : 25;

        do {
            for (int i = 0; i < count; i++) {
                // TODO Для предоставления случайных данных - отдельных класс
                int randomFaceIndex = (int) (Math.random() * FaceType.values().length);
                boolean isRow = Math.random() < 0.5;
                int trajectory = Math.random() < 0.5 ? 1 : -1;
                int countRotate = (int) (Math.random() * 2);

                FaceType faceType = FaceType.values()[randomFaceIndex];
                int index = (int) (Math.random() * side);
                int degrees = ONE_R0TATE * trajectory * countRotate;

                if (isRow) cube.moveRow(faceType, index, degrees);
                else cube.moveColumn(faceType, index, degrees);
            }

        } while (cube.equals(defaultCube));
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
