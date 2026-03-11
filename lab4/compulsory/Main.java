package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main() {

        var nodes = IntStream.rangeClosed(0, 9)
                .mapToObj(i -> new Intersection("INTERSECTIA " + i) )
                .toArray(Intersection[]::new);

        List<Street> streetList = new ArrayList<>();

        Street street1 = new Street("AMURGULUI", 100, nodes[0], nodes[1]);
        Street street2 = new Street("DORULUI", 50, nodes[1], nodes[2]);
        Street street3 = new Street("STEFAN CEL MARE", 200, nodes[0], nodes[2]);
        Street street4 = new Street("DREPTATII", 50, nodes[1], nodes[3]);

        streetList.add(street1);
        streetList.add(street2);
        streetList.add(street3);
        streetList.add(street4);

        Collections.sort(streetList, Comparator.comparing(Street::getLength));
        System.out.println("Lista de strazi sortata: " + streetList);

        Set<Intersection> intersectionSet = new HashSet<>();
        intersectionSet.addAll(Arrays.asList(nodes));

        Intersection duplicateNode = new Intersection("INTERSECTIA 0");
        boolean isAdded = intersectionSet.add(duplicateNode);

        if(isAdded)
        {
            System.out.println("Intersectia '" + duplicateNode + "' a fost adaugata.");
        }
        else
        {
            System.out.println("Intersectia '" + duplicateNode + "' nu a fost adaugata.");
        }

        System.out.println("Setul de intersectii: " + intersectionSet);
    }
}
