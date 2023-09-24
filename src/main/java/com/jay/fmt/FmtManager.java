package com.jay.fmt;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FmtManager {

    private Configuration freemarkerConfig;
    private static final String TEMPLATE_DIRECTORY = "src/main/resources/templates/";

    public FmtManager() {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_23);
        freemarkerConfig.setTagSyntax(Configuration.ANGLE_BRACKET_TAG_SYNTAX);
        freemarkerConfig.setDefaultEncoding("UTF-8");
        freemarkerConfig.setNumberFormat("computer");
        freemarkerConfig.setObjectWrapper(new BeansWrapperBuilder(Configuration.VERSION_2_3_23).build());
        freemarkerConfig.setTemplateLoader(new StringTemplateLoader());
    }

    /**
     * Loads a template from a file and returns it.
     *
     * @param  templateName  the name of the template
     * @param  templatePath  the path to the template file
     * @return               the loaded template
     */
    private Template loadTemplate(String templateName, String templatePath) {
        try {
            String templateContent = new String(Files.readAllBytes(Paths.get(templatePath)));
            ((StringTemplateLoader) freemarkerConfig.getTemplateLoader()).putTemplate(templateName, templateContent);
            return freemarkerConfig.getTemplate(templateName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Processes a template by loading it, applying data to it, and returning the result as a string.
     *
     * @param  templateName The name of the template to be processed.
     * @param  data         A map containing the data to be applied to the template.
     * @return              The result of processing the template as a string.
     */
//    public String processTemplate(String templateName, Map<String, Object> data) {
//        Template template = loadTemplate(templateName, TEMPLATE_DIRECTORY + templateName + ".ftl");
//        try (StringWriter writer = new StringWriter()) {
//            template.process(data, writer);
//            return writer.toString();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public String processTemplate(String templateName, Map<String, Object> data) {
        Template template = loadTemplate(templateName, getTemplatePath(templateName));
        return processTemplateWithData(template, data);
    }


    private String getTemplatePath(String templateName) {
        return TEMPLATE_DIRECTORY + templateName + ".ftl";
    }

    private String processTemplateWithData(Template template, Map<String, Object> data) {
        try (StringWriter writer = new StringWriter()) {
            template.process(data, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
