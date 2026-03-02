import java.util.List;

public class CreditsRule implements EligibilityRule {

    public void apply(StudentProfile s, List<String> reasons) {
        if (s.earnedCredits < 20) {
            reasons.add("credits below 20");
        }
    }
}