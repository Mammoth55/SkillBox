import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_date")
    private Purchase purchase;

    public Subscription() {
    }

    public Subscription(Student student, Course course, Purchase purchase) {
        this.student = student;
        this.course = course;
        this.purchase = purchase;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudentId(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourseId(Course course) {
        this.course = course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}