package org.gendigital.exoplanetcatalog.controller;

import org.gendigital.exoplanetcatalog.service.ExoplanetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ExoplanetController.class)
public class ExoplanetControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ExoplanetService exoplanetService;

  @Test
  public void testGetOrphanPlanetCount() throws Exception {
    int orphanPlanetCount = 5;
    Mockito.when(exoplanetService.getOrphanPlanetCount()).thenReturn(orphanPlanetCount);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/exoplanets/orphans"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(String.valueOf(orphanPlanetCount)));
  }

  @Test
  public void testGetPlanetOrbitingHottestStar() throws Exception {
    String planetName = "Sample Planet";
    Mockito.when(exoplanetService.getPlanetOrbitingHottestStar()).thenReturn(planetName);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/exoplanets/hottest-star"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(planetName));
  }

  @Test
  public void testGetPlanetCountsByYear() throws Exception {
    String timeline = "Sample timeline";
    Mockito.when(exoplanetService.getPlanetCountsTimeline()).thenReturn(timeline);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/exoplanets/timeline"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(timeline));
  }
}

