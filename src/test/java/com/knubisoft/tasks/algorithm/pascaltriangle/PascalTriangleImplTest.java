package com.knubisoft.tasks.algorithm.pascaltriangle;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PascalTriangleImplTest {

    private final PascalsTriangle triangle = new PascalTriangleImpl();

    @Test
    void calculateLineOfPascalsTriangle() {
        assertEquals(List.of(BigInteger.valueOf(1)), triangle.calculateLineOfPascalTriangle(1));
        assertEquals(List.of(BigInteger.valueOf(1), BigInteger.valueOf(1)), triangle.calculateLineOfPascalTriangle(2));
        assertEquals(List.of(BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(1)), triangle.calculateLineOfPascalTriangle(3));
        assertEquals(List.of(BigInteger.valueOf(1), BigInteger.valueOf(3), BigInteger.valueOf(3), BigInteger.valueOf(1)), triangle.calculateLineOfPascalTriangle(4));
        assertEquals(List.of(BigInteger.valueOf(1), BigInteger.valueOf(4), BigInteger.valueOf(6), BigInteger.valueOf(4), BigInteger.valueOf(1)), triangle.calculateLineOfPascalTriangle(5));
        assertEquals(500, triangle.calculateLineOfPascalTriangle(10_000).size(),
           "Expected list of '"+ 500 +"', but was ");
    }
}