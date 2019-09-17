package by.epamgroup.cube.parser;

import by.epamgroup.cube.exception.CubeParseException;
import by.epamgroup.cube.shape.CubeArgumentContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epamgroup.cube.shape.Point;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeParser {
    private static final Logger logger = LogManager.getLogger();

    private static final String STRING_DOUBLE = "[+-]?(\\d+[.])?\\d+";
    private static final String STRING_POINT = String.format("%1$s\\s%1$s\\s%1$s", STRING_DOUBLE);
    private static final String STRING_CUBE_EDGE = String.format("(?<startPoint>%s)\\s(?<edge>%s)", STRING_POINT, STRING_DOUBLE);
    private static final String STRING_CUBE_ID_EDGE = String.format("(?<id>\\d+)\\s%s", STRING_CUBE_EDGE);

    private static final Pattern PATTERN_CUBE_ID_EDGE = Pattern.compile(STRING_CUBE_ID_EDGE);

    private CubeParser() {
    }

    public static CubeArgumentContainer parse(String arguments) throws CubeParseException {
        CubeArgumentContainer container;
        long id;
        Point startPoint;
        double edge;

        Matcher matcher = PATTERN_CUBE_ID_EDGE.matcher(arguments);
        if (matcher.matches()) {
            id = parseID(matcher);
            startPoint = parseStartPoint(matcher);
            edge = parseEdge(matcher);
        } else {
            throw new CubeParseException("Incorrect input line.");
        }
        logger.info("Parsing arguments completed. Line = " + arguments);
        return new CubeArgumentContainer(id, startPoint, edge);
    }

    private static double parseEdge(Matcher matcher) {
        return Double.parseDouble(matcher.group("edge"));
    }

    private static long parseID(Matcher matcher) {
        return Long.parseLong(matcher.group("id"));
    }

    private static Point parseStartPoint(Matcher matcher) {
        String pointsSTR = matcher.group("startPoint");
        String[] points = pointsSTR.split("\\s");
        return new Point(Double.parseDouble(points[0]), Double.parseDouble(points[1]), Double.parseDouble(points[2]));
    }
}
