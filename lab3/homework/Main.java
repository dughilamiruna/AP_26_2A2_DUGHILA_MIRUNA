import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        SocialNetwork socialNetwork = new SocialNetwork();

        Programmer person1 = new Programmer(1, "Ioana", LocalDate.of(2006, 2, 9), "Varsator", "Java");
        Programmer person2 = new Programmer(2, "Alexia", LocalDate.of(2005, 11, 10), "Scorpion", "C++");
        Programmer person3 = new Programmer(3, "Roxana", LocalDate.of(2005, 12, 22), "Capricorn", "Python");

        Designer person4 = new Designer(4, "Miruna", LocalDate.of(2005, 9, 18), "Fecioara", "Latte");
        Designer person5 = new Designer(5, "Lucian", LocalDate.of(2005, 6, 23), "Rac", "Cappuccino");
        Designer person6 = new Designer(6, "Denisa", LocalDate.of(2000, 3, 12), "Pesti", "Espresso");

        Company company1 = new Company(10, "Amazon", 100000);
        Company company2 = new Company(11, "Microsoft", 500000);

        socialNetwork.addProfile(person1);
        socialNetwork.addProfile(person2);
        socialNetwork.addProfile(person3);
        socialNetwork.addProfile(person4);
        socialNetwork.addProfile(person5);
        socialNetwork.addProfile(person6);
        socialNetwork.addProfile(company1);
        socialNetwork.addProfile(company2);

        person1.addRelationship(person2, "Prieteni");
        person2.addRelationship(person1, "Prieteni");

        person1.addRelationship(person3, "Prieteni");
        person3.addRelationship(person1, "Prieteni");

        person3.addRelationship(person5, "Parteneri");
        person5.addRelationship(person3, "Parteneri");

        person4.addRelationship(person5, "Iubiti");
        person5.addRelationship(person4, "Iubiti");

        person4.addRelationship(person6, "Prieteni");
        person6.addRelationship(person4, "Prieteni");

        person5.addRelationship(person6, "Colegi");
        person6.addRelationship(person5, "Colegi");

        person1.addRelationship(company2, "Programator");
        person2.addRelationship(company1, "Programator");
        person3.addRelationship(company2, "Programator");
        person4.addRelationship(company1, "Designer");
        person5.addRelationship(company2, "Designer");
        person6.addRelationship(company2, "Designer");

        System.out.println("Reteaua ordonata dupa importanta (numarul de relatii): ");
        socialNetwork.printNetwork();
    }
}
