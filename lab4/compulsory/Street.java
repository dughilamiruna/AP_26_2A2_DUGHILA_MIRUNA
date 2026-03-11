package org.example;

public class Street implements Comparable<Street>{
    private String name;
    private int length;
    private Intersection start;
    private Intersection end;

    public Street(String name, int length, Intersection start, Intersection end) {
        this.name = name;
        this.length = length;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Intersection getStart() {
        return start;
    }

    public void setStart(Intersection start) {
        this.start = start;
    }

    public Intersection getEnd() {
        return end;
    }

    public void setEnd(Intersection end) {
        this.end = end;
    }

    @Override
    public int compareTo(Street other) {
        return Double.compare(this.length, other.length);
    }

    @Override
    public String toString() {
        return "Strada " + name + " (" +
                length + " metrii)";

    }
}
