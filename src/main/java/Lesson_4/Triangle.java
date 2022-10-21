package Lesson_4;

public class Triangle {

    public static void main(String[] args) {

        try {
            System.out.println(areaTriangle(5, 5, 5));
        } catch (ItIsNotTriangleException exc) {
            System.out.println(exc.getMessage());
        }

    }

    public static int areaTriangle(int a, int b, int c) throws ItIsNotTriangleException {
        if ((a < b + c) && (b < a + c) && (c < a + b)) {
            double p = (double) (a + b + c) / 2;
            double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            return (int) s;
        } else {
            throw new ItIsNotTriangleException();
        }
    }
}
