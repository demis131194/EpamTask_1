package by.mygroup.reader;

import by.mygroup.exception.CubeReaderException;
import by.mygroup.shape.cube.Cube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CubeReader {
    private static final Logger logger = LogManager.getLogger();
    private static final String PATH_CUBES = "../epam_task/src/main/resources/shapes/cubes.txt";


    public static List<String> readLines() throws CubeReaderException {
        logger.info("Starting reading lines from cube.txt");

        List<String> lines = null;
        try (Stream<String> stringStream = Files.lines(Paths.get(PATH_CUBES))) {
            lines = stringStream.collect(Collectors.toList());
        } catch (IOException e) {    // FIXME: 11.09.2019
            logger.error(e.getMessage());
            throw new CubeReaderException(e);
        }

        logger.info("Reading file cube.txt completed.");
        return lines;
    }
}
