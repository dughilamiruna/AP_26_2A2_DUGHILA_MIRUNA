import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person implements Profile,Comparable<Profile>{

    private int id;
    private String name;
    private LocalDate birthDate;
    private String zodiacSign;

    private Map<Profile, String> relationships = new HashMap<>();

    public Person(int id, String name, LocalDate birthDate, String zodiacSign) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.zodiacSign = zodiacSign;
    }

    public void addRelationship(Profile p, String value) {
        relationships.put(p, value);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    @Override
    public int compareTo(Profile profile)
    {
        return name.compareTo(profile.getName());
    }

    @Override
    public int getImportance(List<Profile> allProfiles) {
        return this.relationships.size();
    }

    @Override
    public boolean hasRelationshipWith(Profile profile) {
        return this.relationships.containsKey(profile);
    }

}
