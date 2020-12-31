import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "purchaselist")
public class Purchase {

    @EmbeddedId
    @Column(name = "subscription_date")
    private PurchaseId id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "price")
    private int price;

    public Purchase() {
    }

    public Purchase(String studentName, String courseName, int price, PurchaseId id) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.price = price;
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public PurchaseId getId() {
        return id;
    }

    public void setId(PurchaseId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return getPrice() == purchase.getPrice()
                && Objects.equals(getId(), purchase.getId())
                && Objects.equals(getStudentName(), purchase.getStudentName())
                && Objects.equals(getCourseName(), purchase.getCourseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudentName(), getCourseName(), getPrice());
    }

    @Override
    public String toString() {
        return "Purchase { "
                + "studentName = '" + studentName + '\''
                + ", courseName = '" + courseName + '\''
                + ", price = " + price
                + ", subscriptionDate = " + id.getSubscriptionDate() + " }";
    }
}