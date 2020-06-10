public class GeometryCalculator {

    public static final int truckVolume = 12;
    public static final int containerVolume = 27;
    public static final int box = 590;

    public static double circleSquare(double r) {
        return Math.pow(r, 2) * Math.PI;
    }

    public static double triangleSquare(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt((p - a) * (p - b) * (p - c) * p);
    }

    public static double sphereVolume(double r) {
        return Math.pow(r, 3) * Math.PI * 4 / 3;
    }

    public static boolean triangleCheck(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }

    public static void main(String[] args) {
        System.out.println(circleSquare(1));
        System.out.println(triangleSquare(3, 4, 5));
        System.out.println(sphereVolume(1));
        System.out.println(triangleCheck(3, 2, 5));
        System.out.println("=========================");
        int truck = 0;
        int container = 0;
        for (int i = 0; i < box;) {
            if (i / (truckVolume * containerVolume) + 1 > truck) {
                System.out.println("Грузовик " + ++truck + " :");
            }
            if (i / containerVolume + 1 > container) {
                System.out.println("Контейнер " + ++container + " :");
            }
            System.out.println("Ящик " + ++i);
        }
        System.out.println("ИТОГО для " + box + " ящиков необходимо :");
        System.out.println("Грузовиков - " + (box / (truckVolume * containerVolume) + 1) + " шт.");
        System.out.println("Контейнеров  - " + (box / containerVolume + 1) + " шт.");
    }
}