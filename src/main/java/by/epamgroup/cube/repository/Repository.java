package by.epamgroup.cube.repository;

import by.epamgroup.cube.repository.specification.Specification;
import by.epamgroup.cube.shape.Cube;

import java.util.List;

public interface Repository {
    void createIfAbsent(Cube cube);
    void delete(Cube cube);
    void deleteAll();
    void update(Cube cube);
    Cube findCubeById(long id);
    List<Cube> selectAll();
    List<Cube> query(Specification spec);
}
