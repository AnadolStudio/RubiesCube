package rubiesCube;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class CubePiece {
    private final HashMap<Coordinate, Color> colorMap = new HashMap<>();

    public void addColor(Color color, Coordinate coordinate) {
        colorMap.put(coordinate, color);
    }

    public int getSize() {
        return colorMap.size();
    }

    public boolean hasColors(Color... colors) {
        for (Color color : colors) {
            if (!colorMap.containsKey(color)) return false;
        }
        return true;
    }

    public void moveOnX() { //X <-> Y
        swapCoordinate(Coordinate.X, Coordinate.Y);
    }

    public void moveOnY() {//X <-> Z
        swapCoordinate(Coordinate.X, Coordinate.Z);
    }

    public void moveOnZ() {//Z <-> Y
        swapCoordinate(Coordinate.Z, Coordinate.Y);
    }

    private void swapCoordinate(Coordinate oneCoordinate, Coordinate twoCoordinate) {
        Color one = colorMap.remove(oneCoordinate);
        Color two = colorMap.remove(twoCoordinate);
        if (one != null) {
            colorMap.put(twoCoordinate, one);
        }
        if (two != null) {
            colorMap.put(oneCoordinate, two);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubePiece cubePiece = (CubePiece) o;

        if (colorMap.size() != cubePiece.colorMap.size()) return false;

        for (Coordinate c : colorMap.keySet()) {
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
