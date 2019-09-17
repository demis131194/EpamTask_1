package by.epamgroup.cube.shape;

import by.epamgroup.cube.exception.CubeException;
import by.epamgroup.cube.observer.CubeObserver;
import by.epamgroup.cube.observer.ObservableCube;
import by.epamgroup.cube.observer.Observer;
import by.epamgroup.cube.util.CubeIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Cube extends Shape implements ObservableCube {
    private static Logger logger = LogManager.getLogger();
    private double cubeEdge;
    private Map<CubePoint, Point> cubePoints = new HashMap<>();
    private List<by.epamgroup.cube.observer.Observer> observers = new LinkedList<>();

    {
        addObserver(new CubeObserver());
    }

    public Cube(Point startPoint, double cubeEdge) throws CubeException {
        super(startPoint);
        init(startPoint, cubeEdge);
        super.setId(CubeIdGenerator.generateId());
        notifyAll(this);
        logger.info("Create : " + this.toString());
    }

    public Cube(long id, Point startPoint, double cubeEdge) throws CubeException {
        super(startPoint);
        init(startPoint, cubeEdge);
        if (id <= 0) {
            throw new CubeException("Incorrect Id. Shout be > 0");
        }
        super.setId(id);
        notifyAll(this);
        logger.info("Create : " + this.toString());
    }

    public double getCubeEdge() {
        return cubeEdge;
    }

    public void setCubeEdge(double cubeEdge) {
        this.cubeEdge = cubeEdge;
        notifyAll(this);
    }

    @Override
    public Point getStartPoint() {
        return super.getStartPoint();
    }

    @Override
    public void setStartPoint(Point startPoint) {
        super.setStartPoint(startPoint);
        calculatePoints(super.getStartPoint(), cubeEdge);
        notifyAll(this);
    }

    public Map<CubePoint, Point> getCubePoints() {
        return cubePoints;
    }

    private void init(Point startPoint, double cubeEdge) throws CubeException {
        if (cubeEdge <= 0) {
            throw new CubeException("Length of cube edge must be > 0. Creating cube failed.");
        }
        this.cubeEdge = cubeEdge;
        calculatePoints(startPoint, cubeEdge);
    }

    @SuppressWarnings("all")
    private void calculatePoints(Point startPoint, double cubeEdge) {

        Point downLeft_1 = startPoint;
        BigDecimal startXWithEdge = new BigDecimal(startPoint.getX() + cubeEdge).setScale(5, RoundingMode.HALF_UP);
        BigDecimal startYWithEdge = new BigDecimal(startPoint.getY() + cubeEdge).setScale(5, RoundingMode.HALF_UP);
        BigDecimal startZWithEdge = new BigDecimal(startPoint.getZ() + cubeEdge).setScale(5, RoundingMode.HALF_UP);


        Point downLeft_2 = new Point(startPoint.getX(), startYWithEdge.doubleValue(), startPoint.getZ());
        Point downRight_1 = new Point(startXWithEdge.doubleValue(), startPoint.getY(), startPoint.getZ());
        Point downRight_2 = new Point(startXWithEdge.doubleValue(), startYWithEdge.doubleValue(), startPoint.getZ());

        Point topLeft_1 = new Point(startPoint.getX(), startPoint.getY(), startZWithEdge.doubleValue());
        Point topLeft_2 = new Point(startPoint.getX(), startYWithEdge.doubleValue(), startZWithEdge.doubleValue());
        Point topRight_1 = new Point(startXWithEdge.doubleValue(), startPoint.getY(), startZWithEdge.doubleValue());
        Point topRight_2 = new Point(startXWithEdge.doubleValue(), startYWithEdge.doubleValue(), startZWithEdge.doubleValue());

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
    public void addObserver(by.epamgroup.cube.observer.Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(by.epamgroup.cube.observer.Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAll(Cube cube) {
        for (Observer observer : observers) {
            observer.handleEvent(cube);
        }
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
        sb.append("cube Id=").append(super.getId());
        sb.append(", cubeEdge=").append(cubeEdge);
        sb.append(", cubePoints=").append(cubePoints);
        sb.append(", observers=").append(observers);
        sb.append('}');
        return sb.toString();
    }
}
