package by.mygroup.observer;

import by.mygroup.shape.cube.Cube;
import by.mygroup.shape.cube.Shape;

public interface CubeObserver {
    void handleEvent(Cube cube);
}
