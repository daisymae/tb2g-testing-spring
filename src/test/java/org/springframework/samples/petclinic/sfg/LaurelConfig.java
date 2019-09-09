package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * NOTE: This could have been done along with Base in ONE class instead of multiple.
 */
@Profile("base-test")
@Configuration
public class LaurelConfig {
  @Bean
  LaurelWordProducer laurelWordProducer() {
    return new LaurelWordProducer();
  }
}
