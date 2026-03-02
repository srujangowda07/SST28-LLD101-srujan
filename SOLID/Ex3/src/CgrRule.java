import java.util.List;

public class CgrRule implements EligibilityRule {

    public void apply(StudentProfile s, List<String> reasons) {
        if (s.cgr < 8.0) {
            reasons.add("CGR below 8.0");
        }
    }
}