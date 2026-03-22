package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private Catalog catalog;

    public ReportCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() throws InvalidCatalogException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setClassForTemplateLoading(ReportCommand.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        try {
            Template template = cfg.getTemplate("report.ftl");

            Map<String, Object> templateData = new HashMap<>();
            templateData.put("catalogName", catalog.getName());
            templateData.put("items", catalog.getItems());

            File outputFile = new File("catalog_report.html");
            FileWriter out = new FileWriter(outputFile);

            template.process(templateData, out);
            out.close();

            Desktop.getDesktop().browse(outputFile.toURI());

        } catch (IOException | TemplateException e) {
            throw new InvalidCatalogException( e);
        }
    }
}
