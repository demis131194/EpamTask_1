package by.epamgroup.cube.repository;

import by.epamgroup.cube.repository.specification.Specification;
import by.epamgroup.cube.shape.Cube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CubeRepository implements Repository {
    private static Logger logger = LogManager.getLogger();
    private static final Repository REPOSITORY = new CubeRepository();

    private Map<Long, Cube> repository = new HashMap<>();

    private CubeRepository() {
    }

    public static Repository getInstance() {
        return REPOSITORY;
    }

    @Override
    public void createIfAbsent(Cube cube) {
        repository.putIfAbsent(cube.getId(), cube);
    }

    @Override
    public void delete(Cube cube) {
        repository.remove(cube.getId());
    }

    @Override
    public void deleteAll() {
        repository.clear();
    }

    @Override
    public void update(Cube cube) {
        Cube updateCube = repository.get(cube.getId());
        if (updateCube != null) {
            repository.put(cube.getId(), cube);
        }
    }

    @Override
    public Cube findCubeById(long id) {
        return repository.get(id);
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
