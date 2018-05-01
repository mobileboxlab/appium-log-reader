package com.mobilebox.reader.core;

import java.io.IOException;
import java.io.StringWriter;

import org.eclipse.jetty.io.RuntimeIOException;

import spark.ModelAndView;
import spark.TemplateEngine;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

/**
 * Mustache template engine.
 */
public class MustacheEngine extends TemplateEngine {

    private MustacheFactory mustacheFactory;

    /**
     * Constructs a mustache template engine
     */
    public MustacheEngine() {
        mustacheFactory = new DefaultMustacheFactory("public");
    }

    /**
     * Constructs a mustache template engine
     *
     * @param resourceRoot the resource root
     */
    public MustacheEngine(String resourceRoot) {
        mustacheFactory = new DefaultMustacheFactory(resourceRoot);
    }

    /**
     * Constructs a mustache template engine
     *
     * @param mustacheFactory the mustache factory
     */
    public MustacheEngine(MustacheFactory mustacheFactory) {
        this.mustacheFactory = mustacheFactory;
    }

    @Override
    public String render(ModelAndView modelAndView) {
        String viewName = modelAndView.getViewName();
        Mustache mustache = mustacheFactory.compile(viewName);
        StringWriter stringWriter = new StringWriter();
        try {
            mustache.execute(stringWriter, modelAndView.getModel()).close();
        } catch (IOException e) {
            throw new RuntimeIOException(e);
        }
        return stringWriter.toString();
    }
}
