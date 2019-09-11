package by.mygroup.parser;

import by.mygroup.exception.IllegalArgumentsForCubeParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.mygroup.shape.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeParser {
    private static final Logger logger = LogManager.getLogger();

    private static final String STRING_DOUBLE = "[+-]?([0-9]+[.])?[0-9]+";
    private static final String STRING_POINT = String.format("%1$s\\s%1$s\\s%1$s", STRING_DOUBLE);
    private static final String STRING_CUBE_EDGE = String.format("(?<startPoint>%s)\\s(?<edge>%s)", STRING_POINT, STRING_DOUBLE);
    private static final String STRING_CUBE_ID_EDGE = String.format("(?<id>\\d+)\\s%s", STRING_CUBE_EDGE);
    private static final String STRING_POINTS = String.format("(?<points>(%1$s\\s){7}%1$s)", STRING_POINT);
    private static final String STRING_ID_POINTS = String.format("(?<id>\\d+)\\s%s", STRING_POINTS);

    private static final Pattern PATTERN_CUBE_ID_EDGE = Pattern.compile(STRING_CUBE_ID_EDGE);
    private static final Pattern PATTERN_CUBE_ID_POINTS = Pattern.compile(STRING_ID_POINTS);

    private static final CubeParser INSTANCE = new CubeParser();

    private Long id = null;
    private Point startPoint = null;
    private List<Point> points = null;
    private Double cubeEdge = null;

    private CubeParser() {
    }

    public static CubeParser getInstance() {
        return INSTANCE;
    }

    public long getId() {
        return id;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public List<Point> getPoints() {
        return points;
    }

    public double getCubeEdge() {
        return cubeEdge;
    }

    public void parse(String arguments) {
        logger.info("Start parsing. Line = " + arguments);
        Matcher matcher = null;
        if (arguments.matches(STRING_CUBE_ID_EDGE)) {
            matcher = PATTERN_CUBE_ID_EDGE.matcher(arguments);
            if (matcher.matches()) {
                parseID(matcher);
                parseStartPoint(matcher);
                parseEdge(matcher);
            }
        } else if (arguments.matches(STRING_CUBE_EDGE)) {
            matcher = PATTERN_CUBE_ID_POINTS.matcher(arguments);
            if (matcher.matches()) {
                parseID(matcher);
                parsePoints(matcher);
            }
        } else {
            logger.error("Parsing failed! Incorrect input line.");
            throw new IllegalArgumentsForCubeParseException("Incorrect input line.");
        }
        logger.info("Parsing arguments completed. Line = " + arguments);
    }

    private void parsePoints(Matcher matcher) {
        points = new ArrayList<>();
        String pointsSTR = matcher.group("points");
        String[] massPoints = pointsSTR.split("\\s");
        for (int i = 0; i < massPoints.length/3; i++) {
            points.add(new Point(Double.parseDouble(massPoints[3*i]), Double.parseDouble(massPoints[3*i + 1]), Double.parseDouble(massPoints[3*i + 2])));
        }
    }

    private void parseEdge(Matcher matcher) {
        cubeEdge = Double.parseDouble(matcher.group("edge"));
    }

    private void parseID(Matcher matcher) {
        id = Long.parseLong(matcher.group("id"));
    }

    private void parseStartPoint(Matcher matcher) {
        String pointsSTR = matcher.group("startPoint");
        String[] points = pointsSTR.split("\\s");
        startPoint = new Point(Double.parseDouble(points[0]), Double.parseDouble(points[1]), Double.parseDouble(points[2]));
    }
}
