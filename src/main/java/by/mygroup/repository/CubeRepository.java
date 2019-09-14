package by.mygroup.repository;

import by.mygroup.repository.specification.Specification;
import by.mygroup.shape.cube.Cube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CubeRepository implements Repository {
    private Map<Long, Cube> repository = new HashMap<>();

    @Override
    public void add(Cube cube) {
        repository.putIfAbsent(cube.getId(), cube);
    }

    @Override
    public void remove(Cube cube) {
        repository.remove(cube.getId());
    }

    @Override
    public void update(Cube cube) {
        repository.put(cube.getId(), cube);
    }

    @Override
    public List<Cube> selectAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public List<Cube> query(Specification spec) {
        return repository.values()
                .stream()
                .filter(spec::specify)
                .collect(Collectors.toList());
    }
}
