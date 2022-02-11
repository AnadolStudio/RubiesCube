package rubiesCube;

import static helpers.Colors.*;

public enum FaceType {
    TOP(WHITE, 3),
    FRONT(GREEN, 2),
    RIGHT(RED, -1),
    BACK(BLUE, -2),
    LEFT(ORANGE, 1),
    BOTTOM(YELLOW, -3);


    public final int defaultValue;
    public final int supportIndex; //Необходим для ослеживания зеркальности ряда/колонки

    FaceType(int color, int supportIndex) {
        defaultValue = color;
        this.supportIndex = supportIndex;
    }
}
