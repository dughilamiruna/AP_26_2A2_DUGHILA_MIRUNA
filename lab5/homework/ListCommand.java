package org.example;

public class ListCommand implements Command {
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        System.out.println("Catalog: " + catalog.getName());
        for (Item item : catalog.getItems()) {
            System.out.println(item.getTitle());
        }
    }
}