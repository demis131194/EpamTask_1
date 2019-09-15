package cubetest;

import by.mygroup.comparator.cube.CubeIdComparator;
import by.mygroup.exception.CubeException;
import by.mygroup.shape.cube.Cube;
import by.mygroup.shape.cube.Point;
import by.mygroup.warehouse.Warehouse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Test
public class WarehouseTests {

    @Test
    public void addCubeInRepositoryTest() throws CubeException {
        Warehouse warehouse = Warehouse.getInstance();
        Comparator<Cube> cubeComparator = new CubeIdComparator();
        List<Cube> expectedCubes = new ArrayList<>();
        expectedCubes.add(new Cube(new Point(1,1), 5));
        expectedCubes.add(new Cube(new Point(4,-2), 3));
        expectedCubes.sort(cubeComparator);

        List<Cube> actualCubes = warehouse.getRepository().selectAll();
        actualCubes.sort(cubeComparator);
        Assert.assertEquals(expectedCubes, actualCubes);
    }

    @Test
    public void deleteCubeFromRepositoryTest() throws CubeException {
        Warehouse warehouse = Warehouse.getInstance();
        Comparator<Cube> cubeComparator = new CubeIdComparator();
        List<Cube> expectedCubes = new ArrayList<>();
        expectedCubes.add(new Cube(new Point(1,1), 5));
        expectedCubes.add(new Cube(new Point(4,-2), 3));
        expectedCubes.sort(cubeComparator);

        warehouse.getRepository().remove(expectedCubes.get(1));
        expectedCubes.remove(1);
        List<Cube> actualCubes = warehouse.getRepository().selectAll();
        actualCubes.sort(cubeComparator);
        Assert.assertEquals(expectedCubes, actualCubes);
    }

    public void updateSurfAreaAndVolumeCubeWarehouseTest() throws CubeException {
        Warehouse warehouse = Warehouse.getInstance();
        double surfaceAreaBefore = 150d;
        double volumeBefore = 125d;

        double surfaceAreaAfter = 384d;
        double volumeAfter = 512d;

        Cube cube = new Cube(new Point(1,1), 5);
        Assert.assertEquals(warehouse.getSurfaceArea(cube.getId()), surfaceAreaBefore);
        Assert.assertEquals(warehouse.getVolumeById(cube.getId()), volumeBefore);

        cube.setCubeEdge(8);
        Assert.assertEquals(warehouse.getSurfaceArea(cube.getId()), surfaceAreaAfter);
        Assert.assertEquals(warehouse.getVolumeById(cube.getId()), volumeAfter);
    }

    public void updateRatioCubeWarehouseTest() throws CubeException {
        Warehouse warehouse = Warehouse.getInstance();
        double ratioXBefore = 0;
        double ratioYBefore = 3d;
        double ratioXAfter = 1d;
        double ratioYAfter = 1d;

        Cube cube = new Cube(new Point(-1,1), 4);
        Assert.assertEquals(warehouse.getRatioByX(cube.getId()), ratioXBefore);
        Assert.assertEquals(warehouse.getRatioByY(cube.getId()), ratioYBefore);

        cube.setStartPoint(new Point(-2, -2));
        Assert.assertEquals(warehouse.getRatioByX(cube.getId()), ratioXAfter);
        Assert.assertEquals(warehouse.getRatioByY(cube.getId()), ratioYAfter);
    }
}
