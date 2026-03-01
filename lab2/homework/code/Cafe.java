/**
 * Aceasta clasa reprezinta o locatie de tip cafenea.
 *  *  Extinde clasa de baza {@link Location} prin adaugarea atributului de numar de tip de cafele vandute de cafenea.
 */
public final class Cafe extends Location {

    private int coffeeTypes;

    /**
     * Construiește un obiect de tip Cafe cu numele, coordonatele și numarul de tipuri de cafele specificată.
     * @param locationName Numele cafenelei
     * @param x Coordonata X
     * @param y Coordonata Y
     * @param coffeeTypes Numarul de tipuri de cafele vandute de cafenea
     */
    public Cafe(String locationName, float x, float y, int coffeeTypes) {
        super(locationName, x, y);
        this.coffeeTypes = coffeeTypes;
    }

    /**
     * Returneaza numarul de tipuri de cafele vandute.
     * @return Numarul de tipuri de cafele
     */
    public int getCoffeeTypes() {
        return coffeeTypes;
    }

    /**
     * Seteaza acest numar de tipuri de cafele disponibile specific.
     * @param coffeeTypes
     */
    public void setCoffeeTypes(int coffeeTypes) {
        this.coffeeTypes = coffeeTypes;
    }

    /**
     * Returneaza o reprezentare text ce include numele si numarul de tipuri de cafele.
     * @return Un string formatat pentru afisare in consola.
     */
    @java.lang.Override
    public java.lang.String toString() {
        return getLocationName() +
                " (cafe / " + coffeeTypes + " types of coffe)";
    }
}
