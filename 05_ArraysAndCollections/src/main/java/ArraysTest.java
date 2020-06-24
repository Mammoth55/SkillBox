public class ArraysTest {

    public static void rainbow() {
        String[] words = {"Каждый", "охотник", "желает", "знать", "где", "сидит", "фазан"};
        String str;
        for (int index = 0; index < words.length / 2; index++) {
            str = words[index];
            words[index] = words[words.length - index - 1];
            words[words.length - index - 1] = str;
        }
        for (String word : words) {
            System.out.println(word);
        }
    }

    public static void hospital() {
        final int patients = 30;
        final float patientsMinTemp = 32.0f;
        final float patientsMaxTemp = 40.0f;
        final float healthyPatientsMinTemp = 36.2f;
        final float healthyPatientsMaxTemp = 36.9f;
        float averageTemp = 0f;
        int healthyPatients = 0;
        float[] temp = new float[patients];
        System.out.print("Температуры пациентов : ");
        for (int i = 0; i < patients; i++) {
            temp[i] = (float) Math.random() * (patientsMaxTemp - patientsMinTemp) + patientsMinTemp;
            averageTemp += temp[i];
            if (temp[i] >= healthyPatientsMinTemp && temp[i] <= healthyPatientsMaxTemp) {
                healthyPatients++;
            }
            System.out.printf("%.1f ", temp[i]);
        }
        System.out.printf("\nСредняя температура : %.2f\n", (averageTemp / patients));
        System.out.println("Количество здоровых : " + healthyPatients);
    }

    public static void cross() {
        final int size = 7;
        String[][] matrix = new String[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrix[row][col] = (row == col || row == size - col - 1) ? "><" : "  ";
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        rainbow();
        hospital();
        cross();
    }
}