package com.myCompanyName.myProjectName.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.config.HateoasConfiguration;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.LinkRelationProvider.LookupContext;
import org.springframework.hateoas.server.core.DelegatingLinkRelationProvider;
import org.springframework.plugin.core.support.PluginRegistryFactoryBean;

@Configuration
public class HateoasConfig extends HateoasConfiguration {

  @Bean
  @Primary
  PluginRegistryFactoryBean<LinkRelationProvider, LookupContext> relProviderPluginRegistry() {
    PluginRegistryFactoryBean<LinkRelationProvider, LookupContext> factory = new PluginRegistryFactoryBean();
    factory.setType(LinkRelationProvider.class);
    factory.setExclusions(new Class[]{DelegatingLinkRelationProvider.class});
    return factory;
  }

}
