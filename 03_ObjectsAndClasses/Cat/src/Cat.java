
public class Cat {

    private double originWeight;
    private double weight;
    private double totalFeed;
    private CatHairColor color;
    private boolean isAlive;

    private static int count;

    public static final int EYES_COUNT = 2;
    public static final double MIN_WEIGHT = 1000;
    public static final double MAX_WEIGHT = 5000;

    public Cat() {
        weight = 3000.0 * Math.random() + 1500.0;
        originWeight = weight;
        isAlive = true;
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
        if (isAlive) {
            weight -= 70 * Math.random() + 30;
            System.out.println("Sorry, dig that in, please...");
            checkStatus();
        } else {
            System.out.println("Leave me R.I.P.");
        }
    }

    public void meow() {
        if (isAlive) {
            weight--;
            System.out.println("Meow");
            checkStatus();
        } else {
            System.out.println("Leave me R.I.P.");
        }
    }

    public void feed(Double amount) {
        if (isAlive) {
            weight += amount;
            totalFeed += amount;
            System.out.println("Nyam-nyam");
            checkStatus();
        } else {
            System.out.println("Leave me R.I.P.");
        }
    }

    public void drink(Double amount) {
        if (isAlive) {
            weight += amount;
            System.out.println("Bool-bool");
            checkStatus();
        } else {
            System.out.println("Leave me R.I.P.");
        }
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean live) {
        isAlive = live;
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
        c.isAlive = cat.isAlive;
        c.totalFeed = cat.getTotalFeed();
        count--;
        return c;
    }

    public void checkStatus() {
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            isAlive = false;
            count--;
        }
    }

    public String getStatus() {
        if (weight < MIN_WEIGHT) {
            return "Dead";
        } else if (weight > MAX_WEIGHT) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else return "Playing";
    }
}