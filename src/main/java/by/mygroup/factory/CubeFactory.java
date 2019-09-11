package by.mygroup.factory;

import by.mygroup.exception.CubeException;
import by.mygroup.shape.cube.CubeArgumentContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.mygroup.shape.cube.Cube;
import by.mygroup.parser.CubeParser;

public class CubeFactory {
    private static final Logger logger = LogManager.getLogger();

    public CubeFactory() {
        logger.info("Cube factory created.");
    }

    public Cube create(CubeArgumentContainer container) throws CubeException {
        return new Cube(container.getId(), container.getStartPoint(), container.getCubeEdge());
    }

}
