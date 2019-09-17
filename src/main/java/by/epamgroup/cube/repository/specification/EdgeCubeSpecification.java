package by.epamgroup.cube.repository.specification;

import by.epamgroup.cube.shape.Cube;

public class EdgeCubeSpecification implements Specification {
    private double cubeEdge;

    public EdgeCubeSpecification(double cubeEdge) {
        this.cubeEdge = cubeEdge;
    }

    @Override
    public boolean specify(Cube cube) {
        return cube.getCubeEdge() == cubeEdge;
    }
}
