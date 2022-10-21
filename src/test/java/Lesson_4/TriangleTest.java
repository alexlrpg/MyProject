package Lesson_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TriangleTest {

    static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @Test
    void areaTriangle() throws ItIsNotTriangleException {
        logger.info("Инфо");
        Assertions.assertEquals(10, Triangle.areaTriangle(5, 5, 5));
        Assertions.assertThrows(ItIsNotTriangleException.class, ()-> Triangle.areaTriangle(5, 0, 5));
        Assertions.assertThrows(ItIsNotTriangleException.class, ()-> Triangle.areaTriangle(1, 1, -1));
        Assertions.assertThrows(ItIsNotTriangleException.class, ()-> Triangle.areaTriangle(15, 18, 500));
    }

    @ParameterizedTest
    @CsvSource({"5, 5, 5, 10", "6, 8, 10, 24"})
    void areaTriangle1(int a, int b, int c, int result) throws ItIsNotTriangleException {
        logger.info("Инфо");
        Assertions.assertEquals(result, Triangle.areaTriangle(a, b, c));
    }

    @ParameterizedTest
    @CsvSource({"5, 0, 5", "6, 8, 0"})
    void areaTriangle2(int a, int b, int c) throws ItIsNotTriangleException {
        logger.info("Инфо");
        Assertions.assertThrows(ItIsNotTriangleException.class,() -> Triangle.areaTriangle(a, b, c));
    }

    @ParameterizedTest
    @CsvSource({"-3, 10, 15", "2, 4, -1"})
    void areaTriangle3(int a, int b, int c) throws ItIsNotTriangleException {
        logger.info("Инфо");
        Assertions.assertThrows(ItIsNotTriangleException.class,() -> Triangle.areaTriangle(a, b, c));
    }

    @ParameterizedTest
    @CsvSource({"12, 10, 150", "200, 4, 56"})
    void areaTriangle4(int a, int b, int c) throws ItIsNotTriangleException {
        logger.info("Инфо");
        Assertions.assertThrows(ItIsNotTriangleException.class,() -> Triangle.areaTriangle(a, b, c));
    }
}
