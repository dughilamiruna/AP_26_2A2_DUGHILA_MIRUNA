/**
 * Aceasta clasa reprezinta o locatie de tip oras.
 * * Extinde clasa de bază {@link Location} prin adăugarea atributului de populație.
 */
public final class City extends Location {

    private int population;

    /**
     * Construiește un obiect de tip City cu numele, coordonatele și populația specificată.
     * @param locationName Numele orasului
     * @param x Coordonata X
     * @param y Coordonata Y
     * @param population Numarul de locuitori a orasului
     */
    public City(String locationName, float x, float y, int population) {
        super(locationName, x, y);
        this.population = population;
    }

    /**
     * Returneaza populatia orasului.
     * @return Numarul de locuitori.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Seteaza populatia orasului.
     * @param population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Returneaza o reprezentare text ce include numele si populatia orasului
     * @return Un string formatat pentru afisare in consola.
     */
    @java.lang.Override
    public java.lang.String toString() {
        return getLocationName() +
                " (city / population " + population + ")";
    }
}