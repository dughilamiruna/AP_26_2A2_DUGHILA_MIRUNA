import java.util.List;
import java.util.ArrayList;

/**
 * Clasa Problem gestioneaza instanta problemei de logisitica.
 * Aceasta mentine listele de locatii si drumuri existente si ofera functionalitate in gasirea rutelor dintre doua locatii.
 */
public class Problem {

    private List<Location> locationList;
    private List<Road> roadList;

    /**
     * Se adauga o locatie noua in litsa, verificand daca nu este o duplicata.
     * @param location Locatia care urmeaza sa fie adaugata.
     */
    public void addLocation( Location location)
    {
        if (!this.locationList.contains(location))
            this.locationList.add(location);
        else System.out.println("Location already exists, the entry was ignored.");

    }

    /**
     * Se adauga un drum nou in litsa, verificand daca nu este o duplicata si daca respecta lungimea minima.
     * @param road Drumul care urmeaza sa fie adaugat.
     */
    public void addRoad( Road road)
    {
        if (!road.validLength()) {
            System.out.println("Road too short, the entry was ignored.");
            return;
        }
        if (!this.roadList.contains(road))
            this.roadList.add(road);
        else System.out.println("Road already exists.");
    }

    /**
     * Verifica daca o instanta a problemei este valida,
     * adica daca nu exista locatii sau drumuri duplicate.
     * @return {@code true} dacă problema este validă, {@code false} în caz contrar.
     */
    public boolean isValid()
    {
        for (int i=0; i<locationList.size(); i++)
            for (int j=i+1; j<locationList.size(); j++)
                if (locationList.get(i).equals(locationList.get(j)))
                {
                    System.out.println("Location already exists.");
                    return false;
                }
        for (int i=0; i<roadList.size(); i++)
            for (int j=i+1; j<roadList.size(); j++)
                if (roadList.get(i).equals(roadList.get(j)))
                {
                    System.out.println("Road already exists.");
                    return false;
                }
        return true;
    }

    /**
     * Metodă internă care implementează algoritmul Depth-First Search (DFS), pentru verificarea conectivitatii dintre doua locatii.
     * @param current Locatia curenta ce este explorata
     * @param end Locatia destinatie
     * @param visitedLocation o lista cu locatiile deja vizitate si verificate
     * @return {@code true} dacă am ajuns la destinatie, {@code false} altfel.
     */
    private boolean DFS(Location current, Location end, List<Location> visitedLocation)
    {
        if (current.equals(end)) return true;
        visitedLocation.add(current);

        for(int i=0; i<roadList.size(); i++)
        {
            Road road = roadList.get(i);
            Location neighbor=null;
            if (road.getStart().equals(current))
                neighbor = road.getEnd();
            else if (road.getEnd().equals(current))
                neighbor = road.getStart();

            if(neighbor!=null && !visitedLocation.contains(neighbor))
                if(DFS(neighbor, end, visitedLocation))
                    return true;
        }

        return false;
    }

    /**
     * Verifica daca calea dintre doua locatii este valida si exista, folosindu-se de metoda DFS.
     * @param start Locatia de plecare
     * @param end Locatia destinatie
     * @return {@code true} dacă există o cale de legătură între cele două locații.
     */
    public boolean validPath(Location start, Location end)
    {
        List<Location> visitedLocation = new ArrayList<>();
        return DFS(start, end, visitedLocation);
    }

    /**
     * Inițializează o instanță nouă a problemei cu liste goale pentru locații și drumuri.
     */
    public Problem() {
        this.locationList = new ArrayList<>();
        this.roadList = new ArrayList<>();
    }

    /**
     * Returneaza lista de locatii curenta.
     * @return Lista de locatii
     */
    public List<Location> getLocationList() {
        return locationList;
    }

    /**
     * Seteaza lista de locatii.
     * @param locationList
     */
    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    /**
     * Returneaza lista de drumuri curenta
     * @return Lista de drumuri
     */
    public List<Road> getRoadList() {
        return roadList;
    }

    /**
     * Seteaza lista de drumuri.
     * @param roadList
     */
    public void setRoadList(List<Road> roadList) {
        this.roadList = roadList;
    }

    /**
     * Returneaza o reprezentare text a hartii.
     * @return Un string formatat pentru afisare in consola, ce contine lista de locatii si drumuri.
     */
    @java.lang.Override
    public java.lang.String toString() {
        return "1. Map output ->" + '\n' +
                "Locations: " + locationList + '\n'+
                "Roads: " + roadList;
    }
}
