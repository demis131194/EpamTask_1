package cubetest;

import by.epamgroup.cube.exception.CubeException;
import by.epamgroup.cube.handler.CoordinatePlane;
import by.epamgroup.cube.handler.CubeHandler;
import by.epamgroup.cube.shape.Cube;
import by.epamgroup.cube.shape.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class CubeHandlerTests {
    private Cube cube;
    private final Point START_POINT = new Point(-4.2, 1.3, 4.2);
    private final double CUBE_EDGE = 7;


    @BeforeTest
    public void init() throws CubeException {
        cube = new Cube(START_POINT, CUBE_EDGE);
    }

    @Test
    public void calculateSurfaceAreaTest() {
        double actualSurfaceArea = CubeHandler.calculateSurfaceArea(cube);
        double expectedSurfaceArea = 294.0;
        Assert.assertEquals(actualSurfaceArea, expectedSurfaceArea);
    }

    @Test
    public void calculateVolumeTest() {
        double volume = CubeHandler.calculateVolume(cube);
        double expectedVolume = 343.0;
        Assert.assertEquals(volume, expectedVolume);
    }

    @Test
    public void calculateRatioTest() {
        double ratioX = CubeHandler.calculateSectionByCoordinatePlane(cube, CoordinatePlane.X);
        double ratioY = CubeHandler.calculateSectionByCoordinatePlane(cube, CoordinatePlane.Y);
        double expectedRatioByXPlane = 0;
        double expectedRatioByYPlane = 1.5;

        Assert.assertEquals(ratioX, expectedRatioByXPlane);
        Assert.assertEquals(ratioY, expectedRatioByYPlane);
    }
}
