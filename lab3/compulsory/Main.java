import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Profile> socialNetwork = new ArrayList<>();

        socialNetwork.add(new Person(1, "Miruna", "Designer"));
        socialNetwork.add(new Person(2, "Alexia", "Programmer"));
        socialNetwork.add(new Person(3, "Ioana", "Programmer"));
        socialNetwork.add(new Person(4, "Lucian", "Translator"));

        socialNetwork.add(new Company(5, "Amazon"));
        socialNetwork.add(new Company(6, "Continental"));
        socialNetwork.add(new Company(7, "Apple"));

        System.out.print("Nesortat: ");
        for (int i = 0; i < socialNetwork.size(); i++) {
            Profile profile = socialNetwork.get(i);
            System.out.print(profile.getName() + ", ");
        }
        System.out.println();
        socialNetwork.sort(Comparator.comparing(Profile::getName));

        System.out.print("Sortat: ");
        for (int i = 0; i < socialNetwork.size(); i++) {
            Profile profile = socialNetwork.get(i);
            System.out.print(profile.getName() + ", ");
        }
    }
}
