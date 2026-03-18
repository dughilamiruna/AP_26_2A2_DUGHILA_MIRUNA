package org.example;

import java.awt.*;
import java.io.File;
import java.net.URI;

public class CatalogUtil {
    public static void view(Item item) {
        Desktop desktop = Desktop.getDesktop();
        String loc = item.getLocation();
        try {
            if (loc.startsWith("http")) {
                desktop.browse(new URI(loc));
            } else {
                desktop.open(new File(loc));
            }
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }
}
