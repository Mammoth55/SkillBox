import core.*;
import core.Camera;
import java.util.Scanner;

public class RoadController {

    private static double passengerCarMaxWeight = 3500.0; // kg ПОЛЕ КЛАССА
    private static int passengerCarMaxHeight = 2000; // mm ПОЛЕ КЛАССА
    private static int controllerMaxHeight = 3500; // mm ПОЛЕ КЛАССА
    private static int passengerCarPrice = 100; // RUB ПОЛЕ КЛАССА
    private static int cargoCarPrice = 250; // RUB ПОЛЕ КЛАССА
    private static int vehicleAdditionalPrice = 200; // RUB ПОЛЕ КЛАССА

    public static void main(String[] args) {
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in); // объект типа Scanner
        int carsCount = scanner.nextInt(); // кол-во машин для расчета

        for (int i = 0; i < carsCount; i++) {
            Car car = Camera.getNextCar(); // новый объект типа Car
            System.out.println(car);

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            int price = calculatePrice(car); // стоимость проезда
            if (price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car) {
        int carHeight = car.height; // высота машины
        int price; // цена проезда для возврата из метода
        if (carHeight > controllerMaxHeight) {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;
        } else if (carHeight > passengerCarMaxHeight) {
            double weight = car.weight; // вес авто
            //Грузовой автомобиль
            if (weight > passengerCarMaxWeight) {
                price = cargoCarPrice;
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            } else { //Легковой автомобиль
                price = passengerCarPrice;
            }
        } else {
            price = passengerCarPrice;
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}