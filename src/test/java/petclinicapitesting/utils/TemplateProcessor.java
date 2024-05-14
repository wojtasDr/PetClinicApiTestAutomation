package petclinicapitesting.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;


@Component
public class TemplateProcessor {

    private Configuration dataFreemarkerConfig;
    private static final String TEMPLATES_LOCATION = "/apirequeststemplates";

    /**
     * This method makes setup of FreeMarked template processor
     *
     * @return - FreeMarked configuration
     */
    private Configuration setupFreeMarker() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        cfg.setClassForTemplateLoading(TemplateProcessor.class, TemplateProcessor.TEMPLATES_LOCATION);

        cfg.setIncompatibleImprovements(new Version(2, 3, 32));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);

        return cfg;
    }

    /**
     * This method combine FreeMarker template and given params, and returns parameterized string
     *
     * @param templateName - template name
     * @param params       - template params
     * @param config       - FreeMarked configuration
     * @return parameterized String
     */
    private String process(String templateName, Map<String, String> params, Configuration config) {
        String processedTemplate = "";
        try {
            Template template = config.getTemplate(templateName);
            Writer out = new StringWriter();
            template.process(params, out);
            processedTemplate = out.toString();
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }

        assertThat("Template " + templateName + " was not processed correctly. Output is empty. "
                + "Please check logs.", !processedTemplate.isEmpty());

        return processedTemplate;
    }

    /**
     * This method setup FreeMarker template processor
     *
     * @return FreeMarker configuration
     */
    private Configuration setupDataConfig() {
        if (dataFreemarkerConfig == null) {
            dataFreemarkerConfig = setupFreeMarker();
        }
        dataFreemarkerConfig.setClassForTemplateLoading(TemplateProcessor.class, TEMPLATES_LOCATION);
        return dataFreemarkerConfig;
    }

    /**
     * This method setup FreeMarker template processor, process template and returns template fulfilled with parameters.
     *
     * @param templateName - FreeMarker template name
     * @param parameters - FreeMarker template parameters
     * @return parameterized template string
     */
    public String processRequestTemplate(String templateName, Map<String, String> parameters) {
        Configuration cfg = setupDataConfig();
        return process(templateName, parameters, cfg);
    }

}
