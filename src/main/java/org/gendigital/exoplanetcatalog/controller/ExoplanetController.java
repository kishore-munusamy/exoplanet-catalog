package org.gendigital.exoplanetcatalog.controller;

import org.gendigital.exoplanetcatalog.service.ExoplanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Exoplanet data.
 */
@RestController
@RequestMapping("/api/exoplanets")
public class ExoplanetController {

  private final ExoplanetService exoplanetService;

  @Autowired
  public ExoplanetController(ExoplanetService exoplanetService) {
    this.exoplanetService = exoplanetService;
  }

  /**
   * Gets the number of orphan planets.
   *
   * @return ResponseEntity with the count of orphan planets
   */
  @GetMapping("/orphans")
  public ResponseEntity<Integer> getOrphanPlanetCount() {
    int count = exoplanetService.getOrphanPlanetCount();
    return ResponseEntity.ok(count);
  }

  /**
   * Gets the name of the planet orbiting the hottest star.
   *
   * @return ResponseEntity with the name of the planet
   */
  @GetMapping("/hottest-star")
  public ResponseEntity<String> getPlanetOrbitingHottestStar() {
    String planetName = exoplanetService.getPlanetOrbitingHottestStar();
    return ResponseEntity.ok(planetName);
  }

  /**
   * Gets the timeline of planet counts per year grouped by size.
   *
   * @return ResponseEntity with the timeline of planet counts
   */
  @GetMapping("/timeline")
  public ResponseEntity<String> getPlanetCountsByYear() {
    String planetCountsTimeline = exoplanetService.getPlanetCountsTimeline();
    return ResponseEntity.ok(planetCountsTimeline);
  }
}

