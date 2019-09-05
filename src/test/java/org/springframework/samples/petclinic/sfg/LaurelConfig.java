package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * NOTE: This could have been done along with Base in ONE class instead of multiple.
 */
@Configuration
public class LaurelConfig {
  @Bean
  LaurelWordProducer laurelWordProducer() {
    return new LaurelWordProducer();
  }
}
