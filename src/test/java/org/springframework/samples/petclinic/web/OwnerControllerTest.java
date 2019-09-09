package org.springframework.samples.petclinic.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

/**
 * mvc-test brings in mockMVC configuration
 * mvc-core sets up a number of things for MVC configuration
 * this is old-school spring; springboot will take care of much of this for us
 * This is helpful to know if dealing with an older spring project that is
 * not springboot
 */
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml",
"classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

  @Autowired
  OwnerController ownerController;

  @Autowired
  ClinicService clinicService;

  @Test
  void tempTest() {
    // ownerController
  }
}