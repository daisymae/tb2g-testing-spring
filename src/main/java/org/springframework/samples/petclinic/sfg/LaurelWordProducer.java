package org.springframework.samples.petclinic.sfg;

import org.springframework.stereotype.Component;

/**
 * because this Bean does not have a profile, it will always get loaded
 */
@Component
public class LaurelWordProducer implements WordProducer {

  @Override
  public String getWord() {
    return "Laurel";
  }
}
