public class Main {

    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;
        System.out.println("123456789 --> " + sumDigits(123456789));
    }

    public static Integer sumDigits(Integer number) {
        Integer s = 0;
        String str = number.toString();
        for (int i = 0; i < str.length(); i++) {
            s += Integer.parseInt(String.valueOf(str.charAt(i))); // по базовому ТЗ
            // s += Character.getNumericValue(str.charAt(i)); - рефакторинг по ТЗ со '*'
        }
        return s;
    }
}