package rubiescubesolution;

import rubiescube.*;

import java.awt.*;
import java.util.Objects;

public class RubiesCube3x3x3Solution extends RubiesCubeSolution<SpeedRubiesCube3x3x3> {
    private static final int ONE_R0TATE = 90;
    private final int LAST_INDEX = 2;

    @Override
    protected SpeedRubiesCube3x3x3 createSpeedCube(IRubiesCube cube) {
        if (cube.getSide() != 3) throw new IllegalArgumentException("Cube must have side = 3");

        return new SpeedRubiesCube3x3x3(cube.getData());
    }

    @Override
    protected void cross(SpeedRubiesCube3x3x3 cube) {
        CoordinateCube coordinate = new CoordinateCube(cube.findCubePiece(Color.WHITE));

        //Поворачиваю куб таким образом, чтобы белый центр был наверху
        if (coordinate.y == LAST_INDEX) {
            cube.z().z();
        } else if (coordinate.y != 0) {
            boolean isXAxisRotate = coordinate.x == 1;
            int supportCoordinate = isXAxisRotate ? coordinate.z : coordinate.x;
            int degrees = ONE_R0TATE * (supportCoordinate == LAST_INDEX ? -1 : 1);

            if (isXAxisRotate) cube.rotateCubeOnXAxis(degrees);
            else cube.rotateCubeOnZAxis(degrees);
        }

        // Разбираюсь с четырьмя верхними кубиками
        Color[] colorCross = new Color[]{Color.GREEN, Color.ORANGE, Color.BLUE, Color.RED};
        CoordinateCube workPlace = new CoordinateCube(1, 0, 0);

        for (Color cross : colorCross) {
            coordinate = new CoordinateCube(cube.findCubePiece(Color.WHITE, cross));
            if (!workPlace.equals(coordinate)) {
                if (coordinate.y == 0) moveDownFromTop(cube, coordinate);
                int trajectory = coordinate.x == 0 ? 1 : -1;
                int degrees = ONE_R0TATE * trajectory * (coordinate.y == 1 ? coordinate.z / 2 : coordinate.z);
                cube.moveRow(FaceType.FRONT, coordinate.y, degrees);

                degrees = ONE_R0TATE * coordinate.y * trajectory;
                cube.rotateFace(FaceType.FRONT, degrees);
            }

            CubePiece cubePiece = cube.getCubePiece(1, 0, 0);
            if (cubePiece.getCoordinate(Color.WHITE) != Coordinates.Y) {
                cube.f().er().f();
            }
            cube.y();
        }

        while (!cube.getCubePiece(1, 1, 0).hasAllThisColors(Color.GREEN)) cube.e();
    }

    private void moveDownFromTop(SpeedRubiesCube3x3x3 cube, CoordinateCube coordinate) {
        FaceType faceType;
        int trajectory = 1;
        if (coordinate.x == 0) faceType = FaceType.LEFT;
        else if (coordinate.x == LAST_INDEX) {
            faceType = FaceType.RIGHT;
            trajectory = -1;
        } else faceType = FaceType.BACK;
        cube.rotateFace(faceType, ONE_R0TATE * trajectory);

        switch (faceType) {
            case LEFT -> coordinate.set(0, 1, 0);
            case RIGHT -> coordinate.set(2, 1, 0);
            case BACK -> coordinate.set(0, 1, 2);
        }
    }

    @Override
    protected void mid(SpeedRubiesCube3x3x3 cube) { //firstTwoLayer
        CoordinateCube workPlace = new CoordinateCube(2, 0, 0);
        // Верхние углы
        for (int i = 0; i < 4; i++) {
            CubePiece frontNeighbour = cube.getCubePiece(1, 0, 0);
            CubePiece rightNeighbour = cube.getCubePiece(2, 0, 1);
            CoordinateCube coordinate = new CoordinateCube(cube.findCubePiece(
                    Color.WHITE,
                    frontNeighbour.getColor(Coordinates.X),
                    rightNeighbour.getColor(Coordinates.Z)));

            //Подводит к координате x = 2, y = 2, z = 0
            if (workPlace.equals(coordinate)) {
                if (cube.getCubePiece(workPlace.x, workPlace.y, workPlace.z)
                        .getCoordinate(Color.WHITE) != Coordinates.Y) {
                    cube.f().dr().fr();
                } else {
                    cube.y();
                    continue;
                }
            } else if (coordinate.y == 0) {
                moveDownEdgeFromTop(cube, coordinate);
            } else {
                int trajectory = coordinate.z == 2 ? -1 : 1;

                int degrees = ONE_R0TATE * trajectory * (coordinate.x == coordinate.z ? 1
                        : coordinate.x > coordinate.z ? 0 : 2);
                cube.moveRow(FaceType.FRONT, LAST_INDEX, degrees);
            }

            CubePiece cubePiece = cube.getCubePiece(2, 2, 0);
            switch (cubePiece.getCoordinate(Color.WHITE)) {
                case Y -> cube.f().d().d().fr().dr().f().d().fr();
                case X -> cube.f().d().fr();
                case Z -> cube.d().f().dr().fr();
            }
            cube.y();
        }

        // Средние боковые кубики
        workPlace = new CoordinateCube(2, 1, 0);
        //Спускает все нужные кубики на нижнюю грань
        for (int i = 0; i < 4; i++) {
            CubePiece cubePiece = cube.getCubePiece(workPlace.x, workPlace.y, workPlace.z);
            if (!cubePiece.hasColor(Color.YELLOW)) {
                while (!cube.getCubePiece(1, 2, 0).hasColor(Color.YELLOW)) {
                    cube.d();
                }
                cube.raiseDiagonallyToTheRight();
            }
            cube.y();
        }

        //Расставляет по своим местам
        for (int i = 0; i < 4; i++) {
            CubePiece frontNeighbour = cube.getCubePiece(1, 1, 0);
            CubePiece rightNeighbour = cube.getCubePiece(2, 1, 1);

            while (!cube.getCubePiece(1, 2, 0).hasAllThisColors(frontNeighbour.getColor(Coordinates.X),
                    rightNeighbour.getColor(Coordinates.Z))) {
                cube.d();
            }
            if (cube.getCubePiece(1, 2, 0).getColor(Coordinates.X) == frontNeighbour.getColor(Coordinates.X))
                cube.raiseDiagonallyToTheRight();
            else cube.d().yr().raiseDiagonallyToTheLeft().y();

            cube.y();
        }
    }

