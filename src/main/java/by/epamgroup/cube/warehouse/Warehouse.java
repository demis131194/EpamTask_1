package by.epamgroup.cube.warehouse;

import by.epamgroup.cube.handler.CoordinatePlane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Warehouse {
    private static final Logger logger = LogManager.getLogger();
    private static final Warehouse INSTANCE = new Warehouse();

    private final Map<Long, CubeParameters> warehouse = new HashMap<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public CubeParameters getCubeParametersById(long id) {
        return warehouse.get(id);
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

    public void  putIfAbsent(long id, CubeParameters cubeParameters) {
        warehouse.putIfAbsent(id, cubeParameters);
    }

    public void clean() {
        warehouse.clear();
    }

    public static class CubeParameters {
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

        public void setSurfaceArea(double surfaceArea) {
            this.surfaceArea = surfaceArea;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public void setSectionByPlane(CoordinatePlane plane, double ratio) {
            section.put(plane, ratio);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CubeParameters that = (CubeParameters) o;
            return Double.compare(that.surfaceArea, surfaceArea) == 0 &&
                    Double.compare(that.volume, volume) == 0 &&
                    Objects.equals(section, that.section);
        }

        @Override
        public int hashCode() {
            return Objects.hash(surfaceArea, volume, section);
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
