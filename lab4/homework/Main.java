package org.example;

import com.github.javafaker.Faker;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.spanning.WeightedSpanningTreeIterator;
import org.graph4j.Edge;
import java.util.Collection;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main() {

        Faker nameFaker = new Faker();
        City city = new City("IASI");

        var nodes = IntStream.rangeClosed(0, 9)
                .mapToObj(i -> new Intersection(nameFaker.lorem().word().toUpperCase() + " INTERSECTION"))
                .toArray(Intersection[]::new);

        for (Intersection node : nodes) {
            city.addIntersection(node);
        }

        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 10, nodes[0], nodes[1]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 20, nodes[1], nodes[2]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 30, nodes[2], nodes[3]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 40, nodes[3], nodes[4]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 50, nodes[4], nodes[5]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 60, nodes[5], nodes[6]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 70, nodes[6], nodes[7]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 80, nodes[7], nodes[8]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 90, nodes[8], nodes[9]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 15, nodes[0], nodes[9]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 25, nodes[1], nodes[8]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 35, nodes[2], nodes[7]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 45, nodes[3], nodes[6]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 100, nodes[0], nodes[5]));
        city.addStreet(new Street(nameFaker.address().streetName().toUpperCase(), 55, nodes[4], nodes[9]));


        Collections.sort(city.getStreetList());

        int specifiedValue=50;

        Map<Intersection, Long> intersectionDegrees = city.getStreetList().stream()
                .flatMap(street -> java.util.stream.Stream.of(street.getStart(), street.getEnd()))
                .collect(Collectors.groupingBy(intersection -> intersection, Collectors.counting()));

        System.out.println("The streets that are longer than " + specifiedValue + " and join at least 3 other streets are: ");
        city.getStreetList().stream()
                .filter(s -> s.getLength() > specifiedValue)
                .filter(s -> (intersectionDegrees.get(s.getStart()) + intersectionDegrees.get(s.getEnd()) - 2) >= 3)
                .forEach(System.out::println);

        System.out.println("Possible minimum cost solutions, sorted:");

        Intersection[] nodesArray = city.getIntersectionSet().toArray(new Intersection[0]);
        Map<Intersection, Integer> nodeToID = new HashMap<>();
        for (int i = 0; i < nodesArray.length; i++) {
            nodeToID.put(nodesArray[i], i);
        }

        //construire graf cu nr noduri(intersectii)
        Graph graph = GraphBuilder.numVertices(nodesArray.length).buildGraph();

        //adaugare muchii(strazi)+transmitere costuri(lungimile)
        for (Street street : city.getStreetList()) {
            int startID = nodeToID.get(street.getStart());
            int endID = nodeToID.get(street.getEnd());
            graph.addEdge(startID, endID, street.getLength());
        }

        var iterator = new WeightedSpanningTreeIterator(graph);

        int nrSolutions=5;
        int index=0;

        while (iterator.hasNext() && index < nrSolutions) {
            Collection<Edge> treeEdges = iterator.next();

            double totalCost = 0;
            System.out.print("Solution " + (index + 1) + ":");

            for (Edge edge : treeEdges) {
                totalCost += edge.weight();
            }

            System.out.println(" cost " + totalCost);
            index++;
        }
    }
}
