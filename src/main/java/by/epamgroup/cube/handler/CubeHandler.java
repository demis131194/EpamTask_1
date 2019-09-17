package by.epamgroup.cube.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epamgroup.cube.shape.Cube;
import by.epamgroup.cube.shape.CubePoint;
import by.epamgroup.cube.shape.Point;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;

public class CubeHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final int CALCULATION_SCALE = 5;

    private CubeHandler() {
    }

    public static double calculateSurfaceArea(Cube cube) {
        double edge = cube.getCubeEdge();
        BigDecimal area = new BigDecimal(edge*edge*6);
        area = area.setScale(CALCULATION_SCALE, RoundingMode.HALF_UP);
        logger.info(String.format("Calculate Surface Area of Cube %d, area = %f", cube.getId(), area));
        return area.doubleValue();
    }

    public static double calculateVolume(Cube cube) {
        double edge = cube.getCubeEdge();
        BigDecimal volume = new BigDecimal(edge*edge*edge);
        volume = volume.setScale(CALCULATION_SCALE, RoundingMode.HALF_UP);
        logger.info(String.format("Calculate Volume of Cube %d, volume = %f", cube.getId(), volume));
        return volume.doubleValue();
    }

    public static double calculateSectionByCoordinatePlane(Cube cube, CoordinatePlane coordinatePlane) {
        Map<CubePoint, Point> cubePoints = cube.getCubePoints();
        BigDecimal firstPoint;
        BigDecimal secondPoint;

        logger.info(String.format("Calculate Section By CoordinatePlane Cube id = %d, plane = %s", cube.getId(), coordinatePlane.toString()));
        switch (coordinatePlane) {
            case X:
                firstPoint = new BigDecimal(cubePoints.get(CubePoint.DOWN_LEFT_1).getY());
                secondPoint = new BigDecimal(cubePoints.get(CubePoint.DOWN_LEFT_2).getY());
                return calculateRatio(firstPoint, secondPoint);
            case Y:
                firstPoint = new BigDecimal(cubePoints.get(CubePoint.DOWN_LEFT_1).getX());
                secondPoint = new BigDecimal(cubePoints.get(CubePoint.DOWN_RIGHT_1).getX());
                return calculateRatio(firstPoint, secondPoint);
            default:
                throw new RuntimeException("Incorrect coordinate plane!");
        }
    }

    private static double calculateRatio(BigDecimal firstPoint, BigDecimal secondPoint) {
        if (firstPoint.add(secondPoint).abs().doubleValue() >= firstPoint.subtract(secondPoint).abs().doubleValue()) {
            logger.info("Cube not cut by plane.");
            return 0;
        } else {
            firstPoint = firstPoint.abs();
            secondPoint = secondPoint.abs();
            BigDecimal ratio1 = firstPoint.divide(secondPoint, CALCULATION_SCALE, RoundingMode.HALF_UP);
            BigDecimal ratio2 = secondPoint.divide(firstPoint, CALCULATION_SCALE, RoundingMode.HALF_UP);
            logger.info(String.format("Calculate ratio = %f", ratio1.max(ratio2)));
            return ratio1.max(ratio2).doubleValue();
        }
    }
}
