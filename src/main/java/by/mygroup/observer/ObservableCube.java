package by.mygroup.observer;

import by.mygroup.shape.cube.Cube;

public interface ObservableCube {

    void addObserver(CubeObserver shapeObserver);
    void deleteObserver(CubeObserver shapeObserver);
    void notifyAll(Cube cube);
}
