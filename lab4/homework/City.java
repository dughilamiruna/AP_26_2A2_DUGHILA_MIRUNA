package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class City {
    private String name;
    private Set<Intersection> intersectionSet;
    private List<Street> streetList;

    public City(String name) {
        this.name = name;
        this.intersectionSet = new HashSet<>();
        this.streetList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Intersection> getIntersectionSet() {
        return intersectionSet;
    }

    public void addIntersection(Intersection intersection) {
        intersectionSet.add(intersection);
    }

    public List<Street> getStreetList() {
        return streetList;
    }

    public void addStreet(Street street) {
        streetList.add(street);
    }
}
