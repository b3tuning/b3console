package com.b3tuning.b3console.testing.feature.highlight;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CssHighLightConfig {

  @Bean
  public CssHighLight impl() {
    return new RegexHighLight();
  }
}
