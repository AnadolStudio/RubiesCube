package rubiesCubeSolution;

import rubiesCube.IRubiesCube;
import rubiesCube.SpeedRubiesCube;

public class RubiesCubeShaker {

    public static void shake(IRubiesCube cube) {
        int side = cube.getSide();
        SpeedRubiesCube speedCube = new SpeedRubiesCube(side, cube.getData());

        for (int i = 0; i < 50; i++) {

        }
    }
}
