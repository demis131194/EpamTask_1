package by.mygroup.shape.cube;

import java.util.concurrent.atomic.AtomicInteger;

public class CubeIdGenerator {
    private static AtomicInteger id = new AtomicInteger(1);
    private CubeIdGenerator() {}

    public static long generateID() {
        return id.getAndIncrement();
    }
}
