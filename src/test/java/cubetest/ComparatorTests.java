package cubetest;

import by.epamgroup.cube.comparator.*;
import by.epamgroup.cube.exception.CubeException;
import by.epamgroup.cube.shape.Cube;
import by.epamgroup.cube.shape.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Test
public class ComparatorTests {
    private final long ID_1 = 1L;
    private final Point START_POINT_1 = new Point(-4.2, 1.3, 4.2);
    private final double CUBE_EDGE_1 = 7;
    private final long ID_2 = 2L;
    private final Point START_POINT_2 = new Point(34.2, -4, 3.5);
    private final double CUBE_EDGE_2 = 3.2;
    private final long ID_3 = 3L;
    private final Point START_POINT_3 = new Point(6.2, 0, -5.5);
    private final double CUBE_EDGE_3 = 12;
    private Cube cube1;
    private Cube cube2;
    private Cube cube3;
    private Cube cube4;

    @BeforeTest
    public void init() throws CubeException {
        cube1 = new Cube(ID_1, START_POINT_1, CUBE_EDGE_1);
        cube2 = new Cube(ID_2, START_POINT_2, CUBE_EDGE_2);
        cube3 = new Cube(ID_3, START_POINT_3, CUBE_EDGE_3);
        cube4 = new Cube(ID_3 + 1, new Point(START_POINT_3.getX(), START_POINT_3.getY() + 5, 0), CUBE_EDGE_3 - 2);
    }

    @Test
    public void idComparatorTest() {
        CubeIdComparator comparator = new CubeIdComparator();
        List<Cube> actualCubes = new ArrayList<>(Arrays.asList(cube2, cube3, cube1));
        List<Cube> expectedCubes = new ArrayList<>(Arrays.asList(cube1, cube2, cube3));

        actualCubes.sort(comparator);

        Assert.assertEquals(actualCubes, expectedCubes);
    }

    @Test
    public void edgeComparatorTest() {
        CubeEdgeComparator comparator = new CubeEdgeComparator();
        List<Cube> actualCubes = new ArrayList<>(Arrays.asList(cube2, cube3, cube1));
        List<Cube> expectedCubes = new ArrayList<>(Arrays.asList(cube2, cube1, cube3));

        actualCubes.sort(comparator);

        Assert.assertEquals(actualCubes, expectedCubes);
    }

    @Test
    public void startPointComparatorTest() {
        CubeStartPointComparator comparator = new CubeStartPointComparator();
        List<Cube> actualCubes = new ArrayList<>(Arrays.asList(cube2, cube3, cube1, cube4));
        List<Cube> expectedCubes = new ArrayList<>(Arrays.asList(cube1, cube3, cube4, cube2));

        actualCubes.sort(comparator);

        Assert.assertEquals(actualCubes, expectedCubes);
    }

    @Test
    public void XComparatorTest() {
        CubeXComparator comparator = new CubeXComparator();
        List<Cube> actualCubes = new ArrayList<>(Arrays.asList(cube2, cube3, cube1));
        List<Cube> expectedCubes = new ArrayList<>(Arrays.asList(cube1, cube3, cube2));

        actualCubes.sort(comparator);

        Assert.assertEquals(actualCubes, expectedCubes);
    }

    @Test
    public void YComparatorTest() {
        CubeYComparator comparator = new CubeYComparator();
        List<Cube> actualCubes = new ArrayList<>(Arrays.asList(cube1, cube3, cube2));
        List<Cube> expectedCubes = new ArrayList<>(Arrays.asList(cube2, cube3, cube1));

        actualCubes.sort(comparator);

        Assert.assertEquals(actualCubes, expectedCubes);
    }

    @Test
    public void ZComparatorTest() {
        CubeZComparator comparator = new CubeZComparator();
        List<Cube> actualCubes = new ArrayList<>(Arrays.asList(cube2, cube1, cube3));
        List<Cube> expectedCubes = new ArrayList<>(Arrays.asList(cube3, cube2, cube1));

        actualCubes.sort(comparator);

        Assert.assertEquals(actualCubes, expectedCubes);
    }
}
