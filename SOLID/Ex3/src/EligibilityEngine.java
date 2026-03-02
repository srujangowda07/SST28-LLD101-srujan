import java.util.*;

public class EligibilityEngine {

    private FakeEligibilityStore store;
    private List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {
        this.store = store;

        rules = new ArrayList<>();
        rules.add(new DisciplinaryRule());
        rules.add(new CgrRule());
        rules.add(new AttendanceRule());
        rules.add(new CreditsRule());
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter printer = new ReportPrinter();
        EligibilityEngineResult result = evaluate(s);
        printer.print(s, result);
        store.save(s.rollNo, result.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {

        List<String> reasons = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            rule.apply(s, reasons);

            if (!reasons.isEmpty()) {
                break;
            }
        }

        String status;
        if (reasons.isEmpty()) {
            status = "ELIGIBLE";
        } else {
            status = "NOT_ELIGIBLE";
        }

        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult {
    public String status;
    public List<String> reasons;

    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}