import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class PurchaseId implements Serializable {

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchaseId() {
    }

    public PurchaseId(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseId)) {
            return false;
        }
        PurchaseId that = (PurchaseId) o;
        return Objects.equals(getSubscriptionDate(), that.getSubscriptionDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubscriptionDate());
    }

    @Override
    public String toString() {
        return "PurchaseId{ subscriptionDate = " + subscriptionDate + '}';
    }
}