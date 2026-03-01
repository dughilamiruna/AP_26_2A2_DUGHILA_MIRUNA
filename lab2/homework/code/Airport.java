/**
 * Aceasta clasa reprezinta o locatie de tip aeroport.
 * * Extinde clasa de baza {@link Location} prin adaugarea atributului de numar de terminale.
 */
public final class Airport extends Location {

    private int terminalNumber;

    /**
     * Construiește un obiect de tip Airport cu numele, coordonatele și numarul de terminale specificată.
     * @param locationName Numele aeroportului
     * @param x Coordonata X
     * @param y Coordonata Y
     * @param terminalNumber Numarul de terminale a aeroportului
     */
    public Airport(String locationName, float x, float y, int terminalNumber) {
        super(locationName, x, y);
        this.terminalNumber = terminalNumber;
    }

    /**
     * Returneaza numarul de terminale.
     * @return Numarul de terminale.
     */
    public int getTerminalNumber() {
        return terminalNumber;
    }

    /**
     * Seteaza numarul de terminale specific.
     * @param terminalNumber
     */
    public void setTerminalNumber(int terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    /**
     * Returneaza o reprezentare text ce include numele si numarul de terminale a aeroportului.
     * @return Un string formatat pentru afisare in consola.
     */
    @java.lang.Override
    public java.lang.String toString() {
        return getLocationName() +
                " (airport / " + terminalNumber + " terminals)";
    }
}
