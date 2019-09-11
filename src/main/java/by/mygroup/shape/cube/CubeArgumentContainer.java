package by.mygroup.shape.cube;

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
}
