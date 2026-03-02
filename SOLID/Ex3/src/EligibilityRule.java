import java.util.List;

public interface EligibilityRule {
    void apply(StudentProfile s, List<String> reasons);
}