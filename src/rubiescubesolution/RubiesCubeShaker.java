package rubiescubesolution;

import rubiescube.enumeration.FaceType;
import rubiescube.imlementation.RubiesCubeImpl;
import rubiescube.imlementation.SpeedRubiesCube3x3x3;

import java.util.Random;

public class RubiesCubeShaker {

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
                FaceType faceType = RandomUtil.getFaceType();
                int index = RandomUtil.getIndex(side);
                int degrees = RandomUtil.getDegrees();

                if (Math.random() < 0.5) cube.moveRow(faceType, index, degrees);
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
