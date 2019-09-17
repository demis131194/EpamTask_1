package by.epamgroup.cube.comparator;

import by.epamgroup.cube.shape.Cube;

import java.util.Comparator;

public class CubeStartPointComparator implements Comparator<Cube> {

    @Override
    public int compare(Cube o1, Cube o2) {
        return o1.getStartPoint().compareTo(o2.getStartPoint());
    }
}
