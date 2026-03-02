import java.util.List;

public class AttendanceRule implements EligibilityRule {

    public void apply(StudentProfile s, List<String> reasons) {
        if (s.attendancePct < 75) {
            reasons.add("attendance below 75");
        }
    }
}