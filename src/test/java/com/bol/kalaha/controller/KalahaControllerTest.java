package com.bol.kalaha.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bol.kalaha.service.GameService;

@SpringBootTest
public class KalahaControllerTest {

	 protected MockMvc mvc;

	  @Mock
	  private GameService gameService;

	  @InjectMocks
	  private KalahaController controller;

	  @BeforeEach
	  public void setUp() {

	    this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
	  }
	  
	  @Test
	  public void testCreateGame() throws Exception {

	    mvc.perform(MockMvcRequestBuilders.post("/kalaha-games")).andExpect(status().isCreated());
	  }
	  
	  @Test
	  public void testPlayTheGame() throws Exception {

	    mvc.perform(MockMvcRequestBuilders.put("/kalaha-games/{gameId}/pits/{pitIndex}", 1L, 1))
	        .andExpect(status().isOk());

	  }


}
