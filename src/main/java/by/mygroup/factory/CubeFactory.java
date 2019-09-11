package by.mygroup.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.mygroup.shape.Cube;
import by.mygroup.parser.CubeParser;

public class CubeFactory extends ShapeFactory {
    private static final Logger logger = LogManager.getLogger();

    public CubeFactory() {
        logger.info("Cube factory created.");
    }

    @Override
    public Cube create(String arguments) {
        CubeParser parser = CubeParser.getInstance();
        parser.parse(arguments);
        if (parser.getStartPoint() == null) {
            return new Cube(parser.getId(), parser.getPoints());
        } else {
            return new Cube(parser.getId(), parser.getStartPoint(), parser.getCubeEdge());
        }
    }
}
