
public class Loader {

    public static void main(String[] args) {
        Cat bars = new Cat(CatHairColor.RED);
        String st = bars.getStatus();
        System.out.println("==================================================");
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
        System.out.println("==================================================");
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