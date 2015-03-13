package com.mrwanny.example.web;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LogbackShutdownListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        System.out.println("Shutting down Logback context '" + loggerContext.getName() + "' for " + contextRootFor(event));
        loggerContext.stop();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Logback context shutdown listener registered for " + contextRootFor(event));
    }

    private String contextRootFor(ServletContextEvent event) {
        return event.getServletContext().getContextPath();
    }
}