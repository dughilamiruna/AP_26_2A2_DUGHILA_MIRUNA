import java.util.Objects;

/**
 * Aceasta clasa reprezinta calea de acces (un drum) intre doua locatii.
 */
public class Road {

    /**
     * Enumerare ce defineste clasificarea drumurilor.
     */
    public enum RoadType
    {
        AUTOSTRADA, NATIONALA, EUROPEANA, EXPRES
    }
    private RoadType roadType;
    private int roadLenght;
    private int speedLimit;
    private Location start;
    private Location end;

    /**
     * Verifica daca lungimea drumul respecta lungimea minima ceruta.
     * Aceasta lungime nu poate fi mai mică decât distanța euclidiană (în linie dreaptă) * dintre coordonatele locației de start și ale celei de final.
     * @return {@code true} dacă lungimea este matematic validă, {@code false} în caz contrar.
     */
    public boolean validLength()
    {
        double locationsDistance = Math.sqrt(
                Math.pow(end.getX() - start.getX(), 2) +
                        Math.pow(end.getY() - start.getY(), 2)
        );
        return this.roadLenght >= locationsDistance;
    }

    /**
     * Construieste un obiect de tip Road.
     * @param roadType Tipul drumului, de tip {@link RoadType}
     * @param roadLenght Lungimea drumului
     * @param speedLimit Limita de viteza
     * @param startLocation Locatia de plecare, de tip {@link Location}
     * @param endLocation Locatia destinatie, de tip {@link Location}
     */
    public Road(RoadType roadType, int roadLenght, int speedLimit, Location startLocation, Location endLocation) {
        this.roadType = roadType;
        this.roadLenght = roadLenght;
        this.speedLimit = speedLimit;
        this.start= startLocation;
        this.end=endLocation;
    }

    /**
     *Returneaza tipul drumului.
     * @return Tipul drumului.
     */
    public RoadType getRoadType() {
        return roadType;
    }

    /**
     * Seteaza tipul drumului.
     * @param roadType
     */
    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }

    /**
     * Returneaza lungimea drumului
     * @return Lungimea
     */
    public float getRoadLenght() {
        return roadLenght;
    }

    /**
     * Seteaza lungimea drumului
     * @param roadLenght
     */
    public void setRoadLenght(int roadLenght) {
        this.roadLenght = roadLenght;
    }

    /**
     * Returneaza limita de viteza a drumului.
     * @return limita de viteza
     */
    public int getSpeedLimit() {
        return speedLimit;
    }

    /**
     * Seteaza limita de viteza.
     * @param speedLimit
     */
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    /**
     * Returneaza locatia de start
     * @return Locatia start
     */
    public Location getStart() {
        return start;
    }

    /**
     * Seteaza locatia de start.
     * @param start
     */
    public void setStart(Location start) {
        this.start = start;
    }

    /**
     * Returneaza locatia destinatie
     * @return Locatia destinatie
     */
    public Location getEnd() {
        return end;
    }

    /**
     * Seteaza locatia destinatie
     * @param end
     */
    public void setEnd(Location end) {
        this.end = end;
    }

    /**
     * Returneaza o reprezentare text a drumului.
     * @return Un string ce contine tipul, lungimea si limita de viteza a drumului.
     */
    @Override
    public String toString() {
        return  roadType +
                " (" + roadLenght +
                " km / " + speedLimit +
                " km speed limit)";
    }

    /**
     * Verifică egalitatea între acest drum și un alt obiect.
     * Două drumuri sunt considerate identice dacă au același tip, aceeași lungime, aceeași limită de viteză și conectează exact aceleași locații.
     * @param o   the reference object with which to compare.
     * @return {@code true} dacă drumurile sunt identice, {@code false} altfel.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Float.compare(roadLenght, road.roadLenght) == 0 && speedLimit == road.speedLimit && roadType == road.roadType && Objects.equals(start, road.start) && Objects.equals(end, road.end);
    }

    /**
     * Generează un cod hash pe baza caracteristicilor drumului și a locațiilor conectate.
     * @return O valoare intreaga reprezentand codul hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(roadType, roadLenght, speedLimit, start, end);
    }
}
