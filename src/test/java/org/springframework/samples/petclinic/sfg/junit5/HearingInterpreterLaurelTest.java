package org.springframework.samples.petclinic.sfg.junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.sfg.BaseConfig;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.samples.petclinic.sfg.LaurelConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * SpringJUnitConfig includes @ExtendsWith and @ContextConfiguration as well as others
 * This is using the same Config classes that were created in the JUnit4 tests
 */
@SpringJUnitConfig(classes = {BaseConfig.class, LaurelConfig.class})
class HearingInterpreterLaurelTest {

  @Autowired
  HearingInterpreter hearingInterpreter;

  @Test
  void whatIheard() {
    String word = hearingInterpreter.whatIheard();
    assertEquals("Laurel", word);
  }
}