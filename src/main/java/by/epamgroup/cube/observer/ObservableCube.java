package by.epamgroup.cube.observer;

import by.epamgroup.cube.shape.Cube;

public interface ObservableCube {

    void addObserver(Observer shapeObserver);
    void deleteObserver(Observer shapeObserver);
    void notifyAll(Cube cube);
}
