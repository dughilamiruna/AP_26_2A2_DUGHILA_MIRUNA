import java.time.LocalDate;

public class Programmer extends Person {

    private String programmingLanguage;

    public Programmer(int id, String name, LocalDate birthDate, String zodiacSign, String programmingLanguage) {
        super(id, name, birthDate, zodiacSign);
        this.programmingLanguage = programmingLanguage;
    }
}
