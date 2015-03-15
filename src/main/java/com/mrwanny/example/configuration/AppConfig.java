package com.mrwanny.example.configuration;

import java.util.List;

//import static org.ajar.swaggermvcui.SwaggerSpringMvcUi.WEB_JAR_RESOURCE_PATTERNS;
//import static org.ajar.swaggermvcui.SwaggerSpringMvcUi.WEB_JAR_RESOURCE_LOCATION;
//import static org.ajar.swaggermvcui.SwaggerSpringMvcUi.WEB_JAR_VIEW_RESOLVER_PREFIX;
//import static org.ajar.swaggermvcui.SwaggerSpringMvcUi.WEB_JAR_VIEW_RESOLVER_SUFFIX;

import springdox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.codahale.metrics.MetricRegistry;

@EnableWebMvc
@ComponentScan(basePackages = {"com.mrwanny.example", "com.ak.swaggerspringmvc.shared.controller", "com.ak.spring3.music"})
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    public static final String[] WEB_JAR_RESOURCE_PATTERNS = {"css/", "images/", "lib/", "swagger-ui.js"};
    public static final String WEB_JAR_RESOURCE_LOCATION = "classpath:META-INF/resources/";
    public static final String WEB_JAR_VIEW_RESOLVER_PREFIX = "classpath:/resources/";
    public static final String WEB_JAR_VIEW_RESOLVER_SUFFIX = ".jsp";

    @java.lang.SuppressWarnings("all")
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AppConfig.class);


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/test/**").addResourceLocations("/test/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
        registry.addResourceHandler(WEB_JAR_RESOURCE_PATTERNS).addResourceLocations(WEB_JAR_RESOURCE_LOCATION).setCachePeriod(0);

    }

    @Bean
    public ViewResolver configureViewResolver() {
        InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
        viewResolve.setPrefix("/WEB-INF/views/");
        viewResolve.setSuffix(".jsp");
        return viewResolve;
    }


    @Bean
    public MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }


    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setPrefix(WEB_JAR_VIEW_RESOLVER_PREFIX);
      resolver.setSuffix(WEB_JAR_VIEW_RESOLVER_SUFFIX);
      return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
    }
}