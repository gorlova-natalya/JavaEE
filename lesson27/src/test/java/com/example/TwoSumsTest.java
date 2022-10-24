package com.example;

import com.example.lesson27.TwoSums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class TwoSumsTest {

    private final TwoSums twoSums = new TwoSums();

    @ParameterizedTest
    @MethodSource("provideTestValues")
    public void doTest1(int[] incomingNumbers, int desiredValue, int[] expectedResult) {
        int[] receivedResult = twoSums.calculate(incomingNumbers, desiredValue);
        Assertions.assertArrayEquals(expectedResult, receivedResult);
    }

    private static Stream<Arguments> provideTestValues() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, 4, new int[]{0, 2}),
                Arguments.of(new int[]{1234, 5678, 9012}, 14690, new int[]{1, 2}),
                Arguments.of(null, 5, null),
                Arguments.of(new int[]{1234, 5678, 9012}, 5, null)
        );
    }
}
