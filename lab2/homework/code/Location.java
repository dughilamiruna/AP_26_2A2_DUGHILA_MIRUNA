import java.util.Objects;

/**
 * Aceasta este clasa abstractă de bază care reprezintă o locație generică pe hartă.
 * Este o clasă sigilată (sealed), ceea ce înseamnă că moștenirea este strict
 * controlată și permisă doar pentru clasele {@link City}, {@link Airport}, {@link Cafe}, și {@link Restaurant}.
 */
public abstract sealed class Location permits City, Airport, Cafe, Restaurant{

    private String locationName;
    private float coordinateX, coordinateY;

    /**
     * Construieste o instanta de tip Location.
     * Deoarece este o clasa abstracta, constructorul este apelat doar de clasele copii.
     * @param locationName Numele locatiei.
     * @param x Coordonata X
     * @param y Coordonata y
     */
    public Location(String locationName, float x, float y) {
        this.locationName = locationName;
        this.coordinateX = x;
        this.coordinateY = y;
    }

    /**
     * Returneaza numele locatiei.
     * @return Un sir de caractere ce reprezinta numele
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Seteaza numele locatiei.
     * @param locationName
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * Returneaza coordonata X.
     * @return Coordonata X.
     */
    public float getX() {
        return coordinateX;
    }

    /**
     * Seteaza coordonata X.
     * @param x
     */
    public void setX(float x) {
        this.coordinateX = x;
    }

    /**
     * Returneaza coordonata Y.
     * @return Coordonata Y.
     */
    public float getY() {
        return coordinateY;
    }

    /**
     * Seteaza coordonata Y.
     * @param y
     */
    public void setY(float y) {
        this.coordinateY = y;
    }

    /**
     * Returneaza o reprezentare text a locatiei.
     * @return Un string formatat ce contine numele si coordonatele.
     */
    @Override
    public String toString() {
        return "Location: " +
                "Name:" + locationName  +
                ", Coordinates:" + coordinateX +
                ", " + coordinateY;
    }

    /**
     * Compară această locație cu un alt obiect pentru a verifica egalitatea.
     * Această metodă este fundamentală pentru a preveni adăugarea dublurilor pe hartă.
     * Egalitatea este stabilită strict dacă ambele locații au exact același nume și aceleași coordonate X și Y.
     * @param o   the reference object with which to compare.
     * @return {@code true} dacă obiectele sunt considerate egale, {@code false} în caz contrar.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Float.compare(coordinateX, location.coordinateX) == 0 && Float.compare(coordinateY, location.coordinateY) == 0 && Objects.equals(locationName, location.locationName);
    }

    /**
     * Generează un cod hash pentru această locație.
     * Este utilizat în colecțiile bazate pe hash (cum ar fi HashSet) și respectă contractul metodei {@link #equals(Object)}.
     * @return O valoare intreaga ce reprezinta codul hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(locationName, coordinateX, coordinateY);
    }
}
