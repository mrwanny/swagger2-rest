package com.mrwanny.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

@Configuration
public class EnvironmentConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        final PropertySourcesPlaceholderConfigurer holder = new PropertySourcesPlaceholderConfigurer();

        final MutablePropertySources sources = new StandardEnvironment().getPropertySources();
        holder.setPropertySources(sources);
        return holder;
    }
}
