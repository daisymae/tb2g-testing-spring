package org.springframework.samples.petclinic.sfg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BaseConfig.class, LaurelConfig.class})
public class HearingInterpreterTest {

  @Autowired
  HearingInterpreter hearingInterpreter;

  @Test
  public void whatIheard() {
    String word = hearingInterpreter.whatIheard();
    assertEquals("Laurel", word);
  }
}