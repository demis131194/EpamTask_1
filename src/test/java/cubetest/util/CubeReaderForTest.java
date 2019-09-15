package cubetest.util;

import by.mygroup.exception.CubeReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CubeReaderForTest {
    private static final Logger logger = LogManager.getLogger();
    private static final String PATH_CUBES = "src/test/java/resources/cubes.txt";


    public static List<String> readLines() throws CubeReaderException {
        logger.info("Starting reading lines from cube.txt");

        List<String> lines;
        try (Stream<String> stringStream = Files.lines(Paths.get(PATH_CUBES))) {
            lines = stringStream.collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e.toString());
            throw new CubeReaderException(e);
        }

        logger.info("Reading file cube.txt completed.");
        return lines;
    }
}
