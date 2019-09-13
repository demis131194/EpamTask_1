package by.mygroup.shape.cube;

import by.mygroup.exception.CubeException;

public abstract class Shape {
    private long id;
    private Point startPoint;

    public Shape(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Shape(long id, Point startPoint) {
        this.id = id;
        this.startPoint = startPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
