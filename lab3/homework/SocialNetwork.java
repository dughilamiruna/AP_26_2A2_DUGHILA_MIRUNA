import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SocialNetwork {

    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Profile profile) {
        profiles.add(profile);
    }

    public int computeImportance (Profile object)
    {
        return object.getImportance(this.profiles);
    }

    public void printNetwork()
    {
        profiles.sort(new Comparator<Profile>() {
            @Override
            public int compare(Profile profile1, Profile profile2) {
                int importance1 = computeImportance(profile1);
                int importance2 = computeImportance(profile2);
                int result= Integer.compare(importance2, importance1);
                if (result == 0) {
                    return profile1.getName().compareTo(profile2.getName());
                }

                return result;
            }
        });

        int contor=1;
        for (int index = 0; index < profiles.size(); index++) {
            Profile profile = profiles.get(index);
            System.out.println(contor + ". " + profile.getName() + " -> Importanta: " + computeImportance(profile));
            contor++;
        }
    }
}

