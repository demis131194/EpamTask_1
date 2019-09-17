package by.epamgroup.cube.factory;

import by.epamgroup.cube.exception.CubeException;
import by.epamgroup.cube.shape.CubeArgumentContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epamgroup.cube.shape.Cube;

public class CubeFactory {
    private static final Logger logger = LogManager.getLogger();

    public CubeFactory() {
        logger.info("Cube factory created.");
    }

    public Cube create(CubeArgumentContainer container) throws CubeException {
        return new Cube(container.getId(), container.getStartPoint(), container.getCubeEdge());
    }

}
