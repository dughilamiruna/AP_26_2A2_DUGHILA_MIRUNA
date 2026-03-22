package org.example;

public class AddCommand implements Command {
    private Catalog catalog;
    private Item item;

    public AddCommand(Catalog catalog, Item item) {
        this.catalog = catalog;
        this.item = item;
    }

    @Override
    public void execute() {
        catalog.add(item);
        System.out.println("Item added: " + item.getTitle());
    }
}