package com.mrwanny.example.web;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Properties;

public abstract class ServletDelegatingController<T extends Servlet> extends AbstractController implements InitializingBean, DisposableBean {
    private final T servletInstance;
    private final String servletName;
    private final Properties initParameters;

    public ServletDelegatingController(T servletInstance) {
        this(servletInstance, null, new Properties());
    }

    public ServletDelegatingController(T servletInstance, String servletName) {
        this(servletInstance, servletName, new Properties());
    }

    public ServletDelegatingController(T servletInstance, Properties initParameters) {
        this(servletInstance, null, initParameters);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        getServletInstance().init(new DelegatingServletConfig());
    }

    @Override
    @RequestMapping("**")
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        getServletInstance().service(request, response);
        return null;
    }

    /**
     * Destroy the wrapped Servlet instance.
     * @see javax.servlet.Servlet#destroy()
     */
    @Override
    public void destroy() {
        getServletInstance().destroy();
    }

    /**
     * Internal implementation of the ServletConfig interface, to be passed
     * to the wrapped servlet. Delegates to ServletWrappingController fields
     * and methods to provide init parameters and other environment info.
     */
    private class DelegatingServletConfig implements ServletConfig {


        @Override
        public String getServletName() {
            return ServletDelegatingController.this.getServletName();
        }

        @Override
        public ServletContext getServletContext() {
            return ServletDelegatingController.this.getServletContext();
        }

        @Override
        public String getInitParameter(String paramName) {
            return ServletDelegatingController.this.getInitParameters().getProperty(paramName);
        }

        @Override
        @SuppressWarnings({"unchecked", "rawtypes"})
        public Enumeration<String> getInitParameterNames() {
            return (Enumeration)ServletDelegatingController.this.getInitParameters().keys();
        }
    }

    @java.lang.SuppressWarnings("all")
    public T getServletInstance() {
        return this.servletInstance;
    }

    @java.lang.SuppressWarnings("all")
    public String getServletName() {
        return this.servletName;
    }

    @java.lang.SuppressWarnings("all")
    public Properties getInitParameters() {
        return this.initParameters;
    }

    @java.beans.ConstructorProperties({"servletInstance", "servletName", "initParameters"})
    @java.lang.SuppressWarnings("all")
    public ServletDelegatingController(final T servletInstance, final String servletName, final Properties initParameters) {
        this.servletInstance = servletInstance;
        this.servletName = servletName;
        this.initParameters = initParameters;
    }
}