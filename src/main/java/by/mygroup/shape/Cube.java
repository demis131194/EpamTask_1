package by.mygroup.shape;

import by.mygroup.exception.CubeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Cube extends Shape {
    private static Logger logger = LogManager.getLogger();
    private double cubeEdge;
    private Map<CubePoints, Point> cubePoints = new HashMap<>();

    public Cube(Point startPoint, double cubeEdge) {
        super(startPoint);
        this.cubeEdge = cubeEdge;
        calculatePoints(startPoint, cubeEdge);
        calculateId(); // FIXME: 11.09.2019
        logger.info("Create cube: " + this.toString());
    }

    public Cube(long id, Point startPoint, double cubeEdge) {
        this(startPoint, cubeEdge);
        checkIsCube(id); // FIXME: 11.09.2019
    }

    public double getCubeEdge() {
        return cubeEdge;
    }

    public void setCubeEdge(double cubeEdge) {
        this.cubeEdge = cubeEdge;
    }

    public Map<CubePoints, Point> getCubePoints() {
        return cubePoints;
    }

    public void setCubePoints(Map<CubePoints, Point> cubePoints) {
        this.cubePoints = cubePoints;
    }

    private void checkIsCube(long id) {
        if (super.getId() != id) {
            logger.error("This is not a cube! Creating cube failed.");
            throw new CubeException("This is not a cube!");
        }
        logger.info("Create cube: " + this.toString());
    }

    private void getPointsFromList(List<Point> points) {
        if (points.size() != 8) {
            logger.error("This is not a cube! Creating cube failed.");
            throw new CubeException("Cube have 8 points!");
        }
        Iterator<Point> iterator = points.iterator();
        for (CubePoints cubePoint : CubePoints.values()) {
            if (iterator.hasNext()) {
                Point nextPoint = iterator.next();
                cubePoints.put(cubePoint, nextPoint);
            }
        }
    }

    private void calculateId() {  // FIXME: 11.09.2019 In new class generate ID
        cubePoints.values().forEach(point -> id += Math.round(point.getX() + point.getY() + point.getZ()));
        double x = cubePoints.get(CubePoints.DOWN_LEFT_1).getX();
        double y = cubePoints.get(CubePoints.DOWN_LEFT_1).getY();
        id += 1<<(Math.round(x*y*cubeEdge) % 64);
    }

    @SuppressWarnings("all")
    private void calculatePoints(Point startPoint, double cubeEdge) {
        if (cubeEdge <= 0) {
            logger.error("Length of cube edge must be > 0. Creating cube failed.");
            throw new CubeException("Length of cube edge must be > 0.");
        }

        Point downLeft_1 = startPoint;
        Point downLeft_2 = new Point(startPoint.getX(), startPoint.getY() + cubeEdge, startPoint.getZ());
        Point downRight_1 = new Point(startPoint.getX() + cubeEdge, startPoint.getY(), startPoint.getZ());
        Point downRight_2 = new Point(startPoint.getX() + cubeEdge, startPoint.getY() + cubeEdge, startPoint.getZ());

        Point topLeft_1 = new Point(startPoint.getX(), startPoint.getY(), startPoint.getZ() + cubeEdge);
        Point topLeft_2 = new Point(startPoint.getX(), startPoint.getY() + cubeEdge, startPoint.getZ() + cubeEdge);
        Point topRight_1 = new Point(startPoint.getX() + cubeEdge, startPoint.getY(), startPoint.getZ() + cubeEdge);
        Point topRight_2 = new Point(startPoint.getX() + cubeEdge, startPoint.getY() + cubeEdge, startPoint.getZ() + cubeEdge);

        cubePoints.put(CubePoints.DOWN_LEFT_1, downLeft_1);
        cubePoints.put(CubePoints.DOWN_LEFT_2, downLeft_2);
        cubePoints.put(CubePoints.DOWN_RIGHT_1, downRight_1);
        cubePoints.put(CubePoints.DOWN_RIGHT_2, downRight_2);
        cubePoints.put(CubePoints.TOP_LEFT_1, topLeft_1);
        cubePoints.put(CubePoints.TOP_LEFT_2, topLeft_2);
        cubePoints.put(CubePoints.TOP_RIGHT_1, topRight_1);
        cubePoints.put(CubePoints.TOP_RIGHT_2, topRight_2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return Double.compare(cube.cubeEdge, cubeEdge) == 0 &&
                cubePoints.equals(cube.cubePoints);
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cube{");
        sb.append("cubeEdge=").append(cubeEdge);
        sb.append(", cubePoints=").append(cubePoints);
        sb.append('}');
        return sb.toString();
    }
}
