package by.epamgroup.cube.comparator;

import by.epamgroup.cube.shape.Cube;

import java.util.Comparator;

public class CubeZComparator implements Comparator<Cube> {

    @Override
    public int compare(Cube o1, Cube o2) {
        return Double.compare(o2.getStartPoint().getZ(), o1.getStartPoint().getZ());
    }
}
