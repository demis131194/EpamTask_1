package by.epamgroup.cube.observer;

import by.epamgroup.cube.handler.CoordinatePlane;
import by.epamgroup.cube.handler.CubeHandler;
import by.epamgroup.cube.repository.CubeRepository;
import by.epamgroup.cube.shape.Cube;
import by.epamgroup.cube.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CubeObserver implements Observer {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void handleEvent(Cube cube) {
        logger.trace("Event, cube : " + cube);

        Warehouse warehouse = Warehouse.getInstance();

        warehouse.putIfAbsent(cube.getId(), new Warehouse.CubeParameters());
        CubeRepository.getInstance().createIfAbsent(cube);

        Warehouse.CubeParameters cubeParameters = warehouse.getCubeParametersById(cube.getId());

        cubeParameters.setSurfaceArea(CubeHandler.calculateSurfaceArea(cube));
        cubeParameters.setVolume(CubeHandler.calculateVolume(cube));
        for (CoordinatePlane coordinatePlane : CoordinatePlane.values()) {
            double ratio = CubeHandler.calculateSectionByCoordinatePlane(cube, coordinatePlane);
            cubeParameters.setSectionByPlane(coordinatePlane, ratio);
        }

        logger.trace("Event handel, warehouse : " + cubeParameters);
    }
}
