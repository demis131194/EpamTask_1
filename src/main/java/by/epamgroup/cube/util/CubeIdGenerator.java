package by.epamgroup.cube.util;

public class CubeIdGenerator {
    private static long id;

    private CubeIdGenerator() {}

    public static long generateId() {
        return ++id;
    }
}
