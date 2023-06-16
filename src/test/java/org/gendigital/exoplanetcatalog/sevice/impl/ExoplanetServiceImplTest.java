package org.gendigital.exoplanetcatalog.sevice.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.gendigital.exoplanetcatalog.client.ExoplanetClient;
import org.gendigital.exoplanetcatalog.dto.Exoplanet;
import org.gendigital.exoplanetcatalog.service.impl.ExoplanetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ExoplanetServiceImplTest {

  @Mock
  private ExoplanetClient client;

  @InjectMocks
  private ExoplanetServiceImpl exoplanetService;

  private List<Exoplanet> exoplanets;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);

    exoplanets = new ArrayList<>();
    exoplanets.add(new Exoplanet("Planet1", 3,1.5, 2010, 1000));
    exoplanets.add(new Exoplanet("Planet2", 1,0.8, 2012, 2000));
    exoplanets.add(new Exoplanet("Planet3", 2,2.3, 2012, 4000));
    exoplanets.add(new Exoplanet("Planet4", 3,0.5, 2015, 3000));

    when(client.downloadExoplanetData()).thenReturn(exoplanets);

    exoplanetService = new ExoplanetServiceImpl(client);

  }

  @Test
  void testGetOrphanPlanetCount() {
    int orphanCount = exoplanetService.getOrphanPlanetCount();
    assertEquals(2, orphanCount);
  }

  @Test
  void testGetPlanetOrbitingHottestStar() {
    String planetName = exoplanetService.getPlanetOrbitingHottestStar();
    assertEquals("Planet3", planetName);
  }

  @Test
  void testGetPlanetCountsTimeline() {
    String expectedTimeline = "In 2010 we discovered 0 small planets, 1 medium planets, 0 large planets.\n" +
        "In 2012 we discovered 1 small planets, 0 medium planets, 1 large planets.\n" +
        "In 2015 we discovered 1 small planets, 0 medium planets, 0 large planets.\n";
    String timeline = exoplanetService.getPlanetCountsTimeline();
    assertEquals(expectedTimeline, timeline);
  }
}
