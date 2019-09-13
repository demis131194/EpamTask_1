package by.mygroup.shape.cube;

import by.mygroup.exception.CubeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Cube extends Shape {
    private static Logger logger = LogManager.getLogger();
    private double cubeEdge;
    private Map<CubePoint, Point> cubePoints = new HashMap<>();

    public Cube(Point startPoint, double cubeEdge) throws CubeException {
        super(startPoint);
        this.cubeEdge = cubeEdge;
        calculatePoints(startPoint, cubeEdge);
        super.setId(CubeIdGenerator.generateID(this));
        logger.info("Create : " + this.toString());
    }

    public Cube(long id, Point startPoint, double cubeEdge) throws CubeException {
        this(startPoint, cubeEdge);
        checkIsCube(id);
    }

    public double getCubeEdge() {
        return cubeEdge;
    }

    public void setCubeEdge(double cubeEdge) {
        this.cubeEdge = cubeEdge;
    }

    public Map<CubePoint, Point> getCubePoints() {
        return cubePoints;
    }

    public void setCubePoints(Map<CubePoint, Point> cubePoints) {
        this.cubePoints = cubePoints;
    }

    private void checkIsCube(long id) throws CubeException {
        if (super.getId() != id) {
            throw new CubeException("This is not a cube!");
        }
    }

    @SuppressWarnings("all")
    private void calculatePoints(Point startPoint, double cubeEdge) throws CubeException {
        if (cubeEdge <= 0) {
            throw new CubeException("Length of cube edge must be > 0. Creating cube failed.");
        }

        Point downLeft_1 = startPoint;
        Point downLeft_2 = new Point(startPoint.getX(), startPoint.getY() + cubeEdge, startPoint.getZ());
        Point downRight_1 = new Point(startPoint.getX() + cubeEdge, startPoint.getY(), startPoint.getZ());
        Point downRight_2 = new Point(startPoint.getX() + cubeEdge, startPoint.getY() + cubeEdge, startPoint.getZ());

        Point topLeft_1 = new Point(startPoint.getX(), startPoint.getY(), startPoint.getZ() + cubeEdge);
        Point topLeft_2 = new Point(startPoint.getX(), startPoint.getY() + cubeEdge, startPoint.getZ() + cubeEdge);
        Point topRight_1 = new Point(startPoint.getX() + cubeEdge, startPoint.getY(), startPoint.getZ() + cubeEdge);
        Point topRight_2 = new Point(startPoint.getX() + cubeEdge, startPoint.getY() + cubeEdge, startPoint.getZ() + cubeEdge);

        cubePoints.put(CubePoint.DOWN_LEFT_1, downLeft_1);
        cubePoints.put(CubePoint.DOWN_LEFT_2, downLeft_2);
        cubePoints.put(CubePoint.DOWN_RIGHT_1, downRight_1);
        cubePoints.put(CubePoint.DOWN_RIGHT_2, downRight_2);
        cubePoints.put(CubePoint.TOP_LEFT_1, topLeft_1);
        cubePoints.put(CubePoint.TOP_LEFT_2, topLeft_2);
        cubePoints.put(CubePoint.TOP_RIGHT_1, topRight_1);
        cubePoints.put(CubePoint.TOP_RIGHT_2, topRight_2);
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
        return (int) super.getId();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cube{");
        sb.append("cubeEdge=").append(cubeEdge);
        sb.append(", cubePoints=").append(cubePoints);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Cube cube = new Cube(new Point(1, 1), 3.2);
    }
}
