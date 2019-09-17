package cubetest;

import by.epamgroup.cube.exception.CubeException;
import by.epamgroup.cube.repository.CubeRepository;
import by.epamgroup.cube.repository.Repository;
import by.epamgroup.cube.repository.specification.EdgeCubeSpecification;
import by.epamgroup.cube.repository.specification.EdgeRangeSpecification;
import by.epamgroup.cube.repository.specification.IdCubeSpecification;
import by.epamgroup.cube.repository.specification.StartPointCubeSpecification;
import by.epamgroup.cube.shape.Cube;
import by.epamgroup.cube.shape.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Test
public class RepositoryTest {
    private Repository repository;
    private final Point START_POINT_1 = new Point(-4.2, 1.3, 4.2);
    private final double CUBE_EDGE_1 = 7;
    private final Point START_POINT_2 = new Point(34.2, -4, 3.5);
    private final double CUBE_EDGE_2 = 3.2;

    @BeforeTest
    public void clean() {
        repository = CubeRepository.getInstance();
        repository.deleteAll();
    }

    @Test
    public void createRepositoryTest() throws CubeException {
        new Cube(START_POINT_1, CUBE_EDGE_1);
        new Cube(START_POINT_2, CUBE_EDGE_2);
        Assert.assertEquals(repository.selectAll().size(), 2);
        repository.deleteAll();
    }

    @Test
    public void findByIdRepositoryTest() throws CubeException {
        long cubeId = 525L;
        Cube cube = new Cube(cubeId,START_POINT_1, CUBE_EDGE_1);
        Assert.assertEquals(repository.findCubeById(cubeId), cube);
        repository.deleteAll();
    }

    @Test
    public void updateRepositoryTest() throws CubeException {
        long cubeId = 1432L;
        Cube beforeCube = new Cube(cubeId, START_POINT_1, CUBE_EDGE_1);
        Cube afterCube = new Cube(cubeId, START_POINT_2, CUBE_EDGE_1);

        Assert.assertEquals(repository.findCubeById(cubeId), beforeCube);
        repository.update(afterCube);
        Assert.assertEquals(repository.findCubeById(cubeId), afterCube);
        repository.deleteAll();
    }

    @Test
    public void startPointSpecificationTest() throws CubeException {
        new Cube(1L, START_POINT_1, CUBE_EDGE_1);
        new Cube(2L, START_POINT_2, CUBE_EDGE_2);
        new Cube(3L, START_POINT_1, CUBE_EDGE_2);
        StartPointCubeSpecification specification = new StartPointCubeSpecification(START_POINT_1);

        List<Cube> cubes = repository.query(specification);
        Assert.assertEquals(cubes.size(), 2);
        repository.deleteAll();
    }

    @Test
    public void idSpecificationTest() throws CubeException {
        Cube expectedCube = new Cube(1L, START_POINT_1, CUBE_EDGE_1);
        new Cube(2L, START_POINT_2, CUBE_EDGE_2);
        new Cube(3L, START_POINT_1, CUBE_EDGE_2);
        IdCubeSpecification specification = new IdCubeSpecification(1L);

        List<Cube> expectedCubes = new ArrayList<>(Collections.singletonList(expectedCube));
        List<Cube> actualCubes = repository.query(specification);
        Assert.assertEquals(actualCubes, expectedCubes);
        repository.deleteAll();
    }

    @Test
    public void edgeSpecificationTest() throws CubeException {
        new Cube(1L, START_POINT_1, CUBE_EDGE_1);
        Cube expectedCube1 = new Cube(2L, START_POINT_2, CUBE_EDGE_2);
        Cube expectedCube2 = new Cube(3L, START_POINT_1, CUBE_EDGE_2);
        EdgeCubeSpecification specification = new EdgeCubeSpecification(CUBE_EDGE_2);

        List<Cube> expectedCubes = new ArrayList<>(Arrays.asList(expectedCube1, expectedCube2));
        List<Cube> actualCubes = repository.query(specification);
        Assert.assertEquals(actualCubes, expectedCubes);
        repository.deleteAll();
    }

    @Test
    public void edgeRangeSpecificationTest() throws CubeException {
        new Cube(1L, START_POINT_1, 1);
        Cube expectedCube1 = new Cube(2L, START_POINT_2, 4);
        new Cube(3L, START_POINT_1, 9);
        Cube expectedCube2 = new Cube(4L, START_POINT_1, 5.5);
        Cube expectedCube3 = new Cube(5L, START_POINT_2, 7);
        EdgeRangeSpecification specification = new EdgeRangeSpecification(CUBE_EDGE_2, CUBE_EDGE_1);

        List<Cube> expectedCubes = new ArrayList<>(Arrays.asList(expectedCube1, expectedCube2, expectedCube3));
        List<Cube> actualCubes = repository.query(specification);
        Assert.assertEquals(actualCubes, expectedCubes);
        repository.deleteAll();
    }

    @Test
    public void deleteRepositoryTest() throws CubeException {
        Cube cube = new Cube(1L, START_POINT_1, CUBE_EDGE_1);

        Assert.assertEquals(repository.findCubeById(1L), cube);
        repository.delete(cube);
        Assert.assertNull(repository.findCubeById(1L));
        repository.deleteAll();
    }

}
