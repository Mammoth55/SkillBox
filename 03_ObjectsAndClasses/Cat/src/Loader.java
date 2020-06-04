
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
        String st = bars.getStatus();
        System.out.println("============================================================");
        System.out.println(Cat.getCount() + "  bars  " + st + "\t" + bars.getWeight());
        do {
            bars.meow();
            bars.meow();
            bars.toilette();
            System.out.println(Cat.getCount() + "  bars  " + bars.getWeight());
            st = bars.getStatus();
        } while (! st.equals("Dead"));
        System.out.println(Cat.getCount() + "  bars  " + st + "\t" + bars.getWeight());

        Cat pers = new Cat(3000.0);
        st = pers.getStatus();
        System.out.println("============================================================");
        System.out.println(Cat.getCount() + "  pers  " + st + "\t" + pers.getWeight());
        do {
            pers.feed(1.5);
            pers.drink(1.5);
            System.out.println(Cat.getCount() + "  pers  " + pers.getWeight());
            st = pers.getStatus();
        } while (! st.equals("Exploded"));
        System.out.println(Cat.getCount() + "  pers  " + st + "\t" + pers.getWeight() + "\t" + pers.getTotalFeed());
    }
}