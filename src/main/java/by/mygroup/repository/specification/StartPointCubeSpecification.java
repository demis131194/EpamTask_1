package by.mygroup.repository.specification;

import by.mygroup.shape.cube.Cube;
import by.mygroup.shape.cube.Point;

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
