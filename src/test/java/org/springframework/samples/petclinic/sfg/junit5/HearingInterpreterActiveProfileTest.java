package org.springframework.samples.petclinic.sfg.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ActiveProfiles("yanny")
@SpringJUnitConfig(classes = HearingInterpreterActiveProfileTest.TestConfig.class)
public class HearingInterpreterActiveProfileTest {

  /*
  ComponentScan will also bring in everything in the test package as well.
  We have a lot of configurations and they're conflicting, so need to change
  HearingInterpreterComponentScanTest to also have an ActiveProfile.
   */
  @Configuration
  @ComponentScan("org.springframework.samples.petclinic.sfg")
  static class TestConfig {

  }
  @Autowired
  HearingInterpreter hearingInterpreter;

  @Test
  void whatIheard() {
    String word = hearingInterpreter.whatIheard();
    assertEquals("Yanny", word);
  }

}
