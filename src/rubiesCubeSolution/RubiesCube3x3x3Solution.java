package rubiesCubeSolution;

import rubiesCube.IRubiesCube;
import rubiesCube.SpeedRubiesCube3x3x3;

public class RubiesCube3x3x3Solution extends RubiesCubeSolution<SpeedRubiesCube3x3x3> {

    @Override
    protected SpeedRubiesCube3x3x3 createSpeedCube(IRubiesCube cube) {
        if (cube.getSide() != 3) throw new IllegalArgumentException("Cube must have side = 3");
        return new SpeedRubiesCube3x3x3(cube.getData());
    }

    @Override
    protected void cross(SpeedRubiesCube3x3x3 speedCube) {

    }

    @Override
    protected void firstTwoLayer(SpeedRubiesCube3x3x3 speedCube) {

    }

    @Override
    protected void orientTheLastLayer(SpeedRubiesCube3x3x3 speedCube) {

    }

    @Override
    protected void permuteTheLastLayer(SpeedRubiesCube3x3x3 speedCube) {

    }
}
