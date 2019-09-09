package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * With the setting of a profile, need to mark as Primary because there will be two
 * instances (Laurel and Yanny) loaded, and Spring needs to know which one to use with
 * the yanny profile.
 */
@Profile("yanny")
@Primary
@Component
public class YannyWordProducer implements WordProducer {

  @Override
  public String getWord() {
    return "Yanny";
  }
}
