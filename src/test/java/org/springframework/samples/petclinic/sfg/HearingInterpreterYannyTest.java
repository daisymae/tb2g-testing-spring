package org.springframework.samples.petclinic.sfg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ContextConfiguration tells spring context to look at BaseConfig class
 * and YannyConfig class -- only classes will look at.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BaseConfig.class, YannyConfig.class})
public class HearingInterpreterYannyTest {
  @Autowired
  HearingInterpreter hearingInterpreter;

  @Test
  public void whatIHeard() {
    String word = hearingInterpreter.whatIheard();
    assertEquals("Yanny", word);
  }

}
