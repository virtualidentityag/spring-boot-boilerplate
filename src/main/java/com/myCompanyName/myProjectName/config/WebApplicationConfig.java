package com.myCompanyName.myProjectName.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
public class WebApplicationConfig implements WebMvcConfigurer {

  private static final Logger logger = LogManager.getLogger(WebApplicationConfig.class);

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (logger.isInfoEnabled()) {
      logger.info("add swagger resource handlers ");
    }
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

}
