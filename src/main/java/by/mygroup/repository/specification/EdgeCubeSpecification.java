package by.mygroup.repository.specification;

import by.mygroup.shape.cube.Cube;

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
