package by.mygroup.repository.specification;

import by.mygroup.shape.cube.Cube;

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
