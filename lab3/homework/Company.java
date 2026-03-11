import java.util.List;

public class Company implements Profile{

    private int id;
    private String name;
    private int workersNumber;

    public Company(int id, String name, int workersNumber) {
        this.id = id;
        this.name = name;
        this.workersNumber = workersNumber;
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

    public int getWorkersNumber() {
        return workersNumber;
    }

    public void setWorkersNumber(int workersNumber) {
        this.workersNumber = workersNumber;
    }

    @Override
    public int compareTo( Profile profile)
    {
        return name.compareTo(profile.getName());
    }

    @Override
    public int getImportance(List<Profile> allProfiles) {
        int importanceCount = 0;

        for (int index = 0; index < allProfiles.size(); index++) {
            Profile profile = allProfiles.get(index);
            if (profile.hasRelationshipWith(this)) {
                importanceCount++;
            }
        }

        return importanceCount;
    }

    @Override
    public boolean hasRelationshipWith(Profile profile) {
        return false;
    }
}

