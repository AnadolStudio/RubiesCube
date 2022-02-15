package rubiesCube;

import java.awt.*;

public enum FaceType {
    TOP(Color.WHITE, Coordinate.Y, 3),
    FRONT(Color.GREEN, Coordinate.X, 2),
    RIGHT(Color.RED, Coordinate.Z, -1),
    BACK(Color.BLUE, Coordinate.X, -2),
    LEFT(Color.ORANGE, Coordinate.Z, 1),
    BOTTOM(Color.YELLOW, Coordinate.Y, -3);

    public final Color defaultValue;
    public Coordinate coordinate;
    public final int supportIndex; //Необходим для ослеживания зеркальности ряда/колонки

    FaceType(Color color, Coordinate coordinate, int supportIndex) {
        defaultValue = color;
        this.supportIndex = supportIndex;
        this.coordinate = coordinate;
    }
}
