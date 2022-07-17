package rubiescubesolution;


import rubiescube.RubiesCube;
import rubiescube.enumeration.FaceType;

public class RandomUtil {

    public static int getIndex(int side) {
        return (int) (Math.random() * side);
    }

    public static int getDegrees() {
        int countRotate = (int) (Math.random() * 2);
        int trajectory = Math.random() < 0.5 ? 1 : -1;

        return RubiesCube.ONE_ROTATE * trajectory * countRotate;
    }

    public static FaceType getFaceType() {
        int randomFaceIndex = (int) (Math.random() * FaceType.values().length);

        return FaceType.values()[randomFaceIndex];
    }
}
