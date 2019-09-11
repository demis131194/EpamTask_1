package cube;

import by.mygroup.exception.CubeException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import by.mygroup.shape.Cube;
import by.mygroup.shape.Point;
import by.mygroup.factory.CubeFactory;
import by.mygroup.parser.CubeParser;
import by.mygroup.reader.CubeReader;

import java.util.ArrayList;
import java.util.List;

@Test
public class CubeTests {
    private final Point startPointForCube_1 = new Point(-4.2, 1.3, 4.2);
    private final Point startPointForCube_2 = new Point(23.2, 54.2, 1);
    private final double cube_edge_1 = 7;
    private final double cube_edge_2 = 12;
    private final long cube_id_1 = 67108956L;
    private final long cube_id_2 = 324L;
    private final List<String> linesFromCubeFile = new ArrayList<>();

    @BeforeTest
    public void init() {
        linesFromCubeFile.add("67108956 -4.2 1.3 4.2 7");
        linesFromCubeFile.add("324 23.2 54.2 1 12");
        linesFromCubeFile.add("67108956 -4.2 1.3 4.2 -2");
        linesFromCubeFile.add("324 23.2 t4.2 1 12");
    }

    @Test
    public void readCubeFileTest() {
        List<String> readingLinesFromCube = CubeReader.readLines();
        Assert.assertEquals(readingLinesFromCube, linesFromCubeFile);
    }

    @Test
    public void parseArgumentsTest() {
        CubeParser cubeParser = CubeParser.getInstance();
        cubeParser.parse(linesFromCubeFile.get(0));
        Assert.assertEquals(cubeParser.getStartPoint(), startPointForCube_1);
        Assert.assertEquals(cubeParser.getCubeEdge(), cube_edge_1);
        Assert.assertEquals(cubeParser.getCubeEdge(), cube_edge_1);
        Assert.assertEquals(cubeParser.getId(), cube_id_1);
    }

    @Test
    public void cubeCreateTest() {
        CubeFactory cubeFactory = new CubeFactory();
        Cube expectedCube = new Cube(startPointForCube_1, cube_edge_1);
        Cube cube = cubeFactory.create(linesFromCubeFile.get(0));
        Assert.assertEquals(cube, expectedCube);
    }

    @Test(expectedExceptions = CubeException.class)
    public void cubeCreateTestNotCubeException() {
        CubeFactory cubeFactory = new CubeFactory();
        Cube expectedCube = new Cube(startPointForCube_1, cube_edge_1);
        Cube cube = cubeFactory.create(linesFromCubeFile.get(1));
    }

    @Test(expectedExceptions = CubeException.class)
    public void cubeCreateTestLengthOfCubeException() {
        CubeFactory cubeFactory = new CubeFactory();
        Cube expectedCube = new Cube(startPointForCube_1, cube_edge_1);
        Cube cube = cubeFactory.create(linesFromCubeFile.get(1));
    }
}
