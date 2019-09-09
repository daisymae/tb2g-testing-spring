package org.springframework.samples.petclinic.sfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * When specify a profile, need to make @Primary so this bean will get injected
 * when the externalized profile is active.
 * I found, for this use, having the second profile of laurel-properties wasn't
 * necessary as the test makes use of the laurel.properties file. Maybe if there
 * was one properties file or another way to specify the values using the profile
 * this would be necessary.
 */
@Component
@Profile({"externalized", "laurel-properties"})
@Primary
public class PropertiesWordProducer implements WordProducer {

  private String word;

  @Value("${say.word}")
  public void setWord(String word) {
    this.word = word;
  }

  @Override
  public String getWord() {
    return word;
  }

}
