package main.java;

import java.util.stream.Stream;

public class StringCalculator {
    public static int add(String s) {
        String delimiter = ",|\n";
        if (s.startsWith("//")) {
            delimiter = s.substring(2, 3);
            return addByInput(delimiter, s.substring(4));
        }
        return addByInput(delimiter, s);
    }

    private static int addByInput(String delimiter, String numberString) {
        if (numberString.isEmpty()) {
            return 0;
        }
        return Stream.of(numberString.split(delimiter))
                .map(Integer::valueOf)
                .reduce(0, (accumulator, item) -> accumulator + item);
    }
}
