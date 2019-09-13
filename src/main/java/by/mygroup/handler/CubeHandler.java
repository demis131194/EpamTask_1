package by.mygroup.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.mygroup.shape.cube.Cube;
import by.mygroup.shape.cube.CubePoint;
import by.mygroup.shape.cube.Point;

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
        logger.info(String.format("Calculate Volume of Cube %d, volume = %f", cube.getId(), volume));
        return volume;
    }

    public static double calculateSectionByCoordinatePlane(Cube cube, CoordinatePlane coordinatePlane) {
        Map<CubePoint, Point> cubePoints = cube.getCubePoints();
        double firstPoint;
        double secondPoint;

        logger.info(String.format("Calculate Section By CoordinatePlane Cube id = %d, plane = %s", cube.getId(), coordinatePlane.toString()));
        switch (coordinatePlane) {
            case X:
                firstPoint = cubePoints.get(CubePoint.DOWN_LEFT_1).getY();
                secondPoint = cubePoints.get(CubePoint.DOWN_LEFT_2).getY();
                return calculateRatio(firstPoint, secondPoint);
            case Y:
                firstPoint = cubePoints.get((CubePoint.DOWN_LEFT_1)).getX();
                secondPoint = cubePoints.get(CubePoint.DOWN_RIGHT_1).getX();
                return calculateRatio(firstPoint, secondPoint);
            default:
                throw new RuntimeException("Incorrect coordinate plane!");
        }
    }

    private static double calculateRatio(double firstPoint, double secondPoint) {
        if (Math.abs(firstPoint + secondPoint) >= Math.abs(firstPoint - secondPoint)) {
            logger.info("Cube not cut by plane.");
            return 0;
        } else {
            firstPoint = Math.abs(firstPoint);
            secondPoint = Math.abs(secondPoint);
            float ratio1 = (float) (firstPoint / secondPoint);
            float ratio2 = (float) (secondPoint / firstPoint);
            logger.info(String.format("Calculate ratio = %f", Math.max(ratio1, ratio2)));
            return Math.max(ratio1, ratio2);
        }
    }
}
