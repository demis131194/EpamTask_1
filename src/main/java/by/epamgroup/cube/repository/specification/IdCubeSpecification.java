package by.epamgroup.cube.repository.specification;

import by.epamgroup.cube.shape.Cube;

public class IdCubeSpecification implements Specification {
    private long id;

    public IdCubeSpecification(long id) {
        this.id = id;
    }

    @Override
    public boolean specify(Cube cube) {
        return cube.getId() == id;
    }
}
