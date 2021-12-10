package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BucketImpl implements Bucket {
    public static List<Pills> pills;
    static {
        pills = new ArrayList<>();
    }

    @Override
    public int sumOfBucket() {
        return pills.stream().flatMapToInt(x -> IntStream.of(x.cost)).sum();
    }
}
