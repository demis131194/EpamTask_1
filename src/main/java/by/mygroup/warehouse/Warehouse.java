package by.mygroup.warehouse;

import by.mygroup.handler.CoordinatePlane;
import by.mygroup.handler.CubeHandler;
import by.mygroup.observer.CubeObserver;
import by.mygroup.repository.CubeRepository;
import by.mygroup.repository.Repository;
import by.mygroup.shape.cube.Cube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Warehouse implements CubeObserver {
    private static final Logger logger = LogManager.getLogger();
    private static final Warehouse INSTANCE = new Warehouse();

    private final Map<Long, CubeWarehouse> warehouse = new HashMap<>();
    private final Repository repository = new CubeRepository();
    private Warehouse() {
    }

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public double getVolumeById(long id) {
        return warehouse.get(id).getVolume();
    }

    public double getSurfaceArea(long id) {
        return warehouse.get(id).getSurfaceArea();
    }

    public double getRatioByX(long id) {
        return warehouse.get(id).getSectionByPlane(CoordinatePlane.X);
    }

    public double getRatioByY(long id) {
        return warehouse.get(id).getSectionByPlane(CoordinatePlane.Y);
    }

    public Repository getRepository() {
        return repository;
    }

    @Override
    public void handleEvent(Cube cube) {
        logger.trace("Event, cube : " + cube);
        warehouse.putIfAbsent(cube.getId(), new CubeWarehouse());
        repository.addIfAbsent(cube);
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

        double getSurfaceArea() {
            return surfaceArea;
        }

        double getVolume() {
            return volume;
        }

        double getSectionByPlane(CoordinatePlane plane) {
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
