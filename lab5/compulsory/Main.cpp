package org.example;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog("MyRefs");

        var book = new Item("123", "Brosura de Simboluri", "d:/brosura.pdf");
        book.addTag("year", "2026");
        book.addTag("author", "Miruna");

        var article = new Item("234", "The Java Virtual Machine Specification", "https://weareshifta.com/en/the-history-of-design/");
        article.addTag("year", "2024");
        article.addTag("author", "SHIFTA");

        catalog.add(book);
        catalog.add(article);

        System.out.println("The catalog contains " + catalog.getItems().size() + " elements.");

        System.out.println("Opening the web resource...");
        CatalogUtil.view(article);
        System.out.println("Opening the pdf resource...");
        CatalogUtil.view(book);
    }
}


