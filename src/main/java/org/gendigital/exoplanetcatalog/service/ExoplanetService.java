package org.gendigital.exoplanetcatalog.service;

/**
 * ExoplanetService interface defines methods to retrieve information about exoplanets.
 */
public interface ExoplanetService {

  /**
   * Gets the count of orphan planets.
   * @return the count of orphan planets
   */
  int getOrphanPlanetCount();

  /**
   * Gets the name of the planet orbiting the hottest star.
   * @return the name of the planet orbiting the hottest star, or "No planet found" if no planet is found
   */
  String getPlanetOrbitingHottestStar();

  /**
   * Gets the timeline of planet counts by year.
   * @return the timeline of planet counts by year.
   */
  String getPlanetCountsTimeline();
}
