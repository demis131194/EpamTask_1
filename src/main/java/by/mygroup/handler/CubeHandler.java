package by.mygroup.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.mygroup.shape.Cube;
import by.mygroup.shape.CubePoints;
import by.mygroup.shape.Point;

import java.util.Map;

public class CubeHandler {
    private static final Logger logger = LogManager.getLogger();

    private CubeHandler() {
    }

    public static double calculateSurfaceArea(Cube cube) {
        double edge = cube.getCubeEdge();
        double area = edge*edge*6;
        logger.info(String.format("Calculate Surface Area of Cube %d, area = %f", cube.getId(), area));
        return area;
    }

    public static double calculateVolume(Cube cube) {
        double edge = cube.getCubeEdge();
        double volume = edge*edge*edge;
        logger.info(String.format("Calculate Surface Area of Cube %d, area = %f", cube.getId(), volume));
        return volume;
    }

    public static double calculateSectionByCoordinatePlane(Cube cube, CoordinatePlane coordinatePlane) {
        Map<CubePoints, Point> cubePoints = cube.getCubePoints();
        double firstPoint;
        double secondPoint;

        logger.info(String.format("Calculate Section By CoordinatePlane Cube id = %d, plane = %s", cube.getId(), coordinatePlane.toString()));
        switch (coordinatePlane) {
            case X:
                firstPoint = cubePoints.get(CubePoints.DOWN_LEFT_1).getY();
                secondPoint = cubePoints.get(CubePoints.DOWN_LEFT_2).getY();
                return calculateRatio(firstPoint, secondPoint);
            case Y:
                firstPoint = cubePoints.get((CubePoints.DOWN_LEFT_1)).getX();
                secondPoint = cubePoints.get(CubePoints.DOWN_RIGHT_1).getX();
                return calculateRatio(firstPoint, secondPoint);
            default:
                throw new RuntimeException("Incorrect coordinate plane!");
        }
    }

    private static double calculateRatio(double firstPoint, double secondPoint) {
        if (Math.abs(firstPoint + secondPoint) <= Math.abs(firstPoint - secondPoint)) {
            logger.info("Calculation failed! Cube not cut by plane.");
            throw new RuntimeException("Cube not cut by plane");
        } else {
            firstPoint = Math.abs(firstPoint);
            secondPoint = Math.abs(secondPoint);
            double ratio = Math.max(firstPoint / secondPoint, secondPoint / firstPoint);
            logger.info(String.format("Calculate ratio = %f", ratio));
            return ratio;
        }
    }
}
