package org.springframework.samples.petclinic.web;


import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * mvc-test brings in mockMVC configuration mvc-core sets up a number of things for MVC
 * configuration this is old-school spring; springboot will take care of much of this for us This is
 * helpful to know if dealing with an older spring project that is not springboot
 *
 * Need MockitoExtension to enable JUnit to run mockito extension and see the captor
 */
@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml",
    "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {

  @Autowired
  OwnerController ownerController;

  @Autowired
  ClinicService clinicService;

  MockMvc mockMvc;

  /**
   * String captor for assertions
   */
  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
  }

  @AfterEach
  void tearDown() {
    reset(clinicService);
  }

  @Test
  void testNewOwnerPostValid() throws Exception {
    mockMvc.perform(post("/owners/new")
          .param("firstName", "Jimmy")
          .param("lastName", "Buffett")
          .param("Address", "123 Duval Street")
          .param("city", "Key West")
          .param("telephone", "123123123"))
        .andExpect(status().is3xxRedirection());
  }


  @Test
  void testNewOwnerPostInValid() throws Exception {
    mockMvc.perform(post("/owners/new")
        .param("firstName", "Jimmy")
        .param("lastName", "Buffett")
        .param("city", "Key West"))
        .andExpect(status().isOk())
    .andExpect(model().attributeHasErrors("owner"))
    .andExpect(model().attributeHasFieldErrors("owner", "address"))
    .andExpect(model().attributeHasFieldErrors("owner", "telephone"));
  }


  @Test
  void testUpdateOwnerPostValid() throws Exception {
    mockMvc.perform(post("/owners/{ownerId}/edit", 1)
        .param("firstName", "Jimmy")
        .param("lastName", "Buffett")
        .param("address", "123 Duval St ")
        .param("city", "Key West")
        .param("telephone", "3151231234"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/{ownerId}"));
  }

  @Test
  void testUpdateOwnerPostNotValid() throws Exception {
    mockMvc.perform(post("/owners/{ownerId}/edit", 1)
        .param("firstName", "Jimmy")
        .param("lastName", "Buffett")
        .param("address", "123 Duval St "))
        .andExpect(status().isOk())
        .andExpect(view().name(OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
  }

  @Test
  void tempTest() {
    // ownerController
  }

  /**
   * not found as didn't set up mockito to return any values, so hits the not found code
   */
  @Test
  void testFindByNameNotFound() throws Exception {
    mockMvc.perform(get("/owners")
        .param("lastName", "Dont find ME!"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/findOwners"));
  }

  @Test
  void testReturnListOfOwners() throws Exception {
    given(clinicService.findOwnerByLastName(""))
        .willReturn(Lists.newArrayList(new Owner(), new Owner()));
    mockMvc.perform(get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownersList"));

    then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());

    assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("");
  }

  @Test
  void testFindOwnerOneResult() throws Exception {
    Owner justOne = new Owner();
    justOne.setId(1);
    final String findJustOne = "FindJustOne";

    justOne.setLastName(findJustOne);

    given(clinicService.findOwnerByLastName(findJustOne)).willReturn(Lists.newArrayList(justOne));

    mockMvc.perform(get("/owners")
        .param("lastName", findJustOne))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"));

    then(clinicService).should().findOwnerByLastName(anyString());

  }

  @Test
  void testControllerInitCreationForm() throws Exception {
    mockMvc.perform(get("/owners/new"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("owner"))
        .andExpect(view().name(OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
  }
}