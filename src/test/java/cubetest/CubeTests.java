package cubetest;

import by.epamgroup.cube.exception.CubeException;
import by.epamgroup.cube.exception.CubeParseException;
import by.epamgroup.cube.exception.CubeReaderException;
import by.epamgroup.cube.reader.CubeReader;
import by.epamgroup.cube.shape.CubeArgumentContainer;
import org.testng.Assert;
import org.testng.annotations.*;
import by.epamgroup.cube.shape.Cube;
import by.epamgroup.cube.shape.Point;
import by.epamgroup.cube.factory.CubeFactory;
import by.epamgroup.cube.parser.CubeParser;

import java.util.ArrayList;
import java.util.List;

@Test
public class CubeTests {
    private final Point START_POINT = new Point(-4.2, 1.3, 4.2);
    private final double CUBE_EDGE = 7;
    private final double CUBE_EDGE_WRONG = -1;
    private final long CUBE_ID = 120L;
    private final long CUBE_ID_WRONG = -2L;

    @Test
    public void readCubeFileTest() throws CubeReaderException {
        List<String> linesFromCubeFile = new ArrayList<>();

        linesFromCubeFile.add("120 -4.2 1.3 4.2 7");
        linesFromCubeFile.add("324 23.2 54.2 1 12");
        linesFromCubeFile.add("120 -4.2 1.3 4.2 -2");
        linesFromCubeFile.add("324 23.2 t4.2 1 12");

        List<String> readingLinesFromCube = CubeReader.readLines();
        Assert.assertEquals(readingLinesFromCube, linesFromCubeFile);
    }

    @Test
    public void parseArgumentsTest() throws CubeParseException {
        CubeArgumentContainer actualContainer = CubeParser.parse("120 -4.2 1.3 4.2 7");
        CubeArgumentContainer expectedContainer = new CubeArgumentContainer(CUBE_ID, START_POINT, CUBE_EDGE);
        Assert.assertEquals(actualContainer, expectedContainer);
    }

    @Test(expectedExceptions = CubeParseException.class)
    public void parseWrongArgumentsTest1() throws CubeParseException {
        CubeArgumentContainer actualContainer = CubeParser.parse("120 -4.s2 1.3 4.2 7");
        CubeArgumentContainer expectedContainer = new CubeArgumentContainer(CUBE_ID, START_POINT, CUBE_EDGE);
        Assert.assertEquals(actualContainer, expectedContainer);
    }

    @Test(expectedExceptions = CubeParseException.class)
    public void parseWrongArgumentsTest2() throws CubeParseException {
        CubeArgumentContainer actualContainer = CubeParser.parse("120 1.3 4.2 7");
        CubeArgumentContainer expectedContainer = new CubeArgumentContainer(CUBE_ID, START_POINT, CUBE_EDGE);
        Assert.assertEquals(actualContainer, expectedContainer);
    }

    @Test
    public void cubeFactoryTest() throws CubeException {
        CubeFactory cubeFactory = new CubeFactory();
        Cube expectedCube = new Cube(CUBE_ID, START_POINT, CUBE_EDGE);
        Cube actualCube = cubeFactory.create(new CubeArgumentContainer(CUBE_ID, START_POINT, CUBE_EDGE));
        Assert.assertEquals(actualCube, expectedCube);
    }

    @Test(expectedExceptions = CubeException.class)
    public void cubeFactoryWithWrongArgumentsTest1() throws CubeException {
        CubeFactory cubeFactory = new CubeFactory();
        Cube expectedCube = new Cube(CUBE_ID_WRONG, START_POINT, CUBE_EDGE);
    }

    @Test(expectedExceptions = CubeException.class)
    public void cubeFactoryWithWrongArgumentsTest2() throws CubeException {
        CubeFactory cubeFactory = new CubeFactory();
        Cube expectedCube = new Cube(CUBE_ID, START_POINT, CUBE_EDGE_WRONG);
    }
}
