package rubiesCube;

import java.awt.*;

public enum FaceType {
    TOP(Color.WHITE, Coordinates.Y, 3),
    FRONT(Color.GREEN, Coordinates.X, 2),
    RIGHT(Color.RED, Coordinates.Z, -1),
    BACK(Color.BLUE, Coordinates.X, -2),
    LEFT(Color.ORANGE, Coordinates.Z, 1),
    BOTTOM(Color.YELLOW, Coordinates.Y, -3);

    public final Color defaultValue;
    public final Coordinates coordinate;
    public final int supportIndex; //Необходим для ослеживания зеркальности ряда/колонки

    FaceType(Color color, Coordinates coordinate, int supportIndex) {
        defaultValue = color;
        this.supportIndex = supportIndex;
        this.coordinate = coordinate;
    }
}
