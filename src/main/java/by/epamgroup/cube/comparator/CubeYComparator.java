package by.epamgroup.cube.comparator;

import by.epamgroup.cube.shape.Cube;

import java.util.Comparator;

public class CubeYComparator implements Comparator<Cube> {

    @Override
    public int compare(Cube o1, Cube o2) {
        return Double.compare(o1.getStartPoint().getY(), o2.getStartPoint().getY());
    }
}
