import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubscriptionId implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public SubscriptionId() {
    }

    public SubscriptionId(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionId)) {
            return false;
        }
        SubscriptionId that = (SubscriptionId) o;
        return getStudentId() == that.getStudentId()
                && getCourseId() == that.getCourseId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getCourseId());
    }

    @Override
    public String toString() {
        return "SubscriptionId{ studentId = " + studentId + ", courseId = " + courseId + '}';
    }
}