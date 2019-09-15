package by.mygroup.comparator.cube;

import by.mygroup.shape.cube.Cube;

import java.util.Comparator;

public class CubeIdComparator implements Comparator<Cube> {

    @Override
    public int compare(Cube o1, Cube o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
}
