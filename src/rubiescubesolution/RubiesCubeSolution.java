package rubiescubesolution;

import rubiescube.RubiesCube;
import rubiescube.imlementation.SpeedRubiesCube;

public abstract class RubiesCubeSolution<T extends SpeedRubiesCube> {

    protected abstract T createSpeedCube(RubiesCube cube);

    public final void run(RubiesCube cube) {
        T speedCube = createSpeedCube(cube);

        cross(speedCube);
        mid(speedCube);
        orientTheLastLayer(speedCube);
        permuteTheLastLayer(speedCube);
    }

    protected abstract void cross(T speedCube);

    protected abstract void mid(T speedCube);

    protected abstract void orientTheLastLayer(T speedCube);

    protected abstract void permuteTheLastLayer(T speedCube);
}
