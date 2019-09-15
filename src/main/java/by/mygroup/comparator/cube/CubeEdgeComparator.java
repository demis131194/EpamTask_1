package by.mygroup.comparator.cube;

import by.mygroup.shape.cube.Cube;

import java.util.Comparator;

public class CubeEdgeComparator implements Comparator<Cube> {

    @Override
    public int compare(Cube o1, Cube o2) {
        return Double.compare(o1.getCubeEdge(), o2.getCubeEdge());
    }
}
