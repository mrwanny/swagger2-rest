package com.mrwanny.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import springdox.documentation.service.ResourceGroup;
import springdox.documentation.spi.service.ResourceGroupingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springdox.documentation.swagger2.annotations.EnableSwagger2;
import springdox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;
import springdox.documentation.spring.web.plugins.DocumentationConfigurer;

import springdox.documentation.spring.web.plugins.DocumentationConfigurer;
import springdox.documentation.spi.DocumentationType;
import springdox.documentation.service.ApiInfo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.Set;


@Configuration
@EnableWebMvc
@EnableSwagger2 //Loads the spring beans required by the framework
public class MySwaggerConfig {

   //private SpringSwaggerConfig springSwaggerConfig;

    private DocumentationConfigurer documentationConfigurer;


   @Autowired
   public void setDocumentationConfigurer(DocumentationConfigurer configurer){
      this.documentationConfigurer = documentationConfigurer;
   }

   /**
    * Required to autowire SpringSwaggerConfig
    */
//   @Autowired
//   public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
//      this.springSwaggerConfig = springSwaggerConfig;
//   }

   /**
    * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc framework - allowing for multiple
    * swagger groups i.e. same code base multiple swagger resource listings.
    */
//   @Bean
//   public SwaggerSpringMvcPlugin customImplementation(){
//      final ApiInfo info = new ApiInfo(
//              "My Apps API Title",
//              "My Apps API Description",
//              "My Apps API terms of service",
//              "My Apps API Contact Email",
//              "My Apps API Licence Type",
//              "My Apps API License URL"
//      );
//
//      //info.setVersion("1.0.0");
//      //info.setTitle("Calculator Service api");
//      //info.description("Calculator Service api description");
//
//      return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
//              .includePatterns(".*calculator.*").apiInfo(info).build();
//   }

//   @Autowired
//   public void setSpringConfig(DocumentationConfigurer documentConfigurer) {
//       this.documentationConfigurer = documentConfigurer;
//   }
//
//   @Bean
//   public ResourceGroupingStrategy groupingStrategy(){
//       return new ResourceGroupingStrategy() {
//           @Override
//           public Set<ResourceGroup> getResourceGroups(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
//               return null;
//           }
//
//           @Override
//           public String getResourceDescription(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
//               return "Description";
//           }
//
//           @Override
//           public Integer getResourcePosition(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
//               return 1;
//           }
//
//           @Override
//           public boolean supports(DocumentationType documentationType) {
//               return documentationType == DocumentationType.SWAGGER_2;
//           }
//       };
//   }
   @Bean
   ApiInfo apiInfo() {
      ApiInfo apiInfo = new ApiInfo(
              "My Apps API Title",
              "My Apps API Description",
              "1.0.0",
              "My Apps API terms of service",
              "My Apps API Contact Email",
              "My Apps API Licence Type",
              "My Apps API License URL"
      );
      return apiInfo;
   }
   @Bean
   public DocumentationConfigurer customImplementation(){
      return new DocumentationConfigurer(DocumentationType.SWAGGER_2)
              .groupName("default")
              .includePatterns(".*calculator.*")
              .apiInfo(apiInfo());
   }

   @Bean
   ObjectMapper objectMapper() { return new ObjectMapper(); }

}