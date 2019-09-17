package by.epamgroup.cube.observer;

import by.epamgroup.cube.shape.Cube;

public interface Observer {
    void handleEvent(Cube cube);
}
