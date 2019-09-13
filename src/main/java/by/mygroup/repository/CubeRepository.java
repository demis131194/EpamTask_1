package by.mygroup.repository;

import by.mygroup.repository.specification.Specification;
import by.mygroup.shape.cube.Cube;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CubeRepository implements Repository {
    private Map<Long, Cube> repository = new HashMap<>();

    @Override
    public void add(Cube cube) {
    }

    @Override
    public void remove(Cube cube) {

    }

    @Override
    public void update(Cube cube) {

    }

    @Override
    public List<Cube> selectAll() {
        return null;
    }

    @Override
    public List<Cube> query(Specification spec) {
        return null;
    }
}
