package rubiesCube;

import java.awt.*;

public enum FaceType {
    TOP(Color.WHITE, Coordinates.Y),
    FRONT(Color.GREEN, Coordinates.X),
    RIGHT(Color.RED, Coordinates.Z),
    BACK(Color.BLUE, Coordinates.X),
    LEFT(Color.ORANGE, Coordinates.Z),
    BOTTOM(Color.YELLOW, Coordinates.Y);

    public final Color defaultValue;
    public final Coordinates coordinate;

    FaceType(Color color, Coordinates coordinate) {
        defaultValue = color;
        this.coordinate = coordinate;
    }
}
