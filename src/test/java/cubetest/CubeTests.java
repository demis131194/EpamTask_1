package cubetest;

import by.mygroup.exception.CubeException;
import by.mygroup.exception.CubeParseException;
import by.mygroup.exception.CubeReaderException;
import by.mygroup.shape.cube.CubeArgumentContainer;
import cubetest.util.CubeReaderForTest;
import org.testng.Assert;
import org.testng.annotations.*;
import by.mygroup.shape.cube.Cube;
import by.mygroup.shape.cube.Point;
import by.mygroup.factory.CubeFactory;
import by.mygroup.parser.CubeParser;

import java.util.ArrayList;
import java.util.List;

@Test
public class CubeTests {
    private final Point startPointForCube_1 = new Point(-4.2, 1.3, 4.2);
    private final double cube_edge_1 = 7;
    private final long cube_id_1 = 67108956L;
    private final List<String> linesFromCubeFile = new ArrayList<>();

    @BeforeTest
    public void init() {
        linesFromCubeFile.add("67108956 -4.2 1.3 4.2 7");
        linesFromCubeFile.add("324 23.2 54.2 1 12");
        linesFromCubeFile.add("67108956 -4.2 1.3 4.2 -2");
        linesFromCubeFile.add("324 23.2 t4.2 1 12");
    }

    @Test
    public void readCubeFileTest() throws CubeReaderException {
        List<String> readingLinesFromCube = CubeReaderForTest.readLines();
        Assert.assertEquals(readingLinesFromCube, linesFromCubeFile);
    }

    @Test
    public void parseArgumentsTest() throws CubeParseException {
        CubeArgumentContainer container = CubeParser.parse(linesFromCubeFile.get(0));
        Assert.assertEquals(container.getStartPoint(), startPointForCube_1);
        Assert.assertEquals(container.getCubeEdge(), cube_edge_1);
        Assert.assertEquals(container.getCubeEdge(), cube_edge_1);
        Assert.assertEquals(container.getId(), cube_id_1);
    }

    @Test
    public void cubeCreateTest() throws CubeException, CubeParseException {
        CubeFactory cubeFactory = new CubeFactory();
        Cube expectedCube = new Cube(startPointForCube_1, cube_edge_1);
        Cube cube = cubeFactory.create(CubeParser.parse(linesFromCubeFile.get(0)));
        Assert.assertEquals(cube, expectedCube);
    }

    @Test(expectedExceptions = CubeException.class)
    public void cubeCreateTestLengthOfCubeException() throws CubeParseException, CubeException {
        CubeFactory cubeFactory = new CubeFactory();
        Cube expectedCube = new Cube(startPointForCube_1, cube_edge_1);
        Cube cube = cubeFactory.create(CubeParser.parse(linesFromCubeFile.get(2)));
    }
}
