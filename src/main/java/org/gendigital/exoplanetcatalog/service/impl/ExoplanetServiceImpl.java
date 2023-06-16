package org.gendigital.exoplanetcatalog.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.gendigital.exoplanetcatalog.client.ExoplanetClient;
import org.gendigital.exoplanetcatalog.dto.Exoplanet;
import org.gendigital.exoplanetcatalog.service.ExoplanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to retrieve information about exoplanets.
 */
@Service
public class ExoplanetServiceImpl implements ExoplanetService {

  private final ExoplanetClient client;
  private final List<Exoplanet> exoplanets;

  @Autowired
  public ExoplanetServiceImpl(ExoplanetClient client) {
    this.client = client;
    exoplanets = this.client.downloadExoplanetData();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getOrphanPlanetCount() {
    return (int) exoplanets.stream()
        .filter(exoplanet -> exoplanet.getTypeFlag() == 3)
        .count();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPlanetOrbitingHottestStar() {
    Optional<Exoplanet> hottestStarPlanet = exoplanets.stream()
        .max(Comparator.comparing(Exoplanet::getHostStarTempK));
    return hottestStarPlanet.map(Exoplanet::getPlanetIdentifier).orElse("No planet found");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPlanetCountsTimeline() {
    Map<Integer, Map<String, Integer>> planetCountsByYear = getPlanetCountsByYear();
    StringBuilder timeline = new StringBuilder();

    for (Map.Entry<Integer, Map<String, Integer>> entry : planetCountsByYear.entrySet()) {
      int year = entry.getKey();
      Map<String, Integer> sizeGroupCounts = entry.getValue();

      timeline.append("In ").append(year != 0 ? year : "unknown year").append(" we discovered ");

      int smallCount = sizeGroupCounts.getOrDefault("small", 0);
      int mediumCount = sizeGroupCounts.getOrDefault("medium", 0);
      int largeCount = sizeGroupCounts.getOrDefault("large", 0);

      timeline.append(smallCount).append(" small planets, ")
          .append(mediumCount).append(" medium planets, ")
          .append(largeCount).append(" large planets.")
          .append(System.lineSeparator());
    }

    return timeline.toString();
  }

  /**
   * Retrieves the planet counts by year.
   *
   * @return a map containing planet counts grouped by year
   */
  private Map<Integer, Map<String, Integer>> getPlanetCountsByYear() {
    Map<Integer, Map<String, Integer>> planetCountsByYear = new TreeMap<>();

    for (Exoplanet exoplanet : exoplanets) {
      int discoveryYear = exoplanet.getDiscoveryYear();
      String sizeGroup = getSizeGroup(exoplanet.getRadiusJpt());

      planetCountsByYear.putIfAbsent(discoveryYear, new TreeMap<>());
      Map<String, Integer> sizeGroupCounts = planetCountsByYear.get(discoveryYear);
      sizeGroupCounts.put(sizeGroup, sizeGroupCounts.getOrDefault(sizeGroup, 0) + 1);
    }

    return planetCountsByYear;
  }

  /**
   * Determines the size group of an exoplanet based on its radius.
   *
   * @param radiusJpt the radius of the exoplanet in Jovian Planetary Radii
   * @return the size group of the exoplanet (small, medium, large)
   */
  private String getSizeGroup(Double radiusJpt) {
    if (radiusJpt == null) {
      return "";
    } else if (radiusJpt < 1) {
      return "small";
    } else if (radiusJpt < 2) {
      return "medium";
    } else {
      return "large";
    }
  }
}
