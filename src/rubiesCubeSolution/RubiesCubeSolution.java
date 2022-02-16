package rubiesCubeSolution;

import rubiesCube.IRubiesCube;
import rubiesCube.SpeedRubiesCube;

public abstract class RubiesCubeSolution<T extends SpeedRubiesCube> {

    protected abstract T createSpeedCube(IRubiesCube cube);

    public final void run(IRubiesCube cube) {
        T speedCube = createSpeedCube(cube);

        cross(speedCube);
        firstTwoLayer(speedCube);
        orientTheLastLayer(speedCube);
        permuteTheLastLayer(speedCube);
    }

    protected abstract void cross(T speedCube);

    protected abstract void firstTwoLayer(T speedCube);

    protected abstract void orientTheLastLayer(T speedCube);

    protected abstract void permuteTheLastLayer(T speedCube);
}
