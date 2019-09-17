package by.epamgroup.cube.repository.specification;

import by.epamgroup.cube.shape.Cube;

public class EdgeRangeSpecification implements Specification {
    private double minEdge;
    private double maxEdge;

    public EdgeRangeSpecification(double minEdge, double maxEdge) {
        this.minEdge = minEdge;
        this.maxEdge = maxEdge;
    }

    @Override
    public boolean specify(Cube cube) {
        double cubeEdge = cube.getCubeEdge();
        return (cubeEdge >= minEdge) && (cubeEdge <= maxEdge);
    }
}
