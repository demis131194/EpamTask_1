package by.mygroup.repository;

import by.mygroup.repository.specification.Specification;
import by.mygroup.shape.cube.Cube;

import java.util.List;

public interface Repository {
    void add(Cube cube);
    void remove(Cube cube);
    void update(Cube cube);
    List<Cube> selectAll();
    List<Cube> query(Specification spec);
}
