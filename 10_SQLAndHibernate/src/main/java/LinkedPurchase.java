import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchase {

    @EmbeddedId
    private SubscriptionId id;

    public LinkedPurchase() {
    }

    public LinkedPurchase(SubscriptionId id) {
        this.id = id;
    }

    public SubscriptionId getId() {
        return id;
    }

    public void setId(SubscriptionId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedPurchase)) {
            return false;
        }
        LinkedPurchase that = (LinkedPurchase) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "{ id=" + id + '}';
    }
}