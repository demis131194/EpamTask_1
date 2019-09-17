package cubetest;

import by.epamgroup.cube.exception.CubeException;
import by.epamgroup.cube.handler.CoordinatePlane;
import by.epamgroup.cube.shape.Cube;
import by.epamgroup.cube.shape.Point;
import by.epamgroup.cube.warehouse.Warehouse;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class WarehouseTests {
    private Warehouse warehouse;
    private final Point START_POINT_1 = new Point(-4.2, 1.3, 4.2);
    private final double CUBE_EDGE_1 = 7;
    private final Point START_POINT_2 = new Point(34.2, -4, 3.5);
    private final double CUBE_EDGE_2 = 3.2;

    @BeforeTest
    public void init() {
        warehouse = Warehouse.getInstance();
        warehouse.clean();
    }

    public void addWarehouseTest() throws CubeException {
        Cube cube = new Cube(START_POINT_1,CUBE_EDGE_1);
        Warehouse.CubeParameters actualCubeParameters = warehouse.getCubeParametersById(cube.getId());
        Assert.assertNotNull(actualCubeParameters);
        warehouse.clean();
    }

    public void updateWarehouseTest() throws CubeException {
        Cube cube = new Cube(START_POINT_1,CUBE_EDGE_1);
        Warehouse.CubeParameters expectedCubeParameters = new Warehouse.CubeParameters();
        expectedCubeParameters.setSurfaceArea(61.44d);
        expectedCubeParameters.setVolume(32.768d);
        expectedCubeParameters.setSectionByPlane(CoordinatePlane.X, 1.33333d);
        expectedCubeParameters.setSectionByPlane(CoordinatePlane.Y, 0);

        cube.setStartPoint(START_POINT_2);
        cube.setCubeEdge(CUBE_EDGE_2);

        Warehouse.CubeParameters actualCubeParameters = warehouse.getCubeParametersById(cube.getId());
        Assert.assertEquals(actualCubeParameters, expectedCubeParameters);
        warehouse.clean();
    }

}
