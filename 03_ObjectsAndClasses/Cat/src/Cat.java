
public class Cat {

    private double originWeight;
    private double weight;
    private double totalFeed;
    private CatHairColor color;

    private static int count;

    public static final int EYES_COUNT = 2;
    public static final double MIN_WEIGHT = 1000;
    public static final double MAX_WEIGHT = 5000;

    public Cat() {
        weight = 3000.0 * Math.random() + 1500.0;
        originWeight = weight;
        totalFeed = 0;
        count++;
    }

    public Cat(CatHairColor color) {
        this();
        this.color = color;
    }

    public Cat(double weight) {
        this();
        this.weight = weight;
        this.originWeight = weight;
    }

    public void toilette() {
        weight -= 70 * Math.random() + 30;
        System.out.println("Sorry, dig that in, please...");
    }

    public void meow() {
        weight--;
        System.out.println("Meow");
    }

    public void feed(Double amount) {
        weight += amount;
        totalFeed += amount;
        System.out.println("Nyam-nyam");
    }

    public void drink(Double amount) {
        weight += amount;
        System.out.println("Bool-bool");
    }

    public CatHairColor getColor() {
        return color;
    }

    public void setColor(CatHairColor color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getTotalFeed() {
        return totalFeed;
    }

    public static int getCount() {
        return count;
    }

    public Cat cloneCat(Cat cat) {
        Cat c = new Cat(cat.getWeight());
        c.color = cat.getColor();
        c.totalFeed = cat.getTotalFeed();
        count--;
        return c;
    }

    public String getStatus() {
        if (weight < MIN_WEIGHT) {
            count--;
            return "Dead";
        } else if (weight > MAX_WEIGHT) {
            count--;
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }
}