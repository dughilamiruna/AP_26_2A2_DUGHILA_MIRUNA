import java.time.LocalDate;

public class Designer extends Person {

    private String favouriteCoffee;

    public Designer(int id, String name, LocalDate birthDate, String zodiacSign, String favouriteCoffee) {
        super(id, name, birthDate, zodiacSign);
        this.favouriteCoffee = favouriteCoffee;
    }
}
