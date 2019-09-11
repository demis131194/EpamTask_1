package by.mygroup.shape.cube;

public class CubeIdGenerator {
    private CubeIdGenerator() {}

    public static long generateID(Cube cube) {
        long id = 0;
        for (Point point : cube.getCubePoints().values()) {
            id += Math.round(point.getX() + point.getY() + point.getZ());
        }
        double x = cube.getCubePoints().get(CubePoint.DOWN_LEFT_1).getX();
        double y = cube.getCubePoints().get(CubePoint.DOWN_LEFT_1).getY();
        id += 1<<(Math.round(x*y*cube.getCubeEdge())%64);
        return id;
    }
}
