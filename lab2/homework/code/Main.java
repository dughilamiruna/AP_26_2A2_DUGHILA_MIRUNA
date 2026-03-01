import java.util.Scanner;

/**
 * Clasa principala care testeaza functionalitatea.
 * Aici se creeaza instantele si se populeaza harta.
 */
public class Main {

    /**
     * Punctul de intrare, se initializeaza datele, se valideaza intrarile
     * si interactioneaza cu itilizatorul prin consola.
     * @param args Nu sunt utilizate argumente
     */
    public static void main(String[] args) {

        System.out.println("Errors:");

        City firstCity = new City("Iasi", 10, 11, 20000);
        City secondCity = new City("Cluj", 20, 21, 30000);
        Cafe firstCafe = new Cafe("Frateria", 12, 13, 10);
        Airport firstAirport = new Airport("Henri Coanda", 5, 100, 12);
        Restaurant firstRestaurant = new Restaurant("Harmony", 10, 20, 100);

        City thirdCity = new City("Iasi", 10, 11, 20000);

        Road firstRoad = new Road(Road.RoadType.AUTOSTRADA, 500, 100, firstCity, secondCity);
        Road secondRoad = new Road(Road.RoadType.NATIONALA, 200, 90, firstCity, firstAirport);
        Road thirdRoad = new Road(Road.RoadType.EUROPEANA, 2, 50, firstCafe, firstRestaurant);
        Road fourthRoad = new Road(Road.RoadType.EUROPEANA, 300, 70, secondCity, firstRestaurant);

        Problem earthMap = new Problem();
        earthMap.addLocation(firstCity);
        earthMap.addLocation(secondCity);

        earthMap.addLocation(thirdCity);

        earthMap.addLocation(firstCafe);
        earthMap.addLocation(firstAirport);
        earthMap.addLocation(firstRestaurant);

        earthMap.addRoad(firstRoad);
        earthMap.addRoad(secondRoad);
        earthMap.addRoad(thirdRoad);
        earthMap.addRoad(fourthRoad);

        System.out.println(earthMap);

        System.out.println("2. Algorithm ->");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start location: "); String startLocation = scanner.nextLine();
        System.out.println("End location: "); String endLocation = scanner.nextLine();

        Location start = null; Location end = null;

        for (int i = 0; i < earthMap.getLocationList().size(); i++)
        {
            Location location = earthMap.getLocationList().get(i);

            if (location.getLocationName().equalsIgnoreCase(startLocation)) start = location;
            if (location.getLocationName().equalsIgnoreCase(endLocation)) end = location;
        }

        if (start != null && end != null)
        {
            boolean pathExists = earthMap.validPath(start, end);

            if (pathExists) System.out.println("There is a way from " + start.getLocationName() + " to " + end.getLocationName());
            else System.out.println("There is no way from " + start.getLocationName() + " to " + end.getLocationName());
        }

        else {
            System.out.println("The location(s) doesn't exists.");
        }
    }
}
