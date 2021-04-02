import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity("shops")
public class Shop {

    @Id
    private ObjectId id;

    private String name;

    private Set<String> productNames;

    public Shop() {
    }

    public Shop(String name) {
        this.name = name;
        this.productNames = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<String> getProductNames() {
        return productNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(id, shop.id) && Objects.equals(name, shop.name) && Objects.equals(productNames, shop.productNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}