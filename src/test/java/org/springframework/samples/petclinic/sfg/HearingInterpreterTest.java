package org.springframework.samples.petclinic.sfg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HearingInterpreterTest {

  HearingInterpreter hearingInterpreter;
  @BeforeEach
  void setUp() throws Exception {
    hearingInterpreter = new HearingInterpreter(new LaurelWordProducer());
  }

  @Test
  void whatIheard() {
    String word = hearingInterpreter.whatIheard();

    assertEquals("Laurel", word);
  }
}