package cube;

import by.mygroup.exception.CubeException;
import by.mygroup.handler.CoordinatePlane;
import by.mygroup.handler.CubeHandler;
import by.mygroup.shape.cube.Cube;
import by.mygroup.shape.cube.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class CubeHandlerTests {
    private Cube cube;

    @BeforeTest
    public void init() throws CubeException {
        cube = new Cube(new Point(-4.2, 1.3, 4.2), 7);
    }

    @Test
    public void calculateSurfaceAreaTest() {
        double surfaceArea = CubeHandler.calculateSurfaceArea(cube);
        double expectedSurfaceArea = 294.0;
        Assert.assertEquals(surfaceArea, expectedSurfaceArea);
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
        System.out.println(cube);
        double expectedRatioByXPlane = 0;
        Assert.assertEquals(ratioX, expectedRatioByXPlane);
        double expectedRatioByYPlane = 1.5;
        Assert.assertEquals(ratioY, expectedRatioByYPlane);
    }
}
