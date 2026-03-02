import java.util.*;

public class OnboardingService {

    private final StudentRepository repo;
    private final StudentParser parser = new StudentParser();
    private final StudentValidator validator = new StudentValidator();
    private final StudentPrinter printer = new StudentPrinter();

    public OnboardingService(StudentRepository repo) {
        this.repo = repo;
    }

    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Map<String, String> kv = parser.parse(raw);

        List<String> errors = validator.validate(kv);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String name = kv.get("name");
        String email = kv.get("email");
        String phone = kv.get("phone");
        String program = kv.get("program");

        String id = IdUtil.nextStudentId(repo.count());

        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repo.save(rec);

        printer.printSuccess(id, repo.count());
        printer.printConfirmation(rec);
    }
}