    private void moveDownEdgeFromTop(SpeedRubiesCube3x3x3 cube, CoordinateCube coordinate) {
        if (coordinate.x == 0) {
            if (coordinate.z == 0) cube.lr().d().l();
            else cube.b().dr().dr().br();
        } else cube.br().dr().b();
    }

    @Override
    protected void orientTheLastLayer(SpeedRubiesCube3x3x3 cube) {
        cube.z().z();
        while (!cube.getCubePiece(1, 0, 0).hasColor(Color.GREEN)) {
            cube.u();
        }
        cube.y();

        CoordinateCube workPlace = new CoordinateCube(1, 0, 0);
        for (int i = 0; i < 2; i++) {
            CubePiece cubePiece = cube.getCubePiece(0, 0, 1);
            CubePiece frontNeighbour = cube.getCubePiece(1, 1, 0);

            if (cube.getCubePiece(workPlace.x, workPlace.y, workPlace.z)
                    .hasColor(frontNeighbour.getColor(Coordinates.X))) {
                cube.y();
                continue;
            }
            if (cubePiece.hasColor(frontNeighbour.getColor(Coordinates.X))) {
                cube.swapDiagonallyLeftOnTop().y();
            } else {
                cube.y();
                frontNeighbour = cube.getCubePiece(1, 1, 0);

                if (cube.getCubePiece(workPlace.x, workPlace.y, workPlace.z)
                        .hasColor(frontNeighbour.getColor(Coordinates.X))) {
                    cube.swapDiagonallyRightOnTop();
                }
                cube.swapDiagonallyLeftOnTop().swapDiagonallyRightOnTop();
                break;
            }
        }
        while (!cube.getCubePiece(1, 1, 0).hasColor(Color.GREEN)) {
            cube.y();
        }

        for (int i = 0; i < 4; i++) {
            if (cube.getCubePiece(workPlace.x, workPlace.y, workPlace.z)
                    .getCoordinate(Color.YELLOW) != Coordinates.Y) {
                cube.flipOver();
            }
            cube.u();
        }
    }

    @Override
    protected void permuteTheLastLayer(SpeedRubiesCube3x3x3 cube) {

        CoordinateCube workPlace = new CoordinateCube(2, 0, 0);
        CubePiece frontNeighbour = cube.getCubePiece(1, 1, 0);
        CubePiece rightNeighbour = cube.getCubePiece(2, 1, 1);

        // Расставляет на своих местах
        for (int i = 0; i < 4; i++) {
            if (cube.getCubePiece(workPlace.x, workPlace.y, workPlace.z).hasAllThisColors(
                    Color.YELLOW,
                    frontNeighbour.getColor(Coordinates.X),
                    rightNeighbour.getColor(Coordinates.Z))) {
                break;
            }
            if (i == 2) cube.rotateThreeCubeCounterClockwise();
            else cube.rotateThreeCubeClockwise();
        }
        cube.y().y();
        frontNeighbour = cube.getCubePiece(1, 1, 0);
        rightNeighbour = cube.getCubePiece(2, 1, 1);
        while (!cube.getCubePiece(workPlace.x, workPlace.y, workPlace.z).hasAllThisColors(
                Color.YELLOW,
                frontNeighbour.getColor(Coordinates.X),
                rightNeighbour.getColor(Coordinates.Z))) {
            cube.rotateThreeCubeClockwise();
        }

        // Переворачивает
        for (int i = 0; i < 4; i++) {
            switch (cube.getCubePiece(workPlace.x, workPlace.y, workPlace.z).getCoordinate(Color.YELLOW)) {
                default -> {
                }
                case X -> cube.rotateCubeClockwise();
                case Z -> cube.rotateCubeCounterClockwise();
            }
            cube.u();
        }

        cube.y().y();
        cube.z().z();
    }

    private static class CoordinateCube {
        private int x;
        private int y;
        private int z;

        public CoordinateCube(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public CoordinateCube(int[] coords) {
            if (coords.length != 3)
                throw new IllegalArgumentException("Coordinates length must be = 3");

            this.x = coords[0];
            this.y = coords[1];
            this.z = coords[2];
        }

        protected void set(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CoordinateCube that = (CoordinateCube) o;
            return x == that.x && y == that.y && z == that.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }
}
