package rubiesCube;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class CubePiece {
    private final HashMap<Coordinates, Color> colorMap = new HashMap<>();
    private final HashMap<Color, Coordinates> coordinateMap = new HashMap<>();

    public void addColor(Color color, Coordinates coordinate) {
        //TODO нужен запрет на редактирование извне?
        colorMap.put(coordinate, color);
        coordinateMap.put(color, coordinate);
    }

    public int getColorCount() {
        return colorMap.size();
    }

    public boolean hasAllThisColors(Color... colors) {
        if (colors.length != colorMap.size()) return false;

        for (Color color : colors) {
            if (!coordinateMap.containsKey(color)) return false;
        }
        return true;
    }

    public boolean hasColor(Color color) {
        return coordinateMap.containsKey(color);
    }

    public Color getColor(Coordinates coordinate) {
        return colorMap.get(coordinate);
    }

    public Coordinates getCoordinate(Color color) {
        return coordinateMap.get(color);
    }

    public void moveOnX() { //X <-> Y
        swapCoordinate(Coordinates.X, Coordinates.Y);
    }

    public void moveOnY() {//X <-> Z
        swapCoordinate(Coordinates.X, Coordinates.Z);
    }

    public void moveOnZ() {//Z <-> Y
        swapCoordinate(Coordinates.Z, Coordinates.Y);
    }

    private void swapCoordinate(Coordinates oneCoordinate, Coordinates twoCoordinate) {
        Color one = colorMap.remove(oneCoordinate);
        Color two = colorMap.remove(twoCoordinate);
        coordinateMap.remove(one);
        coordinateMap.remove(two);

        if (one != null) {
            colorMap.put(twoCoordinate, one);
            coordinateMap.put(one, twoCoordinate);
        }
        if (two != null) {
            colorMap.put(oneCoordinate, two);
            coordinateMap.put(two, oneCoordinate);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubePiece cubePiece = (CubePiece) o;

        if (colorMap.size() != cubePiece.colorMap.size()) return false;

        for (Coordinates c : colorMap.keySet()) {
            if (colorMap.get(c) != cubePiece.colorMap.get(c)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colorMap);
    }

    @Override
    public String toString() {
        return "CubePiece{" +
                "colorMap=" + colorMap +
                '}';
    }
}
