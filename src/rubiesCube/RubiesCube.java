package rubiesCube;

public interface RubiesCube {

    void moveColumn(FaceType type, int index, int degrees);

    void moveRow(FaceType type, int index, int degrees);

    void rotateFace(FaceType type, int degrees);

    void rotateCubeOnXAxis(int degrees);

    void rotateCubeOnYAxis(int degrees);

    void rotateCubeOnZAxis(int degrees);
}
