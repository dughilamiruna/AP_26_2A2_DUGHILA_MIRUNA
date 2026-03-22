package org.example;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog("MyRefs");

        var book = new Item("123", "Brosura de Simboluri", "d:/brosura.pdf");
        book.addTag("year", "2026");
        book.addTag("author", "Miruna");

        var article = new Item("234", "The History of Design", "https://weareshifta.com/en/the-history-of-design/");
        article.addTag("year", "2024");
        article.addTag("author", "SHIFTA");

        try {
            new AddCommand(catalog, book).execute();
            new AddCommand(catalog, article).execute();
            System.out.println();

            new ListCommand(catalog).execute();
            System.out.println();

            System.out.println("Opening Catalog Report...");
            //new ViewCommand(article).execute();

            new ReportCommand(catalog).execute();

        } catch (InvalidCatalogException e) {
            System.err.println("Error " + e.getMessage());
            e.printStackTrace();
        }
    }
}


