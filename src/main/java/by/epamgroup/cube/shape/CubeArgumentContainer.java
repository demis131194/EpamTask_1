package by.epamgroup.cube.shape;

import java.util.Objects;

public class CubeArgumentContainer {
    private long id;
    private Point startPoint;
    private double cubeEdge;

    public CubeArgumentContainer(long id, Point startPoint, double cubeEdge) {
        this.id = id;
        this.startPoint = startPoint;
        this.cubeEdge = cubeEdge;
    }

    public long getId() {
        return id;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public double getCubeEdge() {
        return cubeEdge;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setCubeEdge(double cubeEdge) {
        this.cubeEdge = cubeEdge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubeArgumentContainer that = (CubeArgumentContainer) o;
        return id == that.id &&
                Double.compare(that.cubeEdge, cubeEdge) == 0 &&
                startPoint.equals(that.startPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startPoint, cubeEdge);
    }
}
