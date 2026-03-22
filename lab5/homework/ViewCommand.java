package org.example;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

public class ViewCommand implements Command {
    private Item item;

    public ViewCommand(Item item) {
        this.item = item;
    }

    @Override
    public void execute() throws InvalidCatalogException {
        Desktop desktop = Desktop.getDesktop();
        String loc = item.getLocation();
        try {
            if (loc.startsWith("http")) {
                desktop.browse(new URI(loc));
            } else {
                desktop.open(new File(loc));
            }
        } catch (Exception e) {
            throw new InvalidCatalogException( e);
        }
    }
}