package org.springframework.samples.petclinic.web;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * mvc-test brings in mockMVC configuration mvc-core sets up a number of things for MVC
 * configuration this is old-school spring; springboot will take care of much of this for us This is
 * helpful to know if dealing with an older spring project that is not springboot
 */
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml",
    "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

  @Autowired
  OwnerController ownerController;

  @Autowired
  ClinicService clinicService;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
  }

  @Test
  void tempTest() {
    // ownerController
  }

  /**
   * not found as didn't set up mockito to return any values, so hits the not found code
   *
   * @throws Exception
   */
  @Test
  void testFindByNameNotFound() throws Exception {
    mockMvc.perform(get("/owners")
        .param("lastName", "Dont find ME!"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/findOwners"));
  }

  @Test
  void testControllerInitCreationForm() throws Exception {
    mockMvc.perform(get("/owners/new"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("owner"))
        .andExpect(view().name(OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
  }
}