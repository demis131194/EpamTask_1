package by.epamgroup.cube.shape;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Point implements Comparable<Point>{
    private static Logger logger = LogManager.getLogger();
    private double x;
    private double y;
    private double z;
    private long id;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        id = hashCode();
    }

    public Point(double x, double y) {
        this(x, y, 0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public long getId() {
        return id;
    }

    @Override
    public int compareTo(Point o) {
        int result = Double.compare(o.x, this.x);
        if (result == 0) {
            result = Double.compare(o.y, this.y);
            if (result == 0) {
                result = Double.compare(o.z, this.z);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0 &&
                Double.compare(point.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) + Double.hashCode(y) + Double.hashCode(z);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Point{");
        sb.append("id=").append(id);
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", z=").append(z);
        sb.append('}');
        return sb.toString();
    }
}
