package com.music.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.io.Writer;
import java.sql.Wrapper;

public class ThymeleafUtil {
    private static final TemplateEngine engine;
    static  {
        engine = new TemplateEngine();
        ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
        engine.setTemplateResolver(r);
    }

    public static void process(String template, IContext context, Writer writer) {
          engine.process(template,context,writer);
    }

}
