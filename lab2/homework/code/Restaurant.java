/**
 * Aceasta clasa reprezinta o locatie de tip restaurant.
 *  *  Extinde clasa de baza {@link Location} prin adaugarea atributului de capacitate a restaurantului.
 */
public final class Restaurant extends Location {

    private int restaurantCapacity;

    /**
     * Construiește un obiect de tip Restaurant cu numele, coordonatele și capacitatea specificată.
     * @param locationName Numele restaurantului
     * @param x Coordonata X
     * @param y Coordonata Y
     * @param restaurantCapacity Numarul de locuri a restaurantului.
     */
    public Restaurant(String locationName, float x, float y, int restaurantCapacity) {
        super(locationName, x, y);
        this.restaurantCapacity = restaurantCapacity;
    }

    /**
     * Returneaza capacitatea restaurantului.
     * @return Numarul de locuri
     */
    public int getRestaurantCapacity() {
        return restaurantCapacity;
    }

    /**
     * Seteaza capacitatea specofica.
     * @param restaurantCapacity
     */
    public void setRestaurantCapacity(int restaurantCapacity) {
        this.restaurantCapacity = restaurantCapacity;
    }

    /**
     * Returneaza o reprezentare text ce include numele si numarul de locuri a restaurantului.
     * @return Un string formatat pentru afisare in consola.
     */
    @java.lang.Override
    public java.lang.String toString() {
        return getLocationName() +
                " (restaurant / capacity " + restaurantCapacity + ")";
    }
}

