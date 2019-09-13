package by.mygroup.warehouse;

import by.mygroup.handler.CoordinatePlane;
import by.mygroup.handler.CubeHandler;
import by.mygroup.observer.CubeObserver;
import by.mygroup.shape.cube.Cube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Warehouse implements CubeObserver {
    private static final Logger logger = LogManager.getLogger();
    private static final Warehouse INSTANCE = new Warehouse();

    private final Map<Long, CubeWarehouse> warehouse = new HashMap<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public CubeWarehouse getCubeWarehouseByID(long id) {
        return warehouse.get(id);
    }

    @Override
    public void handleEvent(Cube cube) {
        logger.trace("Event, cube : " + cube);
        warehouse.putIfAbsent(cube.getId(), new CubeWarehouse());
        CubeWarehouse cubeWarehouse = warehouse.get(cube.getId());

        cubeWarehouse.surfaceArea = CubeHandler.calculateSurfaceArea(cube);
        cubeWarehouse.volume = CubeHandler.calculateVolume(cube);
        for (CoordinatePlane coordinatePlane : CoordinatePlane.values()) {
            double ratio = CubeHandler.calculateSectionByCoordinatePlane(cube, coordinatePlane);
            cubeWarehouse.setSectionByPlane(coordinatePlane, ratio);
        }
        logger.trace("Event handel, warehouse : " + cubeWarehouse);
    }

    private static class CubeWarehouse {
        private double surfaceArea;
        private double volume;
        private Map<CoordinatePlane, Double> section = new HashMap<>();

        {
            for (CoordinatePlane coordinatePlane : CoordinatePlane.values()) {
                section.put(coordinatePlane, 0d);
            }
        }

        public double getSurfaceArea() {
            return surfaceArea;
        }

        public double getVolume() {
            return volume;
        }

        public double getSectionByPlane(CoordinatePlane plane) {
            return section.get(plane);
        }

        private void setSectionByPlane(CoordinatePlane plane, double ratio) {
            section.put(plane, ratio);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CubeWarehouse{");
            sb.append("surfaceArea=").append(surfaceArea);
            sb.append(", volume=").append(volume);
            sb.append(", section=").append(section);
            sb.append('}');
            return sb.toString();
        }
    }
}
