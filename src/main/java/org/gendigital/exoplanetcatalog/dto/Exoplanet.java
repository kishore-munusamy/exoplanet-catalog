package org.gendigital.exoplanetcatalog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Exoplanet {

  @NonNull
  @JsonProperty("PlanetIdentifier")
  private String planetIdentifier;

  @NonNull
  @JsonProperty("TypeFlag")
  private int typeFlag;

  @JsonProperty("PlanetaryMassJpt")
  private double planetaryMassJpt;

  @NonNull
  @JsonProperty("RadiusJpt")
  private double radiusJpt;

  @JsonProperty("PeriodDays")
  private double periodDays;

  @JsonProperty("SemiMajorAxisAU")
  private double semiMajorAxisAU;

  @JsonProperty("Eccentricity")
  private String eccentricity;

  @JsonProperty("PeriastronDeg")
  private String periastronDeg;

  @JsonProperty("LongitudeDeg")
  private String longitudeDeg;

  @JsonProperty("AscendingNodeDeg")
  private String ascendingNodeDeg;

  @JsonProperty("InclinationDeg")
  private int inclinationDeg;

  @JsonProperty("SurfaceTempK")
  private String surfaceTempK;

  @JsonProperty("AgeGyr")
  private String ageGyr;

  @JsonProperty("DiscoveryMethod")
  private String discoveryMethod;

  @NonNull
  @JsonProperty("DiscoveryYear")
  private int discoveryYear;

  @JsonProperty("LastUpdated")
  private String lastUpdated;

  @JsonProperty("RightAscension")
  private String rightAscension;

  @JsonProperty("Declination")
  private String declination;

  @JsonProperty("DistFromSunParsec")
  private String distFromSunParsec;

  @JsonProperty("HostStarMassSlrMass")
  private double hostStarMassSlrMass;

  @JsonProperty("HostStarRadiusSlrRad")
  private double hostStarRadiusSlrRad;

  @JsonProperty("HostStarMetallicity")
  private double hostStarMetallicity;

  @NonNull
  @JsonProperty("HostStarTempK")
  private int hostStarTempK;

  @JsonProperty("HostStarAgeGyr")
  private String hostStarAgeGyr;
}
