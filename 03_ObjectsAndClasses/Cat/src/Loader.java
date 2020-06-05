
public class Loader {

    private static Cat getKitten() {
        Cat kitty = new Cat(1100.0);
        kitty.setColor(CatHairColor.BLACK);
        return kitty;
    }

    public static void main(String[] args) {
        Cat kuzma = new Cat(CatHairColor.BLACK);
        Cat vasya = new Cat(CatHairColor.TIGER);
        Cat musya = new Cat(CatHairColor.WHITE);
        kuzma.meow();
        musya = musya.cloneCat(vasya);
        vasya.toilette();
        System.out.println(Cat.getCount() + "  kuzma  " + kuzma.getStatus() + "\t" + kuzma.getWeight());
        System.out.println(Cat.getCount() + "  vasya  " + vasya.getStatus() + "\t" + vasya.getWeight());
        System.out.println(Cat.getCount() + "  musya  " + musya.getStatus() + "\t" + musya.getWeight());
        musya.feed(50.0);
        vasya.feed(50.0);
        System.out.println(Cat.getCount() + "  vasya  " + vasya.getStatus() + "\t" + vasya.getWeight() + "\t" + vasya.getTotalFeed());
        System.out.println(Cat.getCount() + "  musya  " + musya.getStatus() + "\t" + musya.getWeight() + "\t" + musya.getTotalFeed());
        Cat bars = new Cat(CatHairColor.RED);
        System.out.println("============================================================");
        System.out.println(Cat.getCount() + "  bars  " + bars.getStatus() + "\t" + bars.getWeight());
        while (bars.getIsAlive()) {
            bars.meow();
            bars.meow();
            bars.toilette();
            System.out.println(Cat.getCount() + "  bars  " + bars.getStatus() + "\t" + bars.getWeight());
        }
        Cat pers = new Cat(3000.0);
        System.out.println("============================================================");
        System.out.println(Cat.getCount() + "  pers  " + pers.getStatus() + "\t" + pers.getWeight());
        while (pers.getIsAlive()) {
            pers.feed(22.5);
            pers.drink(22.5);
            System.out.println(Cat.getCount() + "  pers  " + pers.getStatus() + "\t" + pers.getWeight() + "\t" + pers.getTotalFeed());
        }
    }
}