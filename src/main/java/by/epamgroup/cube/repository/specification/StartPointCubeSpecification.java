package by.epamgroup.cube.repository.specification;

import by.epamgroup.cube.shape.Cube;
import by.epamgroup.cube.shape.Point;

public class StartPointCubeSpecification implements Specification{
    private Point point;

    public StartPointCubeSpecification(Point point) {
        this.point = point;
    }

    @Override
    public boolean specify(Cube cube) {
        return cube.getStartPoint().equals(point);
    }
}
