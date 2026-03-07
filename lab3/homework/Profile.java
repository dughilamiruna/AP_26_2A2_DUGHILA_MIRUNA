import java.util.List;

public interface Profile extends Comparable<Profile> {

    int getId();
    String getName();

    int getImportance(List<Profile> allPorfiles);
    boolean hasRelationshipWith(Profile profile);
}
