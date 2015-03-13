package com.mrwanny.example.web;

import ch.qos.logback.classic.selector.servlet.ContextDetachingSCL;
import com.mrwanny.example.configuration.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
        final AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();

        // Manage the lifecycle of the root application context
        rootContext.register(AppConfig.class);
        container.addListener(new ContextLoaderListener(rootContext));
        container.addListener(new LogbackShutdownListener());

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}